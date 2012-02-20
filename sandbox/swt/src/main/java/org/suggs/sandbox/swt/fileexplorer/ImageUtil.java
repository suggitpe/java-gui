/*
 * ImageUtil.java created on 22 Dec 2008 19:35:23 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.fileexplorer;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.widgets.Display;

/**
 * This is used to manage images in the app.
 * 
 * @author suggitpe
 * @version 1.0 22 Dec 2008
 */
public final class ImageUtil {

    private static volatile ImageRegistry images;
    private static volatile Clipboard clipboard;

    private ImageUtil() {}

    /**
     * Singleton method for managing an image registry for
     * 
     * @return the image registry with all of the image items included
     */
    public static synchronized ImageRegistry getImageRegistry() {
        if ( images == null ) {
            images = new ImageRegistry();
            images.put( "folder", ImageDescriptor.createFromURL( ImageUtil.class.getClassLoader()
                .getResource( "folder.gif" ) ) );
            images.put( "file", ImageDescriptor.createFromURL( ImageUtil.class.getClassLoader()
                .getResource( "file.gif" ) ) );
            images.put( "exit", ImageDescriptor.createFromURL( ImageUtil.class.getClassLoader()
                .getResource( "exit.gif" ) ) );

        }
        return images;
    }

    /**
     * Accessor for the singleton ckipboard.
     * 
     * @return the clipboard
     */
    public static synchronized Clipboard getClipboard() {
        if ( clipboard == null ) {
            clipboard = new Clipboard( Display.getCurrent() );
        }
        return clipboard;
    }
}
