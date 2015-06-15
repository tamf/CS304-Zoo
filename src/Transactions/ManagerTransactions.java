package Transactions;

import java.sql.Connection;

public class ManagerTransactions {
	private Connection con;
	
	public ManagerTransactions(Connection con) {
		this.con = con;
	}

}
