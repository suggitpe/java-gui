/*
 * TemperatureGUI.java created on 9 Jul 2007 07:57:25 by suggitpe for project SandBox - GUI
 * 
 */
package org.suggs.sandbox.mvc.temperatureguage.view;

import org.suggs.sandbox.mvc.temperatureguage.model.TemperatureModel;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class for the temperature guage demol
 * 
 * @author suggitpe
 * @version 1.0 9 Jul 2007
 */
public abstract class AbstractTemperatureGUI implements Observer {

    private static final Logger LOG = LoggerFactory.getLogger( AbstractTemperatureGUI.class );

    private String label;
    private TemperatureModel model;
    private JFrame tempFrame;
    private JTextField display = new JTextField();
    private JButton upButton = new JButton( "Up" );
    private JButton downButton = new JButton( "Down" );

    /**
     * Constructs a new instance.
     * 
     * @param aLabel
     *            the gui label
     * @param aModel
     *            the model to observe
     * @param h
     * @param v
     */
    public AbstractTemperatureGUI( String aLabel, TemperatureModel aModel, int h, int v ) {
        label = aLabel;
        model = aModel;
        tempFrame = new JFrame( label );
        tempFrame.add( "North", new JLabel( label ) );
        tempFrame.add( "Center", display );
        JPanel buttons = new JPanel();
        buttons.add( upButton );
        buttons.add( downButton );
        tempFrame.add( "South", buttons );
        tempFrame.addWindowListener( new CloseListener() );
        model.addObserver( this );
        tempFrame.setSize( 200, 100 );
        tempFrame.setLocation( h, v );
        tempFrame.setVisible( true );
    }

    /**
     * Setter for the text box display
     * 
     * @param s
     *            the string to display
     */
    public void setDisplay( String s ) {
        display.setText( s );
    }

    /**
     * Getter for the display
     * 
     * @return the display converted to a double
     */
    public double getDisplay() {
        double ret = 0.0;
        try {
            ret = Double.valueOf( display.getText() ).doubleValue();
        }
        catch ( NumberFormatException nfe ) {
            LOG.warn( "Failed to convert text [" + display.getText() + "] to a number (double)" );
        }

        return ret;
    }

    /**
     * Add a listsner to the display box
     * 
     * @param a
     *            the listsner to add
     */
    public void addDisplayListener( ActionListener a ) {
        display.addActionListener( a );
    }

    /**
     * Add a listener to the up button
     * 
     * @param a
     *            the action listener to add
     */
    public void addUpListener( ActionListener a ) {
        upButton.addActionListener( a );
    }

    /**
     * Add a listener to the down button
     * 
     * @param a
     *            the action listener to add
     */
    public void addDownListsner( ActionListener a ) {
        downButton.addActionListener( a );
    }

    /**
     * Getter for the model
     * 
     * @return the model
     */
    protected TemperatureModel getModel() {
        return model;
    }

    /**
     * Inner class to manage the closing of the current window
     * 
     * @author suggitpe
     * @version 1.0 9 Jul 2007
     */
    public static class CloseListener extends WindowAdapter {

        /**
         * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
         */
        @Override
        public void windowClosing( WindowEvent e ) {
            e.getWindow().setVisible( false );
            System.exit( 0 );

        }
    }
}
