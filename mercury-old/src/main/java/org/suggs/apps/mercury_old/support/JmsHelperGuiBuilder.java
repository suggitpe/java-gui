/*
 * JmsHelperGuiBuilder.java created on 22 Jun 2007 07:27:52 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old.support;

import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;

import org.springframework.util.Assert;

/**
 * This class is a utility class for building and managing the display of a GUI
 * 
 * @author suggitpe
 * @version 1.0 22 Jun 2007
 */
public final class JmsHelperGuiBuilder {

    /**
     * Constructs a new instance.
     */
    private JmsHelperGuiBuilder() {}

    /**
     * Static method used to delegate the management of the
     * 
     * @param aFrame
     *            the frame to packake and show
     */
    public static void displayFrame( JFrame aFrame ) {

        Assert.notNull( aFrame, "Passed in a null JFrame object" );

        aFrame.pack();
        // first center the frame
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point c = e.getCenterPoint();
        Rectangle bounds = e.getMaximumWindowBounds();

        int w = Math.max( bounds.width / 2, Math.min( aFrame.getWidth(), bounds.width ) );
        int h = Math.max( bounds.height / 2, Math.min( aFrame.getHeight(), bounds.height ) );
        int x = c.x - w / 2;
        int y = c.y - h / 2;
        aFrame.setBounds( x, y, w, h );
        if ( w == bounds.width && h == bounds.height ) {
            aFrame.setExtendedState( Frame.MAXIMIZED_BOTH );
        }

        aFrame.validate();

        // then show it
        aFrame.setVisible( true );
        aFrame.setResizable( true );
        aFrame.toFront();
    }
}
