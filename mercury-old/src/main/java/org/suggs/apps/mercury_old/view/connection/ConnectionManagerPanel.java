/*
 * JmsConnectionManagerPanel.java created on 22 Jun 2007 07:47:15 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old.view.connection;

import org.suggs.apps.mercury_old.model.connection.IConnectionDetails;
import org.suggs.apps.mercury_old.model.connection.manager.ConnectionManager;
import org.suggs.apps.mercury_old.support.AbstractGridbagPanel;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class will manage the user interaction with the Connection manager object.
 * 
 * @author suggitpe
 * @version 1.0 22 Jun 2007
 */
public class ConnectionManagerPanel extends AbstractGridbagPanel implements Observer {

    private static final long serialVersionUID = 4835953967437214080L;
    private static final Logger LOG = LoggerFactory.getLogger( ConnectionManagerPanel.class );
    private ConnectionManager connMgr;
    private final JTextField status = new JTextField();
    private final JComboBox type = new JComboBox();
    private final JComboBox connectionFactories = new JComboBox();
    private final JComboBox destinations = new JComboBox();
    private Map<String, Set<String>> availableConnFacts;
    private Map<String, Set<String>> availableDests;

    /**
     * Constructs a new instance.
     * 
     * @param aConnectionManager
     *            The connection manager that we will be observing
     */
    public ConnectionManagerPanel( ConnectionManager aConnectionManager ) {
        super( "Connection Manager" );
        connMgr = aConnectionManager;
        connMgr.addObserver( this );
    }

    /**
     * Called through the controller to initialise the panel
     * 
     * @param aInitialStatus
     *            the initial status for the panel to display
     */
    public void initialise( String aInitialStatus ) {
        int i = 1;
        EmptyBorder eb = new EmptyBorder( 0, 0, 0, 10 );

        // status field
        final JLabel lStatus = new JLabel( "Connection Status:" );
        lStatus.setBorder( eb );
        addComponent( lStatus, i, 1 );

        status.setText( aInitialStatus );
        status.setEditable( false );
        status.setPreferredSize( LONG_FIELD );
        addFilledComponent( status, i, 2, 3, 1, GridBagConstraints.HORIZONTAL );

        // connection type
        final JLabel lType = new JLabel( "Conn Type" );
        addFilledComponent( lType, ++i, 1 );
        type.setPreferredSize( MEDIUM_FIELD );
        addComponent( type, i, 2 );

        // connection factories
        final JLabel lConnFact = new JLabel( "Conn Factories:" );
        addFilledComponent( lConnFact, ++i, 1 );
        connectionFactories.setPreferredSize( LONG_FIELD );
        addComponent( connectionFactories, i, 2 );

        // destinations
        final JLabel lDestinations = new JLabel( "Destinations:" );
        addFilledComponent( lDestinations, ++i, 1 );
        destinations.setPreferredSize( LONG_FIELD );
        addComponent( destinations, i, 2 );

        type.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed( ActionEvent e ) {
                String choice = (String) ( (JComboBox) e.getSource() ).getSelectedItem();
                LOG.debug( "Connection type =[" + choice + "]" );
                loadConnectionValues( choice );
            }
        } );
    }

    /**
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update( Observable aObserved, Object arg1 ) {
        status.setText( connMgr.getConnectionState().name() );
    }

    /**
     * Populate the combo boxes with the relevant data
     * 
     * @param aDetails
     *            the details from which you can derive the required data
     */
    public void loadTypeValues( IConnectionDetails aDetails ) {
        availableConnFacts = aDetails.getConnectionFactories();
        availableDests = aDetails.getDestinations();
        Set<String> s = availableConnFacts.keySet();
        updateType( s.toArray( new String[s.size()] ) );
    }

    /**
     * Loads the connection data into the comboboxes
     * 
     * @param aType
     *            the connection type
     */
    public void loadConnectionValues( String aType ) {
        if ( aType == null || aType.length() < 1 ) {
            connectionFactories.removeAllItems();
            connectionFactories.setEditable( false );
            destinations.removeAllItems();
            destinations.setEditable( false );
        }
        else {
            Set<String> conns = availableConnFacts.get( aType.toLowerCase() );
            updateConnectionFactories( conns.toArray( new String[conns.size()] ) );
            Set<String> dests = availableDests.get( aType.toLowerCase() );
            if ( dests != null && dests.size() > 0 ) {
                updateDestinations( dests.toArray( new String[dests.size()] ) );
            }
        }
    }

    /**
     * Populate the connection details object with the rest of the connection parameters
     * 
     * @param aDtls
     *            the details to populate
     */
    public void populateConnectionDetails( IConnectionDetails aDtls ) {
        aDtls.setConnectionFactories( availableConnFacts );
        aDtls.setDestinations( availableDests );
    }

    /**
     * Populate the connection type combo box
     * 
     * @param aItems
     *            the values to add to the combo box
     */
    public void updateType( String[] aItems ) {
        type.removeAllItems();
        type.addItem( null );
        for ( String item : aItems ) {
            type.addItem( item.toUpperCase() );
        }
        type.setEditable( true );
    }

    /**
     * Populate the connection factory combo box with values
     * 
     * @param aItems
     *            the values to add to the combo box
     */
    public void updateConnectionFactories( String[] aItems ) {
        connectionFactories.removeAllItems();
        for ( String item : aItems ) {
            connectionFactories.addItem( item );
        }
        connectionFactories.setEditable( true );
    }

    /**
     * Populate the destinations combo box
     * 
     * @param aItems
     *            the items to add to the destinations combo box
     */
    public void updateDestinations( String[] aItems ) {
        destinations.removeAllItems();
        for ( String item : aItems ) {
            destinations.addItem( item );
        }
        destinations.setEditable( true );
    }

    // ============== GETTERS AND SETTERS ============
    /**
     * Getter for the connection manager
     * 
     * @return the connection manager
     */
    public ConnectionManager getConnectionManager() {
        return connMgr;
    }

    /**
     * Setter for the connection manager
     * 
     * @param aConnMgr
     *            the connection manager to set
     */
    public void setConnectionManager( ConnectionManager aConnMgr ) {
        connMgr = aConnMgr;
    }

}
