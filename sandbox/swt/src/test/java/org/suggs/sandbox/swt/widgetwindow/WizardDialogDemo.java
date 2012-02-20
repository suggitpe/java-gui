/*
 * WizardDialogDemo.java created on 11 Sep 2008 18:55:02 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow;

import org.suggs.sandbox.swt.wizards.AddEntryWizard;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.wizard.WizardDialog;

/**
 * Test harness that
 * 
 * @author suggitpe
 * @version 1.0 11 Sep 2008
 */
public class WizardDialogDemo {

    /**
     * @param args
     */
    public static void main( String[] args ) {
        ApplicationWindow w = new ApplicationWindow( null );

        w.setBlockOnOpen( false );
        w.open();

        AddEntryWizard wiz = new AddEntryWizard();
        WizardDialog d = new WizardDialog( w.getShell(), wiz );

        d.create();
        d.open();
    }
}
