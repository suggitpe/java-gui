package org.suggs.sandbox.table;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.CellEditor;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to represent a Table Menu
 * 
 * @author suggitpe
 * @version 1.0 16 Nov 2009
 */
public class TableMenu extends JFrame {

    private static final long serialVersionUID = 7672160185369606838L;
    private static final String PROP_CHANGE_QUANTITY = "CHANGE_QUANTITY";
    private JPanel jContentPane;
    private JScrollPane jScrollPane;
    private JTable jTable;
    private ExampleTableModel tableModel;

    /**
     * Constructs a new instance.
     */
    public TableMenu() {
        super();
        initialize();
    }

    /**
     * Getter for the JTable that will create one if it does not exist
     * 
     * @return
     */
    private JTable getJTable() {
        if ( jTable == null ) {
            jTable = new JTable();
            jTable.setModel( getTableModel() );
            jTable.addMouseListener( new MouseAdapter() {

                private void maybeShowPopup( MouseEvent e ) {
                    if ( e.isPopupTrigger() && jTable.isEnabled() ) {
                        Point p = new Point( e.getX(), e.getY() );
                        int col = jTable.columnAtPoint( p );
                        int row = jTable.rowAtPoint( p );

                        // translate table index to model index
                        int mcol = jTable.getColumn( jTable.getColumnName( col ) ).getModelIndex();

                        if ( row >= 0 && row < jTable.getRowCount() ) {
                            cancelCellEditing();

                            // create popup menu...
                            JPopupMenu contextMenu = createContextMenu( row, mcol );

                            // ... and show it
                            if ( contextMenu != null && contextMenu.getComponentCount() > 0 ) {
                                contextMenu.show( jTable, p.x, p.y );
                            }
                        }
                    }
                }

                @Override
                public void mousePressed( MouseEvent e ) {
                    maybeShowPopup( e );
                }

                @Override
                public void mouseReleased( MouseEvent e ) {
                    maybeShowPopup( e );
                }
            } );
        }
        return jTable;
    }

    private void cancelCellEditing() {
        CellEditor ce = getJTable().getCellEditor();
        if ( ce != null ) {
            ce.cancelCellEditing();
        }
    }

    private JPopupMenu createContextMenu( final int rowIndex, final int columnIndex ) {
        JPopupMenu contextMenu = new JPopupMenu();

        JMenuItem copyMenu = new JMenuItem();
        copyMenu.setText( "Copy" );
        copyMenu.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed( ActionEvent e ) {
                Object value = getTableModel().getValueAt( rowIndex, columnIndex );
                ClipboardHelper.setClipboardContents( value == null ? "" : value.toString() );
            }
        } );
        contextMenu.add( copyMenu );

        JMenuItem pasteMenu = new JMenuItem();
        pasteMenu.setText( "Paste" );
        if ( ClipboardHelper.isClipboardContainingText( this )
             && getTableModel().isCellEditable( rowIndex, columnIndex ) ) {
            pasteMenu.addActionListener( new ActionListener() {

                @Override
                public void actionPerformed( ActionEvent e ) {
                    String value = ClipboardHelper.getClipboardContents( TableMenu.this );
                    getTableModel().setValueAt( value, rowIndex, columnIndex );
                }
            } );
        }
        else {
            pasteMenu.setEnabled( false );
        }
        contextMenu.add( pasteMenu );

        switch ( columnIndex ) {
            case ExampleTableModel.COLUMN_QUANTITY:
                contextMenu.addSeparator();
                ActionListener changer = new ActionListener() {

                    @Override
                    public void actionPerformed( ActionEvent e ) {
                        JMenuItem sourceItem = (JMenuItem) e.getSource();
                        Object value = sourceItem.getClientProperty( PROP_CHANGE_QUANTITY );
                        if ( value instanceof Integer ) {
                            Integer changeValue = (Integer) value;
                            Integer currentValue = (Integer) getTableModel().getValueAt( rowIndex,
                                                                                         columnIndex );
                            getTableModel().setValueAt( Integer.valueOf( currentValue.intValue()
                                                                         + changeValue.intValue() ),
                                                        rowIndex,
                                                        columnIndex );
                        }
                    }
                };
                JMenuItem changeItem = new JMenuItem();
                changeItem.setText( "+1" );
                changeItem.putClientProperty( PROP_CHANGE_QUANTITY, Integer.valueOf( 1 ) );
                changeItem.addActionListener( changer );
                contextMenu.add( changeItem );

                changeItem = new JMenuItem();
                changeItem.setText( "-1" );
                changeItem.putClientProperty( PROP_CHANGE_QUANTITY, Integer.valueOf( -1 ) );
                changeItem.addActionListener( changer );
                contextMenu.add( changeItem );

                changeItem = new JMenuItem();
                changeItem.setText( "+10" );
                changeItem.putClientProperty( PROP_CHANGE_QUANTITY, Integer.valueOf( 10 ) );
                changeItem.addActionListener( changer );
                contextMenu.add( changeItem );

                changeItem = new JMenuItem();
                changeItem.setText( "-10" );
                changeItem.putClientProperty( PROP_CHANGE_QUANTITY, Integer.valueOf( -10 ) );
                changeItem.addActionListener( changer );
                contextMenu.add( changeItem );

                changeItem = null;
                break;
            // case ExampleTableModel.COLUMN_NAME:
            // case ExampleTableModel.COLUMN_AMOUNT:
            // case ExampleTableModel.COLUMN_PRICE:
            default:
                break;
        }
        return contextMenu;
    }

    private JPanel getJContentPane() {
        if ( jContentPane == null ) {
            jContentPane = new JPanel();
            jContentPane.setLayout( new BorderLayout() );
            jContentPane.add( getJScrollPane(), java.awt.BorderLayout.CENTER );
        }
        return jContentPane;
    }

    private JScrollPane getJScrollPane() {
        if ( jScrollPane == null ) {
            jScrollPane = new JScrollPane();
            jScrollPane.setViewportView( getJTable() );
        }
        return jScrollPane;
    }

    ExampleTableModel getTableModel() {
        if ( tableModel == null ) {
            tableModel = new ExampleTableModel();
        }
        return tableModel;
    }

    private final void initialize() {
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setSize( 300, 200 );
        this.setContentPane( getJContentPane() );
        this.setTitle( "Application" );
    }
}

class ExampleTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -8613734897280274338L;
    private static final Logger LOG = LoggerFactory.getLogger( ExampleTableModel.class );

    private static class Item {

        private String name;
        private double price;
        private int quantity;

        public Item( String name, double price, int quantity ) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public double getAmount() {
            return quantity * price;
        }
    }

    public static final int COLUMN_AMOUNT = 3;

    public static final int COLUMN_NAME = 0;

    public static final int COLUMN_PRICE = 1;

    public static final int COLUMN_QUANTITY = 2;

    private List<Item> items = new ArrayList<Item>();

    public void addItem( String name, double price, int quantity ) {
        items.add( new Item( name, price, quantity ) );
    }

    @Override
    public Class<?> getColumnClass( int columnIndex ) {
        switch ( columnIndex ) {
            case COLUMN_NAME:
                return String.class;
            case COLUMN_PRICE:
            case COLUMN_AMOUNT:
                return Double.class;
            case COLUMN_QUANTITY:
                return Integer.class;
            default:
                return Object.class;
        }
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName( int columnIndex ) {
        switch ( columnIndex ) {
            case COLUMN_NAME:
                return "Name";
            case COLUMN_PRICE:
                return "Price";
            case COLUMN_QUANTITY:
                return "Quantity";
            case COLUMN_AMOUNT:
                return "Amount";

            default:
                return "# COLUMN " + columnIndex + " #";
        }
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public Object getValueAt( int rowIndex, int columnIndex ) {
        Item item = items.get( rowIndex );
        switch ( columnIndex ) {
            case COLUMN_NAME:
                return item.name;
            case COLUMN_PRICE:
                return new Double( item.price );
            case COLUMN_QUANTITY:
                return Integer.valueOf( item.quantity );
            case COLUMN_AMOUNT:
                return new Double( item.getAmount() );
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable( int rowIndex, int columnIndex ) {
        switch ( columnIndex ) {
            case COLUMN_NAME:
            case COLUMN_PRICE:
            case COLUMN_QUANTITY:
                return true;
            case COLUMN_AMOUNT:
            default:
                return false;
        }
    }

    @Override
    public void setValueAt( Object aValue, int rowIndex, int columnIndex ) {
        Item item = items.get( rowIndex );
        switch ( columnIndex ) {
            case COLUMN_NAME:
                item.name = aValue.toString();
                fireTableCellUpdated( rowIndex, columnIndex );
                break;
            case COLUMN_PRICE:
                try {
                    item.price = Double.parseDouble( aValue.toString() );
                }
                catch ( NumberFormatException ex ) {
                    LOG.error( "Failed to conmvert Double from string", ex );
                }
                fireTableCellUpdated( rowIndex, columnIndex );
                fireTableCellUpdated( rowIndex, COLUMN_AMOUNT );
                break;
            case COLUMN_QUANTITY:
                try {
                    item.quantity = Integer.parseInt( aValue.toString() );
                }
                catch ( NumberFormatException ex ) {
                    LOG.error( "Caught a number format exception trying to convert [" + aValue.toString()
                               + "]", ex );
                }
                fireTableCellUpdated( rowIndex, columnIndex );
                fireTableCellUpdated( rowIndex, COLUMN_AMOUNT );
                break;
            case COLUMN_AMOUNT:
            default:
                return;
        }
    }
}
