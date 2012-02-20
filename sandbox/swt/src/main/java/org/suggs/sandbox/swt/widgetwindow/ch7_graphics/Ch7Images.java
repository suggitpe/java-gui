/*
 * Ch7Images.java created on 2 Sep 2008 18:46:26 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch7_graphics;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;

/**
 * Simple app that will create an animated GIF file to use by the widget window composite
 * 
 * @author suggitpe
 * @version 1.0 2 Sep 2008
 */
public final class Ch7Images {

    public static final int ROWS = 6;
    public static final int COLS = 11;
    public static final int PIX = 20;

    private Ch7Images() {}

    public static void main( String[] args ) {
        // initialise the colour palette to use in the image
        PaletteData pd = new PaletteData( new RGB[] { new RGB( 0x00, 0x00, 0x00 ),
                                                     new RGB( 0x80, 0x80, 0x80 ), new RGB( 0xFF, 0xFF, 0xFF ) } );

        // create three image descriptions to allow for the animation
        // (these are
        // the image frames in the animation)
        ImageData[] flagArray = new ImageData[3];
        for ( int f = 0; f < flagArray.length; ++f ) {
            flagArray[f] = new ImageData( PIX * COLS, PIX * ROWS, 4, pd );
            flagArray[f].delayTime = 10;
            for ( int x = 0; x < PIX * COLS; ++x ) {
                for ( int y = 0; y < PIX * ROWS; ++y ) {
                    // determine the id in the palette to use for the
                    // colour
                    int val = ( ( ( x / PIX ) % 3 ) + ( 3 - ( ( y / PIX ) % 3 ) ) + f ) % 3;
                    flagArray[f].setPixel( x, y, val );
                    // set the actual pixel colour
                }
            }
        }

        // load the image loader with 3 ImageData frames
        ImageLoader loader = new ImageLoader();
        loader.logicalScreenWidth = PIX * COLS;
        loader.logicalScreenHeight = PIX * ROWS;
        loader.data = flagArray;
        loader.save( "FlagGIF.gif", SWT.IMAGE_GIF );
    }
}
