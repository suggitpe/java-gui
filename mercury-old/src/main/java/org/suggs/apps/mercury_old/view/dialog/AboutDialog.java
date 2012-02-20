/*
 * AboutDialog.java created on 27 Jun 2007 06:00:05 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old.view.dialog;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * A class to represent the the about dialog box
 * 
 * @author suggitpe
 * @version 1.0 27 Jun 2007
 */
public class AboutDialog extends JDialog {

    private static final long serialVersionUID = -1232620478444335142L;

    /**
     * Hidden Constructs a new instance.
     */
    @SuppressWarnings("unused")
    private AboutDialog() {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aOwner
     *            the owning frame
     */
    public AboutDialog( JFrame aOwner ) {
        super( aOwner, "About", true );
        Border b1 = new BevelBorder( BevelBorder.LOWERED );
        Border b2 = new EmptyBorder( 5, 5, 5, 5 );

        // create icon panel
        JPanel panel = new JPanel();
        JLabel icon = new JLabel( new ImageIcon( "icon.gif" ) );
        icon.setBorder( new CompoundBorder( b1, b2 ) );
        panel.add( icon );
        getContentPane().add( panel, BorderLayout.WEST );

        // now create the textual details
        panel = new JPanel();
        String message = "JMS helper application\n" + "(c) Peter GD Suggit 2007";
        JTextArea txt = new JTextArea( message );
        txt.setBorder( new EmptyBorder( 5, 10, 5, 10 ) );
        txt.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
        txt.setEditable( false );
        txt.setBackground( getBackground() );
        panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) );
        panel.add( txt );

        // add in the jvm for good measure
        message = new StringBuffer( "JVM Version " ).append( System.getProperty( "java.version" ) )
            .append( "\n by " )
            .append( System.getProperty( "java.vendor" ) )
            .toString();
        txt = new JTextArea( message );
        txt.setBorder( new EmptyBorder( 5, 10, 5, 10 ) );
        txt.setFont( new Font( "Arial", Font.PLAIN, 12 ) );
        txt.setEditable( false );
        txt.setBackground( getBackground() );
        txt.setLineWrap( true );
        txt.setWrapStyleWord( true );
        panel.add( txt );

        // add the text data to the main content pane
        getContentPane().add( panel, BorderLayout.CENTER );

        // now create and add an OK button
        final JButton ok = new JButton( "OK" );
        ActionListener l = new ActionListener() {

            @Override
            public void actionPerformed( ActionEvent arg0 ) {
                dispose();
            }
        };

        ok.addActionListener( l );
        panel = new JPanel();
        panel.add( ok );
        getRootPane().setDefaultButton( ok );
        getRootPane().registerKeyboardAction( l,
                                              KeyStroke.getKeyStroke( KeyEvent.VK_ENTER, 0 ),
                                              JComponent.WHEN_IN_FOCUSED_WINDOW );
        getContentPane().add( panel, BorderLayout.SOUTH );

        addWindowListener( new WindowAdapter() {

            @Override
            public void windowOpened( WindowEvent e ) {
                ok.requestFocus();
            }
        } );

        pack();
        setResizable( false );
        setLocationRelativeTo( aOwner );
    }
}
