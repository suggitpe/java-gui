/*
 * TableMenuTest.java created on 15 Jul 2009 18:48:22 by suggitpe for project SandBox - GUI
 * 
 */
package org.suggs.sandbox.table;

/**
 * Manual test for the Table Menu
 * 
 * @author suggitpe
 * @version 1.0 15 Jul 2009
 */
public class TableMenuTest {

    /**
     * Starts the GUI and adds test data
     * 
     * @param args
     */
    public static void main( String[] args ) {
        TableMenu app = new TableMenu();
        app.getTableModel().addItem( "Apple", 1.39, 3 );
        app.getTableModel().addItem( "Pear", 2.19, 2 );
        app.getTableModel().addItem( "Banana", 1.52, 4 );
        app.setVisible( true );
    }
}
