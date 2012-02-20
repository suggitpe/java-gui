/*
 * JmsConnectionException.java created on 20 Jul 2007 18:04:16 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection;

import org.suggs.apps.mercury.MercuryException;

/**
 * Exception to be used when there is an issue regarding the jms connection (manager and store)
 * 
 * @author suggitpe
 * @version 1.0 20 Jul 2007
 */
public class ConnectionDataException extends MercuryException {

    private static final long serialVersionUID = -579993736274958645L;

    /**
     * Constructs a new instance.
     */
    public ConnectionDataException() {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     */
    public ConnectionDataException( String aMessage ) {
        super( aMessage );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     * @param aError
     */
    public ConnectionDataException( String aMessage, Throwable aError ) {
        super( aMessage, aError );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aError
     */
    public ConnectionDataException( Throwable aError ) {
        super( aError );
    }

}
