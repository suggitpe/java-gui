/*
 * ConnectionPanel.java created on 24 Nov 2008 08:57:08 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.panels;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Connection Panel.
 * 
 * @author suggitpe
 * @version 1.0 24 Nov 2008
 */
public class ConnectionPanel extends Composite {

    /**
     * Constructs a new instance.
     * 
     * @param parent
     */
    public ConnectionPanel( Composite parent ) {
        super( parent, SWT.BORDER );
        setLayout( new RowLayout() );
    }
}
