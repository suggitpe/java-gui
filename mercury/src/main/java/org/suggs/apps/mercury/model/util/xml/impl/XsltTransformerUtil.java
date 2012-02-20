/*
 * XsltTransformerHelper.java created on 8 Dec 2008 09:30:26 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util.xml.impl;

import org.suggs.apps.mercury.model.util.MercuryUtilityException;
import org.suggs.apps.mercury.model.util.xml.IXsltTransformerUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

/**
 * This class is there to help manage transformation of XML through known xslt.
 * 
 * @author suggitpe
 * @version 1.0 8 Dec 2008
 */
public class XsltTransformerUtil implements IXsltTransformerUtil {

    private static final Logger LOG = LoggerFactory.getLogger( XsltTransformerUtil.class );

    /**
     * @see org.suggs.apps.mercury.model.util.xml.IXsltTransformerUtil#transformXmlToDom(byte[],
     *      java.lang.String)
     */
    @Override
    public final Node transformXmlToDom( byte[] aXmlToTransform, String aXsltName )
                    throws MercuryUtilityException {
        if ( aXmlToTransform == null || aXmlToTransform.length == 0 ) {
            throw new MercuryUtilityException( "Trying to transform empty xml" );
        }

        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "Transforming [" + aXmlToTransform.length + "] bytes of data with xslt [" + aXsltName
                       + "]" );
        }

        try {
            Templates templ = getTemplates( aXsltName );
            Transformer trans = templ.newTransformer();
            DOMResult res = new DOMResult();
            trans.transform( new StreamSource( new ByteArrayInputStream( aXmlToTransform ) ), res );

            return res.getNode();
        }
        catch ( TransformerConfigurationException tce ) {
            throw new MercuryUtilityException( "Failed to create transformer for XSLT [" + aXsltName + "]",
                                               tce );
        }
        catch ( TransformerException te ) {
            throw new MercuryUtilityException( "Failed to transform xml", te );
        }
    }

    /**
     * This method will simply create the compiled XSLT into the templates object so that it can be used by
     * anyone.
     * 
     * @param aXsltName
     *            the name of the xslt that you wish to compile
     * @return the compiled xslt object
     * @throws MercuryUtilityException
     *             if there are any issues in reading the xslt or creating the compiled version of the xslt.
     */
    protected Templates getTemplates( String aXsltName ) throws MercuryUtilityException {
        if ( aXsltName == null || aXsltName.length() == 0 ) {
            throw new MercuryUtilityException( "Trying to use XSLT from an empty filename" );
        }

        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "Creating new Templates from XSLT [" + aXsltName + "]" );
        }

        TransformerFactory fact = TransformerFactory.newInstance();
        // fact.setErrorListener( new SimpleTransformErrorListener( LOG. ) );
        // fact.setURIResolver( this );

        InputStream is = getClass().getClassLoader().getResourceAsStream( aXsltName );
        if ( is == null ) {
            throw new MercuryUtilityException( "Could not find xslt file [" + aXsltName + "]" );
        }
        Source ss = new StreamSource( is );

        try {
            return fact.newTemplates( ss );
        }
        catch ( TransformerConfigurationException tce ) {
            throw new MercuryUtilityException( "Failed to compile XSLT into templates", tce );
        }
    }
}
