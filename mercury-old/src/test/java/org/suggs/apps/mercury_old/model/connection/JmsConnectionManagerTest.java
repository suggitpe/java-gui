/*
 * JmsConnectionTest.java created on 2 Aug 2007 06:03:25 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury_old.model.connection;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A a test for the connection manager classes
 * 
 * @author suggitpe
 * @version 1.0 2 Aug 2007
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-model-connection-JmsConnMgrTest.xml" })
public class JmsConnectionManagerTest {

    private static final Logger LOG = LoggerFactory.getLogger( JmsConnectionManagerTest.class );

    @Resource
    protected String portNum;
    @Resource
    protected String server;
    @Resource
    protected String type;
    @Resource
    protected IConnectionManager connMgr;

    /**
     * Constructs a new instance.
     */
    public JmsConnectionManagerTest() {}

    @Test
    public void testBasicConnection() {

        // IConnectionDetails dtls = new ConnectionDetails(
        // "TestConnection",
        // EConnectionType.valueOf( "EMS" ),
        // mServer_,
        // mPortNum_ );

        LOG.debug( "Initial state is [" + connMgr.getConnectionState().name() + "]" );
        /*
         * try { mConnMgr_.connect( dtls ); } catch ( MercuryConnectionException me ) { fail(
         * "Exception thrown when trying to connect: " + me.getMessage() ); }
         */

        LOG.debug( "Connected state is [" + connMgr.getConnectionState().name() + "]" );
        if ( connMgr.getConnectionState() != EConnectionState.CONNECTED ) {
            // fail( "Test should be in a connecvted state
            // now" );
        }

        try {
            connMgr.disconnect();
        }
        catch ( MercuryConnectionException me ) {
            Assert.fail( "Exception thrown when trying to connect: " + me.getMessage() );
        }

        LOG.debug( "Disconnected state is [" + connMgr.getConnectionState().name() + "]" );
        if ( connMgr.getConnectionState() != EConnectionState.DISCONNECTED ) {
            Assert.fail( "Test should now be in a disconnected state" );
        }

    }
}
