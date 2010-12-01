package csci3308.friendatlas;

/**
 * Created by IntelliJ IDEA.
 * User: correaj
 * Date: Nov 27, 2010
 * Time: 8:31:01 PM
 * To change this template use File | Settings | File Templates.
 */


import java.io.*;
import java.util.*;
import java.sql.*;

public class ContactController
{
    public static ArrayList<Contact> listContacts(int id) {

        int userID = id;
        ArrayList<Contact> userContacts = new ArrayList<Contact>();
        Contact singleContact;

        String DBUrl = "jdbc:mysql://24.8.149.63/friendatlas";
        Connection conn = null;
        Statement stmt = null;

        try {
            try {
                Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            }
            catch (Exception Ex) {
                System.out.println("Unable to Load Driver: " + Ex.toString() + "<br>");
            }

            conn = DriverManager.getConnection(DBUrl, "root", "ararat");
            stmt = conn.createStatement();
            String query = "SELECT * FROM Friends WHERE UserID = '" + userID + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                singleContact = new Contact(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7));

                userContacts.add(singleContact);
            } // gets contacts by column's index number
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return userContacts;
    }

    public boolean addToList(int userID, String firstName, String lastName, String email,
                             String address, String city, String state, String zip) {

        String DBUrl = "jdbc:mysql://24.8.149.63/friendatlas";
        Connection conn = null;
        Statement stmt = null;

        try {
            try {
                Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            }
            catch (Exception Ex) {
                Ex.printStackTrace();
            }

            conn = DriverManager.getConnection(DBUrl, "root", "ararat");
            stmt = conn.createStatement();

            if ((firstName != null && firstName != "") && (lastName != null && lastName != "") && (email != null && email != "") &&
                    (address != null && address != "") && (city != null && city != "") &&
                    (state != null && state != "") && (zip != null)) {

                try {
                    String Query = "INSERT INTO Friends ( FirstName, LastName, Address, City, State, Zip, Email, UserId ) values " +
                            "(\"" + firstName + "\",\"" + lastName + "\",\"" + address + "\",\"" + city
                            + "\",\"" + state + "\",\"" + zip + "\",\"" + email + "\",\"" + userID + "\")";

                    stmt.executeUpdate(Query);
                    return true;
                }
                catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            } else {
                return false;
            }
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return false;
    }

    public boolean removeFromList(String email) {
        String DBUrl = "jdbc:mysql://localhost/friendatlas";
        Connection conn = null;
        Statement stmt = null;

        try {
            try {
                Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            }
            catch (Exception Ex) {
                Ex.printStackTrace();
            }

            conn = DriverManager.getConnection(DBUrl, "root", "oN>72Mxgis");
            stmt = conn.createStatement();

            try {
                String Query = "DELETE FROM Friends WHERE Email = " + email + "";

                stmt.executeUpdate(Query);
                return true;
            }
            catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return false;
    }
}
