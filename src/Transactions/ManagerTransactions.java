package Transactions;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
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
		PreparedStatement s1;
		PreparedStatement s2;
		ResultSet rs1;
		ResultSet rs2;
		boolean todaycheckup = false;
		boolean dne = true;
		SimpleDateFormat fm = new SimpleDateFormat("yy-MM-dd");
		java.util.Date utilDate = null;
		try {
			utilDate = fm.parse(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

		// initally assume that animal has not had checkup today
		try {
			s1 = con.prepareStatement("select * from checkup where name=? and type=?");
			s1.setString(1, name);
			s1.setString(2, type);
			rs1 = s1.executeQuery();
			while (rs1.next()) {
				dne = false;
			}
			s1.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (dne == true) {
			// System.out.println("Animal does not exist!");
			return "Animal does not exist!";
		}

		try {
			s2 = con.prepareStatement("select * from checkup where name=? and type=? and date_checkup=?");
			s2.setString(1, name);
			s2.setString(2, type);
			s2.setDate(3, sqlDate);
			// result of query is stored in rs
			rs2 = s2.executeQuery();
			if (rs2.next()) {
				todaycheckup = true;
			}
			// close statement to free up memory, this closes the ResultSet
			// object as well
			s2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (todaycheckup == true) {
			// System.out.println("Animal has had a checkup on that day.");
			return "Animal has had a checkup on that day.";
		} else {
			// System.out.println("Animal has not had a checkup on that day.");
			return "Animal has not had a checkup on that day.";
		}
	}

	/*
	 * Given animal name, type, and sin of vet, tells user if animal has had a
	 * check up today. if no checkup table updated.
	 */
	public String checkUpToday(String name, String type, int sin) {
		// get current date
		java.util.Date utildate = new Date();
		java.sql.Date currentdate = new java.sql.Date(utildate.getTime());
		SimpleDateFormat f = new SimpleDateFormat("yy-MM-dd");
		String ds = f.format(utildate);
		String checkToday = hasCheckUp(name, type, ds);

		if (checkToday.equals("Animal does not exist!")) {
			System.out.println("Animal does not exist!");
			return "Animal does not exist!";
		}

		if (checkToday.equals("Animal has had a checkup on that day.")) {
			System.out.println("Animal has had a checkup today.");
			return "Animal has had a checkup today.";
		} else {
			PreparedStatement stmt3;
			try {
				// insert values into purchase table
				stmt3 = con.prepareStatement("UPDATE checkup SET date_checkup=? WHERE sin=? and type=? and name=?");

				stmt3.setDate(1, currentdate);
				stmt3.setInt(2, sin);
				stmt3.setString(3, type);
				stmt3.setString(4, name);

				stmt3.executeUpdate();
				// committing changes made to database
				con.commit();
				stmt3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Checkup performed.");
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

		String queryString = "select * from enclosurehas where holdingtype = " + "'" + holdingtype + "'"
				+ " and sectionno = " + sectionno;
		try {
			stmt1 = con.createStatement();
			rs = stmt1.executeQuery(queryString);

			if (rs.next()) {
				return "Enclosure already exists. Try building another enclosure.";
			} else {
				Statement stmt3;

				try {
					// insert values into section table
					stmt3 = con.createStatement();
					stmt3.executeUpdate("insert into enclosurehas values('" + holdingtype + "', " + 0 + ", "
							+ sectionno + ")");

					con.commit();
					stmt3.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			stmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Enclosure Created.";

	}

	/*
	 * Check if animal exists. Check if sin exists. Check if animal has already
	 * been fed. Then feed animal.
	 */

	public String feedAnimal(String type, String name, int sin) {
		Statement stmt1;
		Statement stmt2;
		Statement stmt3;
		Statement stmt4;
		ResultSet rs;

		// Query if animal exists in animallivein table
		String queryString1 = "select count(*) from animallivein where type = '" + type + "' and name = '" + name + "'";
		System.out.println(queryString1);
		try {
			stmt1 = con.createStatement();
			rs = stmt1.executeQuery(queryString1);
			while (rs.next()) {
				if (rs.getInt("count(*)") == 0) {
					System.out.println("Animal does not exist");
					return "Animal does not exist.";
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Query if sin exists
		String queryString2 = "select * from employee where sin = " + sin;
		System.out.println(queryString2);
		try {
			stmt2 = con.createStatement();
			rs = stmt2.executeQuery(queryString2);
			if (!rs.next()) {
				System.out.println("Employee with given SIN does not exist.");
				return "Employee with given SIN does not exist.";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Query if animal has already been fed
		String queryString3 = "select * from feeds where type = '" + type + "' and name = '" + name
				+ "' and datefed = '" + currentDate + "'";
		System.out.println(queryString3);
		try {
			stmt3 = con.createStatement();
			rs = stmt3.executeQuery(queryString3);
			if (rs.next()) {
				System.out.println("Animal has already been fed.");
				return "Animal has already been fed.";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Update query for feeds table
		String queryString4 = "insert into feeds values(20, 'Animal Feed', '" + type + "', '" + name + "', " + sin
				+ ", '" + currentDate + "')";
		System.out.println(queryString4);
		try {
			stmt4 = con.createStatement();
			int rowCount = stmt4.executeUpdate(queryString4);
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Animal has now been fed.");
		return "Animal has now been fed.";
	}

	/*
	 * Given itemid, amount, purchase that item and update in the item table.
	 */
	public String buySupplies(int itemid, int amount) {
		int qtyinstock = 0;
		int newqtyinstock;
		double totalcost;

		Statement stmt1;
		Statement stmt2;
		Statement stmt3;

		ResultSet rs;

		// Determine if item with given itemID exists
		String queryString1 = "select * from item where itemid = " + itemid;
		System.out.println(queryString1);
		try {
			stmt1 = con.createStatement();
			rs = stmt1.executeQuery(queryString1);
			if (rs.next()) {
				qtyinstock = rs.getInt("qtyinstock");
			} else {
				return "itemID entered does not exist.";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Update item table with newqtyinstock
		newqtyinstock = qtyinstock + amount;
		String queryString2 = "update item set qtyinstock = " + newqtyinstock + " where itemid = " + itemid;
		System.out.println(queryString2);
		try {
			stmt2 = con.createStatement();
			rs = stmt2.executeQuery(queryString2);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Stock updated.";
	}

	public String adoptAnimal(String type, String name, String sex, String ht, int sn) {
		java.util.Date utilDate = new Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

		Statement stmt1;
		Statement stmt2;
		Statement stmt3;
		ResultSet rs;

		int numberOfAnimals = 0;

		boolean secAndHoldExist = false;
		boolean animalExist = false;

		String queryString3 = "select * from animallivein " + "where type = '" + type + "' and name = '" + name + "'";
		try {
			stmt3 = con.createStatement();
			// result of query is stored in rs
			rs = stmt3.executeQuery(queryString3);

			if (rs.next()) {
				animalExist = true;
			}
			stmt3.close();
			if (animalExist)
				return "Animal already exists!";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String queryString = "select * from enclosurehas " + "where sectionno = " + sn + " and holdingtype = '" + ht
				+ "'";
		try {
			stmt1 = con.createStatement();
			// result of query is stored in rs
			rs = stmt1.executeQuery(queryString);

			if (rs.next()) {
				secAndHoldExist = true;
				numberOfAnimals = rs.getInt("numberofanimals");
				try {
					PreparedStatement ps = con.prepareStatement("insert into animallivein values(?,?,?,?,?,?)");
					ps.setString(1, type);
					ps.setString(2, name);
					ps.setString(3, sex);
					ps.setDate(4, sqlDate);
					ps.setString(5, ht);
					ps.setInt(6, sn);
					ps.executeUpdate();
					con.commit();
					ps.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					stmt2 = con.createStatement();

					// update item's qtystock in item table
					stmt2.executeUpdate("update enclosurehas set numberofanimals = " + ++numberOfAnimals
							+ " where sectionno = " + sn + " and holdingtype = '" + ht + "'");

					// committing changes made to database
					con.commit();
					stmt2.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				secAndHoldExist = false;
			}
			// close statement to free up memory, this closes the ResultSet
			// object aswell
			stmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (secAndHoldExist)
			return "Animal adopted!";
		else {
			return "Section or Holding doesn't exist.";
		}
	}

	/*
	 * Remove an employee from working in a section. Returns 'There does not
	 * exist an employee that works in this section' or 'Employee removed from
	 * section.'
	 */
	public String deleteFromWorkIn(int sin, int sectionno) {
		Statement stmt1;
		Statement stmt2;
		ResultSet rs;

		// Query if employee sin and sectionno exist in the workin table
		String queryString1 = "select * from workin where sin = " + sin + " and sectionno = " + sectionno;
		System.out.println(queryString1);
		try {
			stmt1 = con.createStatement();
			rs = stmt1.executeQuery(queryString1);

			if (!rs.next()) {
				return "There does not exist an employee that works in this section.";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Delete from the workin table
		String queryString2 = "delete from workin where sin = " + sin + " and sectionno = " + sectionno;
		System.out.println(queryString2);
		try {
			stmt2 = con.createStatement();
			int rowCount = stmt2.executeUpdate(queryString2);
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String returnString = "Employee " + sin + " removed from section " + sectionno + ".";
		System.out.println(returnString);
		return returnString;

	}

	/*
	 * Find visitors who has visited all sections.
	 */

	public ArrayList<String> findVisitorOfAllSections() {
		Statement stmt1;
		ResultSet rs;
		String name;
		ArrayList<String> result = new ArrayList<String>();
		
		// get names from visitor where that visitor has visited all sections
		String queryString = "select name from visitor v1 where not exists "
				+ "((select sectionno from section) minus (select sectionno from visits v2 where v2.visitorno = v1.visitorno))";
		try {
			stmt1 = con.createStatement();
			rs = stmt1.executeQuery(queryString);
			while (rs.next()) {
				 name = rs.getString("name");
				result.add("Name: " + name);
			}
			stmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
