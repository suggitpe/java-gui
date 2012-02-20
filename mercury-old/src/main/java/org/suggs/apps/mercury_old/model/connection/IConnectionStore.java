/*
 * IJmsConnectionStore.java created on 28 Jun 2007 18:35:20 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old.model.connection;

/**
 * Defines a persistence mechanism for storing connection details
 * 
 * @author suggitpe
 * @version 1.0 28 Jun 2007
 */
public interface IConnectionStore {

    /**
     * This will go to the persistent connection store and retrieve a list of the avialable and known
     * connections
     * 
     * @return an array of the names of the connections
     */
    String[] getListOfKnownConnectionNames();

    /**
     * Checks to see if a connection exists already (by name only)
     * 
     * @param aConnectionName
     *            the name of the connection to look for
     * @return true if the connection exists else false
     */
    boolean doesConnectionExist( String aConnectionName );

    /**
     * LOad a connection parameter for a given name
     * 
     * @param aName
     *            the name of the connection to load
     * @return the details fo the connection
     * @throws MercuryConnectionStoreException
     *             when there is an issue with the connection store
     */
    IConnectionDetails loadConnectionParameters( String aName ) throws MercuryConnectionStoreException;

    /**
     * Save a set of connection parameters to the local connection store
     * 
     * @param aDetails
     *            the connection parameters to store
     * @param aName
     *            the name of the connection
     * @throws MercuryConnectionStoreException
     *             when there is an issue in the saving of connection details
     */
    void saveConnectionParameters( String aName, IConnectionDetails aDetails )
                    throws MercuryConnectionStoreException;

    /**
     * Allows the deletion of a known connection
     * 
     * @param aName
     *            the name of the connection
     * @throws MercuryConnectionStoreException
     *             if there are any problems
     */
    void deleteNamedConnection( String aName ) throws MercuryConnectionStoreException;

    /**
     * Getter for the internal status of the Connection Store
     * 
     * @return state
     */
    String getState();

}
