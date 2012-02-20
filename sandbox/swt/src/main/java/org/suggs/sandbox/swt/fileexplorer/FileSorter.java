/*
 * FileSorter.java created on 22 Dec 2008 19:26:29 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.fileexplorer;

import java.io.File;

import org.eclipse.jface.viewers.ViewerSorter;

/**
 * This sorter will sort the directories before the files
 * 
 * @author suggitpe
 * @version 1.0 22 Dec 2008
 */
public class FileSorter extends ViewerSorter {

    /**
     * @see org.eclipse.jface.viewers.ViewerComparator#category(java.lang.Object)
     */
    @Override
    public int category( Object element ) {
        return ( (File) element ).isDirectory() ? 0 : 1;
    }

}
