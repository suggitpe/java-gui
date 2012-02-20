/*
 * JmsConnectionStoreButtons.java created on 11 Jul 2007 18:18:35 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old.view.connection;

import org.suggs.apps.mercury_old.support.AbstractGridbagPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

/**
 * Buttons for the connection store.
 * 
 * @author suggitpe
 * @version 1.0 11 Jul 2007
 */
public class ConnectionButtons extends AbstractGridbagPanel {

    private static final long serialVersionUID = 1200607380709746562L;
    private JButton saveButton = new JButton( "Save" );
    private JButton loadButton = new JButton( "Load" );
    private JButton deleteButton = new JButton( "Delete" );
    private JButton searchButton = new JButton( "Search" );
    private JButton connectButton = new JButton( "Connect" );
    private JButton disconnectButton = new JButton( "Disconnect" );
    private JButton testButton = new JButton( "Test" );

    /**
     * Constructs a new instance.
     */
    public ConnectionButtons() {
        super( "Ctrl" );
        initButons();

        addFilledComponent( new JSeparator(), 1, 1 );
        addFilledComponent( loadButton, 2, 1 );
        addFilledComponent( saveButton, 3, 1 );
        addFilledComponent( deleteButton, 4, 1 );
        addFilledComponent( new JSeparator(), 9, 1 );

        addFilledComponent( searchButton, 10, 1 );

        addFilledComponent( new JSeparator(), 11, 1 );
        addFilledComponent( connectButton, 13, 1 );
        addFilledComponent( disconnectButton, 14, 1 );

        addFilledComponent( new JSeparator(), 30, 1 );
        addFilledComponent( testButton, 31, 1 );
        addFilledComponent( new JSeparator(), 40, 1 );
    }

    /**
     * Initialises the buttons with tool tips and a default action listener
     */
    private void initButons() {
        loadButton.setToolTipText( "Load a previously saved connection into the window" );
        saveButton.setToolTipText( "Save the current connection settings as a named connection" );
        deleteButton.setToolTipText( "Allows you to remove a named connection from the connection store" );
        searchButton.setToolTipText( "Searches for destinations and connection factories" );
        connectButton.setToolTipText( "Connect with the defined connection parameters" );
        disconnectButton.setToolTipText( "Disconnect from the current connection" );
        testButton.setToolTipText( "Test connection settings with current setting" );

        ActionListener l = createDefaultActionListener();
        saveButton.addActionListener( l );
        loadButton.addActionListener( l );
        deleteButton.addActionListener( l );
        searchButton.addActionListener( l );
        connectButton.addActionListener( l );
        disconnectButton.addActionListener( l );
        testButton.addActionListener( l );
    }

    /**
     * Creates a default action listsner so that we can feed back to the user that this has no impl.
     * 
     * @return the defauly action listener
     */
    private ActionListener createDefaultActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed( ActionEvent e ) {
                JOptionPane.showMessageDialog( ConnectionButtons.this,
                                               "This button has not been implemented",
                                               "No implementation",
                                               JOptionPane.INFORMATION_MESSAGE );
            }
        };
    }

    /**
     * Cleans all action listeners from a given JButton
     * 
     * @param aBut
     *            the button to remove the action listeners from
     */
    private void cleanListeners( JButton aBut ) {
        ActionListener[] als = aBut.getActionListeners();
        for ( ActionListener a : als ) {
            aBut.removeActionListener( a );
        }
    }

    /**
     * add an action listsnere to the delete button
     * 
     * @param al
     *            the action litsner to add to the delete button
     */
    public void addDeleteActionListener( ActionListener al ) {
        cleanListeners( deleteButton );
        deleteButton.addActionListener( al );
    }

    /**
     * Add an action listener to the save button
     * 
     * @param al
     *            the action listsner to add
     */
    public void addSaveActionListener( ActionListener al ) {
        cleanListeners( saveButton );
        saveButton.addActionListener( al );
    }

    /**
     * Add an action listener to the load button
     * 
     * @param al
     *            the action listener to add
     */
    public void addLoadActionListener( ActionListener al ) {
        cleanListeners( loadButton );
        loadButton.addActionListener( al );
    }

    /**
     * Add an action listener to the Search button
     * 
     * @param al
     *            the action listener to add
     */
    public void addSearchActionListener( ActionListener al ) {
        cleanListeners( searchButton );
        searchButton.addActionListener( al );
    }

    /**
     * Add an action listener to the test button
     * 
     * @param al
     *            the action listener to add
     */
    public void addTestActionListener( ActionListener al ) {
        cleanListeners( testButton );
        testButton.addActionListener( al );
    }

    /**
     * Add an action listener to the connect button
     * 
     * @param al
     *            the action listener to add
     */
    public void addConnectActionListener( ActionListener al ) {
        cleanListeners( connectButton );
        connectButton.addActionListener( al );
    }

    /**
     * Add an action listener to the disconnect button
     * 
     * @param al
     *            the action listener to add
     */
    public void addDisconnectActionListener( ActionListener al ) {
        cleanListeners( disconnectButton );
        disconnectButton.addActionListener( al );
    }
}
