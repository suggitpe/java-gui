/*
 * FileTree.java created on 1 Dec 2008 08:20:48 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.treesntables.treeviewer;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Sample tree viewer application
 * 
 * @author suggitpe
 * @version 1.0 1 Dec 2008
 */
public class FileTree extends ApplicationWindow
{

    /**
     * Constructs a new instance.
     */
    public FileTree()
    {
        super( null );
    }

    /**
     * Run and then dispose
     */
    public void run()
    {
        setBlockOnOpen( true );
        open();
        Display.getCurrent().dispose();
    }

    /**
     * @see org.eclipse.jface.window.ApplicationWindow#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell( Shell shell )
    {
        super.configureShell( shell );
        shell.setText( "File Tree" );
        shell.setSize( 400, 400 );
    }

    /**
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents( Composite parent )
    {
        Composite comp = new Composite( parent, SWT.NONE );
        comp.setLayout( new GridLayout( 1, false ) );

        Button preserveCase = new Button( comp, SWT.CHECK );
        preserveCase.setText( "&Preserve case" );
        preserveCase.setSelection( true );

        // create the tree viewer
        final TreeViewer tv = new TreeViewer( comp );
        tv.getTree().setLayoutData( new GridData( GridData.FILL_BOTH ) );
        tv.setContentProvider( new FileTreeContentProvider() );
        tv.setLabelProvider( new FileTreeLabelProvider() );
        tv.setInput( "Root" );

        preserveCase.addSelectionListener( new SelectionAdapter()
        {

            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected( SelectionEvent event )
            {
                boolean pc = ( (Button) event.widget ).getSelection();
                FileTreeLabelProvider ftlp = (FileTreeLabelProvider) tv.getLabelProvider();
                ftlp.setPreserveCase( pc );
            }

        } );

        return comp;
    }

    /**
     * @param args
     */
    public static void main( String[] args )
    {
        new FileTree().run();
    }

}
