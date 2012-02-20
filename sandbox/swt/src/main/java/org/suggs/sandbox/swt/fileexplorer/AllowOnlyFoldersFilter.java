/*
 * AllowOnlyFoldersFilter.java created on 22 Dec 2008 19:32:16 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.fileexplorer;

import java.io.File;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * This filter will allow only directories to be shown.
 * 
 * @author suggitpe
 * @version 1.0 22 Dec 2008
 */
public class AllowOnlyFoldersFilter extends ViewerFilter {

    /**
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public boolean select( Viewer viewer, Object parent, Object element ) {
        return ( (File) element ).isDirectory();
    }

}
