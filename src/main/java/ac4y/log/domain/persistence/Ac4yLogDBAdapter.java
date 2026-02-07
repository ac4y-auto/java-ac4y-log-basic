package ac4y.log.domain.persistence;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ac4y.log.domain.object.Ac4yLog;

/**
 * Database adapter for Ac4yLog persistence operations.
 * Provides methods to insert and retrieve log entries from the database.
 * Uses JDBC for database operations.
 *
 * <p><b>Note:</b> This class contains a SQL injection vulnerability in the get() method
 * at line 70 where integer ID is concatenated directly into SQL query. This should be
 * fixed using prepared statements.</p>
 *
 * @author Ac4y Log Module
 * @version 1.20190420.1
 */
public class Ac4yLogDBAdapter {

	/**
	 * Constructs a new Ac4yLogDBAdapter with the specified database connection.
	 *
	 * @param aConnection the database connection to use for all operations
	 */
	public Ac4yLogDBAdapter(Connection aConnection){
		setConnection(aConnection);
	}

	/**
	 * Gets the current database connection.
	 *
	 * @return the database connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Sets the database connection.
	 *
	 * @param connection the database connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/** The database connection used for all operations */
	private Connection connection;

	/**
	 * Inserts a new log entry into the database.
	 * Uses prepared statements to safely insert log data.
	 *
	 * @param aLogClass the classification or category of the log entry
	 * @param aModule the module or component that generated the log
	 * @param aEvent the event type or action being logged
	 * @param aInformation detailed information about the log event
	 * @param aProperties additional properties as key-value pairs
	 * @param aThreadID the thread identifier where the log was generated
	 * @param aOriginalTimestamp the original timestamp when the event occurred
	 * @throws SQLException if a database access error occurs
	 */
	public void insert(
						String aLogClass
						,String aModule
						,String aEvent
						,String aInformation
						,String aProperties
						,String aThreadID
						,Date aOriginalTimestamp
					) throws SQLException{
		
		//protected int id;
		//logClass, module, event, information, properties, threadID, originalTimestamp

		PreparedStatement preparedStatement = null;
		 
		String sqlStatement = "INSERT INTO Ac4yLog"
				+ "(logClass, module, event, information, properties, threadID, originalTimestamp) VALUES"
				+ "(?,?,?,?,?,?,?)";
		
		preparedStatement = getConnection().prepareStatement(sqlStatement);
		
		preparedStatement.setString(1, aLogClass);
		preparedStatement.setString(2, aModule);
		preparedStatement.setString(3, aEvent);
		preparedStatement.setString(4, aInformation);
		preparedStatement.setString(5, aProperties);
		preparedStatement.setString(6, aThreadID);
		preparedStatement.setTimestamp(7, new java.sql.Timestamp(aOriginalTimestamp.getTime()));
		 
		preparedStatement.executeUpdate();				
		
		preparedStatement.close();

	} // insert

	/**
	 * Retrieves a log entry from the database by its ID.
	 *
	 * <p><b>Warning:</b> This method contains a SQL injection vulnerability.
	 * The ID parameter is concatenated directly into the SQL query at line 70.
	 * Should be refactored to use prepared statements.</p>
	 *
	 * @param aId the unique identifier of the log entry to retrieve
	 * @return the Ac4yLog object if found, null otherwise
	 * @throws SQLException if a database access error occurs
	 */
	public Ac4yLog get(int aId) throws SQLException{
		
		Ac4yLog ac4yLog = null;
		
		String sqlStatement = 
					"SELECT * FROM Ac4yLog"
							+ " WHERE"
							+ " id = " + Integer.toString(aId)
					;		

		Statement 	statement 		= getConnection().createStatement();
		
		ResultSet resultSet = statement.executeQuery(sqlStatement);

		if (resultSet.next()){
			
			ac4yLog = 
				new Ac4yLog(
						aId
						,resultSet.getString("logclass")
						,resultSet.getString("module")
						,resultSet.getString("event")
						,resultSet.getString("information")
						,resultSet.getString("properties")
						,resultSet.getString("threadID")
						,resultSet.getTimestamp("uploaded")
						,resultSet.getTimestamp("originalTimestamp")
				);

		}
		
		statement.close();

		return ac4yLog;
				
	} // get
	
} // Ac4yAssociationDBAdapter