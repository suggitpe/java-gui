/*
 * Ch11WizardComposite.java created on 12 Sep 2008 18:16:48 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch11_wizards;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * Composite to house a wizard
 * 
 * @author suggitpe
 * @version 1.0 12 Sep 2008
 */
public class Ch11WizardComposite extends Composite {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composite to attach to
     */
    public Ch11WizardComposite( Composite parent ) {
        super( parent, SWT.NONE );
        buildControls();
    }

    private void buildControls() {
        final Composite parent = this;
        parent.setLayout( new GridLayout() );

        Button dBut = new Button( parent, SWT.PUSH );
        dBut.setText( "Wizard dialog ..." );
        dBut.addSelectionListener( new SelectionListener() {

            @Override
            public void widgetDefaultSelected( SelectionEvent arg0 ) {}

            @Override
            public void widgetSelected( SelectionEvent arg0 ) {
                WizardDialog d = new WizardDialog( parent.getShell(), new ProjectWizard() );
                d.open();
            }
        } );

    }

}
