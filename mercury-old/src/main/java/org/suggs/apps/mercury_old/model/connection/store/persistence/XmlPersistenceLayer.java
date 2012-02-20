/*
 * XmlPersistenceLayer.java created on 27 Jul 2007 06:27:26 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury_old.model.connection.store.persistence;

import org.suggs.apps.mercury_old.MercuryException;
import org.suggs.apps.mercury_old.model.connection.EConnectionType;
import org.suggs.apps.mercury_old.model.connection.IConnectionDetails;
import org.suggs.apps.mercury_old.model.connection.MercuryConnectionStoreException;
import org.suggs.apps.mercury_old.model.connection.store.ConnectionDetails;
import org.suggs.apps.mercury_old.model.connection.store.IPersistenceLayer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.dom.DOMImplementationImpl;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * Implementation of the persistence layer interface using an XML file as its storage layer
 * 
 * @author suggitpe
 * @version 1.0 27 Jul 2007
 */
@SuppressWarnings("restriction")
public class XmlPersistenceLayer implements IPersistenceLayer {

    private static final Logger LOG = LoggerFactory.getLogger( XmlPersistenceLayer.class );
    private static final String MERCURY_HOME_DIR = System.getProperty( "user.home" ) + "/.mercury_old";
    private static final File MERCURY_FILE = new File( MERCURY_HOME_DIR + "/connectionStore.xml" );

    private static final String CONNECTION = "connection";
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final String HOSTNAME = "hostname";
    private static final String PORT = "port";
    private static final String METADATA = "metadata";
    private static final String DATA = "data";
    private static final String CONN_FACTS_NODE = "connectionFactories";
    private static final String CONN_FACT = "factory";
    private static final String DESTINATIONS_NODE = "destinations";
    private static final String DESTINATION = "destination";

    /**
     * @see org.suggs.apps.mercury_old.model.connection.store.IPersistenceLayer#readPersistenceLayer()
     */
    @Override
    public Map<String, IConnectionDetails> readPersistenceLayer() throws MercuryConnectionStoreException {
        // quickly check that the file is not just an empty file
        if ( MERCURY_FILE.length() == 0 ) {
            LOG.warn( "Mercury file is empty assuming worst case and ignoring it" );
            return null;
        }

        Map<String, IConnectionDetails> ret = new HashMap<String, IConnectionDetails>();
        try {
            XmlPersistenceLayerHandler handler = new XmlPersistenceLayerHandler();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setValidating( true );
            builderFactory.setIgnoringElementContentWhitespace( true );
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            builder.setEntityResolver( handler );
            builder.setErrorHandler( handler );

            LOG.debug( "Parsing configuration file [" + MERCURY_FILE + "]" );
            Document d = builder.parse( MERCURY_FILE );

            NodeList list = d.getElementsByTagName( CONNECTION );
            for ( int i = 0; i < list.getLength(); ++i ) {
                Node c = list.item( i );
                NamedNodeMap atts = c.getAttributes();
                String name = atts.getNamedItem( NAME ).getTextContent();
                EConnectionType type = EConnectionType.createTypeFromString( atts.getNamedItem( TYPE )
                    .getTextContent() );
                NodeList childList = list.item( i ).getChildNodes();

                ConnectionDetails connDtls = new ConnectionDetails( name, type );

                for ( int j = 0; j < childList.getLength(); ++j ) {
                    Node n = childList.item( j );
                    if ( n.getTextContent() != null ) {
                        if ( n.getNodeName().equals( HOSTNAME ) ) {
                            connDtls.setHostname( n.getTextContent() );
                        }
                        else if ( n.getNodeName().equals( PORT ) ) {
                            connDtls.setPort( n.getTextContent() );
                        }
                        else if ( n.getNodeName().equals( METADATA ) ) {
                            Map<String, String> metadata = new HashMap<String, String>();
                            NodeList meta = n.getChildNodes();
                            for ( int k = 0; k < meta.getLength(); ++k ) {
                                Node g = meta.item( k );
                                metadata.put( g.getAttributes().getNamedItem( NAME ).getTextContent(),
                                              g.getTextContent() );

                            }
                            connDtls.setMetaData( metadata );
                        }
                        else if ( n.getNodeName().equals( CONN_FACTS_NODE ) ) {
                            NodeList factList = n.getChildNodes();
                            if ( factList.getLength() > 0 ) {
                                Map<String, Set<String>> factories = new HashMap<String, Set<String>>();
                                for ( int f = 0; f < factList.getLength(); ++f ) {
                                    Node fact = factList.item( f );
                                    String factType = fact.getAttributes()
                                        .getNamedItem( TYPE )
                                        .getTextContent();
                                    // first make sure that we have a
                                    // map entry for this type of
                                    // factory
                                    if ( !( factories.containsKey( factType ) ) ) {
                                        factories.put( factType, new TreeSet<String>() );
                                    }
                                    // Then we put the destination in
                                    // the map->set
                                    factories.get( factType ).add( fact.getTextContent() );
                                }
                                connDtls.setConnectionFactories( factories );
                            }
                        }
                        else if ( n.getNodeName().equals( DESTINATIONS_NODE ) ) {
                            NodeList destList = n.getChildNodes();
                            if ( destList.getLength() > 0 ) {
                                Map<String, Set<String>> destinations = new HashMap<String, Set<String>>();
                                for ( int z = 0; z < destList.getLength(); ++z ) {
                                    Node dest = destList.item( z );
                                    String destType = dest.getAttributes()
                                        .getNamedItem( TYPE )
                                        .getTextContent();
                                    // first make sure that we have a
                                    // map entry for this type of
                                    // destination
                                    if ( !( destinations.containsKey( destType ) ) ) {
                                        destinations.put( destType, new TreeSet<String>() );
                                    }
                                    // Then we put the destination in
                                    // the map->set
                                    destinations.get( destType ).add( dest.getTextContent() );
                                }
                                connDtls.setDestinations( destinations );
                            }
                        }
                    }
                }

                ret.put( name, connDtls );
            }

            return ret;
        }
        catch ( SAXException se ) {
            throw new MercuryConnectionStoreException( se );
        }
        catch ( ParserConfigurationException pce ) {
            throw new MercuryConnectionStoreException( pce );
        }
        catch ( IOException ioe ) {
            throw new MercuryConnectionStoreException( ioe );
        }
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.store.IPersistenceLayer#savePersistenceLayer(java.util.Map)
     */
    @Override
    public void savePersistenceLayer( Map<String, IConnectionDetails> aMap )
                    throws MercuryConnectionStoreException {
        LOG.info( "Saving prsistence layer" );

        DOMImplementation impl = DOMImplementationImpl.getDOMImplementation();
        DocumentType type = impl.createDocumentType( "connectionStore",
                                                     "-//MERCURY//DTD CONNECTION STORE//EN",
                                                     "mercury-configuration.dtd" );
        Document newDoc = impl.createDocument( null, "connectionStore", type );

        // we do this to get a sorted set of key values
        SortedSet<String> keys = new TreeSet<String>( aMap.keySet() );
        for ( String key : keys ) {
            LOG.debug( "Building key [" + key + "]" );
            IConnectionDetails dtls = aMap.get( key );

            Element connElem = newDoc.createElement( CONNECTION );
            connElem.setAttribute( NAME, dtls.getName() );
            connElem.setAttribute( TYPE, dtls.getType().name() );

            Element svrElem = newDoc.createElement( HOSTNAME );
            svrElem.setTextContent( dtls.getHostname() );
            connElem.appendChild( svrElem );

            Element portElem = newDoc.createElement( PORT );
            portElem.setTextContent( dtls.getPort() );
            connElem.appendChild( portElem );

            Map<String, String> m = dtls.getMetaData();
            if ( m.size() > 0 ) {
                Element metadataElem = newDoc.createElement( METADATA );
                for ( Map.Entry<String, String> entry : m.entrySet() ) {
                    Element dataElem = newDoc.createElement( DATA );
                    dataElem.setAttribute( NAME, entry.getKey() );
                    dataElem.setTextContent( entry.getValue() );
                    metadataElem.appendChild( dataElem );
                }
                connElem.appendChild( metadataElem );
            }

            Map<String, Set<String>> factMap = dtls.getConnectionFactories();
            Element connFactsElem = newDoc.createElement( CONN_FACTS_NODE );
            for ( Map.Entry<String, Set<String>> factType : factMap.entrySet() ) {
                for ( String f : factType.getValue() ) {
                    Element factoryElem = newDoc.createElement( CONN_FACT );
                    factoryElem.setAttribute( TYPE, factType.getKey() );
                    factoryElem.setTextContent( f );
                    connFactsElem.appendChild( factoryElem );
                }
            }
            connElem.appendChild( connFactsElem );

            Map<String, Set<String>> destMap = dtls.getDestinations();
            Element destsElem = newDoc.createElement( DESTINATIONS_NODE );
            // for ( String destType : destMap.keySet() ) {
            for ( Map.Entry<String, Set<String>> destEntry : destMap.entrySet() ) {
                for ( String d : destEntry.getValue() ) {
                    Element destinationElem = newDoc.createElement( DESTINATION );
                    destinationElem.setAttribute( TYPE, destEntry.getKey() );
                    destinationElem.setTextContent( d );
                    destsElem.appendChild( destinationElem );
                }
            }
            connElem.appendChild( destsElem );

            newDoc.getDocumentElement().appendChild( connElem );
        }

        String xml = null;
        try {
            xml = serializeXmlDoc( newDoc );
        }
        catch ( MercuryException me ) {
            throw new MercuryConnectionStoreException( "Failed to serialise xml doc", me );
        }

        LOG.debug( "New XML doc:\n" + xml );

        persistClob( xml, MERCURY_FILE );

    }

    /**
     * Persist CLOB data to the underlying persistence layer
     * 
     * @param aClob
     *            the clob data to persist
     * @param aFile
     *            the file to persist the data
     * @throws MercuryConnectionStoreException
     *             if there are any issues in the persistence
     */
    public static void persistClob( String aClob, File aFile ) throws MercuryConnectionStoreException {
        // now we persiste the new values to the persistent xml file
        FileOutputStream out = null;
        FileChannel chan = null;
        try {
            LOG.debug( "Overwriting existing xml persistence layer with new file at ["
                       + aFile.getCanonicalPath() + "]" );
            out = new FileOutputStream( aFile );
            chan = out.getChannel();
            int xmlSize = aClob.getBytes().length;

            ByteBuffer buff = ByteBuffer.allocate( xmlSize );
            buff.put( aClob.getBytes() );
            // flip will move the buffer limit to where the buffer
            // pointer now sits
            buff.flip();

            chan.write( buff );
        }
        catch ( IOException ioe ) {
            throw new MercuryConnectionStoreException( ioe );
        }
        finally {
            try {
                if ( chan != null ) {
                    chan.close();
                }

                if ( out != null ) {
                    out.close();
                }
            }
            catch ( IOException ioe ) {
                LOG.warn( "Caught IOException when closing file channel [" + ioe.getMessage() + "]" );
            }
        }
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.store.IPersistenceLayer#verifyPersistenceLayer()
     */
    @Override
    public final void verifyPersistenceLayer() {
        // check that the persistence dir exists
        File dir = new File( MERCURY_HOME_DIR );
        if ( !( dir.exists() ) ) {
            LOG.warn( "Mercury home dir does not exist or is not writable ... assuming firt application execution" );
            LOG.info( "Creating MERCURY xml persistence store dir [" + dir.getAbsolutePath() + "]" );
            if ( !( dir.mkdir() ) ) {
                throw new IllegalStateException( "Failed to create persistence dir [" + MERCURY_HOME_DIR
                                                 + "]" );
            }
            createPersistenceFile();
        }
        else {
            if ( !( dir.isDirectory() ) || !( dir.canWrite() ) ) {
                String err = "Cannot write to the mercury persistence directory [" + MERCURY_HOME_DIR + "]";
                LOG.error( err );
                throw new IllegalStateException( err );
            }
        }

        // now check that the actual file exists
        if ( !( MERCURY_FILE.exists() ) ) {
            createPersistenceFile();
        }
        else {
            if ( !( MERCURY_FILE.isFile() ) || !( MERCURY_FILE.canWrite() ) ) {
                String err = "Cannot write to mercury persistence layer [" + MERCURY_FILE.getAbsolutePath()
                             + "]";
                LOG.error( err );
                throw new IllegalStateException( err );
            }
        }

        LOG.debug( "Mercury connection store persistence layer correctly set up" );
    }

    /**
     * Serializes an xml document to a string
     * 
     * @param aDocument
     *            the document to serialize
     * @return the xml document as a string
     */
    private String serializeXmlDoc( Document aDocument ) throws MercuryException {
        ByteArrayOutputStream strm = new ByteArrayOutputStream();
        OutputFormat out = new OutputFormat( aDocument );
        out.setIndenting( true );
        XMLSerializer serializer = new XMLSerializer( strm, out );

        try {
            serializer.serialize( aDocument );
        }
        catch ( IOException ioe ) {
            throw new MercuryException( ioe );
        }
        return strm.toString();
    }

    /**
     * Create the underlying xml persistence file
     */
    private static void createPersistenceFile() {
        LOG.info( "Creating MERCURY xml persistence store [" + MERCURY_FILE.getAbsolutePath() + "]" );
        try {
            if ( !( MERCURY_FILE.createNewFile() ) ) {
                throw new IllegalStateException( "Failed to create the persistence xml file ["
                                                 + MERCURY_FILE.getAbsolutePath() + "]" );
            }
        }
        catch ( IOException ioe ) {
            LOG.error( "Failed to create persistence file [" + ioe.getMessage() + "]" );
            throw new IllegalStateException( ioe );
        }
    }
}
