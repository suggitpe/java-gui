/*
 * IConnectionStore.java created on 22 Sep 2008 18:43:36 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;

import java.util.Map;

/**
 * Defines a persistence mechanism for storing connection details
 * 
 * @author suggitpe
 * @version 1.0 22 Sep 2008
 */
public interface IConnectionStore {

    /**
     * This will go to the persistent connection store and retrieve a map of the available and known
     * connections
     * 
     * @return a map of the names and connection details of the connections
     */
    Map<String, ConnectionDetails> getKnownConnections();

    /**
     * Checks to see if a connection exists already (by name only)
     * 
     * @param aConnectionName
     *            the name of the connection to look for
     * @return true if the connection exists else false
     */
    boolean doesConnectionExist( String aConnectionName );

    /**
     * Load a connection bean for a given name
     * 
     * @param aName
     *            the name of the connection to load
     * @return the details of the connection
     * @throws ConnectionStoreException
     *             when there is an issue with the connection store
     */
    ConnectionDetails loadConnectionParameters( String aName ) throws ConnectionStoreException;

    /**
     * Save a set of connection parameters to the local connection store
     * 
     * @param aDetails
     *            the connection parameters to store
     * @param aName
     *            the name of the connection
     * @throws ConnectionStoreException
     *             when there is an issue in the saving of connection details
     */
    void saveConnectionParameters( String aName, ConnectionDetails aDetails ) throws ConnectionStoreException;

    /**
     * Allows the deletion of a known connection
     * 
     * @param aName
     *            the name of the connection
     * @throws ConnectionStoreException
     *             if there are any problems
     */
    void deleteNamedConnection( String aName ) throws ConnectionStoreException;

    /**
     * This is the way to get a dump of the connection store data that is currently configured in the system.
     * 
     * @return serialised dump of the connection store data in XML form
     * @throws ConnectionStoreException
     *             if there are any issues in creating the dump
     */
    String getConnectionStoreDumpAsXml() throws ConnectionStoreException;

    /**
     * Adds a new listener to the connection store impl
     * 
     * @param aListener
     *            the listener that will be activated once the
     */
    void addConnectionStoreChangeListener( IConnectionStoreChangeListener aListener );

    /**
     * Removes a known listener from the set of listeners
     * 
     * @param aListener
     *            the listsner to remove
     */
    void removeConnectionStoreChangeListener( IConnectionStoreChangeListener aListener );
}
