/*
 * Ch5Capitaliser.java created on 18 Aug 2008 07:06:29 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch5_widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * Class to show how we can use keyboard events to alter a text widget.
 * 
 * @author suggitpe
 * @version 1.0 18 Aug 2008
 */
public class Ch5Capitaliser extends Composite {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composit to associate this class with
     */
    public Ch5Capitaliser( Composite parent ) {
        super( parent, SWT.NONE );
        buildControls();
    }

    /**
     * Pvt method to set up the composite
     */
    private void buildControls() {
        this.setLayout( new FillLayout() );
        // create a multi pline text box with a vertical scroll bar
        Text txt = new Text( this, SWT.MULTI | SWT.V_SCROLL );
        txt.addVerifyListener( new VerifyListener() {

            @Override
            public void verifyText( VerifyEvent e ) {
                // just for shits and giggles we reject all entries
                // with '1'
                if ( e.text.startsWith( "1" ) ) {
                    e.doit = false;
                }
                else {
                    e.text = e.text.toUpperCase();
                }

            }
        } );
    }

}
