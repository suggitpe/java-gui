/*
 * IConnectionStoreChangeListener.java created on 13 Jan 2009 19:39:08 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore;

/**
 * This interface encapsulates the event of change in the connection store. This basically allows for
 * observers to register interest in changes in the connection store.
 * 
 * @author suggitpe
 * @version 1.0 13 Jan 2009
 */
public interface IConnectionStoreChangeListener {

    enum ConnectionStoreEvent {
        CREATE, EDIT, REMOVE
    }

    /**
     * This interface method is really a notification method but its name suggests that change may happen in
     * the future where an event can be passed in.
     * 
     * @param aConnectionTarget
     * @param aEvent
     */
    void handleConnectionStoreChange( String aConnectionTarget, ConnectionStoreEvent aEvent );

}
