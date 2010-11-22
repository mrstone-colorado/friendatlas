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
		String DBUrl = "jdbc:mysql://24.8.149.63/testDB";
        Connection conn = null;
        Statement stmt = null;
		
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);
		List<String> contacts = new ArrayList<String>();
	
		Object loginobj = session.getAttribute("LoggedIn");
		if (loginobj == null )
		{
			out.println("You are not logged in please <a href=\"/login\">Login</a>");
			return;
		}
		
		String loginvalue = loginobj.toString();
		
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
				conn = DriverManager.getConnection(DBUrl, "root", "ararat");
				stmt = conn.createStatement();
				String query = "SELECT * from Users where id='" + Userid + "'";
				ResultSet rs = stmt.executeQuery(query);
				
				while (rs.next())
				{
					user_name = "<span style=\"color:blue\">" + rs.getString("namef") + " " + rs.getString("namel") + "</span>";
				}
				
				String query2 = "SELECT * from contacts where userid='" + Userid + "'";
				ResultSet rs2 = stmt.executeQuery(query2);
				
				while (rs2.next())
				{
					String friend = rs2.getString("firstname") + " " + rs2.getString("lastname");
					contacts.add( friend );
				}
				
			}
			catch (SQLException sqlEx) 
			{
				out.println("Caught SQL Exception: " + sqlEx.toString() + "<br>");
			}
				
			
			String logo = "<br /><br /><a href=\"/logout\">Logout</a>";
			
			request.setAttribute("contacts", contacts); 
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
		String DBUrl = "jdbc:mysql://24.8.149.63/testDB";
        Connection conn = null;
        Statement stmt = null;
		request.setAttribute("mysqlresponse", "<span style=\"color:red\">Not Succesfully Inserted, please enter ALL information</span>"); 
		
        response.setContentType("text/html");
		
        PrintWriter out = response.getWriter();
		
        HttpSession session = request.getSession(true);
		
		Object loginobj = session.getAttribute("LoggedIn");
		if (loginobj == null )
		{
			out.println("You are not logged in please <a href=\"/login\">Login</a>");
			return;
		}

		String loginvalue = loginobj.toString();
		
		if ( loginvalue.equals("TRUE") ) {
			
		String Userid = session.getAttribute("UserID").toString();

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
			conn = DriverManager.getConnection(DBUrl, "root", "ararat");
			stmt = conn.createStatement();
			
			String namef = request.getParameter("name_first");
			String namel = request.getParameter("name_last");		
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zip = request.getParameter("zip");
			
			if ((namef != null && namef != "") && (namel != null && namel != "") && (email != null && email != "") && (address != null && address != "")  && (city != null && city != "") && (state != null && state != "")) {
				
				try {
					String Query = "insert into contacts (firstname, lastname, email, address, zip, city, state, userid) values (\"" + namef + "\",\"" + namel + "\",\"" + email + "\",\"" + address + "\",\"" + zip + "\",\"" + city + "\",\"" + state + "\",\"" + Userid + "\")";	
					int written =  stmt.executeUpdate(Query); 
					request.setAttribute("mysqlresponse", "<span style=\"color:green\">Succesfuly Inserted</span>"); 
				}
				catch (SQLException sqlEx) 
				{
					out.println("Caught SQL Exception: " + sqlEx.toString() + "<br>");
				}
			
			}
			}
			catch (SQLException sqlEx) 
			{
				out.println("Caught SQL Exception: " + sqlEx.toString() + "<br>");
			}
			
		}
		
		
        doGet(request, response);
    }

}
