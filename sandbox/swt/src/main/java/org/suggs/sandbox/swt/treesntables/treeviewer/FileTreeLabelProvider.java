/*
 * FileTreeLabelProvider.java created on 1 Dec 2008 19:29:26 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.treesntables.treeviewer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider for the file tree viewer.
 * 
 * @author suggitpe
 * @version 1.0 1 Dec 2008
 */
public class FileTreeLabelProvider implements ILabelProvider {

    private boolean preserveCase = true;
    private List<ILabelProviderListener> listeners = new ArrayList<ILabelProviderListener>();
    private Image fileImage;
    private Image dirImage;

    /**
     * Constructs a new instance.
     */
    public FileTreeLabelProvider() {
        try {
            fileImage = new Image( null, new FileInputStream( "images/file.gif" ) );
            dirImage = new Image( null, new FileInputStream( "images/dir.gif" ) );
        }
        catch ( FileNotFoundException fnfe ) {
            // nadda
        }
    }

    /**
     * Update the local var to cover this and then update the listeners
     * 
     * @param pc
     *            th enew state of the preserve case flag
     */
    public void setPreserveCase( boolean pc ) {
        preserveCase = pc;
        LabelProviderChangedEvent evt = new LabelProviderChangedEvent( this );
        for ( ILabelProviderListener l : listeners ) {
            l.labelProviderChanged( evt );
        }
    }

    /**
     * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage( Object obj ) {
        return ( (File) obj ).isDirectory() ? dirImage : fileImage;
    }

    /**
     * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText( Object obj ) {
        String txt = ( (File) obj ).getName();
        if ( txt.length() == 0 ) {
            txt = ( (File) obj ).getPath();
        }

        return preserveCase ? txt : txt.toUpperCase();
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    @Override
    public void addListener( ILabelProviderListener list ) {
        listeners.add( list );
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    @Override
    public void dispose() {
        if ( dirImage != null ) {
            dirImage.dispose();
        }

        if ( fileImage != null ) {
            fileImage.dispose();
        }
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
     */
    @Override
    public boolean isLabelProperty( Object arg0, String arg1 ) {
        return false;
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    @Override
    public void removeListener( ILabelProviderListener list ) {
        listeners.remove( list );
    }
}
