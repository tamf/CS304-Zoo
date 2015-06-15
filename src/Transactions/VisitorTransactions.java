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

		// !!! may have to change 'name' in query

		String queryString = "select * from item where name = " + itemName;
		try {
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

		// User purchases item
		try {
			stmt2 = con.createStatement();

			// update item's qtystock in item table
			int rowCount = stmt2.executeUpdate("update item set qtyinstock = " + --qtyinstock + " where itemid = "
					+ itemid);

			// insert values into purchase table
			stmt3 = con.createStatement();
			int rowCount2 = stmt3.executeUpdate("insert into purchase values (" + itemid + ", " + currentDate + ", "
					+ visitorno + ")");

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
	 * Given itemid, itemname for tour, check if tour guide is available to do tour and if
	 * tour is not at capacity. If tour available, 
	 */
	public String goOnTour(int itemid, String itemname) {
		
		// # spots left in the tour
		int capacity = 0;
		
		// intially, we assume tour is not available
		boolean isTourAvailable = false;
		
		Statement stmt1;
		Statement stmt2;
		ResultSet rs;
		
		// select all cols from tourdirected that have the itemid and itemname given
		String queryString = "select * from tourdirected where itemid = " + itemid + " and itemname = " + itemname;
		try {
			stmt1 = con.createStatement();
			rs = stmt1.executeQuery(queryString);
			
			// if a result exists
			if (rs.next()) {
				capacity = rs.getInt("capacity");
				// if capacity available, then tour is available
				if (capacity > 0) {
					isTourAvailable = true;
				}
			}
			stmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (!isTourAvailable) {
			return "Tour is not available.";
		}
		
		// updating the table: decrease capacity by one
		try {
			stmt2 = con.createStatement();
			int rowCount = stmt2.executeUpdate("update tourdirected set capacity = " + --capacity + " where itemid = " + itemid + " and itemname = " + itemname);
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "You are registered for the tour " + itemname + "!";
	}

	/*
	 * Return section that animal with animalName is in, or 'does not exist'.
	 */
	public String getSectionOfAnimal(String animalName) {
		// to be completed
		return "";
	}

	/*
	 * Given theme, get section id back or 'does not exist'.
	 */

	public String getSectionOfTheme(String theme) {
		// to be completed
		return "";
	}

}
