package ac4y.log.domain.object;

import java.sql.Timestamp;
import java.util.Date;

import ac4y.base.domain.Ac4y;

/**
 * Domain object representing a log entry in the Ac4y logging system.
 * Contains all the information about a single log event including classification,
 * module information, event details, and timestamps.
 *
 * @author Ac4y Log Module
 * @version 1.20190420.1
 */
public class Ac4yLog extends Ac4y {

	/**
	 * Default constructor.
	 */
	public Ac4yLog(){}

	/**
	 * Constructs a complete Ac4yLog entry with all fields.
	 *
	 * @param aId the unique identifier for this log entry
	 * @param aLogClass the classification or category of the log entry
	 * @param aModule the module or component that generated the log
	 * @param aEvent the event type or action being logged
	 * @param aInformation detailed information about the log event
	 * @param aProperties additional properties as key-value pairs
	 * @param aThreadID the thread identifier where the log was generated
	 * @param aUploaded the timestamp when the log was uploaded to the system
	 * @param aOriginalTimestamp the original timestamp when the event occurred
	 */
	public Ac4yLog(
			int aId
			,String aLogClass
			,String aModule
			,String aEvent
			,String aInformation
			,String aProperties
			,String aThreadID
			,Timestamp aUploaded
			,Timestamp aOriginalTimestamp) {
		
		setId(aId);
		setLogClass(aLogClass);
		setModule(aModule);
		setEvent(aEvent);
		setInformation(aInformation);
		setProperties(aProperties);
		setThreadID(aThreadID);
		setOriginalTimestamp(aOriginalTimestamp);
		setUploaded(aUploaded);
		
	}

	/**
	 * Gets the unique identifier.
	 * @return the log entry ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identifier.
	 * @param id the log entry ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the log classification.
	 * @return the log class
	 */
	public String getLogClass() {
		return logClass;
	}

	/**
	 * Sets the log classification.
	 * @param logClass the log class
	 */
	public void setLogClass(String logClass) {
		this.logClass = logClass;
	}

	/**
	 * Gets the module name.
	 * @return the module that generated the log
	 */
	public String getModule() {
		return module;
	}

	/**
	 * Sets the module name.
	 * @param module the module that generated the log
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * Gets the event type.
	 * @return the event being logged
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * Sets the event type.
	 * @param event the event being logged
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	/**
	 * Gets the detailed information.
	 * @return additional information about the log event
	 */
	public String getInformation() {
		return information;
	}

	/**
	 * Sets the detailed information.
	 * @param information additional information about the log event
	 */
	public void setInformation(String information) {
		this.information = information;
	}

	/**
	 * Gets the properties string.
	 * @return additional properties as key-value pairs
	 */
	public String getProperties() {
		return properties;
	}

	/**
	 * Sets the properties string.
	 * @param properties additional properties as key-value pairs
	 */
	public void setProperties(String properties) {
		this.properties = properties;
	}

	/**
	 * Gets the thread identifier.
	 * @return the thread ID where the log was generated
	 */
	public String getThreadID() {
		return threadID;
	}

	/**
	 * Sets the thread identifier.
	 * @param threadID the thread ID where the log was generated
	 */
	public void setThreadID(String threadID) {
		this.threadID = threadID;
	}

	/**
	 * Gets the upload timestamp.
	 * @return the date and time when the log was uploaded
	 */
	public Date getUploaded() {
		return uploaded;
	}

	/**
	 * Sets the upload timestamp.
	 * @param uploaded the date and time when the log was uploaded
	 */
	public void setUploaded(Date uploaded) {
		this.uploaded = uploaded;
	}

	/**
	 * Gets the original event timestamp.
	 * @return the date and time when the event originally occurred
	 */
	public Date getOriginalTimestamp() {
		return originalTimestamp;
	}

	/**
	 * Sets the original event timestamp.
	 * @param originalTimestamp the date and time when the event originally occurred
	 */
	public void setOriginalTimestamp(Date originalTimestamp) {
		this.originalTimestamp = originalTimestamp;
	}

	/** Unique identifier for the log entry */
	protected int id;
	/** Classification or category of the log */
	protected String logClass;
	/** Module or component that generated the log */
	protected String module;
	/** Event type or action being logged */
	protected String event;
	/** Detailed information about the log event */
	protected String information;
	/** Additional properties as key-value pairs */
	protected String properties;
	/** Thread identifier where the log was generated */
	protected String threadID;
	/** Timestamp when the log was uploaded to the system */
	protected Date uploaded;
	/** Original timestamp when the event occurred */
	protected Date originalTimestamp;
	
}