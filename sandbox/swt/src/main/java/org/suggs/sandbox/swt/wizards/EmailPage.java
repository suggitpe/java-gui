/*
 * EmailPage.java created on 21 Oct 2008 18:55:12 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Wizard page used to get an email address
 * 
 * @author suggitpe
 * @version 1.0 21 Oct 2008
 */
public class EmailPage extends WizardPage {

    public static final String PAGE_NAME = "emailPage";

    private String emailAddress = "";

    /**
     * Constructs a new instance.
     * 
     * @param pageName
     */
    protected EmailPage() {
        super( PAGE_NAME, "Email", null );
        setDescription( "Enter an email address" );
        setPageComplete( false );
    }

    /**
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl( Composite parent ) {
        Composite comp = new Composite( parent, SWT.NONE );
        comp.setLayout( new GridLayout( 2, false ) );

        // add the email address label and text
        new Label( comp, SWT.NONE ).setText( "Email Address:" );
        final Text txt = new Text( comp, SWT.BORDER );
        txt.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

        txt.addModifyListener( new ModifyListener() {

            @Override
            public void modifyText( ModifyEvent e ) {
                emailAddress = txt.getText();
                setPageComplete( emailAddress.length() > 0 );
            }
        } );

        setControl( comp );
    }

    /**
     * Getter for the email address
     * 
     * @return email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

}
