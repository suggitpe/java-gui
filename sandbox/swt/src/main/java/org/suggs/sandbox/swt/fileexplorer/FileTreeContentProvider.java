/*
 * FileTreeContentProvider.java created on 19 Dec 2008 21:30:50 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.fileexplorer;

import java.io.File;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Content provider for the file tree viewer
 * 
 * @author suggitpe
 * @version 1.0 19 Dec 2008
 */
public class FileTreeContentProvider implements ITreeContentProvider {

    /**
     * Gets the children of the node
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    @Override
    public Object[] getChildren( Object elem ) {
        Object[] kids = ( (File) elem ).listFiles();
        return kids == null ? new Object[0] : kids;
    }

    /**
     * Gets the parent node
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    @Override
    public Object getParent( Object elem ) {
        return ( (File) elem ).getParent();
    }

    /**
     * Tester to find out if the node has children
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    @Override
    public boolean hasChildren( Object elem ) {
        return getChildren( elem ).length > 0;
    }

    /**
     * This is the method used to get the initial set of root elements for the tree
     * 
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    @Override
    public Object[] getElements( Object elem ) {
        return getChildren( elem );
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
    public void inputChanged( Viewer aArg0, Object aArg1, Object aArg2 ) {}

}
