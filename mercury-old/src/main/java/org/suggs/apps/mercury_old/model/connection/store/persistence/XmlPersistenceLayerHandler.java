/*
 * ConnectionStoreHandler.java created on 23 Jul 2007 18:44:39 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury_old.model.connection.store.persistence;

import org.suggs.apps.mercury_old.MercuryRuntimeException;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 * Connection store dom parser handler
 * 
 * @author suggitpe
 * @version 1.0 23 Jul 2007
 */
final class XmlPersistenceLayerHandler implements EntityResolver, ErrorHandler {

    private static final Logger LOG = LoggerFactory.getLogger( XmlPersistenceLayerHandler.class );

    private boolean failOnError = true;

    /**
     * Constructs a new instance.
     */
    public XmlPersistenceLayerHandler() {
        super();
        String go = System.getProperty( "mercury.failonerror" );
        if ( go != null && go.equals( "false" ) ) {
            failOnError = false;
            LOG.debug( "FailOnError =[false]" );
        }
    }

    /**
     * @see org.xml.sax.EntityResolver#resolveEntity(java.lang.String, java.lang.String)
     */
    @Override
    public InputSource resolveEntity( String publicId, String systemId ) throws java.io.IOException {
        // now get the dtd filename from the systemId URL
        int separator = 0;
        int pos = 0;
        while ( -1 != separator ) {
            separator = systemId.indexOf( '/', separator );
            if ( -1 != separator ) {
                pos = separator;
                ++separator;
            }
        }

        String dtdFileName = "dtd/" + systemId.substring( pos + 1 );

        InputStream stream = XmlPersistenceLayerHandler.class.getClassLoader()
            .getResourceAsStream( dtdFileName );
        if ( stream == null ) {
            String err = "Unable to load dtd file [" + dtdFileName + "]";
            LOG.error( err );
            throw new IOException( err );
        }

        return new InputSource( stream );
    }

    /**
     * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
     */
    @Override
    public void error( SAXParseException e ) {
        LOG.error( "Error picked up in the Connection Store Handler [" + e.getMessage() + "]" );
        if ( failOnError ) {
            throw new MercuryRuntimeException( e );
        }
    }

    /**
     * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
     */
    @Override
    public void warning( SAXParseException e ) {
        LOG.warn( "Warning picked up in the Connection Store Handler [" + e.getMessage() + "]" );
        if ( failOnError ) {
            throw new MercuryRuntimeException( e );
        }
    }

    /**
     * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
     */
    @Override
    public void fatalError( SAXParseException e ) {
        LOG.error( "Fatal error picked up in the Connection Store Handler [" + e.getMessage() + "]" );
        if ( failOnError ) {
            throw new MercuryRuntimeException( e );
        }
    }
}
