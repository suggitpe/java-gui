/*
 * ComponentViewer.java created on 12 Aug 2008 05:55:14 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow;

import org.suggs.sandbox.swt.widgetwindow.ch11_wizards.Ch11WizardComposite;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * Test class to allow you to view a SWT component
 * 
 * @author suggitpe
 * @version 1.0 12 Aug 2008
 */
public class ComponentViewer extends ApplicationWindow {

    /**
     * Constructs a new instance.
     */
    public ComponentViewer() {
        super( null );
    }

    /**
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    @SuppressWarnings("unused")
    @Override
    protected Control createContents( Composite parent ) {
        // new Ch3Group( parent );
        // new Ch3SashForm( parent );

        // new Ch4MouseKey( parent );

        // new Ch5Capitaliser( parent );
        // new Ch5Undoable( parent );
        // new Ch5ComboComposite( parent );

        // new Ch6FillLayoutComposite( parent );
        // new Ch6RowLayoutComposite( parent );
        // new Ch6GridLayoutComposite( parent );
        // new Ch6RadialLayoutComposite( parent );

        // new Ch7Colors( parent );
        // new Ch7Fonts( parent );
        // new Ch7Composite( parent );

        // new Ch8ListComposite( parent );
        // new Ch8TreeComposite( parent );

        // new Ch9TableEditorComposite( parent );

        // new Ch10CustomDialogComposite( parent );

        new Ch11WizardComposite( parent );
        return parent;
    }

    /**
     * Main method
     * 
     * @param args
     */
    public static void main( String[] args ) {
        ComponentViewer v = new ComponentViewer();
        v.setBlockOnOpen( true );
        v.open();
        Display.getCurrent().dispose();
    }

}
