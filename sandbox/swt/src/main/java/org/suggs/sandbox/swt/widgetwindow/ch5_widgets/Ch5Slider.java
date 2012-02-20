/*
 * Ch5Slider.java created on 19 Aug 2008 18:35:04 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch5_widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Slider;

/**
 * Composite class to show how to use a slider widget
 * 
 * @author suggitpe
 * @version 1.0 19 Aug 2008
 */
public class Ch5Slider extends Composite {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composit to associate this class with
     */
    public Ch5Slider( Composite parent ) {
        super( parent, SWT.NONE );
        setLayout( new FillLayout() );
        Slider s = new Slider( this, SWT.HORIZONTAL );
        s.setValues( 1000, 400, 1600, 200, 10, 100 );
    }

}
