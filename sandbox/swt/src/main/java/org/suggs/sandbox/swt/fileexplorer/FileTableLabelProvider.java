/*
 * FileTableLabelProvider.java created on 22 Dec 2008 08:11:26 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.fileexplorer;

import java.io.File;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Table content provider for the file table viewer
 * 
 * @author suggitpe
 * @version 1.0 22 Dec 2008
 */
public class FileTableLabelProvider implements ITableLabelProvider {

    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
     */
    @Override
    public String getColumnText( Object obj, int idx ) {
        if ( idx == 0 ) {
            return ( (File) obj ).getName();
        }
        else if ( idx == 1 ) {
            return "" + ( ( (File) obj ).length() / 1024 );
        }
        return "";
    }

    /**
     * Use this to add an image for the entry in the table
     * 
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
     */
    @Override
    public Image getColumnImage( Object element, int idx ) {
        if ( idx != 0 ) {
            return null;
        }
        File f = (File) element;

        if ( f.isDirectory() ) {
            return ImageUtil.getImageRegistry().get( "folder" );
        }
        return ImageUtil.getImageRegistry().get( "file" );
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    @Override
    public void dispose() {}

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
     */
    @Override
    public boolean isLabelProperty( Object aArg0, String aArg1 ) {
        return false;
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    @Override
    public void addListener( ILabelProviderListener aArg0 ) {}

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    @Override
    public void removeListener( ILabelProviderListener aArg0 ) {}

}
