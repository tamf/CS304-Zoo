package Test;

import java.sql.Connection;

import DatabaseConnection.DatabaseConnection;
import Transactions.ManagerTransactions;
import Transactions.VisitorTransactions;

public class TestManagerTransactions {
	private static Connection con;
	private static ManagerTransactions mt;

	public static void main(String[] args) {
		connectToDatabase();
		mt = new ManagerTransactions(con);

		// testPurchaseItem("Hamburger", 1);

		// testGetSectionOfAnimal("Angel", "Dolphin");
		
		testBuildSection(6, "Stuff");

	}

	private static void connectToDatabase() {
		DatabaseConnection d = DatabaseConnection.getInstance();
		d.connectToDB();
		con = d.getConnection();
	}
	
	private static void testBuildSection(int sectionNo, String theme) {
		String result = mt.buildSection(sectionNo, theme);
		System.out.println(result);
	}

}
