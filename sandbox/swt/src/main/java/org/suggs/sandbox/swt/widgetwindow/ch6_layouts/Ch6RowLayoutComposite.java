/*
 * Ch6RowLayoutComposite.java created on 21 Aug 2008 07:00:47 by suggitpe for project SandBox - SWT
 *
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch6_layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * Really simple example of how the row layout works in a composite.
 * 
 * @author suggitpe
 * @version 1.0 21 Aug 2008
 */
public class Ch6RowLayoutComposite extends Composite {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composit to associate this class with
     */
    public Ch6RowLayoutComposite( Composite parent ) {
        super( parent, SWT.NONE );
        parent.getShell().setText( "Row Layout example" );
        setLayout( new RowLayout( SWT.HORIZONTAL ) );
        for ( int i = 0; i < 16; ++i ) {
            Button b = new Button( this, SWT.PUSH );
            b.setText( "Sample button" );
            b.setLayoutData( new RowData( 200 + 5 * i, 20 + i ) );
        }
    }

}
