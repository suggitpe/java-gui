/*
 * IMenuFactory.java created on 17 Sep 2008 06:58:12 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view;

import org.eclipse.jface.action.MenuManager;

/**
 * The purpose of this interface is to provide a way such that we can decouple the construction of the menus
 * in the system away from the main GUI logic.
 * 
 * @author suggitpe
 * @version 1.0 17 Sep 2008
 */
public interface IMenuFactory {

    /**
     * Creates a menu manager for a given end destination
     * 
     * @param menuType
     *            the type of menu that you would like to create (e.g. the menu for the main screen)
     * @return the correct menu manager for the type
     */
    MenuManager createMenuManager( String menuType );

}
