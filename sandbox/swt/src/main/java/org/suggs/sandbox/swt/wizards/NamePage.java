/*
 * NamePage.java created on 21 Oct 2008 18:54:54 by suggitpe for project SandBox - SWT
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
 * Wizard page to collect name information
 * 
 * @author suggitpe
 * @version 1.0 21 Oct 2008
 */
public class NamePage extends WizardPage {

    public static final String PAGE_NAME = "namePage";
    private String firstName = "";
    private String lastName = "";

    /**
     * Constructs a new instance.
     * 
     * @param pageName
     */
    protected NamePage() {
        super( PAGE_NAME, "Name", null );
        setDescription( "Enter the first and last names" );
        setPageComplete( false );
    }

    /**
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl( Composite parent ) {
        Composite comp = new Composite( parent, SWT.NONE );
        comp.setLayout( new GridLayout( 2, false ) );

        // create the label and text for the first name
        new Label( comp, SWT.NONE ).setText( "First name: " );
        final Text first = new Text( comp, SWT.BORDER );
        first.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

        // create the label and text for the last name
        new Label( comp, SWT.NONE ).setText( "Last name: " );
        final Text last = new Text( comp, SWT.BORDER );
        last.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

        // add a listener for the first name
        first.addModifyListener( new ModifyListener() {

            @Override
            public void modifyText( ModifyEvent e ) {
                firstName = first.getText();
                setPageComplete( isPageDone() );
            }
        } );

        // add a listener for the last namE
        last.addModifyListener( new ModifyListener() {

            @Override
            public void modifyText( ModifyEvent e ) {
                lastName = last.getText();
                setPageComplete( isPageDone() );
            }
        } );
        setControl( comp );
    }

    /**
     * @return
     */
    private boolean isPageDone() {
        return ( firstName.length() > 0 && lastName.length() > 0 );
    }

    /**
     * Getter for the first name
     * 
     * @return the first name to return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for the last name
     * 
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

}
