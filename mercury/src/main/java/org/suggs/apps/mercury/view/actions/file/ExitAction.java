/*
 * ExitAction.java created on 20 Oct 2008 06:56:31 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.actions.file;

import org.suggs.apps.mercury.model.util.image.ImageManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;

/**
 * Encapsulation of the exit from Mercury functionality
 * 
 * @author suggitpe
 * @version 1.0 20 Oct 2008
 */
public class ExitAction extends Action {

    private static final Logger LOG = LoggerFactory.getLogger( ExitAction.class );

    /**
     * Constructs a new instance.
     */
    public ExitAction() {
        super( "&Exit" );
        setToolTipText( "Exit Mercury application" );
        setImageDescriptor( ImageManager.getImageDescriptor( ImageManager.IMAGE_EXIT ) );
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        LOG.debug( "Exiting Mercury" );
        Display.getCurrent().getActiveShell().close();
    }

}
