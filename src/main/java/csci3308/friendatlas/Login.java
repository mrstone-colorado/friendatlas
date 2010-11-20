package csci3308.friendatlas;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class Login extends HttpServlet {
	
    
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
	throws IOException, ServletException
    {
        response.setContentType("text/html");
		
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<head>");
		out.println("<title>Friend Atlas Login</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
		
		
		
        out.println("<DIV ALIGN=CENTER> <h3>Login Page For Friend Atlas</h3>");
		//out.println("Test");

        //out.println("<P>");
        out.print("<img src=\"atlas.jpg\" width=\"250\" height=\"150\" alt=\"Friend Atlas logo\" /> <form action=\"");
        out.print("login\" ");
        out.println("method=POST>");
        out.println("<br />E-Mail:");
        out.println("<input type=text size=20 name=email>");
        out.println("<br>");
        out.println("Password: ");
        out.println("<input type=password size=20 name=password>");
        out.println("<br>");
        out.println("<input type=submit>");
        out.println("</form>");
		out.println("<br /><a href=\"/createaccount\">Create an Account</a></DIV>");
		
        out.println("</body>");
        out.println("</html>");
    }
	
    public void doPost(HttpServletRequest request,
					   HttpServletResponse response)
	throws IOException, ServletException
    {
		
		response.setContentType("text/html");
		boolean loggedin = false;
		
        PrintWriter out = response.getWriter();
		
		String DBUrl = "jdbc:mysql://24.8.149.63/testDB";
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
            conn = DriverManager.getConnection(DBUrl, "root", "ararat");
            // get a new statement object 
            stmt = conn.createStatement();
            // and execute a simple query 
            ResultSet rs = stmt.executeQuery("SELECT * from Users");
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			if ((email != null && email != "") || (password != null && password != "")) {

				while (rs.next())
				{
					// iterate through the result set and print 
					// the query results to the page 
					if ((email.equals(rs.getString("email"))) && (password.equals(rs.getString("password1")))) { 
						loggedin=true;
						HttpSession session = request.getSession(true);
						out.println("<b>Session Information:</b><br />Session ID:" + " " + session.getId());
						out.println("<br>");
						out.println("Created:" + " ");
						out.println(new java.util.Date(session.getCreationTime()) + "<br>");
						out.println("Last Accessed" + " ");
						out.println(new java.util.Date(session.getLastAccessedTime()));
						out.println("<br>");
						session.setAttribute("LoggedIn", "TRUE");
						session.setAttribute("UserID", rs.getString("id"));
						session.setAttribute("Useremail", rs.getString("email"));
						
						
						
						Enumeration names = session.getAttributeNames();
						while (names.hasMoreElements()) {
							String name = (String) names.nextElement(); 
							String value = session.getAttribute(name).toString();
							out.println(name + " = " 
										+ value + "<br>");
						}
						
						out.println("<br />");
						
						
						out.println("<b>Databse Information:</b> <br />");
						out.println("id = " + rs.getString("id") + "<br>");
						out.println("email = " + rs.getString("email") + "<br>");
						out.println("password = " + rs.getString("password1") + "<br>");
						out.println("first name = " + rs.getString("namef") + "<br>");
						out.println("last name = " + rs.getString("namel") + "<br>");
						out.println("<br />");
						
						out.println("<meta http-equiv=\"refresh\" content=\"2;url=/main\">");
					}
				}	
				
			} 
			
			if(!loggedin) {
				out.println("You either entered the wrong username or password.<br />");
				//Nothing for Now
			}
			
			
        }
        catch (SQLException sqlEx) 
        {
			out.println("Caught SQL Exception: " + sqlEx.toString() + "<br>");
        }
		
        //doGet(request, response);
    }
	
}