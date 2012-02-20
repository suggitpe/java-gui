/*
 * Ch7Fonts.java created on 2 Sep 2008 18:30:40 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch7_graphics;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * Canvas example to show Fonts.
 * 
 * @author suggitpe
 * @version 1.0 2 Sep 2008
 */
public class Ch7Fonts extends Canvas {

    private Shell mainShell;
    private Composite comp;
    private FontData fontData;

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composite to associate this class with
     */
    public Ch7Fonts( Composite parent ) {
        super( parent, SWT.NONE );
        parent.setSize( 600, 200 );
        addPaintListener( dl );
        comp = this;
        mainShell = parent.getShell();
        Button fontChoice = new Button( this, SWT.CENTER );
        fontChoice.setBounds( 20, 20, 100, 20 );
        fontChoice.setText( "Choose font" );
        fontChoice.addMouseListener( new MouseAdapter() {

            /**
             * @see org.eclipse.swt.events.MouseAdapter#mouseDown(org.eclipse.swt.events.MouseEvent)
             */
            @Override
            public void mouseDown( MouseEvent me ) {
                FontDialog fd = new FontDialog( mainShell );
                fontData = fd.open();
                comp.redraw();
            }

        } );

    }

    PaintListener dl = new PaintListener() {

        /**
         * @see org.eclipse.swt.events.PaintListener#paintControl(org.eclipse.swt.events.PaintEvent)
         */
        @Override
        public void paintControl( PaintEvent pe ) {
            Display d = pe.display;
            GC gc = pe.gc;
            gc.setBackground( d.getSystemColor( SWT.COLOR_DARK_GRAY ) );
            if ( fontData != null ) {
                Font f = new Font( d, fontData );
                gc.setFont( f );
                FontMetrics fm = gc.getFontMetrics();

                gc.drawText( "The average character width for this font is " + fm.getAverageCharWidth()
                             + " pixels", 20, 60 );
                gc.drawText( "The ascent for this font is " + fm.getAscent() + " pixels", 20, 100, true );
                gc.drawText( "The descent for this font is " + fm.getDescent() + " pixels",
                             20,
                             140,
                             SWT.DRAW_MNEMONIC | SWT.DRAW_TRANSPARENT );
                f.dispose();
            }
        }
    };
}
