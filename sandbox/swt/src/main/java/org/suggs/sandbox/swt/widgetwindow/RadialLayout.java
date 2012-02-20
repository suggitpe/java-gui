/*
 * RadialLayout.java created on 22 Aug 2008 06:58:54 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

/**
 * This layout class is used as an example to show how custom layouts can be created.
 * 
 * @author suggitpe
 * @version 1.0 22 Aug 2008
 */
public class RadialLayout extends Layout {

    /**
     * Constructs a new instance.
     */
    public RadialLayout() {
        super();
    }

    /**
     * This method calculated the required size of the composite based on what is being tried to display an
     * also what the hinted at size of the display should be.
     * 
     * @see org.eclipse.swt.widgets.Layout#computeSize(org.eclipse.swt.widgets.Composite, int, int, boolean)
     */
    @Override
    protected Point computeSize( Composite composite, int wHint, int hHint, boolean flushCache ) {
        // first find out the max dimensions of the widgets to layout
        Point maxDim = calculateMaxDimensions( composite.getChildren() );
        // then find the number of widgets to place per hemisphere
        int stepsPerHemisphere = ( composite.getChildren().length / 2 ) - 1;

        int maxWidth = maxDim.x;
        int maxHeight = maxDim.y;

        // now use the data to calculate the preferred size using the
        // max heights and widths
        int dimensionMultiplier = stepsPerHemisphere + 1;
        int controlWidth = maxWidth * dimensionMultiplier;
        int controlHeight = maxHeight * dimensionMultiplier;
        int diameter = Math.max( controlHeight, controlWidth );
        Point preferedSize = new Point( diameter, diameter );

        // if the preferred sizes are greater than the hinted as sizes
        // then we should be reducing back to the hinted sizes.
        if ( wHint != SWT.DEFAULT ) {
            if ( preferedSize.x > wHint ) {
                preferedSize.x = wHint;
            }
        }

        if ( hHint != SWT.DEFAULT ) {
            if ( preferedSize.y > hHint ) {
                preferedSize.y = hHint;
            }
        }

        return preferedSize;
    }

    /**
     * In this method we place all of the controls based on their preferred size and also the points from
     * which to place them in the layout.
     * 
     * @see org.eclipse.swt.widgets.Layout#layout(org.eclipse.swt.widgets.Composite, boolean)
     */
    @Override
    protected void layout( Composite composite, boolean fluchCache ) {

        Point[] pos = calculateControlPositions( composite );
        Control[] ctrl = composite.getChildren();

        for ( int i = 0; i < ctrl.length; ++i ) {
            Control c = ctrl[i];
            Point prefSize = c.computeSize( SWT.DEFAULT, SWT.DEFAULT );
            c.setBounds( pos[i].x, pos[i].y, prefSize.x, prefSize.y );
        }
    }

    private Point[] calculateControlPositions( Composite composite ) {
        // set up the basics
        int ctrlCnt = composite.getChildren().length;
        int stepsPerHemisphere = ( ctrlCnt / 2 ) - 1;
        Point[] ret = new Point[ctrlCnt];

        // now we need to find the radius and thus the centre of the
        // client area, plus some of the key values for the
        // calculations.
        Point maxCtrlDimensions = calculateMaxDimensions( composite.getChildren() );
        int maxCtrlWidth = maxCtrlDimensions.x;
        Rectangle clientArea = composite.getClientArea();
        int smallestDim = Math.min( clientArea.width, clientArea.height );
        int radius = ( smallestDim / 2 ) - maxCtrlWidth;
        Point centre = new Point( clientArea.width / 2, clientArea.height / 2 );
        long radiusSqrd = radius * radius;

        int stepXDistance = ( radius * 2 ) / ( stepsPerHemisphere + 1 );

        // now we work out what all of the positions for the widgets
        // are
        int signMultiplier = 1;
        int x = -radius;
        int y;
        Control[] ctrl = composite.getChildren();

        for ( int i = 0; i < ctrlCnt; ++i ) {
            Point currSize = ctrl[i].getSize();
            long xSqrd = x * x;

            int sqrRt = (int) Math.sqrt( radiusSqrd - xSqrd );
            y = signMultiplier * sqrRt;

            int translatedX = x + centre.x;
            int translatedY = y + centre.y;

            ret[i] = new Point( translatedX - ( currSize.x / 2 ), translatedY - ( currSize.y / 2 ) );
            x = x + ( signMultiplier * stepXDistance );

            // now for the lower hemisphere
            if ( x >= radius ) {
                x = radius - ( x - radius );
                signMultiplier = -1;
            }
        }

        return ret;
    }

    /**
     * This method will take a collection of widgets and will work out from them all the maximum x and y
     * values from them all.
     * 
     * @param controls
     *            the controls to measure
     * @return a Point representing the max x and y values
     */
    private Point calculateMaxDimensions( Control[] controls ) {
        Point maxes = new Point( 0, 0 );
        for ( Control c : controls ) {
            Point conSz = c.computeSize( SWT.DEFAULT, SWT.DEFAULT );
            maxes.x = Math.max( maxes.x, conSz.x );
            maxes.y = Math.max( maxes.y, conSz.y );
        }
        return maxes;
    }

}
