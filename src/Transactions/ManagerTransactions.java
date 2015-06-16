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
	 * Given animal name, if animal exists and there is food and hasn't been fed
	 * yet, every animal of that type will be fed for that day.
	 */
	public String feedAnimal(String name, String type, int sin) {
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
        Statement stmt6;
        Statement stmt7;
        Statement stmt8;
        ResultSet rs;

        // initially, we assume false for all booleans
        boolean animalexists = false;
        boolean alreadyfed = false;

        String queryString = "select * from animallivein where name = '" + name + "' and type = '"
                + type + "'";
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
                String queryString6 = "select * from feeds where type = '" + type + "' and name = '" +
                        name + "' and datefed = '" + currentDate + "'";
                try {
                    stmt6 = con.createStatement();
                    // result of query is stored in rs
                    rs = stmt6.executeQuery(queryString6);

                    if (rs.next()) {
                        alreadyfed = true;
                    }
                    // close statement to free up memory, this closes the ResultSet
                    // object as well
                    stmt6.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (alreadyfed) {
                    return type + "has already been fed.";
                } else {
                	try {
                    stmt4 = con.createStatement();

                    // update item's qtystock in item table
                    stmt4.executeUpdate("update item set qtyinstock = " + (qtyoffood - numberofanimals) + " where itemid = 20 " +
                            "and itemname = 'Animal Feed'");

                    // insert values into feeds table
                    stmt5 = con.createStatement();
                    stmt5.executeUpdate("insert into feeds values (20, 'Animal Feed', '" + type +
                            "', '" + name + "', " + sin + ", '" + currentDate + "'");

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
}
