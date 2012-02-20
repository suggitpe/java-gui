/*
 * IConnectionParameters.java created on 9 Aug 2007 19:33:06 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury_old.model.connection;

import java.util.Map;

/**
 * Interface to define the requirements for a connection to a broker
 * 
 * @author suggitpe
 * @version 1.0 9 Aug 2007
 */
public interface IConnectionParameters {

    /**
     * Get the connection type
     * 
     * @return the type of the connection
     */
    EConnectionType getType();

    /**
     * Get the name for the server for the connection
     * 
     * @return the name of the server
     */
    String getHostname();

    /**
     * Get the port number to connect to
     * 
     * @return the port number for the connection
     */
    String getPort();

    /**
     * Get any additional connection metadata for the connection
     * 
     * @return the connection metadata
     */
    Map<String, String> getMetaData();

    /**
     * Getter for the connectionFactory name
     * 
     * @return the name of the connectionFactory
     */
    String getConnectionFactory();

}
