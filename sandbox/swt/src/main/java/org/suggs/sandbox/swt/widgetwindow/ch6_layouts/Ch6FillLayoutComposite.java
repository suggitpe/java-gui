/*
 * Ch6FillLayoutComposite.java created on 21 Aug 2008 07:00:47 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch6_layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * Really simple example of how the fill layout works in a composite.
 * 
 * @author suggitpe
 * @version 1.0 21 Aug 2008
 */
public class Ch6FillLayoutComposite extends Composite {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composit to associate this class with
     */
    public Ch6FillLayoutComposite( Composite parent ) {
        super( parent, SWT.NONE );
        parent.getShell().setText( "Fill Layout example" );
        setLayout( new FillLayout( SWT.VERTICAL ) );
        for ( int i = 0; i < 16; ++i ) {
            Button b = new Button( this, SWT.PUSH );
            b.setText( "Sample button" );
        }
    }

}
