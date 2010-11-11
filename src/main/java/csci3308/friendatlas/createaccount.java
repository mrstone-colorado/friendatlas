package csci3308.friendatlas;


import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class createaccount extends HttpServlet {
	
	
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
	throws IOException, ServletException
    {
		
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        //print the HTML header </font>
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Create An account</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
        out.println("<h1>Creat an Account</h1>");
		
		

        out.println("<P>");
        out.print("<form action=\"");
        out.print("createaccount\" ");
        out.println("method=POST>");
		out.println("First Name:");
        out.println("<input type=text size=20 name=firstn><br />");
		out.println("Last name:");
        out.println("<input type=text size=20 name=lastn><br />");
        out.println("E-Mail:");
        out.println("<input type=text size=20 name=email><br />");
        out.println("Password: ");
        out.println("<input type=password size=20 name=password><br />");
		out.println("Repeat Password: ");
        out.println("<input type=password size=20 name=passwordconf>");
        out.println("<br>");
        out.println("<input type=submit>");
        out.println("</form>");
		
		
        out.println("</body>");
        out.println("</html>");
		
		
    }
	
	public void doPost(HttpServletRequest request,
					   HttpServletResponse response)
	throws IOException, ServletException
    {
		
		
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		
		String DBUrl = "jdbc:mysql:///testDB?user='root'";
        Connection conn = null;
        Statement stmt = null;
        try
        {
            try
            {
				//create a new instance of the mysql driver
				Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            }
            catch (Exception Ex)
            {
                out.println("Unable to Load Driver: " + Ex.toString() + "<br>");
            }
			// get a new connection object 
            conn = DriverManager.getConnection(DBUrl);
            // get a new statement object 
            stmt = conn.createStatement();
            // and execute a simple query 
			
        }
        catch (SQLException sqlEx) 
        {
			out.println("Caught SQL Exception: " + sqlEx.toString() + "<br>");
        }
        
		
		String namef = request.getParameter("firstn");
        String namel = request.getParameter("lastn");		
		String email = request.getParameter("email");
        String password = request.getParameter("password");
		String passwordconf = request.getParameter("passwordconf");
		
        if ((namef != null && namef != "") && (namel != null && namel != "") && (email != null && email != "") && (password != null && password != "")) {
			
			try {
				String Query = "insert into users (email,password1,namef,namel) values (\"" + email + "\",\"" + password + "\",\"" + namef + "\",\"" + namel + "\")";	
				int written =  stmt.executeUpdate(Query); 
			}
			catch (SQLException sqlEx) 
			{
				out.println("Caught SQL Exception: " + sqlEx.toString() + "<br>");
			}
			
		} else {
            //Do nothing for now
        }
		
		
		
	try
	{
		ResultSet rs = stmt.executeQuery("SELECT * from Users where namef='" + namef +"' and namel='" + namel +"' ");
		out.println("Account Created succesfully with the following information: <br />");
		while (rs.next())
		{
			// iterate through the result set and print 
			// the query results to the page 
			out.println("id = " + rs.getString("id") + "<br>");
			out.println("email = " + rs.getString("email") + "<br>");
			out.println("password = " + rs.getString("password1") + "<br>");
			out.println("First Name = " + rs.getString("namef") + "<br>");
			out.println("Last Name = " + rs.getString("namel") + "<br>");
			out.println("<br />");
		}
		
		out.println("<a href=\"/login\">Login</a>");
		
	}
	catch (SQLException sqlEx) 
	{
		out.println("Caught SQL Exception: " + sqlEx.toString() + "<br>");
	}
     
		
	// now close the statement and connection if they exist
	if (stmt != null)
	{
		try
		{
			stmt.close();
		}
		catch (SQLException sqlEx)
		{
			out.println("Could not close: " + sqlEx.toString() + "<br>");
		}
	}
	if (conn != null)
	{
		try
		{
			conn.close();
		}
		catch (SQLException sqlEx) 
		{
			out.println("Could not close: " + sqlEx.toString() + "<br>");
		}
	}
	}		
}