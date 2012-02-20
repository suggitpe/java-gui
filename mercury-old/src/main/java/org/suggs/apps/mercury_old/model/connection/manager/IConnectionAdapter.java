/*
 * IConnectionAdapter.java created on 3 Aug 2007 05:58:21 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury_old.model.connection.manager;

import org.suggs.apps.mercury_old.model.connection.IConnectionDetails;

import java.util.Map;

import javax.naming.Context;

/**
 * High level adapter interface to allow for the differences in function for the various middleware
 * implementations
 * 
 * @author suggitpe
 * @version 1.0 3 Aug 2007
 */
public interface IConnectionAdapter {

    /**
     * Creates the connection context from which we can locate factories and destinations
     * 
     * @param aConnDetails
     *            the connection details required for the connection
     * @return the connection context
     */
    Context createJmsContext( IConnectionDetails aConnDetails );

    /**
     * Finds all objects on the messaging broker (queues, topics, connectionFactories)
     * 
     * @return a map of the broker objects
     */
    Map<String, String> findAllBrokerObjects();

}
