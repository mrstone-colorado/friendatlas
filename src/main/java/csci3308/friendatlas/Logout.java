package csci3308.friendatlas;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Logout extends HttpServlet {
    
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body bgcolor=\"white\">");
        out.println("<head>");

        String title = "Logout Page";
        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body>");


        out.println("<h3>" + title + "</h3>");

        HttpSession session = request.getSession(true);
	
		Object loginobj = session.getAttribute("LoggedIn");
		
		if (loginobj == null)
		{
			out.println("You are not logged in please <a href=\"/login\">Login</a>");
			return;
		}
		
		String loginvalue = loginobj.toString();
		
		if ( loginvalue.equals("TRUE") ) {
			//session.invalidate();
			session.setAttribute("LoggedIn", "FALSE");
			out.println("<meta http-equiv=\"refresh\" content=\"0\">");
			out.println("Destroyed");
		}

		else {
		Enumeration names = session.getAttributeNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement(); 
			String value = session.getAttribute(name).toString();
			out.println(name + " = " 
						+ value + "<br>");
		}
		}

    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }

}
