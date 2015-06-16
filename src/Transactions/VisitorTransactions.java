package Transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VisitorTransactions {
	private Connection con;
	private int visitorno;
	public String currentDate;

	public VisitorTransactions(Connection con, int visitorno) {
		this.con = con;
		this.visitorno = visitorno;

		// get current date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		currentDate = dateFormat.format(date);
	}

	/*
	 * Checks if itemName is in item table of database. If not, returns message
	 * indicating so to user. If in database, checks if in stock. If in stock,
	 * decreases stock by one and adds tuple to purchase table. Otherwise,
	 * returns message indicating not in stock.
	 */
	public String purchaseItem(String itemName) {
		// initialize attributes for the item entity
		int itemid = 0;
		double price = 0;
		int qtyinstock = 0;

		Statement stmt1;
		Statement stmt2;
		Statement stmt3;
		ResultSet rs;

		// initially, we assume item is not in database
		boolean itemIsInDatabase = false;

		String queryString = "select * from item where itemname = " + "'" + itemName + "'";

		try {
			System.out.println(queryString);
			stmt1 = con.createStatement();

			// result of query is stored in rs
			rs = stmt1.executeQuery(queryString);

			if (rs.next()) {
				itemIsInDatabase = true;
				itemid = rs.getInt("itemid");
				price = rs.getDouble("price");
				qtyinstock = rs.getInt("qtyinstock");
			}
			// close statement to free up memory, this closes the ResultSet
			// object as well
			stmt1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (!itemIsInDatabase) {
			return "Item not found in database.";
		}

		if (qtyinstock == 0) {
			return "Item not in stock.";
		}

		// Get current date and time (in format ddHHmmss) which will be used to
		// generate a unique receiptNo
		DateFormat dateFormat2 = new SimpleDateFormat("ddHHmmss");
		Date date = new Date();
		long currentDateTime = Long.parseLong(dateFormat2.format(date));

		// User purchases item
		try {
			stmt2 = con.createStatement();

			// update item's qtystock in item table
			int rowCount = stmt2.executeUpdate("update item set qtyinstock = " + --qtyinstock + " where itemid = "
					+ itemid);

			// insert values into purchase table
			stmt3 = con.createStatement();
			String updateQuery = "insert into purchase values (" + itemid + ", '" + itemName + "', '" + currentDate
					+ "'" + ", " + visitorno + ", 1" + ", " + currentDateTime + ", " + price + ")";
			System.out.println(updateQuery);
			int rowCount2 = stmt3.executeUpdate(updateQuery);

			// committing changes made to database
			con.commit();
			stmt2.close();
			stmt3.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Item purchased.";
	}


	/*
	 * Return section that animal with animalName, animalType is in, or 'does
	 * not exist'.
	 */
	public String getSectionOfAnimal(String animalName, String animalType) {
		String sectionno = "";

		Statement stmt1;
		ResultSet rs;

		// get section of animal with given name and type
		String queryString = "select sectionno from animallivein where name = " + animalName + " and type = "
				+ animalType;
		try {
			stmt1 = con.createStatement();
			rs = stmt1.executeQuery(queryString);
			if (rs.next()) {
				sectionno = Integer.toString(rs.getInt("sectionno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (sectionno.equals("")) {
			return "Animal does not exist.";
		}

		return "Animal is in section number " + sectionno + ".";
	}

	/*
	 * Given theme, get section id back or 'does not exist'.
	 */

	public String getSectionOfTheme(String theme) {
		// to be completed
		int sid = 0;
		boolean dne = true;
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = con.prepareStatement("SELECT sectionno FROM section WHERE theme = ?");
			ps.setString(1, theme);
			rs = ps.executeQuery();
			while (rs.next()) {
				sid = rs.getInt(1);
				dne = false;
				System.out.println("Section Number is " + sid);
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (dne == true) {
			System.out.println("Theme does not exist!");
			return "Theme does not exist!";
		}

		return "Section Number is " + sid;
	}

}
