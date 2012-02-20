/*
 * Ch3Group.java created on 11 Aug 2008 18:48:32 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch3_composites;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

/**
 * Demo of how to use a group composite widget.
 * 
 * @author suggitpe
 * @version 1.0 11 Aug 2008
 */
public class Ch3Group extends Composite {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     */
    public Ch3Group( Composite parent ) {
        super( parent, SWT.NONE );

        Group grp = new Group( this, SWT.SHADOW_ETCHED_IN );
        grp.setText( "Group Label" );

        Label l = new Label( grp, SWT.NONE );
        l.setText( "Two Buttons:" );
        l.setLocation( 20, 20 );
        l.pack();

        Button b1 = new Button( grp, SWT.PUSH );
        b1.setText( "Push Button" );
        b1.setLocation( 20, 45 );
        b1.pack();

        Button b2 = new Button( grp, SWT.CHECK );
        b2.setText( "Check Button:" );
        b2.setBounds( 20, 75, 90, 30 );

        grp.pack();
    }
}
