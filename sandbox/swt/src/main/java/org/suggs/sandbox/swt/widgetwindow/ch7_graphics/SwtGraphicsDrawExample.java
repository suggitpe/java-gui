/*
 * SwtGraphicsDrawExample.java created on 22 Aug 2008 18:11:57 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch7_graphics;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Simple main method to show how to build and use a canvas object.
 * 
 * @author suggitpe
 * @version 1.0 22 Aug 2008
 */
public final class SwtGraphicsDrawExample {

    private SwtGraphicsDrawExample() {}

    /**
     * @param args
     */
    public static void main( String[] args ) {
        Display d = new Display();
        Shell s = new Shell( d );
        s.setText( "Drawing Example" );

        Canvas c = new Canvas( s, SWT.NONE );
        c.setSize( 150, 150 );
        c.setLocation( 20, 20 );

        s.setSize( 200, 220 );
        s.open();

        GC gc = new GC( c );
        gc.drawRectangle( 10, 10, 40, 45 );
        gc.drawOval( 65, 10, 10, 35 );
        gc.drawLine( 130, 10, 90, 80 );
        gc.drawPolygon( new int[] { 20, 70, 45, 90, 70, 70 } );
        gc.drawPolyline( new int[] { 10, 120, 70, 100, 100, 130, 130, 75 } );
        gc.dispose();

        while ( !s.isDisposed() ) {
            if ( !d.readAndDispatch() ) {
                d.sleep();
            }
        }
        d.dispose();

    }
}
