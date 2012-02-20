/*
 * SelectConnectionDialog.java created on 14 Jan 2009 19:28:12 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.dialogs;

import java.util.Arrays;
import java.util.Set;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * This dialog will be responsible for selecting the connection to.
 * 
 * @author suggitpe
 * @version 1.0 14 Jan 2009
 */
public class SelectConnectionDialog extends TitleAreaDialog {

    private String[] connectionNames;
    private String choice;
    private String title;
    private String message;

    /**
     * Constructs a new instance.
     * 
     * @param aParentShell
     * @param aConnectionNames
     * @param aTitle
     * @param aMessage
     */
    public SelectConnectionDialog( Shell aParentShell, Set<String> aConnectionNames, String aTitle,
                                   String aMessage ) {
        super( aParentShell );
        connectionNames = aConnectionNames.toArray( new String[aConnectionNames.size()] );
        Arrays.sort( connectionNames );
        title = aTitle;
        message = aMessage;
    }

    /**
     * Creates the dialogs contents
     * 
     * @see org.eclipse.jface.dialogs.TitleAreaDialog#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents( Composite aParent ) {
        Control contents = super.createContents( aParent );
        setTitle( title );
        setMessage( message );
        return contents;
    }

    /**
     * Creates the grey area
     * 
     * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea( Composite aParent ) {
        Composite contents = (Composite) super.createDialogArea( aParent );
        createDialogContent( contents );
        return contents;
    }

    /**
     * This bit creates the content composite for the dialog
     * 
     * @param contents
     */
    private void createDialogContent( Composite contents ) {
        Composite comp = new Composite( contents, SWT.NONE );
        comp.setLayout( new GridLayout( 2, false ) );
        comp.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

        new Label( comp, SWT.NONE ).setText( "Connections:" );

        final Combo combo = new Combo( comp, SWT.DROP_DOWN );
        combo.setItems( connectionNames );
        combo.addSelectionListener( new SelectionAdapter() {

            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected( SelectionEvent aE ) {
                choice = combo.getText();
            }
        } );
        combo.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
    }

    /**
     * Getter for the choice. This is set hen a choice is made in the combo box
     * 
     * @return the string selected from the combo box
     */
    public String getChoice() {
        return choice;
    }

}
