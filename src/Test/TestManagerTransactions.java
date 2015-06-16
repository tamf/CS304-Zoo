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
		
		//testBuildSection(6, "Stuff");
		
		//testBuildEnclosure(2, "Lions");
		
		//testBuySupplies(1001, 20);
		
		testFeedAnimal("Dolphins", 60839453);

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
	
	private static void testBuildEnclosure(int sectionno, String holdingtype) {
		String result = mt.buildEnclosure(sectionno, holdingtype);
		System.out.println(result);
	}
	
	private static void testBuySupplies(int itemid, int amount) {
		String result = mt.buySupplies(itemid, amount);
		System.out.println(result);
	}
	
	private static void testFeedAnimal(String type, int sin) {
		String result = mt.feedAnimal(type, sin);
		System.out.println(result);
	}
	
	

}
