/*
 * CheckedFileTree.java created on 2 Dec 2008 07:05:00 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.treesntables.treeviewer;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * An extension of File Tree in that it uses checked buttons instead.
 * 
 * @author suggitpe
 * @version 1.0 2 Dec 2008
 */
public class CheckedFileTree extends FileTree {

    /**
     * @see org.suggs.sandbox.swt.treesntables.treeviewer.FileTree#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell( Shell shell ) {
        super.configureShell( shell );
        shell.setText( "Check File Tree" );
    }

    /**
     * @see org.suggs.sandbox.swt.treesntables.treeviewer.FileTree#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents( Composite parent ) {
        Composite comp = new Composite( parent, SWT.NONE );
        comp.setLayout( new GridLayout( 1, false ) );

        Button preserveCase = new Button( comp, SWT.CHECK );
        preserveCase.setSelection( true );
        preserveCase.setText( "&Preserve Case" );

        final CheckboxTreeViewer tv = new CheckboxTreeViewer( comp );
        tv.getTree().setLayoutData( new GridData( GridData.FILL_BOTH ) );
        tv.setContentProvider( new FileTreeContentProvider() );
        tv.setLabelProvider( new FileTreeLabelProvider() );
        tv.setInput( "root" );

        // now add the listener
        preserveCase.addSelectionListener( new SelectionAdapter() {

            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected( SelectionEvent event ) {
                boolean pc = ( (Button) event.widget ).getSelection();
                FileTreeLabelProvider ftlp = (FileTreeLabelProvider) tv.getLabelProvider();
                ftlp.setPreserveCase( pc );
            }

        } );

        tv.addCheckStateListener( new ICheckStateListener() {

            /**
             * @see org.eclipse.jface.viewers.ICheckStateListener#checkStateChanged(org.eclipse.jface.viewers.CheckStateChangedEvent)
             */
            @Override
            public void checkStateChanged( CheckStateChangedEvent event ) {
                if ( event.getChecked() ) {
                    tv.setSubtreeChecked( event.getElement(), true );
                }
            }
        } );

        return comp;
    }

    /**
     * Run run run
     * 
     * @param args
     */
    public static void main( String[] args ) {
        new CheckedFileTree().run();
    }
}
