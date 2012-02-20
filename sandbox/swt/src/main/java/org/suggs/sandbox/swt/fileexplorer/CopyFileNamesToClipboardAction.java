/*
 * CopyFileNamesToClipboardAction.java created on 23 Dec 2008 20:15:48 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.fileexplorer;

import java.io.File;
import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This action will copy all of the absolute paths of the selected files into the clipboard
 * 
 * @author suggitpe
 * @version 1.0 23 Dec 2008
 */
public class CopyFileNamesToClipboardAction extends Action {

    private static final Logger LOG = LoggerFactory.getLogger( CopyFileNamesToClipboardAction.class );

    private Explorer window;

    /**
     * Constructs a new instance.
     * 
     * @param expl
     */
    public CopyFileNamesToClipboardAction( Explorer expl ) {
        window = expl;
        setText( "Copy File &Names@ctrl+shift+C" );
        setToolTipText( "Copy absolute filenames to the clipboard" );
        setImageDescriptor( ImageDescriptor.createFromURL( getClass().getClassLoader()
            .getResource( "copy.gif" ) ) );
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        Clipboard clip = ImageUtil.getClipboard();
        TextTransfer trans = TextTransfer.getInstance();

        IStructuredSelection sel = window.getTableSelection();
        if ( sel.isEmpty() ) {
            LOG.warn( "No files selected" );
            return;
        }

        StringBuffer buff = new StringBuffer();
        for ( Iterator<?> iter = sel.iterator(); iter.hasNext(); ) {
            File f = (File) iter.next();
            buff.append( " " ).append( f.getAbsolutePath() );
        }

        clip.setContents( new Object[] { buff.toString() }, new Transfer[] { trans } );
    }
}
