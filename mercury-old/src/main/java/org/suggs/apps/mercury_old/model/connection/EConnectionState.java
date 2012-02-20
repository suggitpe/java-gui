/*
 * ConnectionState.java created on 2 Jul 2007 18:48:36 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old.model.connection;

/**
 * Enumerated type for connection state
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
public enum EConnectionState {
    /**
     * Initial state to show that no connection activities have been
     * performed on this object.
     */
    INITIAL,
    /**
     * State to represent a connected state with the end server
     */
    CONNECTED,
    /**
     * State to represent a disconnected state with the end server
     */
    DISCONNECTED

}
