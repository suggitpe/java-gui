/*
 * SimpleRowDataExample.java created on 8 Apr 2008 17:54:50 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.layout.rowlayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple example to show the use of the RowData object on widgets
 * 
 * @author suggitpe
 * @version 1.0 8 Apr 2008
 */
public final class SimpleRowDataExample {

    private static final Logger LOG = LoggerFactory.getLogger( SimpleRowDataExample.class );

    private SimpleRowDataExample() {}

    public static void main( String[] args ) {
        LOG.debug( "Building dialog" );
        Display d = new Display();

        Shell shell = new Shell( d );
        shell.setLayout( new RowLayout() );

        // now create some widgets
        Button butt1 = new Button( shell, SWT.PUSH );
        Button butt2 = new Button( shell, SWT.PUSH );
        Button butt3 = new Button( shell, SWT.PUSH );

        // set the text
        butt1.setText( "Button 1" );
        butt2.setText( "Button 2" );
        butt3.setText( "Button 3" );

        // set up spme specific rowdata cfdg for each one
        butt1.setLayoutData( new RowData( 50, 40 ) );
        butt2.setLayoutData( new RowData( 50, 30 ) );
        butt3.setLayoutData( new RowData( 50, 20 ) );

        shell.pack();
        shell.open();

        LOG.debug( "Opening GUI" );
        while ( !shell.isDisposed() ) {
            if ( !d.readAndDispatch() ) {
                d.sleep();
            }
        }
        LOG.debug( "All done" );
    }
}
