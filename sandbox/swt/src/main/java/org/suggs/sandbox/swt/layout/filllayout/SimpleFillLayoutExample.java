/*
 * LayoutExample.java created on 8 Apr 2008 06:45:42 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.layout.filllayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example is a very simple implementation of the FillLayout
 * 
 * @author suggitpe
 * @version 1.0 8 Apr 2008
 */
public final class SimpleFillLayoutExample {

    private static final Logger LOG = LoggerFactory.getLogger( SimpleFillLayoutExample.class );

    private SimpleFillLayoutExample() {}

    /**
     * @param args
     */
    public static void main( String[] args ) {
        LOG.debug( "Building dialog" );
        Display d = new Display();
        Shell shell = new Shell( d );

        // create the layout. Note that you can use the SWT.VERTICLE
        // and SWT.HORIZONTAL attributes in the ctor to define the way
        // that the fill works
        FillLayout lay = new FillLayout();
        // optionally set some of the layout fields
        shell.setLayout( lay );

        // now populate
        new Button( shell, SWT.PUSH ).setText( "B1" );
        new Button( shell, SWT.PUSH ).setText( "Wide Button ?" );
        new Button( shell, SWT.PUSH ).setText( "Button 3" );

        shell.pack();
        shell.open();
        LOG.debug( "Dialog opened" );
        while ( !shell.isDisposed() ) {
            if ( !d.readAndDispatch() ) {
                d.sleep();
            }
        }
        LOG.debug( "All done" );
    }

}
