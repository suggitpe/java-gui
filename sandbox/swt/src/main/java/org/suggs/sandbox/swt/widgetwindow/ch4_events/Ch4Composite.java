/*
 * Ch4Composite.java created on 18 Aug 2008 06:53:30 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch4_events;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * A composite class that shows mouse events in motion plus provides access to a GUI full of event listeners.
 * 
 * @author suggitpe
 * @version 1.0 18 Aug 2008
 */
public class Ch4Composite extends Ch4MouseKey {

    public Ch4Composite( Composite parent ) {
        super( parent );
        Button launch = new Button( this, SWT.PUSH );
        launch.setText( "Launch" );
        launch.setLocation( 40, 120 );
        launch.pack();

        launch.addMouseListener( new MouseAdapter() {

            @Override
            public void mouseDown( MouseEvent e ) {
                Ch4Contribution sw = new Ch4Contribution();
                sw.open();
            }
        } );
    }
}
