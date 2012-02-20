/*
 * Ch3Composite.java created on 12 Aug 2008 18:32:29 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch3_composites;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * Composite class to encapsulate the other classes in the chapter.
 * 
 * @author suggitpe
 * @version 1.0 12 Aug 2008
 */
public class Ch3Composite extends Composite {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composit to associate this class with
     */
    public Ch3Composite( Composite parent ) {
        super( parent, SWT.NONE );
        parent.getShell().setText( "Chapter 3 Composite" );

        Ch3Group g = new Ch3Group( this );
        g.setLocation( 0, 0 );
        g.pack();

        Ch3SashForm s = new Ch3SashForm( this );
        s.setLocation( 125, 25 );
        s.pack();

        pack();
    }

}
