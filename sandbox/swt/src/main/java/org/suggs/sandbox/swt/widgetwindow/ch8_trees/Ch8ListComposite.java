/*
 * Ch8ListComposite.java created on 8 Sep 2008 18:33:25 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch8_trees;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Class to demonstrate the use of Lists and the content provider
 * 
 * @author suggitpe
 * @version 1.0 8 Sep 2008
 */
public class Ch8ListComposite extends Composite {

    private static final int[] STYLES = { SWT.SINGLE, SWT.MULTI };

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composite to associate this class with
     */
    public Ch8ListComposite( Composite parent ) {
        super( parent, SWT.NULL );
        populateControl();
    }

    /**
     * Populates the control with a list viewer for each of the defined list viewer styles
     */
    private void populateControl() {
        setLayout( new FillLayout() );

        for ( int i = 0; i < STYLES.length; ++i ) {
            createListViewer( STYLES[i] );
        }
    }

    private void createListViewer( int style ) {
        ListViewer viewer = new ListViewer( this, style );
        viewer.setLabelProvider( new LabelProvider() {

            /**
             * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText( Object elem ) {
                return ( (ListItem) elem ).name;
            }

        } );

        // add a filter
        viewer.addFilter( new ViewerFilter() {

            /**
             * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
             *      java.lang.Object, java.lang.Object)
             */
            @Override
            public boolean select( @SuppressWarnings("hiding") Viewer viewer, Object parent, Object element ) {
                return ( (ListItem) element ).value % 2 == 0;
            }
        } );

        // add a sorter
        viewer.setSorter( new ViewerSorter() {

            /**
             * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer,
             *      java.lang.Object, java.lang.Object)
             */
            @Override
            public int compare( @SuppressWarnings("hiding") Viewer viewer, Object obj1, Object obj2 ) {
                return ( (ListItem) obj2 ).value - ( (ListItem) obj1 ).value;
            }
        } );

        viewer.setContentProvider( new IStructuredContentProvider() {

            /**
             * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
             */
            @Override
            public Object[] getElements( Object inputElement ) {
                return ( (List<?>) inputElement ).toArray();
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
        } );

        List<ListItem> input = new ArrayList<ListItem>();
        for ( int i = 0; i < 20; ++i ) {
            input.add( new ListItem( "Item" + i, i ) );
        }
        viewer.setInput( input );
    }

    /**
     * Class to encapsulate an item to populate on a list (this is a random domain object for this example
     * only)
     * 
     * @author suggitpe
     * @version 1.0 8 Sep 2008
     */
    class ListItem {

        private String name;
        private int value;

        public ListItem( String aName, int aValue ) {
            name = aName;
            value = aValue;

        }

    }

}
