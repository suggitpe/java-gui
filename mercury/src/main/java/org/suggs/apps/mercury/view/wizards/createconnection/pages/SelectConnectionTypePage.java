/*
 * SelectConnectionTypePage.java created on 17 Sep 2008 18:50:55 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.wizards.createconnection.pages;

import org.suggs.apps.mercury.ContextProvider;

import java.util.HashMap;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * This page forces the choice of making the decision about which JMS provider to select.
 * 
 * @author suggitpe
 * @version 1.0 17 Sep 2008
 */
public class SelectConnectionTypePage extends AbstractCreateConnectionPage {

    public static final String PAGE_NAME = "CreateConnectionType";
    private String connType;
    private String connName;
    private String[] options;

    /**
     * Constructs a new instance.
     */
    @SuppressWarnings("unchecked")
    public SelectConnectionTypePage() {
        super( PAGE_NAME, "ConnectionContext Type Selection" );
        setDescription( "Select the middleware implementation from the list below" );

        HashMap<String, String> map = (HashMap<String, String>) ContextProvider.instance()
            .getBean( "adapterList" );
        Set<String> keySet = map.keySet();
        options = keySet.toArray( new String[keySet.size()] );

        setPageComplete( false );
    }

    /**
     * @see org.suggs.apps.mercury.view.wizards.createconnection.pages.AbstractCreateConnectionPage#doBuildControls(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void doBuildControls( Composite controlComposite ) {
        Composite c = new SelectConnectionComposite( controlComposite );
        c.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
    }

    /**
     * Checks to see if the pge has been correctly set up
     */
    private void checkIfPageComplete() {
        if ( connName.length() > 0 && connType.length() > 0 ) {
            setPageComplete( true );
        }
        else {
            setPageComplete( false );
        }
    }

    /**
     * Getter for the text from the combo box
     * 
     * @return the text from the combo box
     */
    public String getConnectionType() {
        return connType;
    }

    /**
     * Getter for the connection name
     * 
     * @return the name of the connection
     */
    public String getConnectionName() {
        return connName;
    }

    /**
     * This composite class is created to house the widgets associated with this screen.
     * 
     * @author suggitpe
     * @version 1.0 5 Nov 2008
     */
    protected class SelectConnectionComposite extends Composite {

        /**
         * Constructs a new instance.
         * 
         * @param comp
         */
        public SelectConnectionComposite( Composite comp ) {
            super( comp, SWT.NONE );
            setLayout( new GridLayout( 2, false ) );

            // build the name entry textbox
            new Label( this, SWT.NONE ).setText( "ConnectionContext Name:" );
            final Text name = new Text( this, SWT.BORDER );
            name.setLayoutData( TEXT_BOX_STYLE );
            name.addModifyListener( new ModifyListener() {

                @Override
                public void modifyText( ModifyEvent e ) {
                    connName = name.getText();
                    checkIfPageComplete();
                }
            } );

            // build the middleware selector
            new Label( this, SWT.CENTER ).setText( "ConnectionContext Type:" );
            final Combo combo = new Combo( this, SWT.DROP_DOWN );
            combo.setLayoutData( TEXT_BOX_STYLE );
            combo.setItems( options );
            combo.addSelectionListener( new SelectionAdapter() {

                /**
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected( SelectionEvent selEv ) {
                    connType = combo.getText();
                    checkIfPageComplete();
                }

            } );
        }
    }
}
