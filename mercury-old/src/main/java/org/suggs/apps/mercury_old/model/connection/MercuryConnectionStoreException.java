/*
 * JmsConnectionException.java created on 20 Jul 2007 18:04:16 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury_old.model.connection;

import org.suggs.apps.mercury_old.MercuryException;

/**
 * Exception to be used when there is an issue regarding the jms connection (manager and store)
 * 
 * @author suggitpe
 * @version 1.0 20 Jul 2007
 */
public class MercuryConnectionStoreException extends MercuryException {

    private static final long serialVersionUID = -6853763885982687014L;

    /**
     * Constructs a new instance.
     */
    public MercuryConnectionStoreException() {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     */
    public MercuryConnectionStoreException( String aMessage ) {
        super( aMessage );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     * @param aError
     */
    public MercuryConnectionStoreException( String aMessage, Throwable aError ) {
        super( aMessage, aError );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aError
     */
    public MercuryConnectionStoreException( Throwable aError ) {
        super( aError );
    }

}
