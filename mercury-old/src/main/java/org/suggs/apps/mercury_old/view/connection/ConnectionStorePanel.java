/*
 * JmsConnectionStorePanel.java created on 9 Jul 2007 17:32:45 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old.view.connection;

import org.suggs.apps.mercury_old.model.connection.EConnectionType;
import org.suggs.apps.mercury_old.model.connection.IConnectionDetails;
import org.suggs.apps.mercury_old.model.connection.IConnectionStore;
import org.suggs.apps.mercury_old.model.connection.MercuryConnectionStoreException;
import org.suggs.apps.mercury_old.model.connection.store.ConnectionDetails;
import org.suggs.apps.mercury_old.model.connection.store.ConnectionStore;
import org.suggs.apps.mercury_old.support.AbstractGridbagPanel;

import java.awt.GridBagConstraints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Panel to represent the connection store.
 * 
 * @author suggitpe
 * @version 1.0 9 Jul 2007
 */
public class ConnectionStorePanel extends AbstractGridbagPanel implements Observer {

    private static final long serialVersionUID = -8438244491631849283L;
    private static final Logger LOG = LoggerFactory.getLogger( ConnectionStorePanel.class );
    private ConnectionStore connectionStore;
    private final JTextField statusText = new JTextField();
    private final JTextField nameText = new JTextField();
    private final JComboBox typeCombo = new JComboBox();
    private final JTextField serverText = new JTextField();
    private final JTextField portText = new JTextField();
    private final JTable metaDataTable = new JTable();
    private final JScrollPane metaDataPane = new JScrollPane( metaDataTable );

    /**
     * Constructs a new instance.
     * 
     * @param aConnectionStore
     *            the connection store that this class will be observing
     */
    public ConnectionStorePanel( ConnectionStore aConnectionStore ) {
        super( "Connection Store" );

        // set up the observable
        connectionStore = aConnectionStore;
        connectionStore.addObserver( this );
    }

    /**
     * Initialise the panel
     * 
     * @param aInitialStatus
     *            the initial status of the row
     */
    public void initialise( String aInitialStatus ) {
        int row = 1;
        EmptyBorder eb = new EmptyBorder( 0, 0, 0, 10 );

        // add in the status label
        final JLabel lStatus = new JLabel( "Save Status:" );
        lStatus.setBorder( eb );
        addComponent( lStatus, row, 1 );

        statusText.setText( aInitialStatus );
        statusText.setEditable( false );
        statusText.setPreferredSize( MEDIUM_FIELD );
        addFilledComponent( statusText, row++, 2, 3, 1, GridBagConstraints.HORIZONTAL );

        // add in the connection name
        final JLabel lName = new JLabel( "Name:" );
        lName.setBorder( eb );

        addComponent( lName, row, 1 );

        nameText.setEditable( false );
        nameText.setPreferredSize( LONG_FIELD );
        addFilledComponent( nameText, row++, 2, 3, 1, GridBagConstraints.HORIZONTAL );

        // add in the connection type
        final JLabel lType = new JLabel( "Type:" );
        lType.setBorder( eb );
        addComponent( lType, row, 1 );

        typeCombo.setEditable( true );
        typeCombo.addItem( null );
        typeCombo.addItem( EConnectionType.EMS );
        typeCombo.addItem( EConnectionType.MQ );
        typeCombo.setPreferredSize( MEDIUM_FIELD );
        addComponent( typeCombo, row++, 2 );

        // add in the server
        final JLabel lServer = new JLabel( "Server:" );
        lServer.setBorder( eb );
        addComponent( lServer, row, 1 );

        serverText.setPreferredSize( LONG_FIELD );
        addFilledComponent( serverText, row++, 2, 3, 1, GridBagConstraints.HORIZONTAL );

        // add in the port
        final JLabel lPort = new JLabel( "Port:" );
        lServer.setBorder( eb );
        addComponent( lPort, row, 1 );

        portText.setPreferredSize( MEDIUM_FIELD );
        addFilledComponent( portText, row++, 2, 1, 1, GridBagConstraints.HORIZONTAL );

        final JLabel lMetaData = new JLabel( "MetaData:" );
        lMetaData.setBorder( eb );
        addComponent( lMetaData, row, 1 );

        buildMetaDataTable();
        addFilledComponent( metaDataPane, row++, 2, 1, 1, GridBagConstraints.HORIZONTAL );
    }

    /**
     * Abstracted out the creation of the metadata table away from the main gui initialisation
     */
    private void buildMetaDataTable() {
        // set up the scroll pane
        metaDataPane.doLayout();

        // now set up the table
        metaDataTable.setColumnSelectionAllowed( true );
        metaDataTable.setCellSelectionEnabled( true );

        // name column
        TableColumn name = new TableColumn();
        name.setHeaderValue( "Name" );

        // value column
        TableColumn value = new TableColumn();
        value.setHeaderValue( "Value" );

        metaDataTable.addColumn( name );
        metaDataTable.addColumn( value );
    }

    /**
     * Creates a new JMS connection details object
     * 
     * @return a new JMS connection details object
     * @throws MercuryConnectionStoreException
     *             if the data collected is not enough for a connection
     */
    public IConnectionDetails getConnectionDetails() throws MercuryConnectionStoreException {
        if ( serverText.getText().length() == 0 || portText.getText().length() == 0
             || typeCombo.getSelectedItem() == null ) {
            throw new MercuryConnectionStoreException( "Invalid data in the connection store fields" );
        }

        return new ConnectionDetails( nameText.getText(),
                                      (EConnectionType) typeCombo.getSelectedItem(),
                                      serverText.getText(),
                                      portText.getText() );
    }

    /**
     * LOad the details into the panel (populates the text fields)
     * 
     * @param aDtls
     *            the jms connection detaiols object from which we can populate the panel components
     */
    public void loadValues( IConnectionDetails aDtls ) {
        nameText.setText( aDtls.getName() );
        serverText.setText( aDtls.getHostname() );
        portText.setText( aDtls.getPort() );
        typeCombo.setSelectedItem( aDtls.getType() );
    }

    /**
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update( Observable aObserved, Object aObj ) {
        LOG.info( "Observable has changed [" + aObserved.getClass().getName() + "]" );
        if ( aObserved instanceof IConnectionStore ) {
            statusText.setText( ( (IConnectionStore) aObserved ).getState() );
        }
    }

    /**
     * Getter for the connection store
     * 
     * @return the connection store
     */
    public ConnectionStore getConnectionStore() {
        return connectionStore;
    }

    /**
     * Setter for the connection store
     * 
     * @param aConnStr
     *            the connection store to set
     */
    public void setConnectionStore( ConnectionStore aConnStr ) {
        connectionStore = aConnStr;
    }
}
