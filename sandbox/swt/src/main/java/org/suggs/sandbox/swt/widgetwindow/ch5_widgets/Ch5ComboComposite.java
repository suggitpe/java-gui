/*
 * Ch5ComboComposite.java created on 19 Aug 2008 18:17:56 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch5_widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

/**
 * Composite class used to show how the different combo box styles work.
 * 
 * @author suggitpe
 * @version 1.0 19 Aug 2008
 */
public class Ch5ComboComposite extends Composite {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composit to associate this class with
     */
    public Ch5ComboComposite( Composite parent ) {
        super( parent, SWT.NONE );
        buildControls();
    }

    /**
     * Build the GUI colntrols
     */
    private void buildControls() {
        setLayout( new RowLayout() );
        int[] comboStyles = { SWT.SIMPLE, SWT.DROP_DOWN, SWT.READ_ONLY };

        for ( int i : comboStyles ) {
            Combo c = new Combo( this, i );
            c.add( "Option #1" );
            c.add( "Option #2" );
            c.add( "Option #3" );
        }

    }

}
