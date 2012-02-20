/*
 * Ch3SashForm.java created on 12 Aug 2008 18:21:39 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch3_composites;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * Composite class to show the use of the SashForm composite object.
 * 
 * @author suggitpe
 * @version 1.0 12 Aug 2008
 */
public class Ch3SashForm extends Composite {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composit to associate this class with
     */
    public Ch3SashForm( Composite parent ) {
        super( parent, SWT.NONE );

        SashForm sf = new SashForm( this, SWT.VERTICAL );
        sf.setSize( 120, 80 );

        Button b1 = new Button( sf, SWT.PUSH );
        b1.setText( "Up" );
        b1.setSize( 120, 40 );

        Button b2 = new Button( sf, SWT.PUSH );
        b2.setText( "Down" );
        b2.setBounds( 0, 40, 120, 40 );
    }

}
