/*
 * Ch5Undoable.java created on 18 Aug 2008 18:49:48 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch5_widgets;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ExtendedModifyEvent;
import org.eclipse.swt.custom.ExtendedModifyListener;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Stylised text example for SWT showing a basic undo and redo function.
 * 
 * @author suggitpe
 * @version 1.0 18 Aug 2008
 */
public class Ch5Undoable extends Composite {

    private static final int MAX_STACK_SIZE = 25;
    private List<String> undoStack = new LinkedList<String>();
    private List<String> redoStack = new LinkedList<String>();
    private StyledText text = null;

    /**
     * Constructs a new instance.
     * 
     * @param parent
     *            a composit to associate this class with
     */
    public Ch5Undoable( Composite parent ) {
        super( parent, SWT.NONE );
        buildControls();
    }

    /**
     * Builds the relevant controls for the composite
     */
    private void buildControls() {
        setLayout( new FillLayout() );
        text = new StyledText( this, SWT.MULTI | SWT.V_SCROLL );
        text.addExtendedModifyListener( new ExtendedModifyListener() {

            @Override
            public void modifyText( ExtendedModifyEvent event ) {
                String currTxt = text.getText();
                String newTxt = currTxt.substring( event.start, event.start + event.length );
                if ( newTxt != null && newTxt.length() > 0 ) {
                    if ( undoStack.size() >= MAX_STACK_SIZE ) {
                        undoStack.remove( undoStack.size() - 1 );
                    }
                    undoStack.add( 0, newTxt );
                }
            }
        } );

        text.addKeyListener( new KeyAdapter() {

            /**
             * Overrides the key pressed implementation with F! for undo and F2 for redo.
             * 
             * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
             */
            @Override
            public void keyPressed( KeyEvent event ) {
                switch ( event.keyCode ) {
                    case SWT.F1:
                        undo();
                        break;
                    case SWT.F2:
                        redo();
                        break;
                    default:
                        // nada
                }
            }
        } );

    }

    /**
     * Implements the undo functionality by removing the existing text and replacing with the previous event
     * text from the undo stack.
     */
    private void undo() {
        if ( undoStack.size() > 0 ) {
            String lastEdit = undoStack.remove( 0 );
            int editLength = lastEdit.length();
            String currText = text.getText();
            int replaceStart = currText.length() - editLength;
            text.replaceTextRange( replaceStart, editLength, "" );
            redoStack.add( 0, lastEdit );
        }
    }

    /**
     * Implements the redo function by removing the last undo and replacing it with the previous text from the
     * redo stack.
     */
    private void redo() {
        if ( redoStack.size() > 0 ) {
            String txt = redoStack.remove( 0 );
            moveCursorToEnd();
            text.append( txt );
            moveCursorToEnd();
        }
    }

    /**
     * Moves the cursor to the end of the text block.
     */
    private void moveCursorToEnd() {
        text.setCaretOffset( text.getText().length() );
    }

}
