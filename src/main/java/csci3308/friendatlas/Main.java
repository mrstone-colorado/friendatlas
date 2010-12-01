package csci3308.friendatlas;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class Main extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        String DBUrl = "jdbc:mysql://localhost/friendatlas";
        Connection conn = null;
        Statement stmt = null;

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);
        List<String> contacts = new ArrayList<String>();
        List<Contact> userFriends = new ArrayList<Contact>();

        Object logInObject = session.getAttribute("LoggedIn");
        Object logName = session.getAttribute("Useremail");

        String userName = logName.toString();

        if (logInObject.equals(null)) {
            out.println("You are not logged in please <a href=\"/login\">/loginpage.html</a>");
            return;
        }

        String[] objs = new String[100];

        for (int i = 0; i < objs.length; i++)
            objs[i] = "John Correa";

        String logInValue = logInObject.toString();

        if (logInValue.equals("TRUE")) {

            int userID = Integer.parseInt(session.getAttribute("UserID").toString());

            userFriends.addAll(ContactController.listContacts(userID));

            String logo = "<br /><br /><a href=\"/logout\">Logout</a>";

            request.setAttribute("userFriends", userFriends);
            request.setAttribute("UserName", userName);
            request.setAttribute("LogoutLink", logo);
            request.getRequestDispatcher("WEB-INF/jsp/mainpage.jsp").forward(request, response);
        } else {
            out.println("You are not logged in please <a href=\"/login\">loginpage.html</a>");
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
        String DBUrl = "jdbc:mysql://localhost/friendatlas";
        Connection conn = null;
        Statement stmt = null;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        Object logInObject = session.getAttribute("LoggedIn");

        if (logInObject.equals(null)) {
            out.println("You are not logged in please <a href=\"/login\">Login</a>");
            return;
        }

        String logInValue = logInObject.toString();

        boolean friendAdded = false;

        if (logInValue.equals("TRUE")) {

            int userID = Integer.parseInt(session.getAttribute("UserID").toString());

            String firstName = request.getParameter("first");
            String lastName = request.getParameter("last");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String zip = request.getParameter("zip");

            ContactController cc = new ContactController();

            friendAdded = cc.addToList(userID, firstName, lastName, email, address, city, state, zip);

            if (friendAdded == true)
                request.setAttribute("mysqlresponse", "<span style=\"color:green\">Succesfuly Inserted</span>");
            else
                request.setAttribute("mysqlresponse", "<span style=\"color:red\">Not Succesfully Inserted, please enter ALL information</span>");

        }
        doGet(request, response);
    }

}
