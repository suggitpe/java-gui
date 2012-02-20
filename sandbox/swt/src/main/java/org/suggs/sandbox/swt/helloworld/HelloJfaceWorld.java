/*
 * HelloJfaceWorld.java created on 8 Aug 2008 19:49:48 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.helloworld;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

/**
 * Hello world implementation to describe how JFace works
 * 
 * @author suggitpe
 * @version 1.0 8 Aug 2008
 */
public class HelloJfaceWorld extends ApplicationWindow {

    /**
     * Constructs a new instance.
     */
    public HelloJfaceWorld() {
        super( null );
    }

    /**
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents( Composite parent ) {
        Text hello = new Text( parent, SWT.CENTER );
        hello.setText( "Hellow SWT and JFace!" );
        parent.pack();
        return parent;
    }

    /**
     * Main method to start the GUI app
     * 
     * @param args
     *            the args to pass to the GUI app
     */
    public static void main( String[] args ) {
        HelloJfaceWorld awin = new HelloJfaceWorld();
        awin.setBlockOnOpen( true );
        awin.open();
        Display.getCurrent().dispose();
    }

}
