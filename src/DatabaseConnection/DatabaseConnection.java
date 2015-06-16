package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Class for connecting to and disconnecting from Oracle SQL database. A connection must be made prior to any query to the database.
 * 
 * @author Fabian
 *
 */


public class DatabaseConnection {
	private static DatabaseConnection databaseConnection = null;
	public Connection con;
	
	public DatabaseConnection() {
	}
	
	// singleton pattern: returns instance of DatabaseConnection
	public static DatabaseConnection getInstance() {
		if (databaseConnection == null) {
			databaseConnection = new DatabaseConnection();
		}
		return databaseConnection;
	}
	
	
	
	public void connectToDB() {
		// Register driver
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			System.out.println("SQLException when registering driver");
			e.printStackTrace();
		}
		
		// Get connection
		try {
			Scanner userInput = new Scanner(System.in);
			String username;
			String password;
			System.out.println("Oracle Username (ora_<cs id>): ");
			username = userInput.next();
			System.out.println("Password ('a' + student #: ");
			password = userInput.next();

			con = DriverManager.getConnection("jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug", username,
					password);
			System.out.println("Connection made");
			con.setAutoCommit(false);

		} catch (SQLException e) {
			System.out.println("SQL Exception making connection");
			e.printStackTrace();
		}

	}
	
	public Connection getConnection() {
		return con;
	}
	
	public void disconnectFromDB() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("SQL Exception disconnecting from DB");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Connection has not been established");
		}
	}

}
