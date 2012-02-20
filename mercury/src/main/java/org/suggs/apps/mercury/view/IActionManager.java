/*
 * IActionManager.java created on 17 Sep 2008 06:53:31 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view;

import org.eclipse.jface.action.IAction;

/**
 * Action Manager interface. The sole purpose her is to provide a way to get hold of actions that will be used
 * throughout the GUI. The various parts of the GUI that will use actions will come to this functional area
 * and will retrieve the actions. This is most useful for using the same action in two widget managers (e.g.
 * toolbar and menu).
 * 
 * @author suggitpe
 * @version 1.0 17 Sep 2008
 */
public interface IActionManager {

    /**
     * Getter for the actions in the action manager.
     * 
     * @param actionName
     *            the name of the action.
     * @return the action that corresponds to that name.
     */
    IAction getAction( String actionName );

}
