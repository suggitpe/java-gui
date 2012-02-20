/*
 * FileTreeLabelProvider.java created on 22 Dec 2008 07:52:16 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.fileexplorer;

import java.io.File;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * This class will manage the labelling of the items in teh tree.
 * 
 * @author suggitpe
 * @version 1.0 22 Dec 2008
 */
public class FileTreeLabelProvider extends LabelProvider {

    /**
     * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText( Object element ) {
        return ( (File) element ).getName();
    }

    /**
     * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage( Object element ) {
        if ( ( (File) element ).isDirectory() ) {
            return ImageUtil.getImageRegistry().get( "folder" );
        }
        return ImageUtil.getImageRegistry().get( "file" );
    }

}
