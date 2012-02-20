/*
 * JmsConnectionDetails.java created on 28 Jun 2007 06:07:29 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.model.connection;

import java.util.HashMap;
import java.util.Map;

/**
 * A bean to represent the connection details.
 * 
 * @author suggitpe
 * @version 1.0 28 Jun 2007
 */
public class ConnectionDetails {

    public static final String META_CHANNEL = "ChannelName";

    private String name;
    private String type;
    private String hostName;
    private int port = 0;
    private boolean isSecurityEnabled;
    private String username;
    private String password;
    private Map<String, String> connectionData = new HashMap<String, String>();

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
    public ConnectionDetails( String aName, String aType ) {
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
    public ConnectionDetails( String aName, String aType, String aHostname, int aPort ) {
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
     * @param aConnData
     */
    public ConnectionDetails( String aName, String aType, String aHostname, int aPort,
                              Map<String, String> aConnData ) {
        name = aName;
        type = aType;
        hostName = aHostname;
        port = aPort;
        connectionData = aConnData;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( connectionData == null ) ? 0 : connectionData.hashCode() );
        result = prime * result + ( ( hostName == null ) ? 0 : hostName.hashCode() );
        result = prime * result + ( isSecurityEnabled ? 1231 : 1237 );
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
        result = prime * result + ( ( password == null ) ? 0 : password.hashCode() );
        result = prime * result + port;
        result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
        result = prime * result + ( ( username == null ) ? 0 : username.hashCode() );
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        ConnectionDetails other = (ConnectionDetails) obj;
        if ( connectionData == null ) {
            if ( other.connectionData != null )
                return false;
        }
        else if ( !connectionData.equals( other.connectionData ) )
            return false;
        if ( hostName == null ) {
            if ( other.hostName != null )
                return false;
        }
        else if ( !hostName.equals( other.hostName ) )
            return false;
        if ( isSecurityEnabled != other.isSecurityEnabled )
            return false;
        if ( name == null ) {
            if ( other.name != null )
                return false;
        }
        else if ( !name.equals( other.name ) )
            return false;
        if ( password == null ) {
            if ( other.password != null )
                return false;
        }
        else if ( !password.equals( other.password ) )
            return false;
        if ( port != other.port )
            return false;
        if ( type == null ) {
            if ( other.type != null )
                return false;
        }
        else if ( !type.equals( other.type ) )
            return false;
        if ( username == null ) {
            if ( other.username != null )
                return false;
        }
        else if ( !username.equals( other.username ) )
            return false;
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer( "JmsConnectionDetails: name=[" ).append( name )
            .append( "], type=[" )
            .append( type )
            .append( "], hostname=[" )
            .append( hostName )
            .append( "], port=[" )
            .append( port );
        for ( String s : connectionData.keySet() ) {
            buff.append( "], " ).append( s ).append( "=[" ).append( connectionData.get( s ) );
        }
        buff.append( "]" );

        buff.append( ", securityEnabled=[" ).append( isSecurityEnabled ).append( "]" );

        if ( isSecurityEnabled ) {
            buff.append( ", username=[" ).append( username ).append( "], password=[*****]" );
        }

        return buff.toString();
    }

    /**
     * Self validation method. This method will look at the internal details and will self validate itself.
     * 
     * @return true if the contents are valid, else false.
     */
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
        else if ( port == 0 ) {
            ret = false;
        }
        return ret;
    }

    /**
     * This is a convenience method that allows you to set up the security details in one fell swoop
     * 
     * @param aUsername
     *            the username to use
     * @param aPassword
     *            the password to use
     */
    public final void setSecurityDetails( String aUsername, String aPassword ) {
        isSecurityEnabled = true;
        username = aUsername;
        password = aPassword;
    }

    /**
     * Getter for the connection name
     * 
     * @return the name of the connection
     */
    public final String getName() {
        return name;
    }

    /**
     * Setter for the name of the connection
     * 
     * @param aName
     *            the name to set
     */
    public final void setName( String aName ) {
        name = aName;
    }

    /**
     * Getter for the type of connection
     * 
     * @return the type of the connection
     */
    public final String getType() {
        return type;
    }

    /**
     * Setter for the type
     * 
     * @param aType
     *            the type to set
     */
    public final void setType( String aType ) {
        type = aType;
    }

    /**
     * Getter for the hostname
     * 
     * @return the hostname
     */
    public final String getHostname() {
        return hostName;
    }

    /**
     * Setter for the host name
     * 
     * @param aHost
     *            the host to set
     */
    public final void setHostname( String aHost ) {
        hostName = aHost;
    }

    /**
     * Getter for the port number
     * 
     * @return the port number
     */
    public final int getPort() {
        return port;
    }

    /**
     * Setter for the port number
     * 
     * @param aPort
     *            the port number to set
     */
    public final void setPort( int aPort ) {
        port = aPort;
    }

    /**
     * Getter for the security enabled flag
     * 
     * @return true if the security is enabled, else false
     */
    public final boolean isSecurityEnabled() {
        return isSecurityEnabled;
    }

    /**
     * Sets the security enabled flag
     */
    public final void setSecurityEnabled() {
        isSecurityEnabled = true;
    }

    /**
     * Unsets the security enabled flag
     */
    public final void unsetSecurityEnabled() {
        isSecurityEnabled = false;
    }

    /**
     * Getter for the username
     * 
     * @return the username value
     */
    public final String getUsername() {
        return username;
    }

    /**
     * Setter for the username
     * 
     * @param aUsername
     *            the username to set
     */
    public final void setUsername( String aUsername ) {
        username = aUsername;
    }

    /**
     * Getter for the password
     * 
     * @return the password value
     */
    public final String getPassword() {
        return password;
    }

    /**
     * Setter for the password field
     * 
     * @param aPassword
     *            the password to set
     */
    public final void setPassword( String aPassword ) {
        password = aPassword;
    }

    /**
     * Getter for the metadata associated with the connection
     * 
     * @return the metadata map
     */
    public final Map<String, String> getConnectionData() {
        return connectionData;
    }

    /**
     * Setter for the connection metadata
     * 
     * @param aMap
     *            the map of metadata to set
     */
    public final void setConnectionData( Map<String, String> aMap ) {
        connectionData = aMap;
    }

    /**
     * Adds a new key value pair to the connection store data
     * 
     * @param key
     *            the key
     * @param value
     *            the value
     * @throws ConnectionDataException
     *             if the key already exists
     */
    public final void addConnectionDataItem( String key, String value ) throws ConnectionDataException {
        if ( connectionData.containsKey( key ) ) {
            throw new ConnectionDataException( "Key [" + key + "] already exists in meta data" );
        }

        connectionData.put( key, value );
    }

}
