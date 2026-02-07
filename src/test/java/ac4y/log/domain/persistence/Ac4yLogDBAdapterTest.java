package ac4y.log.domain.persistence;

import ac4y.log.domain.object.Ac4yLog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for Ac4yLogDBAdapter.
 * Tests database operations with mocked JDBC connections.
 */
public class Ac4yLogDBAdapterTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private Statement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    private Ac4yLogDBAdapter adapter;
    private AutoCloseable closeable;

    @Before
    public void setUp() throws Exception {
        closeable = MockitoAnnotations.openMocks(this);
        adapter = new Ac4yLogDBAdapter(mockConnection);
    }

    @After
    public void tearDown() throws Exception {
        if (closeable != null) {
            closeable.close();
        }
    }

    @Test
    public void testConstructor() {
        assertNotNull("Adapter should be created", adapter);
        assertEquals("Connection should be set", mockConnection, adapter.getConnection());
    }

    @Test
    public void testInsert() throws SQLException {
        String logClass = "TestClass";
        String module = "TestModule";
        String event = "TestEvent";
        String information = "Test info";
        String properties = "prop=value";
        String threadID = "thread-123";
        Date originalTimestamp = new Date();

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        adapter.insert(logClass, module, event, information, properties, threadID, originalTimestamp);

        verify(mockConnection).prepareStatement(contains("INSERT INTO Ac4yLog"));
        verify(mockPreparedStatement).setString(1, logClass);
        verify(mockPreparedStatement).setString(2, module);
        verify(mockPreparedStatement).setString(3, event);
        verify(mockPreparedStatement).setString(4, information);
        verify(mockPreparedStatement).setString(5, properties);
        verify(mockPreparedStatement).setString(6, threadID);
        verify(mockPreparedStatement).setTimestamp(eq(7), any(Timestamp.class));
        verify(mockPreparedStatement).executeUpdate();
        verify(mockPreparedStatement).close();
    }

    @Test(expected = SQLException.class)
    public void testInsertWithSQLException() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        adapter.insert("class", "module", "event", "info", "props", "thread", new Date());
    }

    @Test
    public void testGet() throws SQLException {
        int testId = 123;
        String logClass = "TestClass";
        String module = "TestModule";
        String event = "TestEvent";
        String information = "Test info";
        String properties = "prop=value";
        String threadID = "thread-123";
        Timestamp uploaded = new Timestamp(System.currentTimeMillis());
        Timestamp originalTimestamp = new Timestamp(System.currentTimeMillis() - 1000);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("logclass")).thenReturn(logClass);
        when(mockResultSet.getString("module")).thenReturn(module);
        when(mockResultSet.getString("event")).thenReturn(event);
        when(mockResultSet.getString("information")).thenReturn(information);
        when(mockResultSet.getString("properties")).thenReturn(properties);
        when(mockResultSet.getString("threadID")).thenReturn(threadID);
        when(mockResultSet.getTimestamp("uploaded")).thenReturn(uploaded);
        when(mockResultSet.getTimestamp("originalTimestamp")).thenReturn(originalTimestamp);

        Ac4yLog result = adapter.get(testId);

        assertNotNull("Result should not be null", result);
        assertEquals("ID should match", testId, result.getId());
        assertEquals("LogClass should match", logClass, result.getLogClass());
        assertEquals("Module should match", module, result.getModule());
        assertEquals("Event should match", event, result.getEvent());
        assertEquals("Information should match", information, result.getInformation());
        assertEquals("Properties should match", properties, result.getProperties());
        assertEquals("ThreadID should match", threadID, result.getThreadID());
        assertEquals("Uploaded should match", uploaded, result.getUploaded());
        assertEquals("OriginalTimestamp should match", originalTimestamp, result.getOriginalTimestamp());

        verify(mockStatement).executeQuery(contains("SELECT * FROM Ac4yLog"));
        verify(mockStatement).close();
    }

    @Test
    public void testGetNotFound() throws SQLException {
        int testId = 999;

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        Ac4yLog result = adapter.get(testId);

        assertNull("Result should be null when not found", result);
        verify(mockStatement).close();
    }

    @Test(expected = SQLException.class)
    public void testGetWithSQLException() throws SQLException {
        when(mockConnection.createStatement()).thenThrow(new SQLException("Database error"));

        adapter.get(123);
    }

    @Test
    public void testInsertWithNullValues() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        adapter.insert(null, null, null, null, null, null, new Date());

        verify(mockPreparedStatement).setString(1, null);
        verify(mockPreparedStatement).setString(2, null);
        verify(mockPreparedStatement).setString(3, null);
        verify(mockPreparedStatement).setString(4, null);
        verify(mockPreparedStatement).setString(5, null);
        verify(mockPreparedStatement).setString(6, null);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testConnectionGetterAndSetter() {
        Connection newConnection = mock(Connection.class);
        adapter.setConnection(newConnection);
        assertEquals("Connection should be updated", newConnection, adapter.getConnection());
    }
}
