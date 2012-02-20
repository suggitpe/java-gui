/*
 * XmlSerialiser.java created on 11 Dec 2008 19:46:40 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util.xml.impl;

import org.suggs.apps.mercury.model.util.MercuryUtilityException;
import org.suggs.apps.mercury.model.util.xml.IXmlSerialiser;

import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

/**
 * This class implements the full xml document serialisation process.
 * 
 * @author suggitpe
 * @version 1.0 11 Dec 2008
 */
public class XmlSerialiser implements IXmlSerialiser {

    private static final Logger LOG = LoggerFactory.getLogger( XmlSerialiser.class );

    /**
     * @see org.suggs.apps.mercury.model.util.xml.IXmlSerialiser#serialiseXmlToString(org.w3c.dom.Node)
     */
    @Override
    public final String serialiseXmlToString( Node document ) throws MercuryUtilityException {
        LOG.debug( "Serialising DOM Document" );

        // create the serialiser impl
        DOMImplementationLS implLS = createSerializerImpl();

        // now create the relevant output objects etc
        StringWriter writer = new StringWriter();
        LSOutput output = implLS.createLSOutput();
        // be explicit
        output.setEncoding( "UTF-8" );
        output.setCharacterStream( writer );

        // now create the serialiser itself
        LSSerializer ser = implLS.createLSSerializer();
        ser.write( document, output );

        // serialise away baby!
        return writer.toString();
    }

    /**
     * This method is there to manage the construction of the serialiser
     * 
     * @return the serialiser implementation object
     * @throws MercuryUtilityException
     *             if there are any issues in creating the object
     */
    private DOMImplementationLS createSerializerImpl() throws MercuryUtilityException {
        try {
            System.setProperty( DOMImplementationRegistry.PROPERTY,
                                "com.sun.org.apache.xerces.internal.dom.DOMImplementationSourceImpl" );
            DOMImplementationRegistry reg = DOMImplementationRegistry.newInstance();
            DOMImplementation domImpl = reg.getDOMImplementation( "LS 3.0" );
            if ( domImpl == null ) {
                throw new MercuryUtilityException( "Failed to get DOM Impl for [LS 3.0]" );
            }

            return (DOMImplementationLS) domImpl;
        }
        catch ( InstantiationException ie ) {
            throw new MercuryUtilityException( "Failed to create new serialiser", ie );
        }
        catch ( ClassNotFoundException cnf ) {
            throw new MercuryUtilityException( "Failed to create new serialiser due to the dom impl cfgbeing incorrect",
                                               cnf );
        }
        catch ( IllegalAccessException iae ) {
            throw new MercuryUtilityException( "No access to create serialiser object", iae );
        }

    }
}
