package csci3308.friendatlas;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class Main extends HttpServlet {
    
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
		String DBUrl = "jdbc:mysql:///testDB?user='root'";
        Connection conn = null;
        Statement stmt = null;
		
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);
	
		String loginvalue = session.getAttribute("LoggedIn").toString();
		
		if ( loginvalue.equals("TRUE") ) {

			String Headertext = "This is the main page where everything will be.<br /><br />";

			String Userid = session.getAttribute("UserID").toString();
			
			String user_name = "";
			
			try
			{
				try
				{
					Class.forName("org.gjt.mm.mysql.Driver").newInstance();
				}
				catch (Exception Ex)
				{
					out.println("Unable to Load Driver: " + Ex.toString() + "<br>");
				}
				conn = DriverManager.getConnection(DBUrl);
				stmt = conn.createStatement();
				String query = "SELECT * from Users where id='" + Userid + "'";
				ResultSet rs = stmt.executeQuery(query);
				
				while (rs.next())
				{
					user_name = rs.getString("namef") + " " + rs.getString("namel");
				}
				
			}
			catch (SQLException sqlEx) 
			{
				out.println("Caught SQL Exception: " + sqlEx.toString() + "<br>");
			}
				
			
			String logo = "<br /><br /><a href=\"/logout\">Logout</a>";
			
			request.setAttribute("UserName", user_name); 
			request.setAttribute("headertop", Headertext); 
			request.setAttribute("logoutlink", logo); 
			request.getRequestDispatcher("WEB-INF/jsp/mainpage.jsp").forward(request, response);
		}

		else {
			out.println("<html>");
			out.println("<body bgcolor=\"white\">");
			out.println("<head>");
			
			String title = "Main Page";
			out.println("<title>" + title + "</title>");
			out.println("</head>");
			out.println("<body>");
			
			
			out.println("<h3>" + title + "</h3>");
			out.println("You are not logged in please <a href=\"/login\">Login</a>");
		}

    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }

}
