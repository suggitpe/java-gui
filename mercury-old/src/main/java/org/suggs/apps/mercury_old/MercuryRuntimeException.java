/*
 * JmsHelperException.java created on 21 Jun 2007 08:07:19 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old;

/**
 * This exception class is used by all elements within the application
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2007
 */
public class MercuryRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -5255875794575867483L;

    /**
     * Constructs a new instance.
     */
    public MercuryRuntimeException() {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     */
    public MercuryRuntimeException( String aMessage ) {
        super( aMessage );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     * @param aError
     */
    public MercuryRuntimeException( String aMessage, Throwable aError ) {
        super( aMessage, aError );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aError
     */
    public MercuryRuntimeException( Throwable aError ) {
        super( aError );
    }
}
