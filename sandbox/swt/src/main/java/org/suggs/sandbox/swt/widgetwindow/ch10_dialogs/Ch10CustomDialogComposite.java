/*
 * Ch10CustomDialogComposite.java created on 10 Sep 2008 18:54:09 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch10_dialogs;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;

/**
 * TODO Write javadoc for Ch10CustomDialogComposite
 * 
 * @author suggitpe
 * @version 1.0 10 Sep 2008
 */
public class Ch10CustomDialogComposite extends Composite {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composite to associate with
     */
    public Ch10CustomDialogComposite( Composite parent ) {
        super( parent, SWT.NONE );
        buildControls();
    }

    /**
     * Builds the composite controls
     */
    private void buildControls() {
        GridLayout lay = new GridLayout( 4, false );
        setLayout( lay );

        new Label( this, SWT.RIGHT ).setText( "Username dialog" );
        Button dialogBtn = new Button( this, SWT.PUSH );
        dialogBtn.setText( "Password dialog ..." );
        dialogBtn.addSelectionListener( new SelectionListener() {

            @Override
            public void widgetDefaultSelected( SelectionEvent arg0 ) {}

            @Override
            public void widgetSelected( SelectionEvent arg0 ) {
                UsernamePasswordDialog diag = new UsernamePasswordDialog( getShell() );
                diag.open();
                // you would then interrogate the dialog for the data
            }
        } );

        new Label( this, SWT.RIGHT ).setText( "Colour dialog" );
        Button colBtn = new Button( this, SWT.PUSH );
        colBtn.setText( "Colour dialog ..." );
        colBtn.addSelectionListener( new SelectionListener() {

            @Override
            public void widgetDefaultSelected( SelectionEvent arg0 ) {}

            @Override
            public void widgetSelected( SelectionEvent arg0 ) {
                ColorDialog d = new ColorDialog( getShell() );
                d.open();// returns RGB
            }
        } );

        new Label( this, SWT.RIGHT ).setText( "Directory dialog" );
        Button dirBtn = new Button( this, SWT.PUSH );
        dirBtn.setText( "Directory dialog ..." );
        dirBtn.addSelectionListener( new SelectionListener() {

            @Override
            public void widgetDefaultSelected( SelectionEvent arg0 ) {}

            @Override
            public void widgetSelected( SelectionEvent arg0 ) {
                DirectoryDialog d = new DirectoryDialog( getShell() );
                d.setMessage( "Please selecvt a directory ..." );
                d.open(); // returns string

                // here you would do some validation of the returned
                // value from open;
            }
        } );

        new Label( this, SWT.RIGHT ).setText( "File dialog" );
        Button fileBtn = new Button( this, SWT.PUSH );
        fileBtn.setText( "File dialog ..." );
        fileBtn.addSelectionListener( new SelectionListener() {

            @Override
            public void widgetDefaultSelected( SelectionEvent arg0 ) {}

            @Override
            public void widgetSelected( SelectionEvent arg0 ) {
                FileDialog d = new FileDialog( getShell(), SWT.MULTI );
                d.setFilterExtensions( new String[] { ".txt" } );
                d.open(); // this returns a string

                // if selected multiple files then we would need to
                // call getFileNames to get the files selected
            }
        } );

        new Label( this, SWT.RIGHT ).setText( "Message dialog" );
        Button msgBtn = new Button( this, SWT.PUSH );
        msgBtn.setText( "Message dialog ..." );
        msgBtn.addSelectionListener( new SelectionListener() {

            @Override
            public void widgetDefaultSelected( SelectionEvent arg0 ) {}

            @Override
            public void widgetSelected( SelectionEvent arg0 ) {
                MessageDialog d = new MessageDialog( getShell(),
                                                     "Greeting dialog",
                                                     null,
                                                     "Hello how are you today?",
                                                     MessageDialog.QUESTION,
                                                     new String[] { "Good", "been Better", "Overly Excited" },
                                                     0 );
                d.open();
            }
        } );

        new Label( this, SWT.RIGHT ).setText( "Error dialog" );
        Button errBtn = new Button( this, SWT.PUSH );
        errBtn.setText( "Error dialog ..." );
        errBtn.addSelectionListener( new SelectionListener() {

            @Override
            public void widgetDefaultSelected( SelectionEvent arg0 ) {}

            @Override
            public void widgetSelected( SelectionEvent arg0 ) {
                ErrorDialog d = new ErrorDialog( getShell(),
                                                 "Test Error Dialog",
                                                 "This is a test error dialog",
                                                 createStatus(),
                                                 IStatus.ERROR | IStatus.INFO );
                d.open();
            }
        } );

        final IInputValidator validator = new IInputValidator() {

            @Override
            public String isValid( String txt ) {
                if ( txt.length() < 5 ) {
                    return "You must enter at least 5 characters";
                }
                else if ( txt.length() > 22 ) {
                    return "You cnnot enter more than 22 characters";
                }
                else {
                    return null;
                }
            }
        };

        new Label( this, SWT.RIGHT ).setText( "Input dialog" );
        Button inBtn = new Button( this, SWT.PUSH );
        inBtn.setText( "Input dialog ..." );
        inBtn.addSelectionListener( new SelectionListener() {

            @Override
            public void widgetDefaultSelected( SelectionEvent arg0 ) {}

            @Override
            public void widgetSelected( SelectionEvent arg0 ) {
                InputDialog d = new InputDialog( getShell(),
                                                 "Please input a string",
                                                 "Enter a string",
                                                 "default txt",
                                                 validator );
                d.open();

            }
        } );

    }

    private IStatus createStatus() {
        final String dummyPlugin = "some dummy plugin";
        IStatus[] statuses = new IStatus[2];

        statuses[0] = new Status( IStatus.ERROR,
                                  dummyPlugin,
                                  IStatus.OK,
                                  "Oh no an exception occurred",
                                  new Exception() );

        statuses[1] = new Status( IStatus.INFO, dummyPlugin, IStatus.OK, "more errors", new Exception() );

        MultiStatus ret = new MultiStatus( dummyPlugin,
                                           IStatus.OK,
                                           statuses,
                                           "More than one error has occured",
                                           new Exception() );

        return ret;
    }
}
