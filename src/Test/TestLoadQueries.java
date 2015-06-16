package Test;

import java.sql.Connection;

import DatabaseConnection.DatabaseConnection;
import LoadQueries.LoadQueries;
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

}
