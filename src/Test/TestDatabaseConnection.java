package Test;

import DatabaseConnection.DatabaseConnection;

public class TestDatabaseConnection {
	public static void main(String[] args) {
		DatabaseConnection d = DatabaseConnection.getInstance();
		d.connectToDB();
	}

}
