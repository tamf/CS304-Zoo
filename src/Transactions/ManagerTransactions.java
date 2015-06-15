package Transactions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	 * Given animal name, type, and date returns string telling user if animal has
	 * had checkup on that day.
	 */
	public String hasCheckUp(String name, String type, String date) {

        Statement stmt1;
        ResultSet rs;

        // initally assume that animal has not had checkup today
        boolean todaycheckup = false;

        String queryString = "select * from checkup where name = " + name +
                " and type = " + type + " and date_checkup = " + date;
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

        String checkToday =  hasCheckUp(name, type, currentDate);
        if (checkToday.equals("Animal has had a checkup on that day.")) {
            return "Animal has already had a checkup today.";
        }
        else {
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
