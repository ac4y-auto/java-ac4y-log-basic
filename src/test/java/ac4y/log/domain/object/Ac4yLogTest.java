package ac4y.log.domain.object;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Unit tests for Ac4yLog domain object.
 * Tests getters, setters, and constructors.
 */
public class Ac4yLogTest {

    private Ac4yLog ac4yLog;
    private static final int TEST_ID = 1;
    private static final String TEST_LOG_CLASS = "TestClass";
    private static final String TEST_MODULE = "TestModule";
    private static final String TEST_EVENT = "TestEvent";
    private static final String TEST_INFORMATION = "Test information";
    private static final String TEST_PROPERTIES = "property1=value1";
    private static final String TEST_THREAD_ID = "thread-123";
    private Timestamp testUploaded;
    private Timestamp testOriginalTimestamp;

    @Before
    public void setUp() {
        ac4yLog = new Ac4yLog();
        testUploaded = new Timestamp(System.currentTimeMillis());
        testOriginalTimestamp = new Timestamp(System.currentTimeMillis() - 1000);
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull("Default constructor should create instance", ac4yLog);
    }

    @Test
    public void testParameterizedConstructor() {
        Ac4yLog log = new Ac4yLog(
            TEST_ID,
            TEST_LOG_CLASS,
            TEST_MODULE,
            TEST_EVENT,
            TEST_INFORMATION,
            TEST_PROPERTIES,
            TEST_THREAD_ID,
            testUploaded,
            testOriginalTimestamp
        );

        assertEquals("ID should match", TEST_ID, log.getId());
        assertEquals("LogClass should match", TEST_LOG_CLASS, log.getLogClass());
        assertEquals("Module should match", TEST_MODULE, log.getModule());
        assertEquals("Event should match", TEST_EVENT, log.getEvent());
        assertEquals("Information should match", TEST_INFORMATION, log.getInformation());
        assertEquals("Properties should match", TEST_PROPERTIES, log.getProperties());
        assertEquals("ThreadID should match", TEST_THREAD_ID, log.getThreadID());
        assertEquals("Uploaded should match", testUploaded, log.getUploaded());
        assertEquals("OriginalTimestamp should match", testOriginalTimestamp, log.getOriginalTimestamp());
    }

    @Test
    public void testIdGetterAndSetter() {
        ac4yLog.setId(TEST_ID);
        assertEquals("ID should be set correctly", TEST_ID, ac4yLog.getId());
    }

    @Test
    public void testLogClassGetterAndSetter() {
        ac4yLog.setLogClass(TEST_LOG_CLASS);
        assertEquals("LogClass should be set correctly", TEST_LOG_CLASS, ac4yLog.getLogClass());
    }

    @Test
    public void testModuleGetterAndSetter() {
        ac4yLog.setModule(TEST_MODULE);
        assertEquals("Module should be set correctly", TEST_MODULE, ac4yLog.getModule());
    }

    @Test
    public void testEventGetterAndSetter() {
        ac4yLog.setEvent(TEST_EVENT);
        assertEquals("Event should be set correctly", TEST_EVENT, ac4yLog.getEvent());
    }

    @Test
    public void testInformationGetterAndSetter() {
        ac4yLog.setInformation(TEST_INFORMATION);
        assertEquals("Information should be set correctly", TEST_INFORMATION, ac4yLog.getInformation());
    }

    @Test
    public void testPropertiesGetterAndSetter() {
        ac4yLog.setProperties(TEST_PROPERTIES);
        assertEquals("Properties should be set correctly", TEST_PROPERTIES, ac4yLog.getProperties());
    }

    @Test
    public void testThreadIDGetterAndSetter() {
        ac4yLog.setThreadID(TEST_THREAD_ID);
        assertEquals("ThreadID should be set correctly", TEST_THREAD_ID, ac4yLog.getThreadID());
    }

    @Test
    public void testUploadedGetterAndSetter() {
        ac4yLog.setUploaded(testUploaded);
        assertEquals("Uploaded should be set correctly", testUploaded, ac4yLog.getUploaded());
    }

    @Test
    public void testOriginalTimestampGetterAndSetter() {
        ac4yLog.setOriginalTimestamp(testOriginalTimestamp);
        assertEquals("OriginalTimestamp should be set correctly", testOriginalTimestamp, ac4yLog.getOriginalTimestamp());
    }

    @Test
    public void testNullValues() {
        ac4yLog.setLogClass(null);
        ac4yLog.setModule(null);
        ac4yLog.setEvent(null);
        ac4yLog.setInformation(null);
        ac4yLog.setProperties(null);
        ac4yLog.setThreadID(null);
        ac4yLog.setUploaded(null);
        ac4yLog.setOriginalTimestamp(null);

        assertNull("LogClass should be null", ac4yLog.getLogClass());
        assertNull("Module should be null", ac4yLog.getModule());
        assertNull("Event should be null", ac4yLog.getEvent());
        assertNull("Information should be null", ac4yLog.getInformation());
        assertNull("Properties should be null", ac4yLog.getProperties());
        assertNull("ThreadID should be null", ac4yLog.getThreadID());
        assertNull("Uploaded should be null", ac4yLog.getUploaded());
        assertNull("OriginalTimestamp should be null", ac4yLog.getOriginalTimestamp());
    }

    @Test
    public void testDateTypesCompatibility() {
        Date dateUploaded = new Date();
        Date dateOriginalTimestamp = new Date(System.currentTimeMillis() - 1000);

        ac4yLog.setUploaded(dateUploaded);
        ac4yLog.setOriginalTimestamp(dateOriginalTimestamp);

        assertEquals("Date uploaded should be compatible", dateUploaded, ac4yLog.getUploaded());
        assertEquals("Date originalTimestamp should be compatible", dateOriginalTimestamp, ac4yLog.getOriginalTimestamp());
    }
}
