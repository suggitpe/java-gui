/*
 * JmsConnectionException.java created on 20 Jul 2007 18:04:16 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore;

import org.suggs.apps.mercury.MercuryException;

/**
 * Exception to be used when there is an issue regarding the jms connection (manager and store)
 * 
 * @author suggitpe
 * @version 1.0 20 Jul 2007
 */
public class ConnectionStoreException extends MercuryException {

    private static final long serialVersionUID = 346980511299078215L;

    /**
     * Constructs a new instance.
     */
    public ConnectionStoreException() {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     */
    public ConnectionStoreException( String aMessage ) {
        super( aMessage );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     * @param aError
     */
    public ConnectionStoreException( String aMessage, Throwable aError ) {
        super( aMessage, aError );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aError
     */
    public ConnectionStoreException( Throwable aError ) {
        super( aError );
    }

}
