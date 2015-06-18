package Test;

import java.sql.Connection;
import java.util.ArrayList;

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
		
		//testFeedAnimal("Lion", "Leo", 60839453);
		
		//testDeleteFromWorkIn(578875478, 2);
		
		testFindVisitorInAllSection();

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
	
	private static void testFeedAnimal(String type, String name, int sin) {
		String result = mt.feedAnimal(type, name, sin);
		System.out.println(result);
	}
	
	private static void testDeleteFromWorkIn(int sin, int sectionno) {
		String result = mt.deleteFromWorkIn(sin, sectionno);
		System.out.println(result);
	}
	
	private static void testFindVisitorInAllSection() {
		ArrayList<String> result = mt.findVisitorOfAllSections();
		for (String s : result) {
			System.out.println(s);
		}
	}
	
	

}
