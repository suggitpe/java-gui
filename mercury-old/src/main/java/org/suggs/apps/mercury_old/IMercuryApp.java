/*
 * IJmsHelper.java created on 20 Jun 2007 18:46:20 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old;

/**
 * This is the main route through to the GUI itself
 * 
 * @author suggitpe
 * @version 1.0 20 Jun 2007
 */
public interface IMercuryApp {

    /**
     * Build the GUI and displays it to the user
     * 
     * @throws MercuryException
     */
    void buildGui() throws MercuryException;

}
