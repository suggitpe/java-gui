/*
 * OpenAction.java created on 23 Dec 2008 08:09:15 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.fileexplorer;

import java.io.File;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.program.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This action will allow us to open a file/directory
 * 
 * @author suggitpe
 * @version 1.0 23 Dec 2008
 */
public class OpenAction extends Action implements ISelectionChangedListener {

    private static final Logger LOG = LoggerFactory.getLogger( OpenAction.class );

    private Explorer explorer;

    /**
     * Constructs a new instance.
     * 
     * @param exp
     */
    public OpenAction( Explorer exp ) {
        explorer = exp;
        setText( "Run" );
        setToolTipText( "Run the associated program on the file" );
        setImageDescriptor( ImageDescriptor.createFromURL( getClass().getClassLoader()
            .getResource( "run.gif" ) ) );
        setEnabled( false );
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        IStructuredSelection sel = explorer.getTableSelection();
        if ( sel.size() != 1 ) {
            LOG.warn( "Have to have one (and only one) file selected" );
            return;
        }

        File selected = (File) sel.getFirstElement();
        if ( selected.isFile() ) {
            LOG.debug( "Launching file [" + selected.getAbsolutePath() + "]" );
            Program.launch( selected.getAbsolutePath() );
        }
    }

    /**
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    @Override
    public void selectionChanged( SelectionChangedEvent event ) {
        setText( "Run" );
        setToolTipText( "Run the associated program on a file" );

        IStructuredSelection sel = explorer.getTableSelection();
        if ( sel.size() != 1 ) {
            setEnabled( false );
            setToolTipText( getToolTipText() + "(only enabled when exactly one file is sleected)" );
            return;
        }

        File file = (File) sel.getFirstElement();
        if ( file.isFile() ) {
            setEnabled( true );
            setText( "Run the associated program on " + file.getName() );
            setToolTipText( "Run the associated program with " + file.getName()
                            + " with this file as the argument" );
        }

    }
}
