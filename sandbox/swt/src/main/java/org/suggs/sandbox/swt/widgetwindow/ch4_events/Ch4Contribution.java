/*
 * Ch4Contributions.java created on 13 Aug 2008 18:41:01 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch4_events;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * This is a small window that allows us to test the StatusAction action
 * 
 * @author suggitpe
 * @version 1.0 13 Aug 2008
 */
public class Ch4Contribution extends ApplicationWindow {

    private StatusLineManager statLnMgr = new StatusLineManager();
    private Ch4StatusAction action = new Ch4StatusAction( statLnMgr );
    private ActionContributionItem aci = new ActionContributionItem( action );

    /**
     * Constructs a new instance.
     */
    public Ch4Contribution() {
        super( null );
        addStatusLine();
        addMenuBar();
        addToolBar( SWT.FLAT | SWT.WRAP );
    }

    /**
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents( Composite parent ) {
        getShell().setText( "Action/Contribution" );
        parent.setSize( 290, 150 );
        aci.fill( parent );
        return parent;
    }

    /**
     * Main method
     * 
     * @param args
     */
    public static void main( String[] args ) {
        Ch4Contribution swin = new Ch4Contribution();
        swin.setBlockOnOpen( true );
        swin.open();
        Display.getCurrent().dispose();
    }

    /**
     * @see org.eclipse.jface.window.ApplicationWindow#createMenuManager()
     */
    @Override
    protected MenuManager createMenuManager() {
        MenuManager main = new MenuManager( null );
        MenuManager action1 = new MenuManager( "Menu" );
        main.add( action1 );
        action1.add( action1 );
        return main;
    }

    /**
     * @see org.eclipse.jface.window.ApplicationWindow#createToolBarManager(int)
     */
    @Override
    protected ToolBarManager createToolBarManager( int style ) {
        ToolBarManager mgr = new ToolBarManager( style );
        mgr.add( action );
        return mgr;
    }

    /**
     * @see org.eclipse.jface.window.ApplicationWindow#createStatusLineManager()
     */
    @Override
    protected StatusLineManager createStatusLineManager() {
        return statLnMgr;
    }

}
