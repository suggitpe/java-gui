/*
 * IbmMqConnectionData.java created on 10 Nov 2008 18:55:02 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.wizards.createconnection.pages;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.view.wizards.createconnection.CreateConnectionWizard;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * This pupose of this page is to show to the user what they have selected through the wizard and allow them
 * to run a test connection through to the underlying middleware.
 * 
 * @author suggitpe
 * @version 1.0 10 Nov 2008
 */
public class ConnectionDataSummaryPage extends AbstractCreateConnectionPage {

    private static final Logger LOG = LoggerFactory.getLogger( ConnectionDataSummaryPage.class );
    public static final String PAGE_NAME = "ConnectionDataSummaryPage";

    private TableViewer viewer;

    /**
     * Constructs a new instance.
     */
    public ConnectionDataSummaryPage() {
        super( PAGE_NAME, "Create ConnectionContext Summary Page" );
        setPageComplete( true );
        setDescription( "Please review the connection data provided here before hitting finish." );
    }

    /**
     * @see org.suggs.apps.mercury.view.wizards.createconnection.pages.AbstractCreateConnectionPage#doBuildControls(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void doBuildControls( Composite controlComposite ) {
        Composite c = new SummaryDataComposite( controlComposite );
        c.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

        Composite t = new TestConnectionSummary( controlComposite );
        t.setLayoutData( new GridData( GridData.HORIZONTAL_ALIGN_END ) );
    }

    /**
     * Accessor method to enable us to update the table contents
     */
    public void updateTableContents() {
        Map<String, String> map = ( (CreateConnectionWizard) getWizard() ).getConnectionData();
        EditableTableItem[] data = new EditableTableItem[map.size()];

        Set<String> keys = map.keySet();

        int i = 0;
        for ( String s : keys ) {
            data[i++] = new EditableTableItem( s, map.get( s ) );
        }

        viewer.setInput( data );
    }

    // ##################################
    // ########## INNER CLASSES #########
    // ##################################
    private class SummaryDataComposite extends Composite {

        /**
         * Constructs a new instance.
         * 
         * @param comp
         */
        public SummaryDataComposite( Composite comp ) {
            super( comp, SWT.NONE );
            setLayout( new GridLayout( 1, false ) );

            new Label( this, SWT.NONE ).setText( "Please review the following data to verify that the data is correct." );

            // build the table
            final Table table = new Table( this, SWT.BORDER | SWT.FULL_SELECTION );
            table.setToolTipText( "Review contents for new connection" );

            table.setLinesVisible( true );
            table.setHeaderVisible( true );

            TableLayout layout = new TableLayout();
            layout.addColumnData( new ColumnWeightData( 25, 75, true ) );
            layout.addColumnData( new ColumnWeightData( 75, 75, true ) );

            new TableColumn( table, SWT.LEFT ).setText( "Name" );
            new TableColumn( table, SWT.LEFT ).setText( "Value" );

            table.setLayout( layout );
            table.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

            // build the table viewer
            viewer = new TableViewer( table );

            // hook in the table contents
            viewer.setContentProvider( new SummaryTableContentProvider() );
            viewer.setLabelProvider( new SummaryTableLabelProvider() );
            updateTableContents();
        }
    }

    /**
     * @author suggitpe
     * @version 1.0 14 Nov 2008
     */
    private class TestConnectionSummary extends Composite {

        /**
         * Constructs a new instance.
         * 
         * @param comp
         */
        public TestConnectionSummary( Composite comp ) {
            super( comp, SWT.NONE );
            setLayout( new GridLayout( 1, false ) );

            final Button test = new Button( this, SWT.PUSH );
            test.setText( "Test ConnectionContext" );
            test.addSelectionListener( new SelectionAdapter() {

                /**
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected( SelectionEvent e ) {
                    ConnectionDetails dtls = ( (CreateConnectionWizard) getWizard() ).getConnectionDetails();
                    LOG.debug( "Testing connection with connection details [" + dtls + "]" );
                }
            } );

        }
    }

    /**
     * This class is the content provider for the data for the table
     * 
     * @author suggitpe
     * @version 1.0 12 Nov 2008
     */
    private static class SummaryTableContentProvider implements IStructuredContentProvider {

        /**
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        @Override
        public Object[] getElements( Object inputElement ) {
            return (Object[]) inputElement;
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
        public void inputChanged( Viewer aViewer, Object oldInput, Object newInput ) {}
    }

    /**
     * This class is the Label provider for the table
     * 
     * @author suggitpe
     * @version 1.0 12 Nov 2008
     */
    private static class SummaryTableLabelProvider implements ITableLabelProvider {

        /**
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        @Override
        public Image getColumnImage( Object element, int columnIndex ) {
            // no thanks
            return null;
        }

        /**
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        @Override
        public String getColumnText( Object element, int columnIndex ) {
            EditableTableItem tItem = (EditableTableItem) element;
            switch ( columnIndex ) {
                case 0:
                    return tItem.getName();
                case 1:
                    return tItem.getValue();
                default:
                    return "Inavlid column" + columnIndex;

            }
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        @Override
        public void addListener( ILabelProviderListener listener ) {}

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
         */
        @Override
        public void dispose() {}

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
         *      java.lang.String)
         */
        @Override
        public boolean isLabelProperty( Object element, String property ) {
            return false;
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        @Override
        public void removeListener( ILabelProviderListener listener ) {}
    }

    /**
     * This class is used to encapsulate the data that will be sent to the table.
     * 
     * @author suggitpe
     * @version 1.0 13 Nov 2008
     */
    static class EditableTableItem {

        private String name;
        private String value;

        /**
         * Constructs a new instance.
         * 
         * @param aName
         *            the name to set
         * @param aValue
         *            the value to set
         */
        public EditableTableItem( String aName, String aValue ) {
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
        public String getValue() {
            return value;
        }

        /**
         * Sets the Value field to the specified value.
         * 
         * @param aValue
         *            The Value to set.
         */
        public void setValue( String aValue ) {
            value = aValue;
        }

    }

}
