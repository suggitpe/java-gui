/*
 * AddEntryWizard.java created on 21 Oct 2008 18:52:11 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.wizards;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

/**
 * Address Entry Wizard demonstration.
 * 
 * @author suggitpe
 * @version 1.0 21 Oct 2008
 */
public class AddEntryWizard extends Wizard {

    private WelcomePage welcomePage;
    private NamePage namePage;
    private EmailPage emailPage;

    /**
     * Constructs a new instance.
     */
    public AddEntryWizard() {
        welcomePage = new WelcomePage();
        namePage = new NamePage();
        emailPage = new EmailPage();

        addPage( welcomePage );
        addPage( namePage );
        addPage( emailPage );

        setWindowTitle( "Address Book Entry Wizard" );
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        @SuppressWarnings("unused")
        AddressEntry entry = new AddressEntry( namePage.getFirstName(),
                                               namePage.getLastName(),
                                               emailPage.getEmailAddress() );

        MessageDialog.openInformation( getShell(),
                                       "Demo Complete",
                                       "You have entered the following ...\nFirst name: "
                                                       + namePage.getFirstName() + "\nLast name: "
                                                       + namePage.getLastName() + "\nEmail address: "
                                                       + emailPage.getEmailAddress() );

        return true;
    }

}
