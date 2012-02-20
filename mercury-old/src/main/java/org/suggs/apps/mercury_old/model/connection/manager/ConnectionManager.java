/*
 * JmsConnectionManager.java created on 22 Jun 2007 08:18:48 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old.model.connection.manager;

import org.suggs.apps.mercury_old.model.connection.EConnectionState;
import org.suggs.apps.mercury_old.model.connection.IConnectionManager;
import org.suggs.apps.mercury_old.model.connection.IConnectionParameters;
import org.suggs.apps.mercury_old.model.connection.MercuryConnectionException;

import java.util.Map;
import java.util.Observable;
import java.util.Set;

import javax.jms.Connection;
import javax.jms.ConnectionMetaData;
import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the jms connection manager interface
 * 
 * @author suggitpe
 * @version 1.0 22 Jun 2007
 */
public class ConnectionManager extends Observable implements IConnectionManager {

    private static final Logger LOG = LoggerFactory.getLogger( ConnectionManager.class );

    private EConnectionState connectionState = EConnectionState.INITIAL;
    private Connection connection;
    private Map<String, IConnectionAdapter> adapters;

    /**
     * Constructs a new instance.
     */
    public ConnectionManager( Map<String, IConnectionAdapter> aMapOfConnectionAdapters ) {
        super();
        adapters = aMapOfConnectionAdapters;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionManager#getConnectionState()
     */
    @Override
    public EConnectionState getConnectionState() {
        return connectionState;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionManager#connect(org.suggs.apps.mercury_old.model.connection.IConnectionParameters)
     */
    @Override
    public void connect( IConnectionParameters aDetails ) {
        connectionState = EConnectionState.CONNECTED;

        setChanged();
        notifyObservers();
    }

    /**
     * Get the connection metadata ... ie what is behind the connection
     * 
     * @return the connection meta data
     * @throws MercuryConnectionException
     *             if there is no connection
     */
    public Map<String, Set<String>> getConnectionData() throws MercuryConnectionException {
        if ( connection == null ) {
            throw new MercuryConnectionException( "No connection has been created.  You must connect to the broker first." );
        }

        Map<String, Set<String>> ret = null;
        try {
            ConnectionMetaData data = connection.getMetaData();
            LOG.debug( "Meta data for connection is:\n" + data.toString() );
        }
        catch ( JMSException je ) {
            throw new MercuryConnectionException( "Failed to collect connection metadata", je );
        }
        return ret;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionManager#disconnect()
     */
    @Override
    public void disconnect() {
        connectionState = EConnectionState.DISCONNECTED;
        setChanged();
        notifyObservers();
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionManager#testConnection(org.suggs.apps.mercury_old.model.connection.IConnectionParameters)
     */
    @Override
    public boolean testConnection( IConnectionParameters aDetails ) {
        connectionState = EConnectionState.DISCONNECTED;
        return false;
    }

    /**
     * Getter for the adapters
     * 
     * @return the map of adapters
     */
    public Map<String, IConnectionAdapter> getAdapters() {
        return adapters;
    }

    /**
     * setter for the adapters
     * 
     * @param aMap
     *            the map of adapters to set
     */
    public void setAdapters( Map<String, IConnectionAdapter> aMap ) {
        adapters = aMap;
    }

}
