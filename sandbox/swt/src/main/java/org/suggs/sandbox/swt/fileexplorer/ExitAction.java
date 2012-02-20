/*
 * ExitAction.java created on 23 Dec 2008 07:55:03 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.fileexplorer;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the action class for exiting the main application.
 * 
 * @author suggitpe
 * @version 1.0 23 Dec 2008
 */
public class ExitAction extends Action {

    private static final Logger LOG = LoggerFactory.getLogger( ExitAction.class );

    private ApplicationWindow appWin;

    /**
     * Constructs a new instance.
     * 
     * @param w
     *            the application window from which to execute the action
     */
    public ExitAction( ApplicationWindow w ) {
        appWin = w;
        setText( "E&xit@Ctrl+w" );
        setToolTipText( "Exit the application" );
        setImageDescriptor( ImageDescriptor.createFromURL( ExitAction.class.getClassLoader()
            .getResource( "exit.gif" ) ) );
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        LOG.debug( "Closing main application window" );
        appWin.close();
    }

}
