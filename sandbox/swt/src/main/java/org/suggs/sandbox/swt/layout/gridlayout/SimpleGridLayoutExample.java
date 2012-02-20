/*
 * SimpleGridLayoutExample.java created on 9 Apr 2008 06:46:03 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.layout.gridlayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example show us a very simple way to create a grid layout based dialog.
 * 
 * @author suggitpe
 * @version 1.0 9 Apr 2008
 */
public final class SimpleGridLayoutExample {

    private static final Logger LOG = LoggerFactory.getLogger( SimpleGridLayoutExample.class );

    private SimpleGridLayoutExample() {}

    public static void main( String[] args ) {
        LOG.debug( "Creating GUI" );
        Display d = new Display();
        Shell shell = new Shell( d );

        GridLayout layout = new GridLayout();
        layout.numColumns = 4;
        shell.setLayout( layout );

        new Button( shell, SWT.PUSH ).setText( "B1" );
        new Button( shell, SWT.PUSH ).setText( "Wide Button 2" );
        new Button( shell, SWT.PUSH ).setText( "Button 3" );
        new Button( shell, SWT.PUSH ).setText( "B4" );
        new Button( shell, SWT.PUSH ).setText( "Button 5" );

        LOG.debug( "Opening Dialog" );
        shell.pack();
        shell.open();

        while ( !shell.isDisposed() ) {
            if ( !d.readAndDispatch() ) {
                d.sleep();
            }
        }
    }

}
