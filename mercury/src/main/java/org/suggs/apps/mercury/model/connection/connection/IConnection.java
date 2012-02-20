/*
 * IConnection.java created on 20 Jan 2009 19:25:53 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connection;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;

/**
 * This interface defines the behaviour of the connections within the Mercury application.
 * 
 * @author suggitpe
 * @version 1.0 20 Jan 2009
 */
public interface IConnection {

    /**
     * Getter for the connection details held in the connection.
     * 
     * @return the connection details
     */
    ConnectionDetails getConnectionDetails();

    /**
     * Make a connection using the provided connection details
     */
    void connect();

    /**
     * Disconnect from the connection
     */
    void disconnect();

    /**
     * Getter for the status of the connection
     * 
     * @return the status of the connection
     */
    String getConnectionStatus();

}
