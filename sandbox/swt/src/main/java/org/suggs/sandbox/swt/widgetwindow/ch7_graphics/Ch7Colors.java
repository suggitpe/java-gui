/*
 * Ch7Colors.java created on 2 Sep 2008 18:22:10 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch7_graphics;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * Canvas object to show how colors work with a canvas
 * 
 * @author suggitpe
 * @version 1.0 2 Sep 2008
 */
public class Ch7Colors extends Canvas {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composit to associate this class with
     */
    public Ch7Colors( Composite parent ) {
        super( parent, SWT.NONE );
        setBackground( this.getDisplay().getSystemColor( SWT.COLOR_DARK_GRAY ) );
        addPaintListener( dl );
    }

    private PaintListener dl = new PaintListener() {

        /**
         * @see org.eclipse.swt.events.PaintListener#paintControl(org.eclipse.swt.events.PaintEvent)
         */
        @Override
        public void paintControl( PaintEvent pe ) {
            Display d = pe.display;
            Color light_grey = new Color( d, 0xE0, 0xE0, 0xE0 );
            GC gc = pe.gc;
            gc.setBackground( light_grey );
            gc.fillPolygon( new int[] { 20, 20, 60, 50, 100, 20 } );
            gc.fillOval( 120, 30, 50, 50 );
            // clean up
            light_grey.dispose();
        }
    };

}
