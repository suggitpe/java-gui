/*
 * ClipboardHelper.java created on 15 Jul 2009 18:50:31 by suggitpe for project SandBox - GUI
 * 
 */
package org.suggs.sandbox.table;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to house a collection of clipboard manipulation methods - used in the Table sandlbox
 * 
 * @author suggitpe
 * @version 1.0 15 Jul 2009
 */
public final class ClipboardHelper {

    private static final Logger LOG = LoggerFactory.getLogger( ClipboardHelper.class );

    private ClipboardHelper() {}

    /**
     * Gets the contents of the clipboard and returns it as a string
     * 
     * @param requestor
     * @return
     */
    static String getClipboardContents( Object requestor ) {
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents( requestor );
        if ( t != null ) {
            DataFlavor df = DataFlavor.stringFlavor;
            if ( df != null ) {
                try {
                    Reader r = df.getReaderForText( t );
                    char[] charBuf = new char[512];
                    StringBuffer buf = new StringBuffer();
                    int n;
                    while ( ( n = r.read( charBuf, 0, charBuf.length ) ) > 0 ) {
                        buf.append( charBuf, 0, n );
                    }
                    r.close();
                    return ( buf.toString() );
                }
                catch ( IOException ex ) {
                    LOG.error( "Failed to read/write clipboard", ex );
                }
                catch ( UnsupportedFlavorException ex ) {
                    LOG.error( "Failed to read clipboard", ex );
                }
            }
        }
        return null;
    }

    /**
     * Checks the contents of the clipboard to see if it contains data and that the data is textual. and that
     * the
     * 
     * @param requestor
     * @return true if the data is a string and that the content is plain text, else false
     */
    static boolean isClipboardContainingText( Object requestor ) {
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents( requestor );
        return t != null
               && ( t.isDataFlavorSupported( DataFlavor.stringFlavor ) || t.isDataFlavorSupported( DataFlavor.getTextPlainUnicodeFlavor() ) );
    }

    /**
     * Sets the contents of the clipboard to the passed in string
     * 
     * @param s
     *            the string to drop into the clipboard
     */
    static void setClipboardContents( String s ) {
        StringSelection selection = new StringSelection( s );
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents( selection, selection );
    }
}
