/*
 * IConnectionManager.java created on 22 Jun 2007 08:08:33 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old.model.connection;

/**
 * This interface manages all of the connections available to the GUI
 * 
 * @author suggitpe
 * @version 1.0 22 Jun 2007
 */
public interface IConnectionManager {

    /**
     * Test the connection parameters (makes a connection)
     * 
     * @param aDetails
     *            the context with which to connect
     * @return true if the connection succeeds else false
     */
    boolean testConnection( IConnectionParameters aDetails );

    /**
     * Returns the current state of the connection
     * 
     * @return the current state of the connection
     */
    EConnectionState getConnectionState();

    /**
     * Connect to a defined JMS resource
     * 
     * @param aDetails
     *            the connection details
     * @throws MercuryConnectionException
     */
    void connect( IConnectionParameters aDetails ) throws MercuryConnectionException;

    /**
     * Disconnect from the current connection
     * 
     * @throws MercuryConnectionException
     *             if there are any problems in the disconnect
     */
    void disconnect() throws MercuryConnectionException;

}
