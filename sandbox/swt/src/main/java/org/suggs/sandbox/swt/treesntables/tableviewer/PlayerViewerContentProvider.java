/*
 * PlayerViewerContentProvider.java created on 2 Dec 2008 19:36:36 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.treesntables.tableviewer;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * This is the content provider used for the table population
 * 
 * @author suggitpe
 * @version 1.0 2 Dec 2008
 */
public class PlayerViewerContentProvider implements IStructuredContentProvider {

    /**
     * This is where all of the rows for the table are derived from. This method will process a TeamBean and
     * pull out all of the players into an array of player beans.
     * 
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    @Override
    public Object[] getElements( Object inElem ) {
        if ( inElem instanceof TeamBean ) {
            TeamBean team = (TeamBean) inElem;
            return team.getPlayers().toArray();
        }
        throw new IllegalStateException( "PlayerViewerContentProvider can only manage TeamBean objects" );
    }

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    @Override
    public void dispose() {}

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    @Override
    public void inputChanged( Viewer arg0, Object arg1, Object arg2 ) {}

}
