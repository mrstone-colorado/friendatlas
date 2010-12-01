package csci3308.friendatlas;

/**
 * Created by IntelliJ IDEA.
 * User: correaj
 * Date: Nov 27, 2010
 * Time: 6:07:50 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class CreateAccount extends HttpServlet {


	public void doPost(HttpServletRequest request,
					   HttpServletResponse response)
	throws IOException, ServletException
    {

        boolean isUser = false;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session;
        int userID;

		String firstName = request.getParameter("firstn");
        String lastName = request.getParameter("lastn");
		String email = request.getParameter("email");
        String password = request.getParameter("password");
		String passwordconf = request.getParameter("passwordconf");

        if (( firstName != null && firstName != "" ) && ( lastName != null && firstName != "" ) && ( email != null && email != "" ) && ( password != null && password != "" ))
        {
            CreateAccountController cac = new CreateAccountController();
            isUser = cac.checkForExistingAccount( email, password );

            if ( isUser == false )
            {
                out.print("<b>");
                userID = cac.createNewUser( firstName, lastName, email, password );
                out.println( userID );
                out.print("</b>");

                session = request.getSession( true );
                session.setAttribute( "LoggedIn", "TRUE" );
                session.setAttribute( "UserID", userID );
                session.setAttribute( "Useremail", email);

                out.println("<meta http-equiv=\"refresh\" content=\"0;url=/main\">");

            }
        }
     }
}
