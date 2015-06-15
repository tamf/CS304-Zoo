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

	/*
	 * Given theme name and holdingtype, create new enclosure or return 'already
	 * created' or 'theme does not exist'.
	 */
	public String buildEnclosure(String theme, String holdingtype) {
		// to be completed
		return "";
	}
	
	/*
	 * Given animal name, if animal exists and there is food and hasn't been fed yet, every animal of that type will be fed for that day.
	 */
	public String feedAnimal(String name) {
		// to be completed
		return "";
	}
	/*
	 * Given itemid and amount, purchase that item and update in the item and expense tables.
	 */
	public String buySupplies(int itemid, int amount) {
		// to be completed
		return "";
	}

}
