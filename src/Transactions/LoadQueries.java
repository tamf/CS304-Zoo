package Transactions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LoadQueries {
    private Connection con;
    
    public LoadQueries(Connection connection) {
    	con = connection;
    }

    public ArrayList<String> queryAllZookeeperInfo() {
        Statement stmt1;
        ResultSet rs;
        ArrayList<String> al = new ArrayList<String>();

        String queryString = "select * from zookeepers,employee,workin " +
                "where employee.sin = zookeepers.sin and workin.sin = employee.sin";
        try {
            stmt1 = con.createStatement();
            // result of query is stored in rs
            rs = stmt1.executeQuery(queryString);

            while (rs.next()) {
                int sin = rs.getInt("sin");
                int salary = rs.getInt("salary");
                String name = rs.getString("name");
                int section = rs.getInt("sectionno");

                al.add("Position: Zookeeper  Name: " + name +
                        "  Salary: " + salary +
                        "  Sin: " + sin +
                        "  Works In: " + section);
            }
            // close statement to free up memory, this closes the ResultSet
            // object as well
            stmt1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList<String> queryAllGuideInfo() {
        Statement stmt1;
        ResultSet rs;
        ArrayList<String> al = new ArrayList<String>();

        String queryString = "select * from guides,employee,workin " +
                "where employee.sin = guides.sin and workin.sin = employee.sin";
        try {
            stmt1 = con.createStatement();
            // result of query is stored in rs
            rs = stmt1.executeQuery(queryString);

            while (rs.next()) {
                int sin = rs.getInt("sin");
                int salary = rs.getInt("salary");
                String name = rs.getString("name");
                int section = rs.getInt("sectionno");

                al.add("Position: Guide  Name: " + name +
                        "  Salary: " + salary +
                        "  Sin: " + sin +
                        "  Works In: " + section);
            }
            // close statement to free up memory, this closes the ResultSet
            // object as well
            stmt1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList<String> queryAllVetInfo() {
        Statement stmt1;
        ResultSet rs;
        ArrayList<String> al = new ArrayList<String>();

        String queryString = "select * from vet,employee where employee.sin = vet.sin";
        try {
            stmt1 = con.createStatement();
            // result of query is stored in rs
            rs = stmt1.executeQuery(queryString);

            while (rs.next()) {
                int sin = rs.getInt("sin");
                int salary = rs.getInt("salary");
                String name = rs.getString("name");
                int school = rs.getInt("school");

                al.add("Position: Vet  Name: " + name +
                        "  Salary: " + salary +
                        "  Sin: " + sin +
                        "  Went to: " + school);
            }
            // close statement to free up memory, this closes the ResultSet
            // object as well
            stmt1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList<String> querySections() {
        Statement stmt1;
        ResultSet rs;
        ArrayList<String> al = new ArrayList<String>();

        String queryString = "select * from section";
        try {
            stmt1 = con.createStatement();
            // result of query is stored in rs
            rs = stmt1.executeQuery(queryString);

            while (rs.next()) {
                int sectionno = rs.getInt("sectionno");
                String theme = rs.getString("theme");

                al.add("Section No:" + sectionno +
                        "  Theme: " + theme);
            }
            // close statement to free up memory, this closes the ResultSet
            // object as well
            stmt1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList<String> queryAllEnclosures() {
        Statement stmt1;
        ResultSet rs;
        ArrayList<String> al = new ArrayList<String>();

        String queryString = "select * from section,enclosurehas where " +
                "section.sectionno = enclosurehas.sectionno";
        try {
            stmt1 = con.createStatement();
            // result of query is stored in rs
            rs = stmt1.executeQuery(queryString);

            while (rs.next()) {
                int sectionno = rs.getInt("sectionno");
                String theme = rs.getString("theme");
                int numberofanimals = rs.getInt("numberofanimals");
                String holdingtype = rs.getString("holdingtype");

                al.add("Section No: " + sectionno +
                        "  Theme: " + theme +
                        "  Holding Type: " + holdingtype +
                        "  Number of Animals in Enclosure: " + numberofanimals);
            }
            // close statement to free up memory, this closes the ResultSet
            // object as well
            stmt1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList<String> queryAnimals() {
        Statement stmt1;
        ResultSet rs;
        ArrayList<String> al = new ArrayList<String>();

        String queryString = "select * from animallivein";
        try {
            stmt1 = con.createStatement();
            // result of query is stored in rs
            rs = stmt1.executeQuery(queryString);

            while (rs.next()) {
                String type = rs.getString("type");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                int sectionno = rs.getInt("sectionno");
                String date = rs.getString("sincedate");

                al.add("Animal Type: " + type +
                        "  Name: " + name +
                        "  Sex: " + sex +
                        "  Section No: " + sectionno +
                        "  Since: " + date);
            }
            // close statement to free up memory, this closes the ResultSet
            // object as well
            stmt1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList<String> queryCheckups() {
        Statement stmt1;
        ResultSet rs;
        ArrayList<String> al = new ArrayList<String>();

        String queryString = "select * from checkup,employee" +
                " where checkup.sin=employee.sin";
        try {
            stmt1 = con.createStatement();
            // result of query is stored in rs
            rs = stmt1.executeQuery(queryString);

            while (rs.next()) {
                String type = rs.getString("type");
                String aname = rs.getString("checkup.name");
                String ename = rs.getString("employee.name");
                String date = rs.getString("date_checkup");

                al.add("Animal Name: " + aname +
                        "  Animal Type: " + type +
                        "  Vet Name: " + ename +
                        "  Date: " + date);
            }
            // close statement to free up memory, this closes the ResultSet
            // object as well
            stmt1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList<String> queryVisitors() {
        Statement stmt1;
        ResultSet rs;
        ArrayList<String> al = new ArrayList<String>();

        String queryString = "select * from visitor";
        try {
            stmt1 = con.createStatement();
            // result of query is stored in rs
            rs = stmt1.executeQuery(queryString);

            while (rs.next()) {
                int visitorno = rs.getInt("visitorno");
                String name = rs.getString("name");

                al.add("Visitor No: " + visitorno +
                        "  Visitor Name: " + name);
            }
            // close statement to free up memory, this closes the ResultSet
            // object as well
            stmt1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList<String> queryVisits() {
        Statement stmt1;
        ResultSet rs;
        ArrayList<String> al = new ArrayList<String>();

        String queryString = "select * from visitor, visits " +
                "where visitor.visitorno = visits.visitorno";
        try {
            stmt1 = con.createStatement();
            // result of query is stored in rs
            rs = stmt1.executeQuery(queryString);

            while (rs.next()) {
                int visitorno = rs.getInt("visitorno");
                int sectionno = rs.getInt("sectionno");
                String name = rs.getString("name");

                al.add("Visitor No: " + visitorno +
                        "  Visitor Name: " + name +
                        "  Visited: " + sectionno);
            }
            // close statement to free up memory, this closes the ResultSet
            // object as well
            stmt1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList<String> queryTours() {
        Statement stmt1;
        ResultSet rs;
        ArrayList<String> al = new ArrayList<String>();

        String queryString = "select item.itemid, item.itemname, qtyinstock, price from item, tourdirected where tourdirected.itemid = item.itemid";
        try {
            stmt1 = con.createStatement();
            // result of query is stored in rs
            rs = stmt1.executeQuery(queryString);

            while (rs.next()) {
                int itemid = rs.getInt("itemid");
                String itemname = rs.getString("itemname");
                double price = rs.getDouble("price");
                if (price == (double) 0) {
                    break;
                }
                int qtyinstock = rs.getInt("qtyinstock");

                al.add("Item ID: " + itemid +
                        "  Item Name: " + itemname +
                        "  Quantity In Stock: " + qtyinstock +
                        "  Price: " + price);
            }
            // close statement to free up memory, this closes the ResultSet
            // object as well
            stmt1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList<String> queryFood() {
        Statement stmt1;
        ResultSet rs;
        ArrayList<String> al = new ArrayList<String>();

        String queryString = "select * from item, food where food.itemid = item.itemid";
        try {
            stmt1 = con.createStatement();
            // result of query is stored in rs
            rs = stmt1.executeQuery(queryString);

            while (rs.next()) {
                int itemid = rs.getInt("itemid");
                String itemname = rs.getString("itemname");
                double price = rs.getDouble("price");
                if (price == (double) 0) {
                    break;
                }
                int qtyinstock = rs.getInt("qtyinstock");

                al.add("Item ID: " + itemid +
                        "  Item Name: " + itemname +
                        "  Quantity In Stock: " + qtyinstock +
                        "  Price: " + price);
            }
            // close statement to free up memory, this closes the ResultSet
            // object as well
            stmt1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList<String> querySouvenir() {
        Statement stmt1;
        ResultSet rs;
        ArrayList<String> al = new ArrayList<String>();

        String queryString = "select * from item, souvenir where souvenir.itemid = item.itemid";
        try {
            stmt1 = con.createStatement();
            // result of query is stored in rs
            rs = stmt1.executeQuery(queryString);

            while (rs.next()) {
                int itemid = rs.getInt("itemid");
                String itemname = rs.getString("itemname");
                double price = rs.getDouble("price");
                if (price == (double) 0) {
                    break;
                }
                int qtyinstock = rs.getInt("qtyinstock");

                al.add("Item ID: " + itemid +
                        "  Item Name: " + itemname +
                        "  Quantity In Stock: " + qtyinstock +
                        "  Price: " + price);
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
