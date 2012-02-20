/*
 * CachedXmlConnectionStoreManagerDecoratorTest.java created on 2 Oct 2008 18:55:29 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore;

import junit.framework.Assert;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.impl.CachedXmlConnectionStoreManagerDecorator;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Test class to test the cached xml connection store decorator. The main things that this test will do is to make sure
 * that the decorator actually uses the cache rather than going to the persistent store.
 *
 * @author suggitpe
 * @version 1.0 2 Oct 2008
 */
@RunWith(value = MockitoJUnitRunner.class)
public class CachedXmlConnectionStoreManagerDecoratorUnitTest {

    private static final Logger LOG = LoggerFactory.getLogger( CachedXmlConnectionStoreManagerDecoratorUnitTest.class );

    private CachedXmlConnectionStoreManagerDecorator decorator;

    @Mock
    private IXmlConnectionStoreManager mockConnectionStoreManager;

    @Mock
    private Map<String, ConnectionDetails> mapMock;

    /**
     * This is called before the execution of each test
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        LOG.debug( "------------------- CachedXmlConnectionStoreManagerDecoratorTest" );

        LOG.debug( "Creating mock for IXmlConnectionStoreManager" );
        decorator = new CachedXmlConnectionStoreManagerDecorator( mockConnectionStoreManager );
        decorator.setConnectionStoreManager( mockConnectionStoreManager );
    }

    /**
     * In this test we want to verify that if we call the/ decorator a few times that we only ever call the persistent/
     * store once (ie it uses the cache).
     *
     * @throws ConnectionStoreException needs to be caught due to mock framework syntax
     */
    @Test
    public void testReadConnectionData() throws ConnectionStoreException {

        when( mockConnectionStoreManager.readConnectionData() ).thenReturn( mapMock );

        try {
            decorator.readConnectionData();
            decorator.readConnectionData();
            decorator.readConnectionData();
        }
        catch ( ConnectionStoreException ce ) {
            String err = "";
            LOG.error( err, ce );
            Assert.fail( err );
        }

        verify( mockConnectionStoreManager, times( 1 ) ).readConnectionData();
        verifyNoMoreInteractions( mockConnectionStoreManager );
        LOG.debug( "Correctly read the connection data three times" );
    }

    /**
     * This test will read a new map from the store, then save down the same one again to test the cache is being used
     * correctly (ie only reads once)
     *
     * @throws ConnectionStoreException needs to be caught due to mock framework syntax
     */
    @Test
    public void testSaveConnectionData() throws ConnectionStoreException {

        try {
            decorator.saveConnectionData( mapMock );
        }
        catch ( ConnectionStoreException ce ) {
            String err = "";
            LOG.error( err, ce );
            Assert.fail( err );
        }

        decorator.readConnectionData();

        verify( mockConnectionStoreManager, times( 1 ) ).saveConnectionData( mapMock );
        verifyNoMoreInteractions( mockConnectionStoreManager );
        LOG.debug( "Correctly saved and then read the map without going to the underlying store" );
    }

    /**
     * This test will read a new map from the store, then save down the same one again to test the cache is being used
     * correctly (ie only reads once)
     *
     * @throws ConnectionStoreException needs to be caught due to mock framework syntax
     */
    @Test
    public void testSaveAfterReadConnectionData() throws ConnectionStoreException {
        when( mockConnectionStoreManager.readConnectionData() ).thenReturn( mapMock );

        Map<String, ConnectionDetails> map = decorator.readConnectionData();
        try {
            decorator.saveConnectionData( map );
        }
        catch ( ConnectionStoreException ce ) {
            String err = "";
            LOG.error( err, ce );
            Assert.fail( err );
        }

        Map<String, ConnectionDetails> map2 = decorator.readConnectionData();
        assertThat( map, equalTo( map2 ) );
        assertThat( map, sameInstance( map2 ) );

        verify( mockConnectionStoreManager, times( 1 ) ).saveConnectionData( mapMock );
        verify( mockConnectionStoreManager, times( 1 ) ).readConnectionData();
        verifyNoMoreInteractions( mockConnectionStoreManager );

        LOG.debug( "Test has correctly read, saved and then re-read the map" );
    }
}
