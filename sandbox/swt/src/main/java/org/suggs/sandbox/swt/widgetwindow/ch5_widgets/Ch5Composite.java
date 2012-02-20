/*
 * Ch5Composite.java created on 19 Aug 2008 18:41:24 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch5_widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Composite class to wrap up the other composites used
 * 
 * @author suggitpe
 * @version 1.0 19 Aug 2008
 */
public class Ch5Composite extends Composite {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composit to associate this class with
     */
    public Ch5Composite( Composite parent ) {
        super( parent, SWT.NONE );
        parent.getShell().setText( "Chapter 5 Composite" );

        setLayout( new GridLayout( 4, false ) );

        new Label( this, SWT.NONE ).setText( "Capitaliser: " );
        Ch5Capitaliser a = new Ch5Capitaliser( this );
        a.pack();

        new Label( this, SWT.NONE ).setText( "Undoable: " );
        Ch5Undoable b = new Ch5Undoable( this );
        b.pack();

        new Label( this, SWT.NONE ).setText( "Combo box: " );
        Ch5ComboComposite c = new Ch5ComboComposite( this );
        GridData d = new GridData();
        d.horizontalSpan = 3;
        c.setLayoutData( d );
        c.pack();

        new Label( this, SWT.NONE ).setText( "Slider:" );
        Ch5Slider e = new Ch5Slider( this );
        d = new GridData();
        d.horizontalSpan = 3;
        e.setLayoutData( d );
        e.pack();

        pack();
    }
}
