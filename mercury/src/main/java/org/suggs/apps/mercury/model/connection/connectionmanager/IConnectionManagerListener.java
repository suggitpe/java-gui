/*
 * IConnectionManagerListener.java created on 21 Jan 2009 08:00:00 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionmanager;

/**
 * This interface will allow a set of classes to become observers to the connection manager for any changes to
 * the state of the class.
 * 
 * @author suggitpe
 * @version 1.0 21 Jan 2009
 */
public interface IConnectionManagerListener {

    enum ConnectionManagerEvent {
        CREATE, EDIT, REMOVE, CONNECT, DISCONNECT
    }

    /**
     * This is the impl method that the observers of the
     * 
     * @param aConnectionName
     * @param aEvent
     */
    void handleConnectionManagerChange( String aConnectionName, ConnectionManagerEvent aEvent );

}
