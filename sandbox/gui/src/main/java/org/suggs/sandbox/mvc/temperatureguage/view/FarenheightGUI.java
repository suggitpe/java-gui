/*
 * FarenheightGUI.java created on 9 Jul 2007 08:30:32 by suggitpe for project SandBox - GUI
 * 
 */
package org.suggs.sandbox.mvc.temperatureguage.view;

import org.suggs.sandbox.mvc.temperatureguage.model.TemperatureModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Simple GUI for the temperature GUI
 * 
 * @author suggitpe
 * @version 1.0 9 Jul 2007
 */
public class FarenheightGUI extends AbstractTemperatureGUI {

    /**
     * Constructs a new instance.
     * 
     * @param aModel
     * @param h
     * @param v
     */
    public FarenheightGUI( TemperatureModel aModel, int h, int v ) {
        super( "Farenheight Temperature", aModel, h, v );
        setDisplay( "" + getModel().getF() );
        addUpListener( new UpListener() );
        addDownListsner( new DownListener() );
        addDisplayListener( new DisplayListener() );
    }

    /**
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update( Observable arg0, Object arg1 ) {
        setDisplay( "" + getModel().getF() );
    }

    /**
     * up listener class
     * 
     * @author suggitpe
     * @version 1.0 9 Jul 2007
     */
    class UpListener implements ActionListener {

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        @Override
        public void actionPerformed( ActionEvent arg0 ) {
            getModel().setF( getModel().getF() + 1.0 );
        }
    }

    /**
     * Down listener class
     * 
     * @author suggitpe
     * @version 1.0 9 Jul 2007
     */
    class DownListener implements ActionListener {

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        @Override
        public void actionPerformed( ActionEvent arg0 ) {
            getModel().setF( getModel().getF() - 1.0 );
        }
    }

    /**
     * Display listener class
     * 
     * @author suggitpe
     * @version 1.0 9 Jul 2007
     */
    class DisplayListener implements ActionListener {

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        @Override
        public void actionPerformed( ActionEvent arg0 ) {
            double val = getDisplay();
            getModel().setF( val );
        }
    }
}
