/*
 * IXmlPersistenceManager.java created on 25 Sep 2008 18:41:12 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore.xmldao;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connectionstore.ConnectionStoreException;

import java.util.Map;

/**
 * This interface manages the persistence of the underlying XML file. It is managed through an interface so
 * that cached or non-cached or other persistence mechanisms can be injected. The default will be for a cache
 * implementation to decorate the actual file persistence layer.
 * 
 * @author suggitpe
 * @version 1.0 25 Sep 2008
 */
public interface IXmlConnectionStoreManager {

    /**
     * Reads all connection details from the underlying persistence layer and passes back to the caller in the
     * form of a map
     * 
     * @return a map of connection details objects
     * @throws ConnectionStoreException
     *             if there are problems in the reading of the data;
     */
    Map<String, ConnectionDetails> readConnectionData() throws ConnectionStoreException;

    /**
     * Saves a given map of connection details to the underlying persistence layer. This will completely
     * replace the content of the existing layer contents. The process is to first marshall the data into xml
     * and then serialise that xml to a string (then persist).
     * 
     * @param aMap
     *            the map of connection details to persist
     * @throws ConnectionStoreException
     *             if there are any problems in the persistence of the data
     */
    void saveConnectionData( Map<String, ConnectionDetails> aMap ) throws ConnectionStoreException;

    /**
     * This accessor is there to get access to the raw underlying XML for the connection store.
     * 
     * @return the raw xml that is on the file system
     * @throws ConnectionStoreException
     *             if there is a problem getting hold of the raw xml data
     */
    String getRawXml() throws ConnectionStoreException;

}
