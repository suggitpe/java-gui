/*
 * WelcomePage.java created on 21 Oct 2008 18:54:37 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Wizard page to welcome people to the wizard
 * 
 * @author suggitpe
 * @version 1.0 21 Oct 2008
 */
public class WelcomePage extends WizardPage {

    public static final String PAGE_NAME = "welcomePage";

    /**
     * Constructs a new instance.
     * 
     * @param pageName
     */
    protected WelcomePage() {
        super( PAGE_NAME, "Welcome", null );
        setDescription( "Welcome to the Address Book Entry Wizard" );
    }

    /**
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl( Composite parent ) {
        Composite comp = new Composite( parent, SWT.NONE );
        comp.setLayout( new FillLayout( SWT.VERTICAL ) );

        new Label( comp, SWT.NONE ).setText( "Welcome to the Address Book Entry Wizard" );
        new Label( comp, SWT.NONE ).setText( "This wizard guides you through creating an address bok entry" );
        new Label( comp, SWT.NONE ).setText( "Click next to continue." );

        setControl( comp );
    }

}
