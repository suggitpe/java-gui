/*
 * DomParserHelper.java created on 27 Nov 2008 07:37:37 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util.xml.impl;

import org.suggs.apps.mercury.model.util.MercuryUtilityException;
import org.suggs.apps.mercury.model.util.xml.IDomParserUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * This utility class will abstract all DOM based function away from the rest of the application. This
 * implementation will create a thread safe parser member the first time one of the parsers are required. This
 * can then be reused by other threads that need to use a parser.
 * 
 * @author suggitpe
 * @version 1.0 27 Nov 2008
 */
public class DomParserUtil implements IDomParserUtil {

    private static final Logger LOG = LoggerFactory.getLogger( DomParserUtil.class );

    private Object lock = new Object();
    private Map<String, DocumentBuilder> builderMap = new HashMap<String, DocumentBuilder>();

    /**
     * Create an XML document from a file.
     * 
     * @param aFilename
     *            the filename of the document
     * @param aSchemaLocation
     *            a valid URL pointing to the schema to validate the XML file
     * @return the XML document object that was contained in the file
     */
    @Override
    public final Document createDocFromXmlFile( String aFilename, String aSchemaLocation )
                    throws MercuryUtilityException {
        DocumentBuilder b = createDocumentBuilder( aSchemaLocation );

        try {
            return b.parse( new File( aFilename ) );
        }
        catch ( IOException ioe ) {
            throw new MercuryUtilityException( ioe );
        }
        catch ( SAXException se ) {
            throw new MercuryUtilityException( se );
        }
    }

    /**
     * Create a document builder for the particular schema.
     * 
     * @param aSchemaLocation
     *            the location of the schema for validation
     * @return the document builder configured to validate the defined schema
     */
    private DocumentBuilder createDocumentBuilder( String aSchemaLocation ) throws MercuryUtilityException {
        if ( builderMap.containsKey( aSchemaLocation ) ) {
            return builderMap.get( aSchemaLocation );
        }

        DocumentBuilder builder = null;
        try {
            DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
            fact.setNamespaceAware( true );
            fact.setValidating( false );

            SchemaFactory sf = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
            URL url = getClass().getClassLoader().getResource( aSchemaLocation );
            if ( url == null ) {
                throw new MercuryUtilityException( "Unable to locate resource [" + aSchemaLocation + "]" );
            }
            fact.setSchema( sf.newSchema( url ) );

            builder = fact.newDocumentBuilder();
            builder.setErrorHandler( new MercuryDomErrorHandler() );
        }
        catch ( ParserConfigurationException pce ) {
            throw new IllegalStateException( "Failed to create parser, pce" );
        }
        catch ( SAXException se ) {
            LOG.error( "Sax exception", se );
        }

        synchronized ( lock ) {
            if ( !builderMap.containsKey( aSchemaLocation ) ) {
                builderMap.put( aSchemaLocation, builder );
            }
        }

        return builder;
    }

    /**
     * Mercury version of the error handler.
     * 
     * @author suggitpe
     * @version 1.0 27 Nov 2008
     */
    static class MercuryDomErrorHandler implements ErrorHandler {

        private boolean failFast = true;

        /**
         * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
         */
        @Override
        public void error( SAXParseException exception ) {
            LOG.error( "SAX Parse excepion caught: " + exception.getMessage() );
            if ( failFast ) {
                throw new IllegalStateException( "Exception thrown from xml parsing", exception );
            }
        }

        /**
         * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
         */
        @Override
        public void fatalError( SAXParseException exception ) {
            LOG.error( "FATAL: SAX Parse excepion caught: " + exception.getMessage() );
            if ( failFast ) {
                throw new IllegalStateException( "Exception thrown from xml parsing", exception );
            }
        }

        /**
         * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
         */
        @Override
        public void warning( SAXParseException exception ) {
            LOG.warn( "SAX Parse excepion caught: " + exception.getMessage() );
        }
    }

}
