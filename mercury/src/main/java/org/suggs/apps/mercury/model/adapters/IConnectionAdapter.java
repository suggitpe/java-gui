/*
 * IMecuryConnectionAdapter.java created on 10 Nov 2008 07:47:38 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.adapters;

/**
 * This is the highest level used by the adapter and describes the basic behaviour that all Mercury adapters
 * must adhere to.
 * 
 * @author suggitpe
 * @version 1.0 10 Nov 2008
 */
public interface IConnectionAdapter {

    enum CONNECTION_TYPE {
        IBM_MQ, TIBCO_EMS
    }

    /**
     * This method is used to return the unique adapater name corresponding to their underlying middleware
     * implementation
     * 
     * @return the type of adapter
     */
    String getType();

    /**
     * This method will return the friendly name of the adapter, this name is not necessarily unique
     * 
     * @return the name of the adapter
     */
    String getFriendlyName();

}
