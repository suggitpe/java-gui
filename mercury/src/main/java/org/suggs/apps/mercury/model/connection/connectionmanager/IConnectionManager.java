/*
 * IConnectionManager.java created on 20 Jan 2009 07:37:01 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionmanager;

import org.suggs.apps.mercury.model.connection.connection.IConnection;
import org.suggs.apps.mercury.model.connection.connectionstore.ConnectionStoreException;

/**
 * The connection manager is responsible for managing all of the connections in the system. It will interface
 * with the connection store and the relevant adapters to fulfil this position.
 * 
 * @author suggitpe
 * @version 1.0 20 Jan 2009
 */
public interface IConnectionManager {

    /**
     * Method to indicate whether the manager is managing a named connection
     * 
     * @param aConnectionName
     *            the name of the connection.
     * @return true if the manager is managing a connection by that name.
     */
    boolean containsConnection( String aConnectionName );

    /**
     * Gets a connection from the connection manager
     * 
     * @param aConnectionName
     *            the name of the connection to get
     * @return a connection
     */
    IConnection getConnection( String aConnectionName );

    /**
     * delete me wjhen this ois done properly TODO: deleyte me
     * 
     * @return teh dump of connections
     * @throws ConnectionStoreException
     */
    String getConnectionDump() throws ConnectionStoreException;

    /**
     * Adds a connection manager listener.
     * 
     * @param aListener
     *            a connection manager listener to add
     */
    void addConnectionManagerListener( IConnectionManagerListener aListener );

    /**
     * Removes a connection manager listener
     * 
     * @param aListener
     *            a connection manager listener to remove
     */
    void removeConnectionManagerListener( IConnectionManagerListener aListener );

}
