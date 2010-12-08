package csci3308.friendatlas;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session;

        response.setContentType("text/html");

        boolean loggedIn = false;
        int userID;

        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        LoginService lg = LoginServiceFactory.getInstance();
        userID = lg.verifyUser(email, password);

        out.println("<b>Proceed " + " " + email + "</b>");

        if (userID > 0) {
            loggedIn = true;

            session = request.getSession(true);
            session.setAttribute("LoggedIn", "TRUE");
            session.setAttribute("UserID", userID);
            session.setAttribute("Useremail", email);

            //out.println("<meta http-equiv=\"refresh\" content=\"0;url=/main\">");
            response.sendRedirect("/main");

        } else {
//            out.println("<meta http-equiv=\"refresh\" content=\"0;url=/createaccount.html\">");
//            out.println("<b> It appears that you are not a member, don't a be a stranger and become a friend </b>");
            response.sendRedirect("/createaccount.html");
        }

    }
}



		
