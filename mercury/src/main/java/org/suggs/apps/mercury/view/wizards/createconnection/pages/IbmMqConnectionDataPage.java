/*
 * IbmMqConnectionData.java created on 10 Nov 2008 18:55:02 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.wizards.createconnection.pages;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * This page is to be used as an optional page for when the underlying middleware is an IBM MQ middleware.
 * 
 * @author suggitpe
 * @version 1.0 10 Nov 2008
 */
public class IbmMqConnectionDataPage extends AbstractCreateConnectionPage {

    public static final String PAGE_NAME = "IbmMqConnectionDataPage";
    private String channelName;

    /**
     * Constructs a new instance.
     */
    public IbmMqConnectionDataPage() {
        super( PAGE_NAME, "Populate additional MQ connection data" );
        setPageComplete( false );
        setDescription( "Please enter the additional connection details required for an IBM MQ connection" );
    }

    /**
     * @see org.suggs.apps.mercury.view.wizards.createconnection.pages.AbstractCreateConnectionPage#doBuildControls(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void doBuildControls( Composite controlComposite ) {
        Composite c = new IbmConnectionDetailsComposite( controlComposite );
        c.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
    }

    /**
     * Getter for the channel name field
     * 
     * @return the channel name field
     */
    public final String getChannelName() {
        return channelName;
    }

    /**
     * Private method to check if the page has been completed successfully.
     */
    private void checkIfPageComplete() {
        if ( channelName == null || channelName.length() == 0 ) {
            setPageComplete( false );
        }
        else {
            setPageComplete( true );
        }
    }

    /**
     * Private composite class that can be added to the main page class
     * 
     * @author suggitpe
     * @version 1.0 11 Nov 2008
     */
    private class IbmConnectionDetailsComposite extends Composite {

        /**
         * Constructs a new instance.
         * 
         * @param comp
         */
        public IbmConnectionDetailsComposite( Composite comp ) {
            super( comp, SWT.NONE );
            setLayout( new GridLayout( 2, false ) );

            new Label( this, SWT.NONE ).setText( "Channel name:" );
            final Text channel = new Text( this, SWT.BORDER );
            channel.setLayoutData( TEXT_BOX_STYLE );
            channel.addModifyListener( new ModifyListener() {

                @Override
                public void modifyText( ModifyEvent e ) {
                    channelName = channel.getText();
                    checkIfPageComplete();
                }
            } );
        }
    }

}
