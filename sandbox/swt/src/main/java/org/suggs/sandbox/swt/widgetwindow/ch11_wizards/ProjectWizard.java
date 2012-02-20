/*
 * ProjectWizard.java created on 11 Sep 2008 18:47:54 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch11_wizards;

import org.suggs.sandbox.swt.widgetwindow.ch11_wizards.wizardpages.ChooseDirectoryPage;
import org.suggs.sandbox.swt.widgetwindow.ch11_wizards.wizardpages.DirectoryPage;
import org.suggs.sandbox.swt.widgetwindow.ch11_wizards.wizardpages.SummaryPage;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author suggitpe
 * @version 1.0 11 Sep 2008
 */
public class ProjectWizard extends Wizard {

    private static final Logger LOG = LoggerFactory.getLogger( ProjectWizard.class );

    /**
     * Constructs a new instance.
     */
    public ProjectWizard() {
        super();
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        addPage( new DirectoryPage() );
        addPage( new ChooseDirectoryPage() );
        addPage( new SummaryPage() );
    }

    /**
     * This is the method where we take the data collected in the wizard pages and do something with it.
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        DirectoryPage dPage = (DirectoryPage) getPage( DirectoryPage.PAGE_NAME );
        if ( dPage.useDefaultDirectory() ) {
            LOG.debug( "Using default directoy" );
        }
        else {
            ChooseDirectoryPage cPage = (ChooseDirectoryPage) getPage( ChooseDirectoryPage.PAGE_NAME );
            LOG.debug( "Using directory " + cPage.getDirectory() );
        }

        return true;
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#performCancel()
     */
    @Override
    public boolean performCancel() {
        LOG.debug( "Cancel called" );
        return true;
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
     */
    @Override
    public IWizardPage getNextPage( IWizardPage page ) {
        if ( page instanceof DirectoryPage ) {
            DirectoryPage p = (DirectoryPage) page;
            if ( p.useDefaultDirectory() ) {
                SummaryPage s = (SummaryPage) getPage( SummaryPage.PAGE_NAME );
                s.updateText( "Using default directory" );
                return s;
            }
        }

        IWizardPage next = super.getNextPage( page );
        if ( next instanceof SummaryPage ) {
            SummaryPage s = (SummaryPage) next;
            DirectoryPage d = (DirectoryPage) getPage( DirectoryPage.PAGE_NAME );

            s.updateText( d.useDefaultDirectory()
                            ? "Using default directory"
                            : "Using directory "
                              + ( (ChooseDirectoryPage) getPage( ChooseDirectoryPage.PAGE_NAME ) ).getDirectory() );
        }
        return next;
    }

}
