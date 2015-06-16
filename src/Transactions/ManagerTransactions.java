package Transactions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ManagerTransactions {
	private Connection con;
	public String currentDate;

	public ManagerTransactions(Connection con) {
		this.con = con;

		// get current date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		currentDate = dateFormat.format(date);
	}

	/*
	 * Given animal name, type, and date returns string telling user if animal
	 * has had checkup on that day.
	 */
	public String hasCheckUp(String name, String type, String date) {

		Statement stmt1;
		ResultSet rs;

		// initally assume that animal has not had checkup today
		boolean todaycheckup = false;

		String queryString = "select * from checkup where name = " + name + " and type = " + type
				+ " and date_checkup = " + date;
		try {
			stmt1 = con.createStatement();
			// result of query is stored in rs
			rs = stmt1.executeQuery(queryString);

			if (rs.next()) {
				todaycheckup = true;
			}
			// close statement to free up memory, this closes the ResultSet
			// object as well
			stmt1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (todaycheckup)
			return "Animal has had a checkup on that day.";
		else {
			return "Animal has not had a checkup on that day.";
		}
	}

	/*
	 * Given animal name, type, and sin of vet, tells user if animal has had a
	 * check up today. if no checkup table updated.
	 */
	public String checkUpToday(String name, String type, int sin) {
		// get current date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		currentDate = dateFormat.format(date);

		String checkToday = hasCheckUp(name, type, currentDate);
		if (checkToday.equals("Animal has had a checkup on that day.")) {
			return "Animal has already had a checkup today.";
		} else {
			Statement stmt3;

			try {
				// insert values into purchase table
				stmt3 = con.createStatement();
				int rowCount2 = stmt3.executeUpdate("insert into checkup values(" + currentDate + ", " + sin + ", "
						+ type + ", " + name + ")");

				// committing changes made to database
				con.commit();
				stmt3.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "Checkup performed.";
		}

	}

	/*
	 * Given sectionno, theme, create new section or return 'already created'.
	 */
	public String buildSection(int sectionno, String theme) {

		Statement stmt1;
		ResultSet rs;

		String queryString = "select * from section where sectionno = " + sectionno;
		try {
			stmt1 = con.createStatement();
			rs = stmt1.executeQuery(queryString);

			if (rs.next()) {
				return "Section Number already exists. Try using another Section Number.";
			}

			Statement stmt3;

			try {
				// insert values into section table
				stmt3 = con.createStatement();
				String updateQuery = "insert into section values(" + sectionno + ", " + "'" + theme + "')";
				System.out.println(updateQuery);
				stmt3.executeUpdate(updateQuery);

				// committing changes made to database
				con.commit();
				stmt3.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			stmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Section Created.";
	}

	/*
	 * Given theme name and holdingtype, create new enclosure or return 'already
	 * created' or 'theme does not exist'.
	 */
	public String buildEnclosure(int sectionno, String holdingtype) {
		Statement stmt1;
		ResultSet rs;

		// assume section does not exist initally
		boolean enclosureExist = false;

		String queryString = "select * from enclosurehas where holdingtype = " + "'" + holdingtype + "'" + " and sectionno = "
				+ sectionno;
		try {
			stmt1 = con.createStatement();
			// result of query is stored in rs
			rs = stmt1.executeQuery(queryString);

			if (rs.next()) {
				enclosureExist = true;
			} else {
				Statement stmt3;

				try {
					// insert values into section table
					stmt3 = con.createStatement();
					stmt3.executeUpdate("insert into enclosurehas values(" + holdingtype + ", " + 0 + ", " + sectionno
							+ ")");

					// committing changes made to database
					con.commit();
					stmt3.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// close statement to free up memory, this closes the ResultSet
			// object aswell
			stmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (enclosureExist)
			return "Enclosure already exists. Try building another enclosure.";
		else {
			return "Enclosure Created.";
		}
	}

	/*
	 * Given animal name, if animal exists and there is food and hasn't been fed
	 * yet, every animal of that type will be fed for that day.
	 */
	public String feedAnimal(String name, String type) {
		String holdingtype;
		String sectionno;
		int numberofanimals;
		int qtyoffood;
		ArrayList<String> typeAnimals;
		Statement stmt1;
		Statement stmt2;
		Statement stmt3;
		Statement stmt4;
		Statement stmt5;
		ResultSet rs;

		// initially, we assume animal is not in database
		boolean animalexists = false;

		String queryString = "select * from animallivein where name = '" + name + "' and type = '" + type + "'";
		try {
			stmt1 = con.createStatement();
			// result of query is stored in rs
			rs = stmt1.executeQuery(queryString);

			if (rs.next()) {
				animalexists = true;
				holdingtype = rs.getString("holdingtype");
				sectionno = rs.getString("sectionno");
			}
			// close statement to free up memory, this closes the ResultSet
			// object as well
			stmt1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (!animalexists) {
			return "This animal does not exist.";
		} else {
			String queryString2 = "select * from enclosurehas where holdingtype = '" + holdingtype
					+ "' and sectionno = '" + sectionno + "'";
			try {
				stmt2 = con.createStatement();
				// result of query is stored in rs
				rs = stmt2.executeQuery(queryString2);

				if (rs.next()) {
					numberofanimals = rs.getInt("numberofanimals");
				}
				// close statement to free up memory, this closes the ResultSet
				// object as well
				stmt2.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			String queryString3 = "select * from item where itemname = 'Animal Feed' and itemid = 20 ";
			try {
				stmt3 = con.createStatement();
				// result of query is stored in rs
				rs = stmt3.executeQuery(queryString3);

				if (rs.next()) {
					qtyoffood = rs.getInt("qtyinstock");
				}
				// close statement to free up memory, this closes the ResultSet
				// object as well
				stmt3.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (qtyoffood < numberofanimals)
				return "Not enough food to feed all animals.";
			else {
				try {
					stmt4 = con.createStatement();

					// update item's qtystock in item table
					stmt4.executeUpdate("update item set qtyinstock = " + (qtyoffood - numberofanimals)
							+ " where itemid = 20 " + "and itemname = 'Animal Feed'");

					// insert values into feeds table
					stmt5 = con.createStatement();
					stmt5.executeUpdate("insert into feeds values (20, 'Animal Feed', " + currentDate + "");

					// committing changes made to database
					con.commit();
					stmt4.close();
					stmt5.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
				return "All " + type + " have been fed!";
			}
		}
	}

	/*
	 * Given itemid and amount, purchase that item and update in the item and
	 * expense tables.
	 */
	public String buySupplies(int itemid, int amount) {
		// to be completed
		return "";
	}

}
