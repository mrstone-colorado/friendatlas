package csci3308.friendatlas;

/**
 * Created by IntelliJ IDEA.
 * User: correaj
 * Date: Nov 27, 2010
 * Time: 6:57:59 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

public class ViewFriends extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {

         response.setContentType("text/html");
         PrintWriter out = response.getWriter();

         HttpSession session = request.getSession( true );
         Object loggedIn = session.getAttribute( "LoggedIn ");
         Object userID = session.getAttribute( "UserID" );

         String logInValue;
         String userIdValue;
         int userIDNumber;

        ArrayList < Contact > userContacts = new ArrayList< Contact >( );
        ContactController cont = new ContactController();

        if ( loggedIn.equals( null ) )
		{
			out.println("You are not logged in please <a href=\"/loginpage.html\">Login</a>");
			return;
		}

        logInValue = loggedIn.toString(  );
        userIDNumber = Integer.parseInt( userID.toString() );

        if ( logInValue.equals( "TRUE" ) )
        {
              userContacts.addAll( cont.listContacts( userIDNumber ) );
        }

        out.println("<html>");
        out.println("<body bgcolor=\"white\">");
        out.println("<head>");

            String title = "Main Page";
                    out.println("<title>" + title + "</title>");
                    out.println("</head>");
                    out.println("<body>");



        for ( Contact ct: userContacts )
        {
            out.println("<b>" + ct.getFirst() + "</b>");
        }

        out.println("</body>");
        out.println("</html>");


    }

}
