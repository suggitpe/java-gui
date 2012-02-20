/*
 * ComplexGridLayoutExample.java created on 9 Apr 2008 07:06:14 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.layout.gridlayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class aims to show a varying range of layouts and SWT widgets that can be aranged on a GUI canvas
 * 
 * @author suggitpe
 * @version 1.0 9 Apr 2008
 */
public final class ComplexGridLayoutExample {

    private static final Logger LOG = LoggerFactory.getLogger( ComplexGridLayoutExample.class );

    private static Display display;
    private static Shell shell;
    private static Text dogName;
    private static Combo dogBreed;
    private static Canvas dogPhoto;
    private static Image dogImage;
    private static List categories;
    private static Text ownerName;
    private static Text ownerPhone;

    private ComplexGridLayoutExample() {}

    public static void main( String[] args ) {

        LOG.debug( "Creating GUI" );
        // set up the shell
        display = new Display();
        shell = new Shell( display );
        shell.setText( "Dog Show Entry" );

        // now set up the high level layout
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        shell.setLayout( layout );

        // ------------------------------------
        // dog name
        new Label( shell, SWT.NULL ).setText( "Dog's name:" );
        dogName = new Text( shell, SWT.SINGLE | SWT.BORDER );
        GridData data = new GridData( GridData.HORIZONTAL_ALIGN_FILL );
        data.horizontalSpan = 2;
        dogName.setLayoutData( data );

        // breed combo box
        new Label( shell, SWT.NULL ).setText( "Dog's breed:" );
        dogBreed = new Combo( shell, SWT.NULL );
        dogBreed.setItems( new String[] { "Collie", "Pitbull", "Poodle", "Scottie" } );
        dogBreed.setLayoutData( new GridData( GridData.HORIZONTAL_ALIGN_FILL ) );

        // categories label
        Label l = new Label( shell, SWT.NULL );
        l.setText( "Categories" );
        l.setLayoutData( new GridData( GridData.HORIZONTAL_ALIGN_FILL ) );

        // Photos
        new Label( shell, SWT.NULL ).setText( "Photo:" );
        dogPhoto = new Canvas( shell, SWT.BORDER );
        data = new GridData( GridData.FILL_BOTH );
        data.widthHint = 80;
        data.heightHint = 80;
        data.verticalSpan = 3;
        dogPhoto.setLayoutData( data );
        dogPhoto.addPaintListener( new PaintListener() {

            @Override
            public void paintControl( PaintEvent aEvent ) {
                if ( dogImage != null ) {
                    aEvent.gc.drawImage( dogImage, 0, 0 );
                }
            }
        } );

        // categories
        categories = new List( shell, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL );
        categories.setItems( new String[] { "Best of breed", "Prettiest female", "Handsomest male",
                                           "Best dressed", "Fluffiest ears", "Most colours",
                                           "Best performer", "Loudest bark", "Best behaved",
                                           "Prettiest eyes", "Most hair", "Longest tail", "Cutest trick",
                                           "Most stupig grin" } );
        data = new GridData( GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL );
        data.verticalSpan = 4;
        int listHeight = categories.getItemHeight() * 12;
        Rectangle trim = categories.computeTrim( 0, 0, 0, listHeight );
        data.heightHint = trim.height;
        categories.setLayoutData( data );

        // add a button to load the doggie picture
        Button browse = new Button( shell, SWT.PUSH );
        browse.setText( "Browse..." );
        data = new GridData( GridData.HORIZONTAL_ALIGN_FILL );
        data.horizontalIndent = 5;
        browse.setLayoutData( data );
        browse.addSelectionListener( new SelectionAdapter() {

            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected( SelectionEvent event ) {
                String filename = new FileDialog( shell ).open();
                if ( filename != null ) {
                    dogImage = new Image( display, filename );
                }
            }

        } );

        // add button to delete the doggie picture
        Button del = new Button( shell, SWT.PUSH );
        del.setText( "Delete" );
        data = new GridData( GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_BEGINNING );
        data.horizontalIndent = 5;
        del.setLayoutData( data );
        del.addSelectionListener( new SelectionAdapter() {

            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected( SelectionEvent event ) {
                if ( dogImage != null ) {
                    dogImage.dispose();
                    dogImage = null;
                    dogPhoto.redraw();
                }
            }
        } );

        // create a group for the owner layout
        Group ownerInfo = new Group( shell, SWT.NULL );
        ownerInfo.setText( "Owner Info" );
        layout = new GridLayout();
        layout.numColumns = 2;
        ownerInfo.setLayout( layout );
        data = new GridData( GridData.HORIZONTAL_ALIGN_FILL );
        data.horizontalSpan = 2;
        ownerInfo.setLayoutData( data );

        // set up owner name
        new Label( ownerInfo, SWT.NULL ).setText( "Name:" );
        ownerName = new Text( ownerInfo, SWT.SINGLE | SWT.BORDER );
        ownerName.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

        // set up the owner phone
        new Label( ownerInfo, SWT.NULL ).setText( "Phone:" );
        ownerPhone = new Text( ownerInfo, SWT.SINGLE | SWT.BORDER );
        ownerPhone.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

        // complete with an enter button
        Button enter = new Button( shell, SWT.PUSH );
        enter.setText( "Enter" );
        data = new GridData( GridData.HORIZONTAL_ALIGN_END );
        data.horizontalSpan = 3;
        enter.setLayoutData( data );
        enter.addSelectionListener( new SelectionAdapter() {

            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected( SelectionEvent event ) {
                StringBuffer buff = new StringBuffer();
                buff.append( "\nDog Name:" ).append( dogName.getText() ).append( "\n" );
                buff.append( "Dog Breed:" ).append( dogBreed.getText() ).append( "\n" );
                buff.append( "Owner name:" ).append( ownerName.getText() ).append( "\n" );
                buff.append( "Owner phone:" ).append( ownerPhone.getText() ).append( "\n" );
                buff.append( "Categories:\n" );
                for ( String s : categories.getSelection() ) {
                    buff.append( "\t" ).append( s ).append( "\n" );
                }

                LOG.info( buff.toString() );
            }
        } );

        // ------------------------------------
        LOG.debug( "Opening GUI" );
        // now get the dialog up and running
        shell.pack();
        shell.open();
        while ( !shell.isDisposed() ) {
            if ( !display.readAndDispatch() ) {
                display.sleep();
            }
        }
        LOG.debug( "All done" );
    }
}
