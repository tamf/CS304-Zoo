package Test;

import DatabaseConnection.DatabaseConnection;

public class DatabaseConnectionTest {
	public static void main(String[] args) {
		DatabaseConnection d = DatabaseConnection.getInstance();
		d.connectToDB();
	}

}
