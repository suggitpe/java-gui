/*
 * DirectoryPage.java created on 11 Sep 2008 18:36:45 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch11_wizards.wizardpages;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Wizard page to get an initial selection
 * 
 * @author suggitpe
 * @version 1.0 11 Sep 2008
 */
public class DirectoryPage extends WizardPage {

    public static final String PAGE_NAME = "Directory";
    private Button button;

    /**
     * Constructs a new instance.
     */
    public DirectoryPage() {
        super( PAGE_NAME, "Directory Page", null );
    }

    /**
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl( Composite parent ) {
        Composite topLevel = new Composite( parent, SWT.NONE );
        topLevel.setLayout( new GridLayout( 2, false ) );

        new Label( topLevel, SWT.CENTER ).setText( "Use default directory?" );
        button = new Button( topLevel, SWT.CHECK );

        setControl( topLevel );
        setPageComplete( true );
    }

    /**
     * Getter for the button selection
     * 
     * @return true of the check box has been selected, else false
     */
    public boolean useDefaultDirectory() {
        return button.getSelection();
    }
}
