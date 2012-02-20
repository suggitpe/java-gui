/*
 * Explorer.java created on 19 Dec 2008 21:35:15 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.fileexplorer;

import java.io.File;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableColumn;

/**
 * This is the main application window class
 * 
 * @author suggitpe
 * @version 1.0 19 Dec 2008
 */
public class Explorer extends ApplicationWindow {

    private static final String FILE_LOC = "C:\\";

    private TableViewer table;
    private TreeViewer tree;
    private OpenAction open;
    private CopyFileNamesToClipboardAction copy;
    private ExitAction exit;

    /**
     * Constructs a new instance.
     */
    public Explorer() {
        super( null );
        open = new OpenAction( this );
        copy = new CopyFileNamesToClipboardAction( this );
        exit = new ExitAction( this );

        addStatusLine();
        addMenuBar();
        addToolBar( SWT.FLAT | SWT.WRAP );
    }

    /**
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents( Composite composite ) {
        getShell().setText( "JFace File Explorer" );
        // create sash form
        SashForm sf = new SashForm( composite, SWT.HORIZONTAL | SWT.NULL );

        // build tree viewer
        tree = new TreeViewer( sf );
        tree.setContentProvider( new FileTreeContentProvider() );
        tree.setLabelProvider( new FileTreeLabelProvider() );
        tree.setInput( new File( FILE_LOC ) );
        tree.addFilter( new AllowOnlyFoldersFilter() );

        // create the table viewer
        table = new TableViewer( sf, SWT.BORDER | SWT.MULTI );
        table.setContentProvider( new FileTableContentProvider() );
        table.setLabelProvider( new FileTableLabelProvider() );
        table.setSorter( new FileSorter() );

        TableColumn col1 = new TableColumn( table.getTable(), SWT.LEFT );
        col1.setText( "Name" );
        col1.setWidth( 200 );

        TableColumn col2 = new TableColumn( table.getTable(), SWT.RIGHT );
        col2.setText( "Size (k)" );
        col2.setWidth( 100 );

        table.getTable().setHeaderVisible( true );

        // now set the listener on the tree view to change the input
        // to the table view
        tree.addSelectionChangedListener( new ISelectionChangedListener() {

            @Override
            public void selectionChanged( SelectionChangedEvent event ) {
                IStructuredSelection sel = (IStructuredSelection) event.getSelection();
                Object selectedFile = sel.getFirstElement();
                table.setInput( selectedFile );
            }
        } );

        table.addSelectionChangedListener( new ISelectionChangedListener() {

            @Override
            public void selectionChanged( SelectionChangedEvent event ) {
                IStructuredSelection sel = (IStructuredSelection) event.getSelection();
                setStatus( "Number of rows selected is " + sel.size() );

            }
        } );

        table.addSelectionChangedListener( open );

        // also we need
        MenuManager menu = new MenuManager();
        table.getTable().setMenu( menu.createContextMenu( table.getTable() ) );
        menu.add( copy );
        menu.add( open );

        return sf;
    }

    /**
     * @see org.eclipse.jface.window.ApplicationWindow#createMenuManager()
     */
    @Override
    protected MenuManager createMenuManager() {
        MenuManager ret = new MenuManager( "" );

        MenuManager file = new MenuManager( "&File" );
        MenuManager edit = new MenuManager( "&Edit" );
        MenuManager view = new MenuManager( "&View" );

        ret.add( file );
        ret.add( edit );
        ret.add( view );

        file.add( exit );

        edit.add( copy );
        edit.add( open );

        return ret;
    }

    /**
     * @see org.eclipse.jface.window.ApplicationWindow#createToolBarManager(int)
     */
    @Override
    protected ToolBarManager createToolBarManager( int style ) {
        ToolBarManager ret = new ToolBarManager( style );

        ret.add( exit );
        ret.add( copy );

        ret.add( open );

        return ret;
    }

    /**
     * Getter for the table selection
     * 
     * @return the table selection
     */
    protected IStructuredSelection getTableSelection() {
        return (IStructuredSelection) table.getSelection();
    }

    public static void main( String[] aArgv ) {
        Explorer e = new Explorer();
        e.setBlockOnOpen( true );
        e.open();
        Display.getCurrent().dispose();
        if ( !ImageUtil.getClipboard().isDisposed() ) {
            ImageUtil.getClipboard().dispose();
        }
    }

}
