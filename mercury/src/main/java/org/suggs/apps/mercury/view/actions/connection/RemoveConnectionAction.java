/*
 * RemoveConnectionAction.java created on 14 Jan 2009 19:15:11 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.actions.connection;

import org.suggs.apps.mercury.model.connection.connectionstore.ConnectionStoreException;
import org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStore;
import org.suggs.apps.mercury.model.util.image.ImageManager;
import org.suggs.apps.mercury.view.dialogs.SelectConnectionDialog;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * This action will manage the removal of a connection from the connection store.
 * 
 * @author suggitpe
 * @version 1.0 14 Jan 2009
 */
public class RemoveConnectionAction extends Action {

    private IConnectionStore connectionStore;
    private String connectionToRemove;

    // initialiser section
    {
        setToolTipText( "Remove an existing connection" );
        setImageDescriptor( ImageManager.getImageDescriptor( ImageManager.IMAGE_CONN_REMOVE_CONN ) );
    }

    /**
     * Constructs a new instance.
     */
    public RemoveConnectionAction( IConnectionStore aConnectionStore ) {
        super();
        setText( "&Remove ConnectionContext" );
        connectionStore = aConnectionStore;
    }

    /**
     * Constructs a new instance.
     * 
     * @param aConnStr
     * @param aConnectionToRemove
     *            the name of the connection to remove
     */
    public RemoveConnectionAction( IConnectionStore aConnStr, String aConnectionToRemove ) {
        super();
        connectionStore = aConnStr;
        connectionToRemove = aConnectionToRemove;
        setText( "&Remove " + aConnectionToRemove );
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        Shell s = Display.getCurrent().getActiveShell();

        // only get the connection if we need to, else we will proceed
        // with removal
        if ( connectionToRemove == null || connectionToRemove.length() == 0 ) {
            SelectConnectionDialog rcd = new SelectConnectionDialog( s,
                                                                     connectionStore.getKnownConnections()
                                                                         .keySet(),
                                                                     "Remove existing connection",
                                                                     "Select the connection that you wish to remove from the below list" );
            int ok = rcd.open();
            connectionToRemove = rcd.getChoice();

            if ( ok != Window.OK ) {
                return;
            }
        }

        // here we check we have valid data
        if ( connectionToRemove != null && connectionToRemove.length() > 0 ) {
            // check they do actually want it removed
            if ( !MessageDialog.openConfirm( s,
                                             "Confirm connection removal",
                                             "Please confirm that you wish to remove connection ["
                                                             + connectionToRemove + "]" ) ) {
                return;
            }

            // be bullet proof
            if ( !connectionStore.doesConnectionExist( connectionToRemove ) ) {
                MessageDialog.openError( s, "ConnectionContext removal error", "The connection ["
                                                                               + connectionToRemove
                                                                               + "] does not actually exist" );
                return;
            }

            // now we actually remove the connection
            try {
                connectionStore.deleteNamedConnection( connectionToRemove );
            }
            catch ( ConnectionStoreException e ) {
                MessageDialog.openError( s,
                                         "ConnectionContext removal error",
                                         "Failed to remove connection [conn] because of the following error\n"
                                                         + e.getMessage() );
            }
        }
    }

    /**
     * Setter for the connection store
     * 
     * @param store
     *            the store to set
     */
    public void setConnectionStore( IConnectionStore store ) {
        connectionStore = store;
    }

}
