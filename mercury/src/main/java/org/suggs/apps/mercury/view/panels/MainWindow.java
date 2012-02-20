/*
 * MainWindow.java created on 24 Nov 2008 09:08:00 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.panels;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

/**
 * This class manages what the main window looks like
 * 
 * @author suggitpe
 * @version 1.0 24 Nov 2008
 */
public class MainWindow extends Composite {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     */
    public MainWindow( Composite parent ) {
        super( parent, SWT.BORDER );
        setLayout( new FillLayout() );

        // now create the two underlying composites
        SashForm sf = new SashForm( this, SWT.HORIZONTAL );

        @SuppressWarnings("unused")
        ConnectionTreePanel connectionTreePanel = new ConnectionTreePanel( sf );

        TabFolder tf = new TabFolder( sf, SWT.NONE );

        TabItem admin = new TabItem( tf, SWT.NONE );
        admin.setText( "Admin" );
        admin.setControl( new AdminTab( tf ) );

        TabItem jms = new TabItem( tf, SWT.NONE );
        jms.setText( "JMS" );
        jms.setControl( new JmsTab( tf ) );

        sf.setWeights( new int[] { 1, 3 } );
    }
}

/**
 * @author suggitpe
 * @version 1.0 25 Nov 2008
 */
class JmsTab extends Composite {

    /**
     * Constructs a new instance.
     */
    JmsTab( Composite comp ) {
        super( comp, SWT.NONE );
    }
}

/**
 * This class will represent the admin tab for the tab folder in the main window.
 * 
 * @author suggitpe
 * @version 1.0 25 Nov 2008
 */
class AdminTab extends Composite {

    /**
     * Constructs a new instance.
     */
    AdminTab( Composite comp ) {
        super( comp, SWT.NONE );
        setLayout( new FillLayout() );

        @SuppressWarnings("unused")
        MessagePanel messagePanel = new MessagePanel( this );
    }
}
