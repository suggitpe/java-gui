/*
 * ConnectionType.java created on 3 Jul 2007 07:53:33 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old.model.connection;

/**
 * Enumeration to define the allowable connection types
 * 
 * @author suggitpe
 * @version 1.0 3 Jul 2007
 */
public enum EConnectionType {
    /**
     * MQ Series connection type
     */
    MQ,
    /**
     * Tibco EMS connection type
     */
    EMS;

    /**
     * Simple builder method so that you can create an enum form a
     * string
     * 
     * @param aTypeName
     *            the name for the enum
     * @return the enumeration for the string (null if none found)
     */
    public static final EConnectionType createTypeFromString( String aTypeName )
    {
        if ( aTypeName.equalsIgnoreCase( "ems" ) )
        {
            return EMS;
        }
        else if ( aTypeName.equalsIgnoreCase( "mq" ) )
        {
            return MQ;
        }
        return null;
    }

}
