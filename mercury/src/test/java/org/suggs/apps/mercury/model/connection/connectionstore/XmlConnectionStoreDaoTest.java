/*
 * XmlConnectionStoreDaoTest.java created on 24 Sep 2008 07:02:17 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStoreChangeListener.ConnectionStoreEvent;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.XmlConnectionStoreDao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * This test class is used to test the XmlConnectionStore DAO objects.
 *
 * @author suggitpe
 * @version 1.0 24 Sep 2008
 */
public class XmlConnectionStoreDaoTest {

    private static final Logger LOG = LoggerFactory.getLogger(XmlConnectionStoreDaoTest.class);

    private XmlConnectionStoreDao connectionStoreDao;
    private IXmlConnectionStoreManager mockConnectionStoreManager;
    private Map<String, ConnectionDetails> connectionDetailsMap;

    @Before
    public void setUp() throws Exception {
        LOG.debug("------------------- XmlConnectionStoreDaoTest");
        mockConnectionStoreManager = mock(IXmlConnectionStoreManager.class);
        connectionStoreDao = new XmlConnectionStoreDao(mockConnectionStoreManager);
        connectionDetailsMap = new HashMap<String, ConnectionDetails>();
    }

    @Test
    public void shouldListAllConnectionNames() throws ConnectionStoreException {
        final String TEST_1 = "test1";
        final String TEST_2 = "test2";

        connectionDetailsMap.put(TEST_1, new ConnectionDetails(TEST_1));
        connectionDetailsMap.put(TEST_2, new ConnectionDetails(TEST_2));

        when(mockConnectionStoreManager.readConnectionData()).thenReturn(connectionDetailsMap);

        Set<String> set = connectionStoreDao.getKnownConnections().keySet();
        LOG.debug("ConnectionContext list returned: " + set);

        assertThat(set.size(), is(2));
        assertTrue(set.contains(TEST_1));
        assertTrue(set.contains(TEST_2));
    }

    @Test
    public void shouldReturnEmptySetWhenNoDefinedConnections() throws ConnectionStoreException {
        when(mockConnectionStoreManager.readConnectionData()).thenReturn(connectionDetailsMap);

        Set<String> set = connectionStoreDao.getKnownConnections().keySet();
        LOG.debug("ConnectionContext list returned: " + set);

        Assert.assertTrue(set.size() == 0);
    }

    @Test
    public void shouldDeleteConnectionByName() throws ConnectionStoreException {
        final String TO_DELETE = "to_delete";

        connectionDetailsMap.put(TO_DELETE, new ConnectionDetails(TO_DELETE));
        when(mockConnectionStoreManager.readConnectionData()).thenReturn(connectionDetailsMap);

        try {
            connectionStoreDao.deleteNamedConnection(TO_DELETE);

        } catch (ConnectionStoreException e) {
            fail("Failed to delete connection with the name [" + TO_DELETE + "]");
        }

        verify(mockConnectionStoreManager).saveConnectionData(connectionDetailsMap);
    }

    @Test
    public void shouldThrowExceptionWhenDeletingNonExistentConnection() throws ConnectionStoreException {
        final String TO_DELETE = "to_delete";

        when(mockConnectionStoreManager.readConnectionData()).thenReturn(connectionDetailsMap);

        try {
            connectionStoreDao.deleteNamedConnection(TO_DELETE);
            fail("Succeeded in deleting connection with the name [" + TO_DELETE + "] ... this is bad as the connection does not exist");
        } catch (ConnectionStoreException e) {
            LOG.info("Correctly thrown exception for the missing connection");
        }
    }

    @Test
    public void shouldAssertThatConnectionDoesExist() throws ConnectionStoreException {
        final String FIND_ME = "find_me";
        connectionDetailsMap.put(FIND_ME, new ConnectionDetails(FIND_ME));

        when(mockConnectionStoreManager.readConnectionData()).thenReturn(connectionDetailsMap);

        boolean exists = connectionStoreDao.doesConnectionExist(FIND_ME);
        if (!exists) {
            fail("The connection [" + FIND_ME + "] should exist");
        }
    }

    @Test
    public void shouldAssertThatConnectionDoesNotExist() throws ConnectionStoreException {
        final String I_DONT_EXIST = "I am not real";
        final String I_AM_REAL = "I am real";

        connectionDetailsMap.put(I_AM_REAL, new ConnectionDetails(I_AM_REAL));

        when(mockConnectionStoreManager.readConnectionData()).thenReturn(connectionDetailsMap);

        boolean exists = connectionStoreDao.doesConnectionExist(I_DONT_EXIST);
        if (exists) {
            fail("The connection [" + I_DONT_EXIST + "] should not exist");
        }
    }

    @Test
    public void shouldAllowRetrievalOfConnectionProperties() throws ConnectionStoreException {
        final String LOAD_ME = "load_me";

        ConnectionDetails testConnectionDetails = new ConnectionDetails(LOAD_ME);
        connectionDetailsMap.put(LOAD_ME, testConnectionDetails);

        when(mockConnectionStoreManager.readConnectionData()).thenReturn(connectionDetailsMap);

        ConnectionDetails connectionDetails = null;
        try {
            connectionDetails = connectionStoreDao.loadConnectionParameters(LOAD_ME);
        } catch (ConnectionStoreException ce) {
            fail("Failed to load connection store details for [" + LOAD_ME + "]");
        }

        assertThat(connectionDetails, notNullValue());
        assertThat(connectionDetails, equalTo(testConnectionDetails));
    }

    @Test
    public void shouldReturnNullWhenNoConnectionDetailsPresent() throws ConnectionStoreException {
        final String LOAD_ME = "load_me";
        when(mockConnectionStoreManager.readConnectionData()).thenReturn(connectionDetailsMap);

        ConnectionDetails connectionDetails = null;
        try {
            connectionDetails = connectionStoreDao.loadConnectionParameters(LOAD_ME);
            fail("Failed to throw connection store exception when loading connection [" + LOAD_ME + "]");
        } catch (ConnectionStoreException ce) {
            LOG.debug("Correctly caught exception: " + ce.getMessage());
        }

        assertThat(connectionDetails, nullValue());
    }

    @Test
    public void shouldPickUpChangeEventWhenListenerIsPresent() throws ConnectionStoreException {
        IConnectionStoreChangeListener changeListener = mock(IConnectionStoreChangeListener.class);
        final String TO_DELETE = "to_delete";
        connectionDetailsMap.put(TO_DELETE, new ConnectionDetails(TO_DELETE));

        when(mockConnectionStoreManager.readConnectionData()).thenReturn(connectionDetailsMap);

        connectionStoreDao.addConnectionStoreChangeListener(changeListener);
        try {
            connectionStoreDao.deleteNamedConnection(TO_DELETE);
        } catch (ConnectionStoreException e) {
            fail("Failed to delete connection with the name [" + TO_DELETE + "]");
        }

        verify(mockConnectionStoreManager).saveConnectionData(connectionDetailsMap);
        verify(changeListener).handleConnectionStoreChange(anyString(), Matchers.any(ConnectionStoreEvent.class));
    }

    @Test
    public void shouldNotPickUpChangeEventsWhenNoListener() throws ConnectionStoreException {
        IConnectionStoreChangeListener changeListener = mock(IConnectionStoreChangeListener.class);
        final String TO_DELETE = "to_delete";
        connectionDetailsMap.put(TO_DELETE, new ConnectionDetails(TO_DELETE));

        when(mockConnectionStoreManager.readConnectionData()).thenReturn(connectionDetailsMap);

        connectionStoreDao.addConnectionStoreChangeListener(changeListener);
        connectionStoreDao.removeConnectionStoreChangeListener(changeListener);
        try {
            connectionStoreDao.deleteNamedConnection(TO_DELETE);
        } catch (ConnectionStoreException e) {
            fail("Failed to delete connection with the name [" + TO_DELETE + "]");
        }

        verify(mockConnectionStoreManager).saveConnectionData(connectionDetailsMap);
        verifyNoMoreInteractions(changeListener);
    }

    @Test
    public void shouldSaveConnectionParameters() throws ConnectionStoreException {
        final String NEW_CONN = "new_connection";
        final ConnectionDetails conn = new ConnectionDetails(NEW_CONN);

        when(mockConnectionStoreManager.readConnectionData()).thenReturn(connectionDetailsMap);

        try {
            connectionStoreDao.saveConnectionParameters(NEW_CONN, conn);
        } catch (ConnectionStoreException ce) {
            fail("Failed to save connection store details for [" + NEW_CONN + "]");
        }

        verify(mockConnectionStoreManager).saveConnectionData(connectionDetailsMap);
    }

    @Test
    public void shouldThrowExceptionWhenResavingOverExistingConnectionDetails() throws ConnectionStoreException {
        final String NEW_CONN = "new_connection";
        final ConnectionDetails conn = new ConnectionDetails(NEW_CONN);
        connectionDetailsMap.put(NEW_CONN, new ConnectionDetails(NEW_CONN));

        when(mockConnectionStoreManager.readConnectionData()).thenReturn(connectionDetailsMap);

        try {
            connectionStoreDao.saveConnectionParameters(NEW_CONN, conn);
            fail("Failed to throw exception when saving down new connection [" + NEW_CONN + "]");
        } catch (ConnectionStoreException ce) {
            LOG.debug("Exception correctly thrown when trying to persist connection [" + NEW_CONN + "]");
        }
    }

}
