/*
 * XmlConnectionStoreDaoTest.java created on 24 Sep 2008 07:02:17 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore;

import junit.framework.Assert;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStoreChangeListener.ConnectionStoreEvent;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.XmlConnectionStoreDao;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This test class is used to test the XmlConnectionStore DAO objects.
 *
 * @author suggitpe
 * @version 1.0 24 Sep 2008
 */
public class XmlConnectionStoreDaoTest {

    private static final Logger LOG = LoggerFactory.getLogger( XmlConnectionStoreDaoTest.class );

    private XmlConnectionStoreDao connectionStoreDao;
    private IXmlConnectionStoreManager mockConnectionStoreManager;
    private Map<String, ConnectionDetails> mapMock;

    /**
     * This is run before each test is run
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        LOG.debug( "------------------- XmlConnectionStoreDaoTest" );
        mockConnectionStoreManager = EasyMock.createStrictMock( IXmlConnectionStoreManager.class );
        connectionStoreDao = new XmlConnectionStoreDao( mockConnectionStoreManager );
    }

    /**
     * This test will test that the correct list of connection names is returned
     *
     * @throws ConnectionStoreException
     */
    @Test
    public void testFullGetKnownConnections() throws ConnectionStoreException {
        // prepare data for the mock object
        final String TEST_1 = "test1";
        final String TEST_2 = "test2";
        HashSet<String> ret = new HashSet<String>();
        ret.add( TEST_1 );
        ret.add( TEST_2 );

        // ------- MOCK PREP
        EasyMock.expect( mockConnectionStoreManager.readConnectionData() ).andReturn( mapMock ).times( 1 );
        EasyMock.expect( mapMock.keySet() ).andReturn( ret ).times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mockConnectionStoreManager );
        EasyMock.replay( mapMock );

        // ------- TEST EXEC
        Set<String> set = connectionStoreDao.getKnownConnections().keySet();
        LOG.debug( "ConnectionContext list returned: " + set );

        Assert.assertTrue( set.size() == 2 );
        Assert.assertTrue( set.contains( TEST_1 ) );
        Assert.assertTrue( set.contains( TEST_2 ) );

        // ------- MOCK VERIFY
        EasyMock.verify( mockConnectionStoreManager );
        EasyMock.verify( mapMock );

        LOG.debug( "Test has correctly found the connection names" );
    }

    /**
     * This test will test that the correct list of connection names is returned for an empty list of connections
     *
     * @throws ConnectionStoreException
     */
    @Test
    public void testEmptyGetKnownConnections() throws ConnectionStoreException {
        // ------- MOCK PREP
        EasyMock.expect( mockConnectionStoreManager.readConnectionData() ).andReturn( mapMock ).times( 1 );

        EasyMock.expect( mapMock.keySet() ).andReturn( new HashSet<String>() ).times( 1 );
        // ------- MOCK LOAD
        EasyMock.replay( mockConnectionStoreManager );
        EasyMock.replay( mapMock );

        // ------- TEST EXEC
        Set<String> set = connectionStoreDao.getKnownConnections().keySet();
        LOG.debug( "ConnectionContext list returned: " + set );

        Assert.assertTrue( set.size() == 0 );

        // ------- MOCK VERIFY
        EasyMock.verify( mockConnectionStoreManager );
        EasyMock.verify( mapMock );

        LOG.debug( "Test has correctly found no connection names" );
    }

    /**
     * This test should delete the named connection that we specify here. Here we want to check that if we call delete
     * then we end calling the get list of names and then the save them down again
     *
     * @throws ConnectionStoreException
     */
    @Test
    public void testDeleteNamedConnection() throws ConnectionStoreException {
        final String TO_DELETE = "to_delete";

        // ------- MOCK PREP
        EasyMock.expect( mockConnectionStoreManager.readConnectionData() ).andReturn( mapMock ).times( 1 );

        EasyMock.expect( new Boolean( mapMock.containsKey( TO_DELETE ) ) )
                .andReturn( new Boolean( true ) )
                .times( 1 );

        mapMock.remove( TO_DELETE );
        EasyMock.expectLastCall().andReturn( mapMock ).times( 1 );

        mockConnectionStoreManager.saveConnectionData( mapMock );
        EasyMock.expectLastCall().times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mockConnectionStoreManager );
        EasyMock.replay( mapMock );

        // ------- TEST EXEC
        try {
            connectionStoreDao.deleteNamedConnection( TO_DELETE );

        }
        catch ( ConnectionStoreException e ) {
            LOG.error( "Failed to delete connection with name [" + TO_DELETE + "]", e );
            Assert.fail( "Failed to delete connection with the name [" + TO_DELETE + "]" );
        }

        // ------- MOCK VERIFY
        EasyMock.verify( mockConnectionStoreManager );
        EasyMock.verify( mapMock );

        LOG.debug( "Test has correctly called remove and save on the underlying containers" );
    }

    /**
     * This test should delete the named connection that we specify here. Here we want to check that if we call delete
     * then we end up with an exception being thrown.
     *
     * @throws ConnectionStoreException
     */
    @Test
    public void testDeleteUnknownConnection() throws ConnectionStoreException {
        final String TO_DELETE = "to_delete";

        // ------- MOCK PREP
        EasyMock.expect( mockConnectionStoreManager.readConnectionData() ).andReturn( mapMock ).times( 1 );

        EasyMock.expect( new Boolean( mapMock.containsKey( TO_DELETE ) ) )
                .andReturn( new Boolean( false ) )
                .times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mockConnectionStoreManager );
        EasyMock.replay( mapMock );

        // ------- TEST EXEC
        try {
            connectionStoreDao.deleteNamedConnection( TO_DELETE );
            LOG.error( "Suceeded in deleting connection with name [" + TO_DELETE
                    + "] .. this is bad as the connection does not exist" );
            Assert.fail( "Succeeded in deleting connection with the name [" + TO_DELETE + "]" );
        }
        catch ( ConnectionStoreException e ) {
            LOG.info( "Correctly thrown exception for the missing connection" );
        }

        // ------- MOCK VERIFY
        EasyMock.verify( mockConnectionStoreManager );
        EasyMock.verify( mapMock );

        LOG.debug( "Test has correctly found no connection to delete" );
    }

    /**
     * here we simply want to find out of the connection exists or not in the persistent store
     *
     * @throws ConnectionStoreException
     */
    @Test
    public void testDoesValidConnectionExist() throws ConnectionStoreException {
        final String FIND_ME = "find_me";
        final Set<String> set = new HashSet<String>();
        set.add( FIND_ME );
        // ------- MOCK PREP
        EasyMock.expect( mockConnectionStoreManager.readConnectionData() ).andReturn( mapMock ).times( 1 );
        EasyMock.expect( mapMock.keySet() ).andReturn( set ).times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mockConnectionStoreManager );
        EasyMock.replay( mapMock );

        // ------- TEST EXEC
        boolean exists = connectionStoreDao.doesConnectionExist( FIND_ME );
        LOG.debug( "ConnectionContext exists test for connection [" + FIND_ME + "] returns [" + exists + "]" );
        if ( !exists ) {
            Assert.fail( "The connection [" + FIND_ME + "] should exist" );
        }

        // ------- MOCK VERIFY
        EasyMock.verify( mockConnectionStoreManager );
        EasyMock.verify( mapMock );

        LOG.debug( "Test has correctly found that the connection exits" );
    }

    /**
     * here we simply want to find out of the connection exists or not in the persistent store
     *
     * @throws ConnectionStoreException
     */
    @Test
    public void testDoesInvalidConnectionExist() throws ConnectionStoreException {
        final String DONT_FIND_ME = "dont_find_me";
        final Set<String> set = new HashSet<String>();
        set.add( "hahhahahahaha" );
        // ------- MOCK PREP
        EasyMock.expect( mockConnectionStoreManager.readConnectionData() ).andReturn( mapMock ).times( 1 );
        EasyMock.expect( mapMock.keySet() ).andReturn( set ).times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mockConnectionStoreManager );
        EasyMock.replay( mapMock );

        // ------- TEST EXEC
        boolean exists = connectionStoreDao.doesConnectionExist( DONT_FIND_ME );
        LOG.debug( "ConnectionContext exists test for connection [" + DONT_FIND_ME + "] returns [" + exists
                + "]" );
        if ( exists ) {
            Assert.fail( "The connection [" + DONT_FIND_ME + "] should not exist" );
        }

        // ------- MOCK VERIFY
        EasyMock.verify( mockConnectionStoreManager );
        EasyMock.verify( mapMock );

        LOG.debug( "Test has correctly found that the connection doesn not exist" );
    }

    /**
     * in this test we want to check that when we try and load a valid connection details that it is retrieved
     * correctly
     *
     * @throws ConnectionStoreException
     */
    @Test
    public void testLoadConnectionParameters() throws ConnectionStoreException {
        final String LOAD_ME = "load_me";
        ConnectionDetails testConn = new ConnectionDetails( LOAD_ME );
        // ------- MOCK PREP
        EasyMock.expect( mockConnectionStoreManager.readConnectionData() ).andReturn( mapMock ).times( 1 );
        EasyMock.expect( new Boolean( mapMock.containsKey( LOAD_ME ) ) )
                .andReturn( new Boolean( true ) )
                .times( 1 );
        EasyMock.expect( mapMock.get( LOAD_ME ) ).andReturn( testConn ).times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mockConnectionStoreManager );
        EasyMock.replay( mapMock );

        // ------- TEST EXEC
        ConnectionDetails got = null;
        try {
            got = connectionStoreDao.loadConnectionParameters( LOAD_ME );
        }
        catch ( ConnectionStoreException ce ) {
            String err = "Failed to load connection store details for [" + LOAD_ME + "]";
            LOG.error( err, ce );
            Assert.fail( err );
        }

        Assert.assertNotNull( got );
        Assert.assertEquals( testConn, got );

        // ------- MOCK VERIFY
        EasyMock.verify( mockConnectionStoreManager );
        EasyMock.verify( mapMock );

        LOG.debug( "Test has successfully loaded connection with name of [" + LOAD_ME + "]" );
    }

    /**
     * here we want to test that if we try and load a connection parameters with no valid connections defined that we
     * throw an exception
     *
     * @throws ConnectionStoreException
     */
    @Test
    public void testFailToLoadConnectionParameters() throws ConnectionStoreException {
        final String LOAD_ME = "load_me";
        // ------- MOCK PREP
        EasyMock.expect( mockConnectionStoreManager.readConnectionData() ).andReturn( mapMock ).times( 1 );
        EasyMock.expect( new Boolean( mapMock.containsKey( LOAD_ME ) ) )
                .andReturn( new Boolean( false ) )
                .times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mockConnectionStoreManager );
        EasyMock.replay( mapMock );

        // ------- TEST EXEC
        ConnectionDetails got = null;
        try {
            got = connectionStoreDao.loadConnectionParameters( LOAD_ME );

            String err = "Failed to throw connection store exception when loading connection [" + LOAD_ME
                    + "]";
            LOG.error( err );
            Assert.fail( err );
        }
        catch ( ConnectionStoreException ce ) {
            LOG.debug( "Correctly caught exception: " + ce.getMessage() );
        }

        Assert.assertNull( got );

        // ------- MOCK VERIFY
        EasyMock.verify( mockConnectionStoreManager );
        EasyMock.verify( mapMock );

        LOG.debug( "Test has successfully failed to retrieve a connection called [" + LOAD_ME + "]" );
    }

    /**
     * This test will verify that the listeners are added and removed when the api call is made.
     *
     * @throws ConnectionStoreException
     */
    @Test
    public void testAddListenersForDelete() throws ConnectionStoreException {
        IConnectionStoreChangeListener mockList = EasyMock.createStrictMock( IConnectionStoreChangeListener.class );
        final String TO_DELETE = "to_delete";

        // ------- MOCK PREP
        EasyMock.expect( mockConnectionStoreManager.readConnectionData() ).andReturn( mapMock ).times( 1 );

        EasyMock.expect( new Boolean( mapMock.containsKey( TO_DELETE ) ) )
                .andReturn( new Boolean( true ) )
                .times( 1 );

        mapMock.remove( TO_DELETE );
        EasyMock.expectLastCall().andReturn( mapMock ).times( 1 );

        mockConnectionStoreManager.saveConnectionData( mapMock );
        EasyMock.expectLastCall().times( 1 );

        mockList.handleConnectionStoreChange( ( String ) EasyMock.anyObject(),
                ( ConnectionStoreEvent ) EasyMock.anyObject() );
        EasyMock.expectLastCall().times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mockConnectionStoreManager );
        EasyMock.replay( mapMock );
        EasyMock.replay( mockList );

        // ------- TEST EXEC
        connectionStoreDao.addConnectionStoreChangeListener( mockList );
        try {
            connectionStoreDao.deleteNamedConnection( TO_DELETE );

        }
        catch ( ConnectionStoreException e ) {
            LOG.error( "Failed to delete connection with name [" + TO_DELETE + "]", e );
            Assert.fail( "Failed to delete connection with the name [" + TO_DELETE + "]" );
        }

        // ------- MOCK VERIFY
        EasyMock.verify( mockConnectionStoreManager );
        EasyMock.verify( mapMock );
        EasyMock.verify( mockList );

        LOG.debug( "Test has correctly called remove and save on the underlying containers" );
    }

    /**
     * This test will verify that the listeners are added and removed when the api call is made.
     *
     * @throws ConnectionStoreException
     */
    @Test
    public void testAddAndRemoveListenersForDelete() throws ConnectionStoreException {
        IConnectionStoreChangeListener mockList = EasyMock.createStrictMock( IConnectionStoreChangeListener.class );
        final String TO_DELETE = "to_delete";

        // ------- MOCK PREP
        EasyMock.expect( mockConnectionStoreManager.readConnectionData() ).andReturn( mapMock ).times( 1 );

        EasyMock.expect( new Boolean( mapMock.containsKey( TO_DELETE ) ) )
                .andReturn( new Boolean( true ) )
                .times( 1 );

        mapMock.remove( TO_DELETE );
        EasyMock.expectLastCall().andReturn( mapMock ).times( 1 );

        mockConnectionStoreManager.saveConnectionData( mapMock );
        EasyMock.expectLastCall().times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mockConnectionStoreManager );
        EasyMock.replay( mapMock );
        EasyMock.replay( mockList );

        // ------- TEST EXEC
        connectionStoreDao.addConnectionStoreChangeListener( mockList );
        connectionStoreDao.removeConnectionStoreChangeListener( mockList );
        try {
            connectionStoreDao.deleteNamedConnection( TO_DELETE );

        }
        catch ( ConnectionStoreException e ) {
            LOG.error( "Failed to delete connection with name [" + TO_DELETE + "]", e );
            Assert.fail( "Failed to delete connection with the name [" + TO_DELETE + "]" );
        }

        // ------- MOCK VERIFY
        EasyMock.verify( mockConnectionStoreManager );
        EasyMock.verify( mapMock );
        EasyMock.verify( mockList );

        LOG.debug( "Test has correctly called remove and save on the underlying containers" );
    }

    /**
     * In this test we ensure that we can save the data down to the persistent store correctly
     *
     * @throws ConnectionStoreException
     */
    @Test
    public void testSaveConnectionParameters() throws ConnectionStoreException {
        final String NEW_CONN = "new_connection";
        final ConnectionDetails conn = new ConnectionDetails( NEW_CONN );
        // ------- MOCK PREP
        EasyMock.expect( mockConnectionStoreManager.readConnectionData() ).andReturn( mapMock ).times( 1 );
        EasyMock.expect( new Boolean( mapMock.containsKey( NEW_CONN ) ) )
                .andReturn( new Boolean( false ) )
                .times( 1 );
        EasyMock.expect( mapMock.put( NEW_CONN, conn ) ).andReturn( conn ).times( 1 );
        mockConnectionStoreManager.saveConnectionData( mapMock );
        EasyMock.expectLastCall().times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mockConnectionStoreManager );
        EasyMock.replay( mapMock );

        // ------- TEST EXEC
        try {
            connectionStoreDao.saveConnectionParameters( NEW_CONN, conn );
        }
        catch ( ConnectionStoreException ce ) {
            String err = "Failed to save connection store details for [" + NEW_CONN + "]";
            LOG.error( err, ce );
            Assert.fail( err );
        }

        // ------- MOCK VERIFY
        EasyMock.verify( mockConnectionStoreManager );
        EasyMock.verify( mapMock );

        LOG.debug( "Test has successfully added a new connection to the underlying store" );
    }

    /**
     * In this test we ensure that if we try and resave over the top of an existing connection that we throw the correct
     * exception
     *
     * @throws ConnectionStoreException
     */
    @Test
    public void testFailToSaveConnectionParameters() throws ConnectionStoreException {
        final String NEW_CONN = "new_connection";
        final ConnectionDetails conn = new ConnectionDetails( NEW_CONN );
        // ------- MOCK PREP
        EasyMock.expect( mockConnectionStoreManager.readConnectionData() ).andReturn( mapMock ).times( 1 );
        EasyMock.expect( new Boolean( mapMock.containsKey( NEW_CONN ) ) )
                .andReturn( new Boolean( true ) )
                .times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mockConnectionStoreManager );
        EasyMock.replay( mapMock );

        // ------- TEST EXEC
        try {
            connectionStoreDao.saveConnectionParameters( NEW_CONN, conn );
            String err = "Failed to throw exception when saving down new connection [" + NEW_CONN + "]";
            LOG.error( err );
            Assert.fail( err );
        }
        catch ( ConnectionStoreException ce ) {
            LOG.debug( "Exception correctly thrown when trying to persist connection [" + NEW_CONN + "]" );
        }

        // ------- MOCK VERIFY
        EasyMock.verify( mockConnectionStoreManager );
        EasyMock.verify( mapMock );

        LOG.debug( "Test has successfully failed to add a new connection to the underlying store" );
    }

}
