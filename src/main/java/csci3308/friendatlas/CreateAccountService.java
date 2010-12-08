package csci3308.friendatlas;


import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class CreateAccountService {


    public boolean checkForExistingAccount(String em, String pw) {
        String email = em.toLowerCase();
        String password = pw.toLowerCase();

        String DBUrl = "jdbc:mysql://24.8.149.63/friendatlas";
        Connection conn = null;
        Statement stmt = null;

        try {
            try {
                //create a new instance of the mysql driver
                Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            } catch (Exception Ex) {
                System.out.println("Unable to Load Driver: " + Ex.toString());
            }

            conn = DriverManager.getConnection(DBUrl, "root", "ararat");// get a new connection object
            stmt = conn.createStatement(); // get a new statement object
            ResultSet rs = stmt.executeQuery("SELECT * from Users"); // and execute a simple query

            if ((email != null && email != "") || (password != null && password != "")) {
                while (rs.next()) {
                    // iterate through the result set and print
                    // the query results to the page
                    if ((email.equals(rs.getString("Email").toLowerCase())))
                        return true;
                }
            } // end if
        } catch (SQLException sqlEx) {
            System.out.println("Caught SQL Exception: " + sqlEx.toString());
        } // caught up

        return false;
    } // end of method

    public int createNewUser(String first, String last, String argEmail, String pw) {

        String DBUrl = "jdbc:mysql://24.8.149.63/friendatlas";
        Connection conn = null;
        Statement stmt = null;


        String firstName = first;
        String lastName = last;
        String email = argEmail;
        String password = pw;

        try {
            try {
                //create a new instance of the mysql driver
                Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            }
            catch (Exception Ex) {
                System.out.println("Unable to Load Driver: " + Ex.toString() + "<br>");
            }
            // get a new connection object 
            conn = DriverManager.getConnection(DBUrl, "root", "ararat");
            // get a new statement object 
            stmt = conn.createStatement();
            // and execute a simple query 

        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        if ((firstName != null && firstName != "") && (lastName != null && lastName != "") && (email != null && email != "") && (password != null && password != "")) {
            try {
                String Query = "INSERT INTO Users ( FirstName, LastName, Email, Password ) VALUES" +
                        "(\"" + firstName + "\",\"" + lastName + "\",\"" + email + "\",\"" + password + "\")";
                stmt.executeUpdate(Query);
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }

        try {
            ResultSet rs = stmt.executeQuery("SELECT * from Users where FirstName='" + firstName + "' and LastName='" + lastName + "' ");
            System.out.println("Account Created Successfully with the following information: <br />");
            while (rs.next()) {

                if ((email.equals(rs.getString("Email").toLowerCase()))) {
                    return rs.getInt(1);
                }
            }
        }
        catch (SQLException sqlEx) {
            System.out.println("Caught SQL Exception: " + sqlEx.toString() + "<br>");
        }
        // now close the statement and connection if they exist
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            } // try/catch
        } // close if
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }  // try/cathc
        } // close if
        return 0;
    } // method createNewUser
} // class