/*
 * AbstractGridbagPanel.java created on 25 Jun 2007 06:14:27 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old.support;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * Abstract Panel class used by deriving Panels to make the addition of panel components much easier
 * 
 * @author suggitpe
 * @version 1.0 25 Jun 2007
 */
public abstract class AbstractGridbagPanel extends JPanel {

    private static final long serialVersionUID = 2785057007986923888L;

    private GridBagConstraints constraints;

    private static final int C_HORZ = GridBagConstraints.HORIZONTAL;
    private static final int C_NONE = GridBagConstraints.NONE;
    private static final int C_WEST = GridBagConstraints.WEST;
    private static final int C_WIDTH = 1;
    private static final int C_HEIGHT = 1;

    protected static final Dimension SHORT_FIELD = new Dimension( 40, 20 );
    protected static final Dimension MEDIUM_FIELD = new Dimension( 120, 20 );
    protected static final Dimension LONG_FIELD = new Dimension( 240, 20 );
    protected static final Dimension HUGE_FIELD = new Dimension( 240, 80 );

    /**
     * Constructs a new instance.
     */
    public AbstractGridbagPanel() {
        this( "" );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aBorderText
     */
    public AbstractGridbagPanel( String aBorderText ) {
        this( aBorderText, new Insets( 2, 2, 2, 2 ) );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aBorderText
     * @param aInsets
     */
    public AbstractGridbagPanel( String aBorderText, Insets aInsets ) {
        super( new GridBagLayout() );
        this.setBorder( new TitledBorder( new EtchedBorder(), aBorderText ) );
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = aInsets;
    }

    /**
     * Shows an error message dialog box
     * 
     * @param aMessage
     *            the message to show
     * @param aHeader
     *            the header of the dialog box
     */
    protected void showErrorDialog( String aMessage, String aHeader ) {
        JOptionPane.showMessageDialog( this, aMessage, aHeader, JOptionPane.ERROR_MESSAGE );
    }

    /**
     * Add a component to the panel
     * 
     * @param aComp
     *            the component to add
     * @param aRow
     *            the row to add it to
     * @param aColumn
     *            the column to add it to
     */
    public void addComponent( JComponent aComp, int aRow, int aColumn ) {
        addComponent( aComp, aRow, aColumn, C_WIDTH, C_HEIGHT, 1, 1, C_WEST, C_NONE );
    }

    /**
     * Add a component to the panel
     * 
     * @param aComp
     *            the component to add
     * @param aRow
     *            the row to add the component to
     * @param aColumn
     *            the column to add the component to
     * @param aWidth
     *            number of columns to span
     * @param aHeight
     *            numof rows to span
     */
    public void addComponent( JComponent aComp, int aRow, int aColumn, int aWidth, int aHeight ) {
        addComponent( aComp, aRow, aColumn, aWidth, aHeight, 1, 1, C_WEST, C_NONE );
    }

    /**
     * Add a component to the panel
     * 
     * @param aComp
     *            the component to add
     * @param aRow
     *            the row to add it to
     * @param aColumn
     *            the column to add it to
     * @param aAnchor
     *            the anchor for the cell
     */
    public void addAnchoredComponent( JComponent aComp, int aRow, int aColumn, int aAnchor ) {
        addComponent( aComp, aRow, aColumn, C_WIDTH, C_HEIGHT, 1, 1, aAnchor, C_NONE );
    }

    /**
     * Add a component to the panel
     * 
     * @param aComp
     *            the component to add
     * @param aRow
     *            the row to add the component to
     * @param aColumn
     *            the column to add the component to
     * @param aWidth
     *            number of columns to span
     * @param aHeight
     *            numof rows to span
     * @param aAnchor
     *            the anchor for the component
     */
    public void addAnchoredComponent( JComponent aComp, int aRow, int aColumn, int aWidth, int aHeight,
                                      int aAnchor ) {
        addComponent( aComp, aRow, aColumn, aWidth, aHeight, 1, 1, aAnchor, C_NONE );
    }

    /**
     * Add a horizontally filled component to the panel
     * 
     * @param aComp
     *            the component to add
     * @param aRow
     *            the row to add it to
     * @param aColumn
     *            the column to add it to
     */
    public void addFilledComponent( JComponent aComp, int aRow, int aColumn ) {
        addComponent( aComp, aRow, aColumn, C_WIDTH, C_HEIGHT, 1, 1, C_WEST, C_HORZ );
    }

    /**
     * Add a filled component to the panel
     * 
     * @param aComp
     *            the component to add
     * @param aRow
     *            the row to add it to
     * @param aColumn
     *            the column to add it to
     * @param aFill
     *            the filling to use
     */
    public void addFilledComponent( JComponent aComp, int aRow, int aColumn, int aFill ) {
        addComponent( aComp, aRow, aColumn, C_WIDTH, C_HEIGHT, 1, 1, C_WEST, aFill );
    }

    /**
     * Add a filled component to the panel
     * 
     * @param aComp
     *            the component to add
     * @param aRow
     *            the row to add the component to
     * @param aColumn
     *            the column to add the component to
     * @param aWidth
     *            number of columns to span
     * @param aHeight
     *            numof rows to span
     * @param aFill
     *            the filling to use
     */
    public void addFilledComponent( JComponent aComp, int aRow, int aColumn, int aWidth, int aHeight,
                                    int aFill ) {
        addComponent( aComp, aRow, aColumn, aWidth, aHeight, 1, 1, C_WEST, aFill );
    }

    /**
     * Add a filled component to the panel
     * 
     * @param aComp
     *            the component to add
     * @param aRow
     *            the row to add it to
     * @param aColumn
     *            the column to add it to
     * @param aWidthPad
     *            the padding use in the width
     * @param aHeightPad
     *            the padding to use in the height
     */
    public void addFilledComponent( JComponent aComp, int aRow, int aColumn, int aWidthPad, int aHeightPad ) {
        addComponent( aComp, aRow, aColumn, C_WIDTH, C_HEIGHT, aWidthPad, aHeightPad, C_WEST, C_HORZ );
    }

    /**
     * Add a component to the panel
     * 
     * @param aComp
     *            the component to add
     * @param aRow
     *            the row to add the component to
     * @param aColumn
     *            the column to add the component to
     * @param aWidth
     *            number of columns to span
     * @param aHeight
     *            numof rows to span
     * @param aWidthPad
     *            the padding use in the width
     * @param aHeightPad
     *            the padding to use in the height
     * @param aAnchor
     *            the anchor to use in the cell
     * @param aFill
     *            the filling to use
     */
    public void addComponent( JComponent aComp, int aRow, int aColumn, int aWidth, int aHeight,
                              int aWidthPad, int aHeightPad, int aAnchor, int aFill ) {
        constraints.gridx = aColumn;
        constraints.gridy = aRow;
        constraints.gridwidth = aWidth;
        constraints.gridheight = aHeight;
        constraints.anchor = aAnchor;
        constraints.ipadx = aWidthPad;
        constraints.ipady = aHeightPad;
        double weightx = 0.0;
        double weighty = 0.0;

        if ( aWidth > 1 ) {
            weightx = 1.0;
        }

        if ( aHeight > 1 ) {
            weighty = 1.0;
        }

        switch ( aFill ) {
            case GridBagConstraints.HORIZONTAL:
                constraints.weightx = weightx;
                constraints.weighty = 0.0;
                break;
            case GridBagConstraints.VERTICAL:
                constraints.weightx = 0.0;
                constraints.weighty = weighty;
                break;
            case GridBagConstraints.BOTH:
                constraints.weightx = weightx;
                constraints.weighty = weighty;
                break;
            case GridBagConstraints.NONE:
                constraints.weightx = 0.0;
                constraints.weighty = 0.0;
                break;
            default:
                break;
        }

        constraints.fill = aFill;

        add( aComp, constraints );
    }
}
