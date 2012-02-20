/*
 * Ch7Composite.java created on 2 Sep 2008 18:48:40 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch7_graphics;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * Composite class to encapsulate the canvas composites
 * 
 * @author suggitpe
 * @version 1.0 2 Sep 2008
 */
public class Ch7Composite extends Canvas {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composit to associate this class with
     */
    public Ch7Composite( Composite parent ) {
        super( parent, SWT.BORDER );

        Ch7Colors drawing = new Ch7Colors( this );
        drawing.setBounds( 20, 20, 200, 100 );

        Ch7Fonts f = new Ch7Fonts( this );
        f.setBounds( 0, 150, 500, 200 );

        addPaintListener( new PaintListener() {

            /**
             * @see org.eclipse.swt.events.PaintListener#paintControl(org.eclipse.swt.events.PaintEvent)
             */
            @Override
            public void paintControl( PaintEvent pe ) {
                Display d = pe.display;
                GC gc = pe.gc;
                InputStream is = getClass().getClassLoader().getResourceAsStream( "FlagGIF.gif" );
                if ( is == null ) {
                    throw new IllegalStateException( "No resource found" );
                }
                Image img = new Image( d, is );
                gc.drawImage( img, 255, 10 );
                img.dispose();
            }
        } );
    }
}
