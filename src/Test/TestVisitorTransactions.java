package Test;

import java.sql.Connection;

import DatabaseConnection.DatabaseConnection;
import Transactions.VisitorTransactions;

public class TestVisitorTransactions {
	private static Connection con;
	private static VisitorTransactions vt;

	public static void main(String[] args) {
		connectToDatabase();
		vt = new VisitorTransactions(con, 1);
		
		
		//testPurchaseItem("Hamburger", 1);

		

	}

	private static void connectToDatabase() {
		DatabaseConnection d = DatabaseConnection.getInstance();
		d.connectToDB();
		con = d.getConnection();
	}

	private static void testPurchaseItem(String itemName, int visitorno) {
		String result = vt.purchaseItem(itemName);
		System.out.println(result);
	}
	
	//private static void 

}
