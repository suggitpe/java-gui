/*
 * UsernamePasswordDialog.java created on 10 Sep 2008 18:39:23 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch10_dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Dialog class that exemplifies how to build a dialog for user and password entry
 * 
 * @author suggitpe
 * @version 1.0 10 Sep 2008
 */
public class UsernamePasswordDialog extends Dialog {

    public static final int RESET_ID = IDialogConstants.NO_TO_ALL_ID + 1;

    private Text usernameField;
    private Text passwordField;

    /**
     * Constructs a new instance.
     * 
     * @param parentShell
     *            a composite to associate this class with
     */
    public UsernamePasswordDialog( Shell parentShell ) {
        super( parentShell );
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea( Composite parent ) {
        Composite comp = (Composite) super.createDialogArea( parent );
        GridLayout layout = (GridLayout) comp.getLayout();
        layout.numColumns = 2;

        new Label( comp, SWT.RIGHT ).setText( "Username: " );
        usernameField = new Text( comp, SWT.SINGLE );
        GridData data = new GridData( GridData.FILL_HORIZONTAL );
        usernameField.setLayoutData( data );

        new Label( comp, SWT.RIGHT ).setText( "Password: " );
        passwordField = new Text( comp, SWT.SINGLE | SWT.PASSWORD );
        data = new GridData( GridData.FILL_HORIZONTAL );
        passwordField.setLayoutData( data );

        return comp;
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar( Composite parent ) {
        super.createButtonsForButtonBar( parent );
        createButton( parent, RESET_ID, "Reset All", false );
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#buttonPressed(int)
     */
    @Override
    protected void buttonPressed( int buttonId ) {
        if ( buttonId == RESET_ID ) {
            usernameField.setText( "" );
            passwordField.setText( "" );
        }
        else {
            super.buttonPressed( buttonId );
        }
    }
}
