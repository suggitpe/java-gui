/*
 * CachedXmlConnectionStore.java created on 29 Sep 2008 07:05:37 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore.xmldao.impl;

import org.suggs.apps.mercury.connectionstore.ConnectionStoreType;
import org.suggs.apps.mercury.connectionstore.ConnectionType;
import org.suggs.apps.mercury.connectionstore.ObjectFactory;
import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connectionstore.ConnectionStoreException;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager;
import org.suggs.apps.mercury.model.util.file.IFileManager;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * This class is the main JAXB implementation of the xml connection manager.
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2008
 */
public class JaxbXmlConnectionStoreManager implements IXmlConnectionStoreManager {

    private static final Logger LOG = LoggerFactory.getLogger( JaxbXmlConnectionStoreManager.class );

    private IFileManager fileManager;
    private static JAXBContext jaxbContext;
    private Schema schemaCache;
    private String persistentFile;
    private String connectionDataCache;

    static {
        try {
            jaxbContext = JAXBContext.newInstance( ConnectionStoreType.class.getPackage().getName() );
        }
        catch ( JAXBException je ) {
            throw new IllegalStateException( "Unable to create JAXB context in ConnectionContext Store Manager",
                                             je );
        }
    }

    /**
     * Constructs a new instance.
     */
    public JaxbXmlConnectionStoreManager( IFileManager aFileManager, String aPersistentFilename ) {
        fileManager = aFileManager;
        persistentFile = aPersistentFilename;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager#getRawXml()
     */
    @Override
    public String getRawXml() throws ConnectionStoreException {
        if ( connectionDataCache == null || connectionDataCache.length() == 0 ) {
            try {
                connectionDataCache = fileManager.retrieveClobFromFile( new File( persistentFile ) );

            }
            catch ( IOException ioe ) {
                String err = "IOException caught when trying to retrieve data from the ";
                LOG.error( err, ioe );
                throw new ConnectionStoreException( err, ioe );
            }
        }
        return connectionDataCache;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager#readConnectionData()
     */
    @Override
    public Map<String, ConnectionDetails> readConnectionData() throws ConnectionStoreException {
        String xmlClob = getRawXml();

        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "Retrieved clob with length [" + xmlClob.length() + "] from the file manager" );
        }

        ConnectionStoreType connStr = null;
        try {
            Unmarshaller uMrsh = jaxbContext.createUnmarshaller();

            if ( schemaCache == null ) {
                try {
                    SchemaFactory sf = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
                    URL url = getClass().getClassLoader().getResource( "connection-store.xsd" );
                    schemaCache = sf.newSchema( url );
                }
                catch ( SAXException sx ) {
                    throw new ConnectionStoreException( "Unable to retrieve schema for validation of connction store xml",
                                                        sx );
                }
            }

            if ( schemaCache == null ) {
                throw new ConnectionStoreException( "Failed to create validating schema for marshaller" );
            }

            uMrsh.setSchema( schemaCache );

            @SuppressWarnings("unchecked")
            JAXBElement<ConnectionStoreType> elem = (JAXBElement<ConnectionStoreType>) uMrsh.unmarshal( new StringReader( xmlClob ) );
            connStr = elem.getValue();
        }
        catch ( JAXBException je ) {
            String err = "Error found when trying to unmarshal connection store data";
            LOG.error( err + ":\n" + xmlClob );
            throw new ConnectionStoreException( err, je );
        }

        if ( connStr.getConnection().size() == 0 ) {
            LOG.warn( "Have parsed connection store XML but cannot find any valid connections within: returning an empty connection store (either this is first time use or the connection store xml is corrupt in some way)" );
        }

        return JaxbXmlConnectionStoreManagerHelper.createDetailsFromConnection( connStr );
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager#saveConnectionData(java.util.Map)
     */
    @Override
    public void saveConnectionData( Map<String, ConnectionDetails> map ) throws ConnectionStoreException {
        if ( map == null ) {
            throw new ConnectionStoreException( "Trying to save down a null connection data map" );
        }

        String xmlClob = null;
        try {
            Marshaller mshlr = jaxbContext.createMarshaller();
            ObjectFactory fact = new ObjectFactory();

            // create the main connection store
            ConnectionStoreType ct = fact.createConnectionStoreType();
            for ( Map.Entry<String, ConnectionDetails> entry : map.entrySet() ) {

                ConnectionType conn = JaxbXmlConnectionStoreManagerHelper.createConnectionFromDetails( fact,
                                                                                                       entry.getValue() );
                ct.getConnection().add( conn );
            }

            JAXBElement<ConnectionStoreType> elem = fact.createConnectionStore( ct );
            StringWriter writer = new StringWriter();
            LOG.debug( "Marshalling data into XML document" );
            mshlr.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
            mshlr.marshal( elem, writer );
            xmlClob = writer.toString();
        }
        catch ( JAXBException je ) {
            throw new ConnectionStoreException( "Failed to marshall (JAXB) xml document", je );
        }

        // update internal cache with copy
        connectionDataCache = xmlClob;

        // now we need to persist the xml clob
        try {
            LOG.debug( "Persisting XML of length [" + xmlClob.length() + "]" );
            fileManager.persistClobToFile( xmlClob, new File( persistentFile ) );
        }
        catch ( IOException ioe ) {
            throw new ConnectionStoreException( "Failed to persiste the clob file", ioe );
        }
    }

    /**
     * Setter for the file manager
     * 
     * @param aFileManager
     *            the file manager to set
     */
    public void setFileManager( IFileManager aFileManager ) {
        fileManager = aFileManager;
    }

    /**
     * Getter for the file manager
     * 
     * @return the file manager
     */
    public IFileManager getFileManager() {
        return fileManager;
    }

    /**
     * Setter for the persistent filename to set
     * 
     * @param aFilename
     *            the name of the persistent file
     */
    public void setPersistentFile( String aFilename ) {
        persistentFile = aFilename;
    }

}
