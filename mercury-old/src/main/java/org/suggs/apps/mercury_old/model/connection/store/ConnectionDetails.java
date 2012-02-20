/*
 * JmsConnectionDetails.java created on 28 Jun 2007 06:07:29 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old.model.connection.store;

import org.suggs.apps.mercury_old.model.connection.EConnectionType;
import org.suggs.apps.mercury_old.model.connection.IConnectionDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Implementation bean of the connection details interface
 * 
 * @author suggitpe
 * @version 1.0 28 Jun 2007
 */
public class ConnectionDetails implements IConnectionDetails {

    private String name;
    private EConnectionType type;
    private String hostName;
    private String port;
    private Map<String, String> metaData = new HashMap<String, String>();
    private Map<String, Set<String>> connectionFactories = new HashMap<String, Set<String>>();
    private Map<String, Set<String>> destinations = new HashMap<String, Set<String>>();

    /**
     * Constructs a new instance.
     * 
     * @param aName
     */
    public ConnectionDetails( String aName ) {
        name = aName;
    }

    /**
     * Constructs a new instance.
     * 
     * @param aName
     *            the name of the connection
     * @param aType
     *            the type of the connection
     */
    public ConnectionDetails( String aName, EConnectionType aType ) {
        name = aName;
        type = aType;
    }

    /**
     * Constructs a new instance.
     * 
     * @param aName
     *            the name of the connection
     * @param aType
     *            the type of the connection
     * @param aHostname
     *            the address of the server
     * @param aPort
     *            the port number for the server
     */
    public ConnectionDetails( String aName, EConnectionType aType, String aHostname, String aPort ) {
        name = aName;
        type = aType;
        hostName = aHostname;
        port = aPort;
    }

    /**
     * Constructs a new instance.
     * 
     * @param aName
     *            the name of the connection
     * @param aType
     *            the connection type
     * @param aHostname
     *            the name of the host for the broker
     * @param aPort
     *            the port number to access the broker
     * @param aMetaData
     *            additional connection metadata
     */
    public ConnectionDetails( String aName, EConnectionType aType, String aHostname, String aPort,
                              Map<String, String> aMetaData ) {
        name = aName;
        type = aType;
        hostName = aHostname;
        port = aPort;
        metaData = aMetaData;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer( "JmsConnectionDetails: name=[" ).append( name )
            .append( "], type=[" )
            .append( type.name() )
            .append( "], hostname=[" )
            .append( hostName )
            .append( "], port=[" )
            .append( port );
        for ( String s : metaData.keySet() ) {
            buff.append( "], " ).append( s ).append( "=[" ).append( metaData.get( s ) );
        }

        buff.append( "]" );
        return buff.toString();
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionDetails#isConnectionDetailsValid()
     */
    @Override
    public boolean isConnectionDetailsValid() {
        boolean ret = true;
        if ( name == null || name.length() < 1 ) {
            ret = false;
        }
        else if ( type == null ) {
            ret = false;
        }
        else if ( hostName == null || hostName.length() < 1 ) {
            ret = false;
        }
        else if ( port == null || port.length() < 1 ) {
            ret = false;
        }
        return ret;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionDetails#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionDetails#setName(java.lang.String)
     */
    @Override
    public void setName( String aName ) {
        name = aName;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionDetails#getType()
     */
    @Override
    public EConnectionType getType() {
        return type;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionDetails#getHostname()
     */
    @Override
    public String getHostname() {
        return hostName;
    }

    /**
     * Setter for the host name
     * 
     * @param aHost
     *            the host to set
     */
    public void setHostname( String aHost ) {
        hostName = aHost;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionDetails#getPort()
     */
    @Override
    public String getPort() {
        return port;
    }

    /**
     * Setter for the port number
     * 
     * @param aPort
     *            the port number to set
     */
    public void setPort( String aPort ) {
        port = aPort;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionDetails#getMetaData()
     */
    @Override
    public Map<String, String> getMetaData() {
        return metaData;
    }

    /**
     * Setter for the connection metadata
     * 
     * @param aMap
     *            the map of metadata to set
     */
    public void setMetaData( Map<String, String> aMap ) {
        metaData = aMap;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionDetails#getConnectionFactories()
     */
    @Override
    public Map<String, Set<String>> getConnectionFactories() {
        return connectionFactories;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionDetails#setConnectionFactories(java.util.Map)
     */
    @Override
    public void setConnectionFactories( Map<String, Set<String>> aMap ) {
        connectionFactories = aMap;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionDetails#getDestinations()
     */
    @Override
    public Map<String, Set<String>> getDestinations() {
        return destinations;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionDetails#setDestinations(java.util.Map)
     */
    @Override
    public void setDestinations( Map<String, Set<String>> aMap ) {
        destinations = aMap;
    }

}
