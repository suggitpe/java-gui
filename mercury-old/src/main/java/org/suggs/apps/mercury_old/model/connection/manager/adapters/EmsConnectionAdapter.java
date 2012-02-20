/*
 * EmsConnectionAdapter.java created on 3 Aug 2007 06:13:32 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury_old.model.connection.manager.adapters;

import org.suggs.apps.mercury_old.model.connection.IConnectionDetails;
import org.suggs.apps.mercury_old.model.connection.manager.IConnectionAdapter;

import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Adapter for the EMS middleware implementation.
 * 
 * @author suggitpe
 * @version 1.0 3 Aug 2007
 */
public class EmsConnectionAdapter implements IConnectionAdapter {

    private static final Logger LOG = LoggerFactory.getLogger( EmsConnectionAdapter.class );
    private String initialontextFactory;

    /**
     * Constructs a new instance.
     */
    public EmsConnectionAdapter( String aInitialContextFactory ) {
        initialontextFactory = aInitialContextFactory;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.manager.IConnectionAdapter#createJmsContext(org.suggs.apps.mercury_old.model.connection.IConnectionDetails)
     */
    @Override
    public Context createJmsContext( IConnectionDetails aConnDetails ) {
        String url = "tcp://" + aConnDetails.getHostname() + ":" + aConnDetails.getPort();
        LOG.debug( "Creating EMS context with [" + initialontextFactory + "] and [" + url + "]" );
        Properties p = new Properties();
        p.put( Context.INITIAL_CONTEXT_FACTORY, initialontextFactory );
        p.put( Context.PROVIDER_URL, url );

        Context ret = null;
        try {
            ret = new InitialContext( p );
        }
        catch ( NamingException ne ) {
            LOG.warn( "Failed to create initial context" );
        }

        return ret;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.manager.IConnectionAdapter#findAllBrokerObjects()
     */
    @Override
    public Map<String, String> findAllBrokerObjects() {
        // TODO need to do the impl for all of the broker metadata
        // info
        return null;
    }

    /**
     * Getter for the initial context factory
     * 
     * @return the name of the initial context factory
     */
    public String getInitialContextFactory() {
        return initialontextFactory;
    }

    /**
     * Setter for the initial context factory
     * 
     * @param aInitialContext
     *            the initial context factory
     */
    public void setInitialContextFactory( String aInitialContext ) {
        initialontextFactory = aInitialContext;
    }

}
