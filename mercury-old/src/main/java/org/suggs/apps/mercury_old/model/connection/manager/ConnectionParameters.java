/*
 * ConnectionParameters.java created on 9 Aug 2007 19:40:28 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury_old.model.connection.manager;

import org.suggs.apps.mercury_old.model.connection.EConnectionType;
import org.suggs.apps.mercury_old.model.connection.IConnectionParameters;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of a connection parameters class.
 * 
 * @author suggitpe
 * @version 1.0 9 Aug 2007
 */
public class ConnectionParameters implements IConnectionParameters {

    private EConnectionType type;
    private String hostName;
    private String port;
    private Map<String, String> metaData = new HashMap<String, String>();
    private String connectionFactory;

    /**
     * Constructs a new instance.
     */
    @SuppressWarnings("unused")
    private ConnectionParameters() {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aType
     * @param aHostname
     * @param aPort
     * @param aMetadata
     * @param aConnectionFactory
     */
    public ConnectionParameters( EConnectionType aType, String aHostname, String aPort,
                                 Map<String, String> aMetadata, String aConnectionFactory ) {
        super();
        type = aType;
        hostName = aHostname;
        port = aPort;
        metaData = aMetadata;
        connectionFactory = aConnectionFactory;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionParameters#getType()
     */
    @Override
    public EConnectionType getType() {
        return type;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionParameters#getHostname()
     */
    @Override
    public String getHostname() {
        return hostName;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionParameters#getPort()
     */
    @Override
    public String getPort() {
        return port;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionParameters#getMetaData()
     */
    @Override
    public Map<String, String> getMetaData() {
        return metaData;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionParameters#getConnectionFactory()
     */
    @Override
    public String getConnectionFactory() {
        return connectionFactory;
    }

}
