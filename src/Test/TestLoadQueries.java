package Test;

import java.sql.Connection;
import java.util.ArrayList;

import DatabaseConnection.DatabaseConnection;
import Transactions.LoadQueries;
import Transactions.ManagerTransactions;

public class TestLoadQueries {
	private static Connection con;
	private static LoadQueries lq;

	public static void main(String[] args) {
		connectToDatabase();
		lq = new LoadQueries(con);
		
		
	}

	private static void connectToDatabase() {
		DatabaseConnection d = DatabaseConnection.getInstance();
		d.connectToDB();
		con = d.getConnection();
	}
	
	
	
	
	
}

	