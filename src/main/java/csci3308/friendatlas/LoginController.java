
package csci3308.friendatlas;

/**
 *
 * @author correaj
 */

import java.io.*;
import java.util.*;
import java.sql.*;

public class LoginController {

    public int verifyUser(String em, String pw) {

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
            // get a new connection object 
            conn = DriverManager.getConnection(DBUrl, "root", "ararat");
            // get a new statement object 
            stmt = conn.createStatement();
            // and execute a simple query 
            ResultSet rs = stmt.executeQuery("SELECT * from Users");

            if ((email != null && email != "") || (password != null && password != "")) {
                while (rs.next()) {
                    // iterate through the result set and print 
                    // the query results to the page
                    System.out.println(rs.toString());

                    if ((email.equals(rs.getString("Email").toLowerCase())) && (password.equals(rs.getString("Password").toLowerCase()))) {
                        return rs.getInt(1);
                    }

                } // end while

            } // end if  	

        } catch (SQLException sqlEx) {
            System.out.println("Caught SQL Exception: " + sqlEx.toString());
        } // caught up

        return 0;
    }     // END VERIFY USERS
} // END CLASS
