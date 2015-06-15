package Transactions;

import java.sql.Connection;

public class ManagerTransactions {
	private Connection con;

	public ManagerTransactions(Connection con) {
		this.con = con;
	}

	/*
	 * Given animal name, type, and date. Returns true if animal has had checkup
	 * on that day. If not, returns false and checkup table updated.
	 */
	public boolean hasCheckUp(String name, String type, String date) {
		// to be completed
		return false;
	}

	/*
	 * Given sectionno, theme, create new section or return 'already created'.
	 */
	public String buildSection(int sectionno, String theme) {
		// to be completed
		return "";
	}

}
