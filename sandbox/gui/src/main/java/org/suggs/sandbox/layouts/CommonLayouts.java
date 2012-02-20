/*
 * CommonLayouts.java created on 22 Jun 2007 17:39:31 by suggitpe for project SandBox - GUI
 * 
 */
package org.suggs.sandbox.layouts;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.Spring;
import javax.swing.SpringLayout;

/**
 * Test impl
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
public class CommonLayouts extends JFrame {

    private static final long serialVersionUID = 2303587558719184461L;

    /**
     * Constructs a new instance.
     */
    public CommonLayouts() {
        super( "Common Layout Managers" );
        setSize( 500, 460 );
        JDesktopPane desktop = new JDesktopPane();
        getContentPane().add( desktop );
        JInternalFrame fr1 = new JInternalFrame( "FlowLayout", true, true );
        fr1.setBounds( 10, 10, 150, 150 );
        Container c = fr1.getContentPane();
        c.setLayout( new FlowLayout() );
        c.add( new JButton( "1" ) );
        c.add( new JButton( "2" ) );
        c.add( new JButton( "3" ) );
        c.add( new JButton( "4" ) );
        desktop.add( fr1, 0 );
        fr1.show();
        JInternalFrame fr2 = new JInternalFrame( "GridLayout", true, true );
        fr2.setBounds( 170, 10, 150, 150 );
        c = fr2.getContentPane();
        c.setLayout( new GridLayout( 2, 2 ) );
        c.add( new JButton( "1" ) );
        c.add( new JButton( "2" ) );
        c.add( new JButton( "3" ) );
        c.add( new JButton( "4" ) );
        desktop.add( fr2, 0 );
        fr2.show();
        JInternalFrame fr3 = new JInternalFrame( "BorderLayout", true, true );
        fr3.setBounds( 330, 10, 150, 150 );
        c = fr3.getContentPane();
        c.add( new JButton( "1" ), BorderLayout.NORTH );
        c.add( new JButton( "2" ), BorderLayout.EAST );
        c.add( new JButton( "3" ), BorderLayout.SOUTH );
        c.add( new JButton( "4" ), BorderLayout.WEST );
        desktop.add( fr3, 0 );
        fr3.show();
        JInternalFrame fr4 = new JInternalFrame( "BoxLayout - X", true, true );
        fr4.setBounds( 10, 170, 250, 80 );
        c = fr4.getContentPane();
        c.setLayout( new BoxLayout( c, BoxLayout.X_AXIS ) );
        c.add( new JButton( "1" ) );
        c.add( Box.createHorizontalStrut( 12 ) );
        c.add( new JButton( "2" ) );
        c.add( Box.createGlue() );
        c.add( new JButton( "3" ) );
        c.add( Box.createHorizontalGlue() );
        c.add( new JButton( "4" ) );
        desktop.add( fr4, 0 );
        fr4.show();
        JInternalFrame fr5 = new JInternalFrame( "BoxLayout - Y", true, true );
        fr5.setBounds( 330, 170, 150, 200 );
        c = fr5.getContentPane();
        c.setLayout( new BoxLayout( c, BoxLayout.Y_AXIS ) );
        c.add( new JButton( "1" ) );
        c.add( Box.createVerticalStrut( 10 ) );
        c.add( new JButton( "2" ) );
        c.add( Box.createGlue() );
        c.add( new JButton( "3" ) );
        c.add( Box.createVerticalGlue() );
        c.add( new JButton( "4" ) );
        desktop.add( fr5, 0 );
        fr5.show();
        JInternalFrame fr6 = new JInternalFrame( "SpringLayout", true, true );
        fr6.setBounds( 10, 260, 250, 170 );
        c = fr6.getContentPane();
        c.setLayout( new SpringLayout() );
        c.add( new JButton( "1" ), new SpringLayout.Constraints( Spring.constant( 10 ),
                                                                 Spring.constant( 10 ),
                                                                 Spring.constant( 120 ),
                                                                 Spring.constant( 70 ) ) );
        c.add( new JButton( "2" ), new SpringLayout.Constraints( Spring.constant( 160 ),
                                                                 Spring.constant( 10 ),
                                                                 Spring.constant( 70 ),
                                                                 Spring.constant( 30 ) ) );
        c.add( new JButton( "3" ), new SpringLayout.Constraints( Spring.constant( 160 ),
                                                                 Spring.constant( 50 ),
                                                                 Spring.constant( 70 ),
                                                                 Spring.constant( 30 ) ) );
        c.add( new JButton( "4" ), new SpringLayout.Constraints( Spring.constant( 10 ),
                                                                 Spring.constant( 90 ),
                                                                 Spring.constant( 50 ),
                                                                 Spring.constant( 40 ) ) );
        c.add( new JButton( "5" ), new SpringLayout.Constraints( Spring.constant( 120 ),
                                                                 Spring.constant( 90 ),
                                                                 Spring.constant( 50 ),
                                                                 Spring.constant( 40 ) ) );
        desktop.add( fr6, 0 );
        fr6.show();
        desktop.setSelectedFrame( fr6 );
    }

    /**
     * Main impl.
     * 
     * @param argv
     */
    public static void main( String argv[] ) {
        CommonLayouts frame = new CommonLayouts();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible( true );
    }
}
