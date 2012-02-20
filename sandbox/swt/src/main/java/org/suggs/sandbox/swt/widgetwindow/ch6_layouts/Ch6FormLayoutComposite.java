/*
 * Ch6FormLayoutComposite.java created on 21 Aug 2008 07:00:47 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch6_layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * Really simple example of how the form layout works in a composite.
 * 
 * @author suggitpe
 * @version 1.0 21 Aug 2008
 */
public class Ch6FormLayoutComposite extends Composite {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composit to associate this class with
     */
    public Ch6FormLayoutComposite( Composite parent ) {
        super( parent, SWT.NONE );
        parent.getShell().setText( "Form Layout example" );
        setLayout( new FormLayout() );

        // set up the text area and position on the form
        Text t = new Text( this, SWT.MULTI );
        FormData d = new FormData();
        // start at upper left of the form
        d.top = new FormAttachment( 0, 0 );
        d.left = new FormAttachment( 0, 0 );
        // span 100% of the form
        d.right = new FormAttachment( 100 );
        // drop 75% of the form
        d.bottom = new FormAttachment( 75 );
        t.setLayoutData( d );

        // create two new buttons
        Button ok = new Button( this, SWT.PUSH );
        ok.setText( "OK" );
        Button canc = new Button( this, SWT.PUSH );
        canc.setText( "Cancel" );

        // set the position of OK to be under the text area and to the
        // left of the canc button
        d = new FormData();
        d.top = new FormAttachment( t );
        d.right = new FormAttachment( canc );
        ok.setLayoutData( d );

        // set the position of the canc button to be under the text
        // area and to the right of the composite
        d = new FormData();
        d.top = new FormAttachment( t );
        d.right = new FormAttachment( 100 );
        canc.setLayoutData( d );

    }

}
