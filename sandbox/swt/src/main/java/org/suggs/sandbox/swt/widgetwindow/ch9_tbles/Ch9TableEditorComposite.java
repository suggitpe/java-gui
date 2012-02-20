/*
 * Ch9TableEditorComposite.java created on 9 Sep 2008 07:03:55 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch9_tbles;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * TODO Write javadoc for Ch9TableEditorComposite
 * 
 * @author suggitpe
 * @version 1.0 9 Sep 2008
 */
public class Ch9TableEditorComposite extends Composite {

    // this would normally be read from a persistent store
    private static final Object[] CONTENT = new Object[] {
                                                          new EditableTableItem( "Item 1",
                                                                                 Integer.valueOf( 0 ) ),
                                                          new EditableTableItem( "Item 2",
                                                                                 Integer.valueOf( 1 ) ) };
    private static final String[] VALUE_SET = new String[] { "xxxx", "yyyy", "zzzz" };
    private static final String NAME_PROP = "name";
    private static final String VALUE_PROP = "value";

    private TableViewer viewer;

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composite to associate this class with
     */
    public Ch9TableEditorComposite( Composite parent ) {
        super( parent, SWT.NULL );
        buildControls();
    }

    /**
     * This class encapsulates the concept of adding a new row to the table
     * 
     * @author suggitpe
     * @version 1.0 10 Sep 2008
     */
    private class NewRowAction extends Action {

        /**
         * Constructs a new instance.
         */
        public NewRowAction() {
            super( "Insert New Row" );
        }

        /**
         * @see org.eclipse.jface.action.Action#run()
         */
        @Override
        public void run() {
            EditableTableItem newItem = new EditableTableItem( "new row", Integer.valueOf( 2 ) );
            viewer.add( newItem );
        }
    }

    /**
     * Build the composite controls
     */
    protected final void buildControls() {
        setLayout( new FillLayout() );

        final Table table = new Table( this, SWT.FULL_SELECTION );
        table.setToolTipText( "Right click to add a new row" );
        viewer = buildAndLayoutTable( table );

        attachContentProvider( viewer );
        attachLabelProvider( viewer );
        attachCellEditors( viewer, table );

        MenuManager popupMenu = new MenuManager();
        IAction newRowAction = new NewRowAction();
        popupMenu.add( newRowAction );
        Menu menu = popupMenu.createContextMenu( table );
        table.setMenu( menu );

        viewer.setInput( CONTENT );
    }

    /**
     * build and sets up the layout for the table
     * 
     * @param table
     *            the table to set up
     * @return a viewer that relates to the table
     */
    private TableViewer buildAndLayoutTable( final Table table ) {
        TableViewer ret = new TableViewer( table );

        TableLayout layout = new TableLayout();
        // set up two columns that take up 50% of the width and have a
        // min width of 75 px
        layout.addColumnData( new ColumnWeightData( 50, 75, true ) );
        layout.addColumnData( new ColumnWeightData( 50, 75, true ) );
        table.setLayout( layout );

        // define and set up the actual columns
        TableColumn nameCol = new TableColumn( table, SWT.CENTER );
        nameCol.setText( "Name" );
        TableColumn valCol = new TableColumn( table, SWT.CENTER );
        valCol.setText( "Value" );

        table.setHeaderVisible( true );
        return ret;
    }

    /**
     * ] Attaches the label provider to the table
     * 
     * @param aViewer
     *            the viewer to attach the label provider to
     */
    private void attachLabelProvider( TableViewer aViewer ) {
        aViewer.setLabelProvider( new ITableLabelProvider() {

            @Override
            public Image getColumnImage( Object arg0, int arg1 ) {
                return null;
            }

            @Override
            public String getColumnText( Object element, int colIdx ) {
                switch ( colIdx ) {
                    case 0:
                        return ( (EditableTableItem) element ).getName();
                    case 1:
                        Number index = ( (EditableTableItem) element ).getValue();
                        return VALUE_SET[index.intValue()];
                    default:
                        return "Invalid column " + colIdx;
                }
            }

            @Override
            public void addListener( ILabelProviderListener arg0 ) {}

            @Override
            public void dispose() {}

            @Override
            public boolean isLabelProperty( Object arg0, String arg1 ) {
                return false;
            }

            @Override
            public void removeListener( ILabelProviderListener arg0 ) {}
        } );
    }

    /**
     * Attach a content provider to the table viewer
     * 
     * @param aViewer
     *            the viewer to attach the content provider
     */
    private void attachContentProvider( TableViewer aViewer ) {
        aViewer.setContentProvider( new IStructuredContentProvider() {

            /**
             * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
             */
            @Override
            public Object[] getElements( Object inputElem ) {
                return (Object[]) inputElem;
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
    }

    private void attachCellEditors( final TableViewer aViewer, Composite parent ) {
        aViewer.setCellModifier( new ICellModifier() {

            @Override
            public boolean canModify( Object elem, String prop ) {
                return true;
            }

            @Override
            public Object getValue( Object elem, String prop ) {
                if ( NAME_PROP.equals( prop ) ) {
                    return ( (EditableTableItem) elem ).getName();
                }
                return ( (EditableTableItem) elem ).getValue();
            }

            @Override
            public void modify( Object elem, String prop, Object value ) {

                TableItem tableItem = (TableItem) elem;
                EditableTableItem data = (EditableTableItem) tableItem.getData();
                if ( NAME_PROP.equals( prop ) ) {
                    data.setName( value.toString() );
                }
                else {
                    data.setValue( (Integer) value );
                }
                aViewer.refresh( data );
            }
        } );

        aViewer.setCellEditors( new CellEditor[] { new TextCellEditor( parent ),
                                                  new ComboBoxCellEditor( parent, VALUE_SET ) } );

        aViewer.setColumnProperties( new String[] { NAME_PROP, VALUE_PROP } );
    }

}

/**
 * Domain class for this example only
 * 
 * @author suggitpe
 * @version 1.0 9 Sep 2008
 */
class EditableTableItem {

    private String name;
    private Integer value;

    /**
     * Constructs a new instance.
     * 
     * @param aName
     *            the name to set
     * @param aValue
     *            the value to set
     */
    public EditableTableItem( String aName, Integer aValue ) {
        name = aName;
        value = aValue;
    }

    /**
     * Returns the value of Name.
     * 
     * @return Returns the Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the mName_ field to the specified value.
     * 
     * @param aName
     *            The Name to set.
     */
    public void setName( String aName ) {
        name = aName;
    }

    /**
     * Returns the value of Value.
     * 
     * @return Returns the Value.
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Sets the Value field to the specified value.
     * 
     * @param aValue
     *            The Value to set.
     */
    public void setValue( Integer aValue ) {
        value = aValue;
    }

}
