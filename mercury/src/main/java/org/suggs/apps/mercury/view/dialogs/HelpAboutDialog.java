/*
 * HelpAboutDialog.java created on 15 Oct 2008 18:26:58 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.dialogs;

import org.suggs.apps.mercury.ContextProvider;
import org.suggs.apps.mercury.view.images.MercuryImageCanvas;

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
 * This dialog will detail some information about the GUI application and will show this to the caller.
 * 
 * @author suggitpe
 * @version 1.0 15 Oct 2008
 */
public class HelpAboutDialog extends Dialog {

    /**
     * Constructs a new instance.
     * 
     * @param parentShell
     */
    public HelpAboutDialog( Shell parentShell ) {
        super( parentShell );
        parentShell.setText( "Mercury About" );
    }

    /**
     * Sets up the dialog area
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea( Composite parent ) {
        Composite comp = (Composite) super.createDialogArea( parent );

        GridLayout layout = (GridLayout) comp.getLayout();
        layout.numColumns = 3;

        MercuryImageCanvas c = new MercuryImageCanvas( comp );
        GridData d = new GridData( GridData.HORIZONTAL_ALIGN_FILL );
        d.verticalSpan = 3;
        c.setLayoutData( d );

        new Label( comp, SWT.NONE ).setText( "Version:" );
        Text verTxt = new Text( comp, SWT.READ_ONLY );
        verTxt.setText( (String) ContextProvider.instance().getBean( "mercuryVersion" ) );

        new Label( comp, SWT.NONE ).setText( "Author:" );
        Text cpyText = new Text( comp, SWT.READ_ONLY );
        cpyText.setText( "(c) Peter G D Suggitt" );

        new Label( comp, SWT.NONE ).setText( "JVM:" );
        Text jvm = new Text( comp, SWT.READ_ONLY | SWT.MULTI );
        jvm.setText( createJvmText() );

        return comp;
    }

    /**
     * This method will manage the creation of the JVM dta that we present to the user.
     * 
     * @return
     */
    private String createJvmText() {
        return new StringBuffer( "JVM Version " ).append( System.getProperty( "java.version" ) )
            .append( "\n" )
            .append( System.getProperty( "java.vendor" ) )
            .toString();
    }

    /**
     * Sets up the OK button for the about dialog
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar( Composite parent ) {
        createButton( parent, IDialogConstants.OK_ID, "OK", true );
    }

}
