/*
 * Ch6RadialLayoutComposite.java created on 22 Aug 2008 17:53:11 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch6_layouts;

import org.suggs.sandbox.swt.widgetwindow.RadialLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * A composite that uses the radial layout.
 * 
 * @author suggitpe
 * @version 1.0 22 Aug 2008
 */
public class Ch6RadialLayoutComposite extends Composite {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composit to associate this class with
     */
    public Ch6RadialLayoutComposite( Composite parent ) {
        super( parent, SWT.NONE );
        setLayout( new RadialLayout() );
        for ( int i = 0; i < 8; ++i ) {
            Button b = new Button( this, SWT.PUSH );
            b.setText( "Cell " + ( i + 1 ) );
        }
    }

}
