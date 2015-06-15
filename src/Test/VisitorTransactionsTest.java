package Test;

import DatabaseConnection.DatabaseConnection;
import Transactions.VisitorTransactions;

public class VisitorTransactionsTest {
	public static void main(String[] args) {
		DatabaseConnection d = DatabaseConnection.getInstance();
		d.connectToDB();
		
		VisitorTransactions vt = new VisitorTransactions(d.getConnection(), 0);
		System.out.println(vt.currentDate);
	}
}
