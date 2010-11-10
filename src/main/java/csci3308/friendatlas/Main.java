package csci3308.friendatlas;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;



public class Main extends HttpServlet {

    ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
    
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body bgcolor=\"white\">");
        out.println("<head>");

        String title = "Main Page";
        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body>");


        out.println("<h3>" + title + "</h3>");

        HttpSession session = request.getSession(true);
	
		String loginvalue = session.getAttribute("LoggedIn").toString();
		
		if ( loginvalue.equals("TRUE") ) {

			out.println("This is the main page where everything will be.<br /><br /> <b>Your Login Information: </b><br />");
			Enumeration names = session.getAttributeNames();
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement(); 
				String value = session.getAttribute(name).toString();
				out.println(name + " = " 
							+ value + "<br>");
			}
			
						out.println("<br /><br /><a href=\"/Friend_Atlas2/Logout\">Logout</a>");
		}

		else {
			out.println("You are not logged in please <a href=\"/Friend_Atlas2/Login\">Login</a>");
		}

    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }

}
