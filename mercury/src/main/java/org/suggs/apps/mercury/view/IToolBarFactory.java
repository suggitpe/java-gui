/*
 * IToolbarFactory.java created on 17 Sep 2008 07:19:11 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view;

import org.eclipse.jface.action.ToolBarManager;

/**
 * The purpose of this intrface is to provide a way so that we can decouple the construction of the toolbars
 * away from the main GUI logic.
 * 
 * @author suggitpe
 * @version 1.0 17 Sep 2008
 */
public interface IToolBarFactory {

    /**
     * Creates a tool bar manager for the given end destination
     * 
     * @param toolBarType
     *            the type of toolbar to create
     * @param style
     *            the style that the toolbar should take
     * @return the toolbar manager
     */
    ToolBarManager createToolbar( String toolBarType, int style );

}
