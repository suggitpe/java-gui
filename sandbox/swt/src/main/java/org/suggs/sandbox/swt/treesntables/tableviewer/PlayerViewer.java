/*
 * PlayerViewer.java created on 2 Dec 2008 19:23:55 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.treesntables.tableviewer;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO Write javadoc for PlayerViewer
 * 
 * @author suggitpe
 * @version 1.0 2 Dec 2008
 */
public class PlayerViewer extends ApplicationWindow {

    private static final Logger LOG = LoggerFactory.getLogger( PlayerViewer.class );

    /**
     * Constructs a new instance.
     */
    public PlayerViewer() {
        super( null );
    }

    /**
     * Run the shell and GUI within
     */
    public void run() {
        setBlockOnOpen( true );
        open();
        LOG.debug( "finished" );
        Display.getCurrent().dispose();
    }

    /**
     * @see org.eclipse.jface.window.ApplicationWindow#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell( Shell shell ) {
        super.configureShell( shell );
        shell.setText( "Player Viewer" );
        shell.setSize( 400, 600 );
    }

    /**
     * Here we just set up the table and set it to work with the rest of the GUI.
     * 
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents( Composite parent ) {
        Composite comp = new Composite( parent, SWT.NONE );
        comp.setLayout( new GridLayout( 1, false ) );

        final Table table = new Table( comp, SWT.BORDER | SWT.FULL_SELECTION );
        table.setToolTipText( "Team player data" );
        table.setLinesVisible( true );
        table.setHeaderVisible( true );

        TableLayout tLay = new TableLayout();
        tLay.addColumnData( new ColumnWeightData( 20 ) );
        tLay.addColumnData( new ColumnWeightData( 20 ) );
        tLay.addColumnData( new ColumnWeightData( 20 ) );
        tLay.addColumnData( new ColumnWeightData( 20 ) );
        tLay.addColumnData( new ColumnWeightData( 20 ) );

        new TableColumn( table, SWT.LEFT ).setText( "Firstname" );
        new TableColumn( table, SWT.LEFT ).setText( "Lastname" );
        new TableColumn( table, SWT.LEFT ).setText( "Points" );
        new TableColumn( table, SWT.LEFT ).setText( "Rebounds" );
        new TableColumn( table, SWT.LEFT ).setText( "Assists" );

        table.setLayout( tLay );
        table.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

        final TableViewer tv = new TableViewer( table );
        tv.setContentProvider( new PlayerViewerContentProvider() );
        tv.setLabelProvider( new PlayerViewerLabelProvider() );
        tv.setInput( PlayerTableModel.createTeam() );

        return comp;
    }

    /**
     * Starter for 10!!!!
     * 
     * @param args
     */
    public static void main( String[] args ) {
        new PlayerViewer().run();
    }

}
