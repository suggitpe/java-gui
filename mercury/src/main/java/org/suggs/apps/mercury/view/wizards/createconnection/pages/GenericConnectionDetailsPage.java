/*
 * AbstractConnectionDetailsPage.java created on 5 Nov 2008 19:28:57 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.wizards.createconnection.pages;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * This is the abstract implementation for the create details page.
 * 
 * @author suggitpe
 * @version 1.0 5 Nov 2008
 */
public class GenericConnectionDetailsPage extends AbstractCreateConnectionPage {

    private static final String NFE_ERROR_TEXT = "The value that has been entered for the port number is not a number, please only user whole numbers for this field";

    public static final String PAGE_NAME = "GenericConnectionDetailsPage";

    private String hostname;
    private int port;
    private boolean securityEnabled;
    private String username;
    private String password;

    /**
     * Constructs a new instance.
     */
    public GenericConnectionDetailsPage() {
        super( PAGE_NAME, "Enter generic connection details" );
        setDescription( "Enter details for the basic connection information for the broker that you wish to connect to" );
        setPageComplete( false );
    }

    /**
     * @see org.suggs.apps.mercury.view.wizards.createconnection.pages.AbstractCreateConnectionPage#doBuildControls(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void doBuildControls( Composite controlComposite ) {
        Composite c = new ConnectionDetailsComposite( controlComposite );
        c.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
    }

    /**
     * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
     */
    @Override
    public IWizardPage getNextPage() {
        SelectConnectionTypePage sp = (SelectConnectionTypePage) getWizard().getPage( SelectConnectionTypePage.PAGE_NAME );
        if ( sp.getConnectionType().equals( "IBM_MQ" ) ) {
            return getWizard().getPage( IbmMqConnectionDataPage.PAGE_NAME );
        }
        ( (WizardPage) getWizard().getPage( IbmMqConnectionDataPage.PAGE_NAME ) ).setPageComplete( true );
        return getWizard().getPage( ConnectionDataSummaryPage.PAGE_NAME );

    }

    /**
     * Checking method to see if this page is complete
     */
    protected void checkIfPageComplete() {
        if ( hostname == null || hostname.length() == 0 || port == 0 ) {
            setPageComplete( false );
            return;
        }

        if ( securityEnabled ) {
            if ( username == null || username.length() == 0 ) {
                setPageComplete( false );
                return;
            }
        }
        setPageComplete( true );
    }

    /**
     * Getter for the hostname field
     * 
     * @return the hostname field
     */
    public final String getHostname() {
        return hostname;
    }

    /**
     * Getter for the port number
     * 
     * @return the port number
     */
    public final int getPort() {
        return port;
    }

    /**
     * Getter for the security enabled flag
     * 
     * @return the security enabled flag
     */
    public final boolean isSecurityEnabled() {
        return securityEnabled;
    }

    /**
     * Getter for the username field
     * 
     * @return the user name
     */
    public final String getUsername() {
        return username;
    }

    /**
     * Getter for the password field
     * 
     * @return the password field
     */
    public final String getPassword() {
        return password;
    }

    // ##################################
    // ########## INNER CLASSES #########
    // ##################################
    /**
     * Class to represent the data behind the
     * 
     * @author suggitpe
     * @version 1.0 5 Nov 2008
     */
    private class ConnectionDetailsComposite extends Composite {

        /**
         * Constructs a new instance.
         * 
         * @param comp
         */
        public ConnectionDetailsComposite( Composite comp ) {
            super( comp, SWT.NONE );
            setLayout( new GridLayout( 2, false ) );

            // create hostname field
            new Label( this, SWT.NONE ).setText( "Hostname:" );
            final Text hostnameText = new Text( this, SWT.BORDER );
            hostnameText.setLayoutData( TEXT_BOX_STYLE );
            hostnameText.addModifyListener( new ModifyListener() {

                @Override
                public void modifyText( ModifyEvent e ) {
                    hostname = hostnameText.getText();
                    checkIfPageComplete();
                }
            } );

            // port number
            new Label( this, SWT.NONE ).setText( "Port number:" );
            final Text portNumText = new Text( this, SWT.BORDER );
            portNumText.setLayoutData( TEXT_BOX_STYLE );
            portNumText.addModifyListener( new ModifyListener() {

                @Override
                public void modifyText( ModifyEvent e ) {

                    String p = portNumText.getText();
                    if ( p == null || p.equals( "" ) ) {
                        port = 0;
                        return;
                    }

                    int ptNum = 0;
                    try {
                        ptNum = Integer.parseInt( p );
                        port = ptNum;
                    }
                    catch ( NumberFormatException nfe ) {
                        port = 0;
                        MessageDialog.openError( getShell(), "Number Format Error", NFE_ERROR_TEXT );
                        portNumText.setText( "" );
                    }
                    checkIfPageComplete();
                }
            } );

            // set security check box
            new Label( this, SWT.NONE ).setText( "Enable Security:" );
            final Button setSecurity = new Button( this, SWT.CHECK );

            // security username
            new Label( this, SWT.NONE ).setText( "Username:" );
            final Text usernameText = new Text( this, SWT.BORDER | SWT.READ_ONLY );
            usernameText.setLayoutData( TEXT_BOX_STYLE );
            usernameText.setEnabled( false );
            usernameText.addModifyListener( new ModifyListener() {

                @Override
                public void modifyText( ModifyEvent e ) {
                    username = usernameText.getText();
                    checkIfPageComplete();
                }
            } );

            new Label( this, SWT.NONE ).setText( "Password:" );
            final Text passwordText = new Text( this, SWT.BORDER | SWT.READ_ONLY | SWT.PASSWORD );
            passwordText.setLayoutData( TEXT_BOX_STYLE );
            passwordText.setEnabled( false );
            passwordText.addModifyListener( new ModifyListener() {

                @Override
                public void modifyText( ModifyEvent e ) {
                    password = passwordText.getText();
                }
            } );

            // this is down here for obvious reasons
            setSecurity.addSelectionListener( new SelectionAdapter() {

                /**
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected( SelectionEvent e ) {
                    securityEnabled = setSecurity.getSelection();
                    if ( securityEnabled ) {
                        usernameText.setEditable( true );
                        usernameText.setEnabled( true );
                        passwordText.setEditable( true );
                        passwordText.setEnabled( true );
                    }
                    else {
                        usernameText.setEditable( false );
                        usernameText.setEnabled( false );
                        usernameText.setText( "" );
                        passwordText.setEditable( false );
                        passwordText.setEnabled( false );
                        passwordText.setText( "" );
                    }
                    checkIfPageComplete();
                }

            } );
        }

    }

}
