/*
 * HelloSwtWorld.java created on 8 Aug 2008 19:34:10 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.helloworld;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Simple class to show how to create a GUI app using SWT only
 * 
 * @author suggitpe
 * @version 1.0 8 Aug 2008
 */
public final class HelloSwtWorld {

    private HelloSwtWorld() {}

    public static void main( String[] args ) {
        Display display = new Display();
        Shell shell = new Shell( display );

        Text hello = new Text( shell, SWT.CENTER );
        hello.setText( "Hello SWT!" );
        hello.pack();

        shell.pack();
        shell.open();

        while ( !shell.isDisposed() ) {
            if ( !display.readAndDispatch() ) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
