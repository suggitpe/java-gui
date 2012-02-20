/*
 * CachedXmlConnectionStoreManagerDecorator.java created on 2 Oct 2008 18:47:56 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore.xmldao.impl;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connectionstore.ConnectionStoreException;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class decorates an existing implementation of an IXmlConnectionStoreManager with a cache mechanism.
 * 
 * @author suggitpe
 * @version 1.0 2 Oct 2008
 */
public class CachedXmlConnectionStoreManagerDecorator implements IXmlConnectionStoreManager {

    private static final Logger LOG = LoggerFactory.getLogger( CachedXmlConnectionStoreManagerDecorator.class );

    private IXmlConnectionStoreManager xmlConnectionStoreManager;
    private Map<String, ConnectionDetails> cache;

    /**
     * Constructs a new instance.
     */
    public CachedXmlConnectionStoreManagerDecorator( IXmlConnectionStoreManager aXmlConnectionStoreManager ) {
        xmlConnectionStoreManager = aXmlConnectionStoreManager;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager#readConnectionData()
     */
    @Override
    public final Map<String, ConnectionDetails> readConnectionData() throws ConnectionStoreException {
        if ( cache == null ) {
            LOG.debug( "Loading data from persistent store" );
            cache = xmlConnectionStoreManager.readConnectionData();
        }
        return cache;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager#getRawXml()
     */
    @Override
    public final String getRawXml() throws ConnectionStoreException {
        return xmlConnectionStoreManager.getRawXml();
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager#saveConnectionData(java.util.Map)
     */
    @Override
    public final void saveConnectionData( Map<String, ConnectionDetails> map )
                    throws ConnectionStoreException {
        LOG.debug( "Refreshing local cache" );
        cache = map;
        xmlConnectionStoreManager.saveConnectionData( map );
    }

    /**
     * Getter for the connection store manager
     * 
     * @return the connection store manager
     */
    public final IXmlConnectionStoreManager getConnectionStoreManager() {
        return xmlConnectionStoreManager;
    }

    /**
     * Setter for the connection store manager
     * 
     * @param connStrManager
     *            the connection store manager to set
     */
    public final void setConnectionStoreManager( IXmlConnectionStoreManager connStrManager ) {
        xmlConnectionStoreManager = connStrManager;
    }

}
