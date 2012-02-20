/*
 * FileTableContentProvider.java created on 22 Dec 2008 07:59:09 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.fileexplorer;

import java.io.File;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Content provider for a table viewer
 * 
 * @author suggitpe
 * @version 1.0 22 Dec 2008
 */
public class FileTableContentProvider implements IStructuredContentProvider {

    /**
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    @Override
    public Object[] getElements( Object elem ) {
        Object[] kids = null;
        kids = ( (File) elem ).listFiles();
        return kids == null ? new Object[0] : kids;
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
