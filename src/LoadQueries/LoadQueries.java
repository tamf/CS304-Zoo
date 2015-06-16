package LoadQueries;

import java.sql.*;
import java.util.ArrayList;

public class LoadQueries {
	private Connection con;
	
	public LoadQueries(Connection con) {
		this.con = con;
	}
	
    public ArrayList<String> queryEmployee() {

        Statement stmt1;
        ResultSet rs;
        ArrayList<String> al = new ArrayList<String>();

        String queryString = "select * from employee";
        try {
            stmt1 = con.createStatement();
            // result of query is stored in rs
            rs = stmt1.executeQuery(queryString);

            while (rs.next()) {
                int sin = rs.getInt("sin");
                int salary = rs.getInt("salary");
                String name = rs.getString("name");
                al.add("Sin: " + sin + " Salary: " + salary + " Name: " + name);
                rs.next();
            }
            // close statement to free up memory, this closes the ResultSet
            // object as well
            stmt1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }
}