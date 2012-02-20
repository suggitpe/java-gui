/*
 * ImageManager.java created on 20 Oct 2008 18:57:59 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util.image;

import java.io.InputStream;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Class to load an image from the file system.
 * 
 * @author suggitpe
 * @version 1.0 20 Oct 2008
 */
public final class ImageManager {

    public static final String IMAGE_MERCURY = "images/mercury.png";
    public static final String IMAGE_MERCURY_SMALL = "images/mercury-small.png";

    public static final String IMAGE_EXIT = "images/exit.png";

    public static final String IMAGE_CONN_CONNECTION = "images/connect.png";
    public static final String IMAGE_CONN_DISCONNECT = "images/conn-disconnect.png";
    public static final String IMAGE_CONN_CONNECT = "images/conn-connect.png";
    public static final String IMAGE_CONN_NEW_CONN = "images/conn-create.png";
    public static final String IMAGE_CONN_EDIT_CONN = "images/conn-edit.png";
    public static final String IMAGE_CONN_REMOVE_CONN = "images/conn-remove.png";

    /**
     * Constructs a new instance.
     */
    private ImageManager() {}

    /**
     * This static method will utilise the passed in classloader to retrieve the image from the classpath and
     * create it into an ImageDescriptor to be used.
     * 
     * @param imagename
     *            the name of the image to load
     * @return an Image descriptor object for the passed in image name
     */
    public static ImageDescriptor getImageDescriptor( String imagename ) {
        return ImageDescriptor.createFromURL( ImageManager.class.getClassLoader().getResource( imagename ) );
    }

    /**
     * This static method will use the passed in classloader to retrieve the image fromt he classpath and
     * return it as a stream.
     * 
     * @param imagename
     *            the name of the image to load
     * @return an input stream for the image from the classpath
     */
    public static InputStream getImageStream( String imagename ) {
        return ImageManager.class.getClassLoader().getResourceAsStream( imagename );
    }

}
