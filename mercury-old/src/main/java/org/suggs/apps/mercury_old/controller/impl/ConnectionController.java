/*
 * ConnectionController.java created on 12 Jul 2007 16:30:12 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old.controller.impl;

import org.suggs.apps.mercury_old.MercuryException;
import org.suggs.apps.mercury_old.controller.IConnectionController;
import org.suggs.apps.mercury_old.model.connection.IConnectionDetails;
import org.suggs.apps.mercury_old.model.connection.IConnectionManager;
import org.suggs.apps.mercury_old.model.connection.IConnectionStore;
import org.suggs.apps.mercury_old.model.connection.MercuryConnectionStoreException;
import org.suggs.apps.mercury_old.view.connection.ConnectionButtons;
import org.suggs.apps.mercury_old.view.connection.ConnectionManagerPanel;
import org.suggs.apps.mercury_old.view.connection.ConnectionStorePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the main controller for theconnection part of the GUI.
 * 
 * @author suggitpe
 * @version 1.0 12 Jul 2007
 */
public class ConnectionController implements IConnectionController {

    private static final Logger LOG = LoggerFactory.getLogger( ConnectionController.class );
    private static final ImageIcon IMG = new ImageIcon( "jms.gif" );

    private IConnectionStore connStoreModel;
    private IConnectionManager connManagerModel;
    private ConnectionButtons buttonsView;
    private ConnectionManagerPanel connManagerView;
    private ConnectionStorePanel connStoreView;

    /**
     * Constructs a new instance.
     * 
     * @param aStr
     *            the connection store
     * @param aMgr
     *            the connection manager
     * @param aButtons
     *            the buttons panel
     * @param aStrPanel
     *            the connection store panel
     * @param aMgrPanel
     *            the onnection manager panel
     */
    public ConnectionController( IConnectionStore aStr, IConnectionManager aMgr, ConnectionButtons aButtons,
                                 ConnectionStorePanel aStrPanel, ConnectionManagerPanel aMgrPanel ) {
        super();
        connStoreModel = aStr;
        connManagerModel = aMgr;
        buttonsView = aButtons;
        connStoreView = aStrPanel;
        connManagerView = aMgrPanel;

        init();
    }

    /**
     * Local method to initialise itself and all of the inter related objects
     */
    private void init() {
        LOG.debug( "Initialising the Connection Controller" );
        connStoreView.initialise( connStoreModel.getState() );
        connManagerView.initialise( connManagerModel.getConnectionState().name() );

        buttonsView.addLoadActionListener( createLoadActionListener() );
        buttonsView.addSaveActionListener( createSaveActionListener() );
        buttonsView.addDeleteActionListener( createDeleteActionListener() );
        buttonsView.addTestActionListener( createTestConnActionListener() );
        buttonsView.addConnectActionListener( createConnectActionListener() );
        buttonsView.addDisconnectActionListener( createDisconnectActionListener() );
    }

    /**
     * Creates a new Test connection action listener
     * 
     * @return a new test connection action listener
     */
    private ActionListener createTestConnActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed( ActionEvent arg0 ) {
                if ( !( connManagerModel.testConnection( null ) ) ) {
                    String err = "Test connection failed";
                    LOG.warn( err );
                    JOptionPane.showMessageDialog( connManagerView,
                                                   err,
                                                   "Connect failure",
                                                   JOptionPane.ERROR_MESSAGE );
                }
            }
        };
    }

    /**
     * Creates a new connect action listsner
     * 
     * @return a new connect action listener
     */
    private ActionListener createConnectActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed( ActionEvent arg0 ) {
                try {
                    connManagerModel.connect( null );
                }
                catch ( MercuryException jhe ) {
                    String err = "Exception caught when trying to connect to connection:\n"
                                 + jhe.getMessage();
                    LOG.warn( err );
                    JOptionPane.showMessageDialog( connManagerView,
                                                   err,
                                                   "Connect failure",
                                                   JOptionPane.ERROR_MESSAGE );
                }
            }
        };
    }

    /**
     * Creates a new disconnect action listsner
     * 
     * @return a new disconnect action listener
     */
    private ActionListener createDisconnectActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed( ActionEvent arg0 ) {
                try {
                    connManagerModel.disconnect();
                }
                catch ( MercuryException jhe ) {
                    String err = "Exception caught when trying to disconnect from connection:\n"
                                 + jhe.getMessage();
                    LOG.warn( err );
                    JOptionPane.showMessageDialog( connManagerView,
                                                   err,
                                                   "Disconnect failure",
                                                   JOptionPane.ERROR_MESSAGE );
                }
            }
        };
    }

    /**
     * Creates a new delete action listener
     * 
     * @return a new delete action listsner
     */
    private ActionListener createDeleteActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed( ActionEvent arg0 ) {
                String[] conns = connStoreModel.getListOfKnownConnectionNames();
                String input = (String) JOptionPane.showInputDialog( connStoreView,
                                                                     "Please select the connection to delete:",
                                                                     "Select connection for delete",
                                                                     JOptionPane.INFORMATION_MESSAGE,
                                                                     IMG,
                                                                     conns,
                                                                     "..." );

                if ( input != null ) {
                    int choice = JOptionPane.showConfirmDialog( connStoreView,
                                                                "Are you sure that you want to delete the named connection["
                                                                                + input + "]",
                                                                "Delete Confirmation",
                                                                JOptionPane.YES_NO_CANCEL_OPTION );
                    if ( choice == JOptionPane.OK_OPTION ) {
                        int sure = JOptionPane.showConfirmDialog( connStoreView,
                                                                  "Are you sure?\n[" + input
                                                                                  + "] could be useful",
                                                                  "Delete Verify",
                                                                  JOptionPane.YES_NO_CANCEL_OPTION );
                        if ( sure == JOptionPane.OK_OPTION ) {
                            try {
                                LOG.info( "Deleting named connection [" + input + "]" );
                                connStoreModel.deleteNamedConnection( input );
                            }
                            catch ( MercuryConnectionStoreException mcse ) {
                                JOptionPane.showMessageDialog( connStoreView,
                                                               "Delete failed",
                                                               "Failed to delete connection [" + input + "]",
                                                               JOptionPane.ERROR_MESSAGE );
                            }
                        }
                        else {
                            LOG.debug( "Delete cancelled at the last minute for [" + input + "]" );
                        }
                    }
                    else {
                        LOG.debug( "Delete action for [" + input + "] canceled" );
                    }
                }
                else {
                    LOG.debug( "Delete ction cancelled" );
                }
            }
        };
    }

    /**
     * Creates an action listener for the loading of connection pramters from a source
     * 
     * @returN
     */
    private ActionListener createLoadActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed( ActionEvent arg0 ) {
                String[] conns = connStoreModel.getListOfKnownConnectionNames();
                String input = (String) JOptionPane.showInputDialog( connStoreView,
                                                                     "Please select the connection to load:",
                                                                     "Select connection",
                                                                     JOptionPane.INFORMATION_MESSAGE,
                                                                     IMG,
                                                                     conns,
                                                                     "..." );
                if ( input != null ) {
                    LOG.debug( "Loading connection [" + input + "]" );
                    try {
                        IConnectionDetails dtls = connStoreModel.loadConnectionParameters( input );
                        connStoreView.loadValues( dtls );
                        connManagerView.loadTypeValues( dtls );
                    }
                    catch ( MercuryConnectionStoreException mce ) {
                        JOptionPane.showMessageDialog( connStoreView,
                                                       "Failed to load named configuration [" + input + "]\n"
                                                                       + mce.getMessage(),
                                                       "Load failure",
                                                       JOptionPane.ERROR_MESSAGE );
                    }
                }
                else {
                    LOG.debug( "Load action canceled" );
                }
            }
        };
    }

    /**
     * Creates a new action listener for the saving of a set of connection parameters
     * 
     * @return the action listener
     */
    private ActionListener createSaveActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed( ActionEvent arg0 ) {
                // first we should make sure that the conn dedtails
                // have been saved correctly
                String input = (String) JOptionPane.showInputDialog( connStoreView,
                                                                     "Please enter a name for this conection",
                                                                     "Save connection",
                                                                     JOptionPane.INFORMATION_MESSAGE,
                                                                     IMG,
                                                                     null,
                                                                     "..." );

                if ( input != null ) {
                    LOG.debug( "Saving connection as [" + input + "]" );

                    if ( connStoreModel.doesConnectionExist( input ) ) {
                        int sure = JOptionPane.showConfirmDialog( connStoreView,
                                                                  "This will overwrite the exitsing connection\ncontinue?",
                                                                  "Overwrite existing",
                                                                  JOptionPane.YES_NO_CANCEL_OPTION );
                        if ( sure != JOptionPane.OK_OPTION ) {
                            return;
                        }
                    }

                    try {
                        IConnectionDetails dtls = connStoreView.getConnectionDetails();
                        connManagerView.populateConnectionDetails( dtls );
                        connStoreModel.saveConnectionParameters( input, dtls );
                    }
                    catch ( MercuryConnectionStoreException mce ) {
                        JOptionPane.showMessageDialog( connStoreView,
                                                       "Failed to save named configuration [" + input + "]\n"
                                                                       + mce.getMessage(),
                                                       "Internal save failure",
                                                       JOptionPane.ERROR_MESSAGE );
                    }
                    catch ( Exception e ) {
                        JOptionPane.showMessageDialog( connStoreView,
                                                       "Failed to save named configuration [" + input + "]\n"
                                                                       + e.getMessage(),
                                                       "Save failure",
                                                       JOptionPane.ERROR_MESSAGE );
                    }
                }
                else {
                    LOG.debug( "Save action cancelled" );
                }
            }
        };
    }

    /**
     * Returns the value of ConnectionManager.
     * 
     * @return Returns the ConnectionManager.
     */
    public IConnectionManager getConnectionManager() {
        return connManagerModel;
    }

    /**
     * Returns the value of ConnectionStore.
     * 
     * @return Returns the ConnectionStore.
     */
    public IConnectionStore getConnectionStore() {
        return connStoreModel;
    }

    /**
     * Returns the value of ButtonsView.
     * 
     * @return Returns the ButtonsView.
     */
    public ConnectionButtons getButtonsView() {
        return buttonsView;
    }

    /**
     * Returns the value of ConnManagerView.
     * 
     * @return Returns the ConnManagerView.
     */
    public ConnectionManagerPanel getConnManagerView() {
        return connManagerView;
    }

    /**
     * Returns the value of ConnStoreView.
     * 
     * @return Returns the ConnStoreView.
     */
    public ConnectionStorePanel getConnStoreView() {
        return connStoreView;
    }

}
