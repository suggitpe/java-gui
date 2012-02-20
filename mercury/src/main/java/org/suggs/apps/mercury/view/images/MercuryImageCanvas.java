/*
 * MercuryImageCanvas.java created on 16 Oct 2008 05:51:41 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.images;

import org.suggs.apps.mercury.model.util.image.ImageManager;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

/**
 * This class manages the encapsulation of the mercury image as a canvas composite so that it can be added to
 * any composite as an image.
 * 
 * @author suggitpe
 * @version 1.0 16 Oct 2008
 */
public class MercuryImageCanvas extends Canvas {

    private static final Logger LOG = LoggerFactory.getLogger( MercuryImageCanvas.class );
    private static final String IMG = "images/mercury.png";

    /**
     * Constructs a new instance.
     * 
     * @param parent
     */
    public MercuryImageCanvas( Composite parent ) {
        super( parent, SWT.NONE );

        addPaintListener( new PaintListener() {

            @Override
            public void paintControl( PaintEvent pe ) {
                InputStream is = ImageManager.getImageStream( ImageManager.IMAGE_MERCURY );
                if ( is == null ) {
                    LOG.warn( "Cannot find image [" + IMG + "]" );
                }
                Image img = new Image( pe.display, is );
                pe.gc.drawImage( img, 0, 0 );
                img.dispose();

            }
        } );
    }

}
