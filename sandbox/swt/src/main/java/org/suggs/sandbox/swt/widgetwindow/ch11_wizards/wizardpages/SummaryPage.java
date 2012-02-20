/*
 * SummaryPage.java created on 12 Sep 2008 07:08:17 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch11_wizards.wizardpages;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Summary page for a wizard
 * 
 * @author suggitpe
 * @version 1.0 12 Sep 2008
 */
public class SummaryPage extends WizardPage {

    public static final String PAGE_NAME = "Summary";

    private Label label;

    /**
     * Constructs a new instance.
     */
    public SummaryPage() {
        super( PAGE_NAME, "Summary Page", null );
    }

    /**
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl( Composite parent ) {
        Composite top = new Composite( parent, SWT.NONE );
        top.setLayout( new FillLayout() );

        label = new Label( top, SWT.CENTER );
        label.setText( "" );

        setControl( top );
        setPageComplete( true );
    }

    /**
     * Setter for the label
     * 
     * @param txt
     */
    public void updateText( String txt ) {
        label.setText( txt );
    }

}
