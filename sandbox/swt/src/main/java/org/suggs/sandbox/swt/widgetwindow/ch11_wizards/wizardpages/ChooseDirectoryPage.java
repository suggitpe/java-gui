/*
 * ChooseDirectoryPage.java created on 12 Sep 2008 06:56:54 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch11_wizards.wizardpages;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Wizard page that allows you to select a directory
 * 
 * @author suggitpe
 * @version 1.0 12 Sep 2008
 */
public class ChooseDirectoryPage extends WizardPage {

    public static final String PAGE_NAME = "Choose Directory";
    private Text text;

    /**
     * Constructs a new instance.
     */
    public ChooseDirectoryPage() {
        super( PAGE_NAME, "Choose Directory Page", null );
    }

    /**
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl( Composite parent ) {
        Composite top = new Composite( parent, SWT.NONE );
        top.setLayout( new GridLayout( 3, false ) );

        new Label( top, SWT.CENTER ).setText( "Enter the directory to use: " );

        text = new Text( top, SWT.SINGLE );
        text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

        // add a button to get the dir from a dialog
        Button b = new Button( top, SWT.PUSH );
        b.setText( "..." );
        b.addSelectionListener( new SelectionListener() {

            @Override
            public void widgetDefaultSelected( SelectionEvent arg0 ) {}

            @Override
            public void widgetSelected( SelectionEvent arg0 ) {
                DirectoryDialog d = new DirectoryDialog( getShell() );
                text.setText( d.open() );
            }
        } );

        setControl( top );
        setPageComplete( true );
    }

    /**
     * Getter for the inputed text @ text
     * 
     * @return the directory name
     */
    public String getDirectory() {
        return text.getText();
    }

}
