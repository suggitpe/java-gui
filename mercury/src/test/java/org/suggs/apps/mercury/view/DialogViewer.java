/*
 * DialogViewer.java created on 15 Oct 2008 18:42:05 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view;

import org.suggs.apps.mercury.view.wizards.createconnection.CreateConnectionWizard;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.wizard.WizardDialog;

/**
 * This test class when executed will open up a dialog box and show it
 * to the tester
 * 
 * @author suggitpe
 * @version 1.0 15 Oct 2008
 */
public class DialogViewer
{

    /**
     * @param args
     */
    public static void main( String[] args )
    {
        ApplicationWindow w = new ApplicationWindow( null );

        w.setBlockOnOpen( false );
        w.open();

        // HelpAboutDialog d = new HelpAboutDialog( w.getShell() );
        // HelpConnectionDebugDialog d = new
        // HelpConnectionDebugDialog( w.getShell() );
        // d.open();
        WizardDialog wiz = new WizardDialog( w.getShell(), new CreateConnectionWizard() );
        wiz.open();
    }

}
