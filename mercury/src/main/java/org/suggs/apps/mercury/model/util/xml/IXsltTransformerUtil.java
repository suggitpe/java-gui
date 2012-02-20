/*
 * IXsltTransformerUtil.java created on 9 Dec 2008 08:40:05 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util.xml;

import org.suggs.apps.mercury.model.util.MercuryUtilityException;

import org.w3c.dom.Node;

/**
 * This utility interface will manage high level XSLT requirements for Mercury. The underlying classes should
 * encapsulate the rest of the XSLT transformation.
 * 
 * @author suggitpe
 * @version 1.0 9 Dec 2008
 */
public interface IXsltTransformerUtil {

    /**
     * This method will transform an xml document into a DOM node tree
     * 
     * @param aXmlToTransform
     *            the xml to tranform
     * @param aXsltName
     *            the xslt that is needed to transform the xml
     * @return the Dom node that represents the DOM tree
     * @throws MercuryUtilityException
     *             if there are any issues
     */
    Node transformXmlToDom( byte[] aXmlToTransform, String aXsltName ) throws MercuryUtilityException;

}
