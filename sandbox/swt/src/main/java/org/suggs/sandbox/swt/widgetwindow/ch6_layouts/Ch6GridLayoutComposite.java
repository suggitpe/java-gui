/*
 * Ch6GridLayoutComposite.java created on 21 Aug 2008 07:00:47 by suggitpe for project SandBox - SWT
 *
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch6_layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * Really simple example of how the grid layout works in a composite.
 * 
 * @author suggitpe
 * @version 1.0 21 Aug 2008
 */
public class Ch6GridLayoutComposite extends Composite {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composit to associate this class with
     */
    public Ch6GridLayoutComposite( Composite parent ) {
        super( parent, SWT.NONE );
        parent.getShell().setText( "Grid Layout example" );
        // use 4 columns and use as little space as possible (make
        // columns equal width)
        setLayout( new GridLayout( 4, false ) );
        for ( int i = 0; i < 16; ++i ) {
            Button b = new Button( this, SWT.PUSH );
            b.setText( "Cell " + ( i + 1 ) );
        }
    }

}
