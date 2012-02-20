/*
 * PlayerViewerLabelProvider.java created on 2 Dec 2008 19:45:45 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.treesntables.tableviewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * This is the way in which the data is displayed to the user.
 * 
 * @author suggitpe
 * @version 1.0 2 Dec 2008
 */
public class PlayerViewerLabelProvider implements ITableLabelProvider {

    private List<ILabelProviderListener> listeners = new ArrayList<ILabelProviderListener>();

    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
     */
    @Override
    public Image getColumnImage( Object arg0, int arg1 ) {
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
     */
    @Override
    public String getColumnText( Object obj, int colIdx ) {
        if ( obj instanceof PlayerBean ) {
            PlayerBean p = (PlayerBean) obj;
            switch ( colIdx ) {
                case 0:
                    return p.getFirstname();
                case 1:
                    return p.getLastname();
                case 2:
                    return Double.toString( p.getPointsPerGame() );
                case 3:
                    return Double.toString( p.getReboundsPerGame() );
                case 4:
                    return Double.toString( p.getAssistsPerGame() );
                default:
                    return "";
            }

        }
        throw new IllegalArgumentException( "object passed in is a [" + obj.getClass().getName()
                                            + "], should be a PlayerBean" );
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
     */
    @Override
    public boolean isLabelProperty( Object arg0, String arg1 ) {
        return false;
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    @Override
    public void dispose() {}

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    @Override
    public void addListener( ILabelProviderListener arg0 ) {
        listeners.add( arg0 );
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    @Override
    public void removeListener( ILabelProviderListener arg0 ) {
        listeners.remove( arg0 );
    }

}
