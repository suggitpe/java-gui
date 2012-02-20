/*
 * JmsHelperException.java created on 21 Jun 2007 08:07:19 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury;

/**
 * This exception class is used by all elements within the application
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2007
 */
public class MercuryException extends Exception {

    private static final long serialVersionUID = 5993836781035133794L;

    /**
     * Constructs a new instance.
     */
    public MercuryException() {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     */
    public MercuryException( String aMessage ) {
        super( aMessage );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     * @param aError
     */
    public MercuryException( String aMessage, Throwable aError ) {
        super( aMessage, aError );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aError
     */
    public MercuryException( Throwable aError ) {
        super( aError );
    }
}
