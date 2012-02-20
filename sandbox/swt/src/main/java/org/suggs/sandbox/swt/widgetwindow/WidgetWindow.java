/*
 * WidgetWindow.java created on 11 Aug 2008 07:17:52 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow;

import org.suggs.sandbox.swt.widgetwindow.ch10_dialogs.Ch10CustomDialogComposite;
import org.suggs.sandbox.swt.widgetwindow.ch11_wizards.Ch11WizardComposite;
import org.suggs.sandbox.swt.widgetwindow.ch3_composites.Ch3Composite;
import org.suggs.sandbox.swt.widgetwindow.ch4_events.Ch4Composite;
import org.suggs.sandbox.swt.widgetwindow.ch5_widgets.Ch5Composite;
import org.suggs.sandbox.swt.widgetwindow.ch6_layouts.Ch6FormLayoutComposite;
import org.suggs.sandbox.swt.widgetwindow.ch7_graphics.Ch7Composite;
import org.suggs.sandbox.swt.widgetwindow.ch8_trees.Ch8ListComposite;
import org.suggs.sandbox.swt.widgetwindow.ch8_trees.Ch8TreeComposite;
import org.suggs.sandbox.swt.widgetwindow.ch9_tbles.Ch9TableEditorComposite;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

/**
 * Construct the basic window and its operations.
 * 
 * @author suggitpe
 * @version 1.0 11 Aug 2008
 */
public class WidgetWindow extends ApplicationWindow {

    /**
     * Constructs a new instance.
     */
    public WidgetWindow() {
        // let parent set up shell
        super( null );
    }

    /**
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents( Composite parent ) {
        TabFolder f = new TabFolder( parent, SWT.NONE );

        TabItem chap3b = new TabItem( f, SWT.NONE );
        chap3b.setText( "Chapter 3" );
        chap3b.setControl( new Ch3Composite( f ) );

        TabItem chap4 = new TabItem( f, SWT.NONE );
        chap4.setText( "Chapter 4" );
        chap4.setControl( new Ch4Composite( f ) );

        TabItem chap5 = new TabItem( f, SWT.NONE );
        chap5.setText( "Chapter 5 widgets" );
        chap5.setControl( new Ch5Composite( f ) );

        TabItem chap6 = new TabItem( f, SWT.NONE );
        chap6.setText( "Chapter 6 layout" );
        chap6.setControl( new Ch6FormLayoutComposite( f ) );

        TabItem chap7 = new TabItem( f, SWT.NONE );
        chap7.setText( "Chapter 7 graphics" );
        chap7.setControl( new Ch7Composite( f ) );

        TabItem chap8a = new TabItem( f, SWT.NONE );
        chap8a.setText( "Chapter 8a Trees" );
        chap8a.setControl( new Ch8TreeComposite( f ) );

        TabItem chap8b = new TabItem( f, SWT.NONE );
        chap8b.setText( "Chapter 8b Lists" );
        chap8b.setControl( new Ch8ListComposite( f ) );

        TabItem chap9 = new TabItem( f, SWT.NONE );
        chap9.setText( "Chapter 9 tables" );
        chap9.setControl( new Ch9TableEditorComposite( f ) );

        TabItem chap10 = new TabItem( f, SWT.NONE );
        chap10.setText( "Chapter 10 dialogs" );
        chap10.setControl( new Ch10CustomDialogComposite( f ) );

        TabItem chap11 = new TabItem( f, SWT.NONE );
        chap11.setText( "Chapter 11 wizards" );
        chap11.setControl( new Ch11WizardComposite( f ) );

        getShell().setText( "Widget Window" );
        return parent;
    }

    /**
     * Main app
     * 
     * @param args
     */
    public static void main( String[] args ) {
        WidgetWindow wwin = new WidgetWindow();
        wwin.setBlockOnOpen( true );
        wwin.open();
        Display.getCurrent().dispose();
    }

}
