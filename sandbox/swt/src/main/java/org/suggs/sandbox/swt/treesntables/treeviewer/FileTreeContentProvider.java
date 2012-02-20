/*
 * FileTreeContentProvider.java created on 1 Dec 2008 19:29:12 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.treesntables.treeviewer;

import java.io.File;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * This content provider will work through the file roots of the system
 * 
 * @author suggitpe
 * @version 1.0 1 Dec 2008
 */
public class FileTreeContentProvider implements ITreeContentProvider {

    /**
     * For a given file node, this will get the children of that node.
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    @Override
    public Object[] getChildren( Object parent ) {
        return ( (File) parent ).listFiles();
    }

    /**
     * For a given node, this will get the parent node
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    @Override
    public Object getParent( Object child ) {
        return ( (File) child ).getParentFile();
    }

    /**
     * For a given node, this will return whether the node has children
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    @Override
    public boolean hasChildren( Object parent ) {
        Object[] arr = getChildren( parent );
        return arr == null ? false : arr.length > 0;
    }

    /**
     * This will detail all of the root elemnts of the tree
     * 
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    @Override
    public Object[] getElements( Object arg0 ) {
        return File.listRoots();
    }

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    @Override
    public void dispose() {}

    /**
     * Here we manage the switch between root nodes
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    @Override
    public void inputChanged( Viewer arg0, Object arg1, Object arg2 ) {}

}
