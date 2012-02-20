/*
 * JmsHelper.java created on 20 Jun 2007 18:49:45 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old.impl;

import org.suggs.apps.mercury_old.IMercuryApp;
import org.suggs.apps.mercury_old.MercuryException;
import org.suggs.apps.mercury_old.controller.impl.ConnectionController;
import org.suggs.apps.mercury_old.support.JmsHelperGuiBuilder;
import org.suggs.apps.mercury_old.view.dialog.AboutDialog;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class manages the construction of the main GUI itself along with the placement of teh main panels
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2007
 */
public class MercuryApplication implements IMercuryApp {

    private static final Logger LOG = LoggerFactory.getLogger( MercuryApplication.class );

    private JPanel connectionStorePanel;
    private JPanel connectionManagerPanel;
    private JPanel connectionButtons;
    private ConnectionController connectionController;

    /**
     * Constructs a new instance.
     */
    public MercuryApplication( ConnectionController aConnectionController, JPanel aConnectionStorePanel,
                               JPanel aConnectionManagerPanel, JPanel aConnectionButtons ) {
        super();
        connectionController = aConnectionController;
        connectionStorePanel = aConnectionStorePanel;
        connectionManagerPanel = aConnectionManagerPanel;
        connectionButtons = aConnectionButtons;
    }

    /**
     * @see org.suggs.apps.mercury_old.IMercuryApp#buildGui()
     */
    @Override
    public void buildGui() throws MercuryException {

        LOG.debug( "Building GUI" );
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        }
        catch ( Exception e ) {
            throw new MercuryException( "Failed to set UI look and feel", e );
        }

        JFrame.setDefaultLookAndFeelDecorated( true );

        final JFrame frame = new JFrame( "Mercury - a messaging helper" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        buildGuiPanels( frame.getContentPane() );

        buildMenuBar( frame );

        // finally show the GUI frame
        JmsHelperGuiBuilder.displayFrame( frame );

    }

    /**
     * Builds the main GUI panels
     * 
     * @param aCtnr
     *            the container to build upon
     * @throws MercuryException
     */
    private void buildGuiPanels( Container aCntr ) {
        LOG.debug( "Building GUI Panels" );

        GridBagLayout mainLayout = new GridBagLayout();
        aCntr.setLayout( mainLayout );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // add in the connection manager buttons
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        mainLayout.setConstraints( connectionButtons, gbc );
        aCntr.add( connectionButtons );

        // add in the connection store panel
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        mainLayout.setConstraints( connectionStorePanel, gbc );
        aCntr.add( connectionStorePanel );

        // add in the connection manager store
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainLayout.setConstraints( connectionManagerPanel, gbc );
        aCntr.add( connectionManagerPanel );
    }

    /**
     * Create and add the menu bar to the GUI
     * 
     * @param aFrame
     *            the frame to add the menu to
     */
    private void buildMenuBar( final JFrame aFrame ) {
        LOG.debug( "Building menu bar" );

        final JMenuBar menu = new JMenuBar();

        // buid the exit item
        JMenuItem exit = new JMenuItem( "Exit" );
        exit.setMnemonic( 'x' );
        exit.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed( ActionEvent arg0 ) {
                System.exit( 0 );
            }
        } );

        // build the about item
        JMenuItem about = new JMenuItem( "About" );
        about.setMnemonic( 'a' );
        about.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed( ActionEvent arg0 ) {
                AboutDialog aboutDialog = new AboutDialog( aFrame );
                aboutDialog.setVisible( true );
            }
        } );

        // build the file menu
        JMenu file = new JMenu( "File" );
        file.setMnemonic( 'f' );
        file.add( exit );

        // huild the about menu
        JMenu help = new JMenu( "Help" );
        help.setMnemonic( 'h' );
        help.add( about );

        // now add the menus to the menu bar
        menu.add( file );
        menu.add( help );

        aFrame.setJMenuBar( menu );
    }

    // ============ GETTERS AND SETTERS ===========
    /**
     * Getter for the connection manager panel
     * 
     * @return the connection manager panel
     */
    public JPanel getConnectionManagerPanel() {
        return connectionManagerPanel;
    }

    /**
     * Setter for the connection manager panel
     * 
     * @param aPanel
     *            the panel to set as the connection manager panel
     */
    public void setConnectionManagerPanel( JPanel aPanel ) {
        connectionManagerPanel = aPanel;
    }

    /**
     * Getter for the connection buttons panel
     * 
     * @return the connection buttons panel
     */
    public JPanel getConnectionButtons() {
        return connectionButtons;
    }

    /**
     * Setter for the connection buttons panel
     * 
     * @param aPanel
     *            the panel to set as the connection buttons panel
     */
    public void setConnectionButtons( JPanel aPanel ) {
        connectionButtons = aPanel;
    }

    /**
     * Setter for the connection store panel
     * 
     * @param aPanel
     *            the panel to set as the connection store panel
     */
    public void setConnectionStorePanel( JPanel aPanel ) {
        connectionStorePanel = aPanel;
    }

    /**
     * Getter for the connection store panel
     * 
     * @return the connection store panel
     */
    public JPanel getConnectionStorePanel() {
        return connectionStorePanel;
    }

    /**
     * @return connection controller
     */
    public ConnectionController getConnectionConroller() {
        return connectionController;
    }

    /**
     * @param aCtrl
     */
    public void setConnectionController( ConnectionController aCtrl ) {
        connectionController = aCtrl;
    }
}
