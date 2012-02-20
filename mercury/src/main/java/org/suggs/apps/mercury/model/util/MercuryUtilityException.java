/*
 * JmsHelperException.java created on 21 Jun 2007 08:07:19 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.model.util;

import org.suggs.apps.mercury.MercuryException;

/**
 * This exception class is used by all elements within the application
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2007
 */
public class MercuryUtilityException extends MercuryException {

    private static final long serialVersionUID = 6212936560917402533L;

    /**
     * Constructs a new instance.
     */
    public MercuryUtilityException() {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     */
    public MercuryUtilityException( String aMessage ) {
        super( aMessage );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     * @param aError
     */
    public MercuryUtilityException( String aMessage, Throwable aError ) {
        super( aMessage, aError );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aError
     */
    public MercuryUtilityException( Throwable aError ) {
        super( aError );
    }
}
