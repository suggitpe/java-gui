/*
 * DebugAction.java created on 15 Oct 2008 06:22:01 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.actions.connection;

import org.eclipse.jface.action.Action;

/**
 * This is the action that supports the help connection debug screen.
 * 
 * @author suggitpe
 * @version 1.0 15 Oct 2008
 */
public class DebugAction extends Action {

    /**
     * Constructs a new instance.
     */
    public DebugAction() {
        super( "&Debug Connections" );
        setToolTipText( "This will debug the currently known connection set" );
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
    // HelpConnectionDebugDialog d = new
    // HelpConnectionDebugDialog( Display.getCurrent()
    // .getActiveShell() );
    // d.open();
    }

}
