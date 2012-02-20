/*
 * IXmlSerialiser.java created on 11 Dec 2008 19:46:23 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util.xml;

import org.suggs.apps.mercury.model.util.MercuryUtilityException;

import org.w3c.dom.Node;

/**
 * This will provide the high level interface for the serialising XML documents
 * 
 * @author suggitpe
 * @version 1.0 11 Dec 2008
 */
public interface IXmlSerialiser {

    String serialiseXmlToString( Node aDocument ) throws MercuryUtilityException;
}
