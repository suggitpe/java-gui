/*
 * Ch4StatusAction.java created on 13 Aug 2008 18:30:01 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch4_events;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Action class for status'
 * 
 * @author suggitpe
 * @version 1.0 13 Aug 2008
 */
public class Ch4StatusAction extends Action {

    private StatusLineManager statMan;
    private short triggerCnt;

    /**
     * Constructs a new instance.
     * 
     * @param mgr
     */
    public Ch4StatusAction( StatusLineManager mgr ) {
        super( "&Trigger@Ctrl+T", AS_PUSH_BUTTON );
        statMan = mgr;
        setToolTipText( "Trigger the action" );
        // gets the image from the classpath
        setImageDescriptor( ImageDescriptor.createFromURL( this.getClass()
            .getClassLoader()
            .getResource( "eclipse.gif" ) ) );
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        ++triggerCnt;
        statMan.setMessage( "The status action has fired [" + triggerCnt + "] times" );
    }

}
