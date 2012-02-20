/*
 * EditConnectionAction.java created on 16 Jan 2009 08:16:39 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.actions.connection;

import org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStore;
import org.suggs.apps.mercury.model.util.image.ImageManager;
import org.suggs.apps.mercury.view.dialogs.SelectConnectionDialog;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * This action will allow you to update an existing connection within the connection store.
 * 
 * @author suggitpe
 * @version 1.0 16 Jan 2009
 */
public class EditConnectionAction extends Action {

    private IConnectionStore connectionStore;
    private String connectionToEdit;

    {
        setToolTipText( "Edit an existing connection" );
        setImageDescriptor( ImageManager.getImageDescriptor( ImageManager.IMAGE_CONN_EDIT_CONN ) );
    }

    /**
     * Constructs a new instance.
     */
    public EditConnectionAction( IConnectionStore aConnectionStore ) {
        super();
        setText( "&Edit ConnectionContext" );
        connectionStore = aConnectionStore;
    }

    /**
     * Constructs a new instance.
     * 
     * @param aConnStr
     * @param aConnectionToEdit
     *            the connecrtion to edit
     */
    public EditConnectionAction( IConnectionStore aConnStr, String aConnectionToEdit ) {
        super();
        setText( "&Edit " + aConnectionToEdit );
        connectionStore = aConnStr;
        connectionToEdit = aConnectionToEdit;
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        Shell s = Display.getCurrent().getActiveShell();

        // only get the connection if we need to, else we will proceed
        // with removal
        if ( connectionToEdit == null || connectionToEdit.length() == 0 ) {
            SelectConnectionDialog rcd = new SelectConnectionDialog( s,
                                                                     connectionStore.getKnownConnections()
                                                                         .keySet(),
                                                                     "Edit existing connection",
                                                                     "Select the connection that you wish to edit from the below list" );
            int ok = rcd.open();
            connectionToEdit = rcd.getChoice();

            if ( ok != Window.OK ) {
                return;
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
