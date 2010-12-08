package csci3308.friendatlas;

import csci3308.friendatlas.LoginServlet;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.StringWriter;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: mrstone
 * Date: Dec 8, 2010
 * Time: 3:25:01 PM
 * To change this template use File | Settings | File Templates.
 */

@Test
public class LoginServletTest {

        public void doGetShouldForwardtoJSP() throws Exception{
            HttpServletRequest request = mock(HttpServletRequest.class);

            RequestDispatcher dispatcher = mock(RequestDispatcher.class);
            when(request.getRequestDispatcher("/WEB-INF/jsp/login.jsp")).thenReturn(dispatcher);

            new LoginServlet().doGet(request, null);
            verify(dispatcher).forward(request, null);                        
        }

        public void doPostTestSuccessful() throws Exception {
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);

            HttpServletResponse response = mock(HttpServletResponse.class);
            when(response.getWriter()).thenReturn(writer);

            LoginService loginService = mock(LoginService.class);
            when(loginService.verifyUser("email@test.com", "password")).thenReturn(5);

            LoginServiceFactory.setInstance(loginService);

            try {
                HttpSession session = mock(HttpSession.class);

                HttpServletRequest request = mock(HttpServletRequest.class);
                when(request.getParameter("email")).thenReturn("email@test.com");
                when(request.getParameter("password")).thenReturn("password");
                when(request.getSession(true)).thenReturn(session);

                new LoginServlet().doPost(request, response);

                verify(session).setAttribute("LoggedIn", "TRUE");
                verify(session).setAttribute("UserID", 5);
                verify(session).setAttribute("Useremail", "email@test.com");
                verify(response).sendRedirect("/main");
                assertEquals(stringWriter.toString(), "<b>Proceed  email@test.com</b>\n");
            }
            finally {
                LoginServiceFactory.setInstance(null);
            }
        }

    public void doPostTestFailure() throws Exception {
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);

            HttpServletResponse response = mock(HttpServletResponse.class);
            when(response.getWriter()).thenReturn(writer);

            LoginService loginService = mock(LoginService.class);
            when(loginService.verifyUser("email@test.com", "password")).thenReturn(0);

            LoginServiceFactory.setInstance(loginService);

            try {
                HttpSession session = mock(HttpSession.class);

                HttpServletRequest request = mock(HttpServletRequest.class);
                when(request.getParameter("email")).thenReturn("email@test.com");
                when(request.getParameter("password")).thenReturn("password");
                when(request.getSession(true)).thenReturn(session);

                new LoginServlet().doPost(request, response);

                verify(session, never()).setAttribute("LoggedIn", "TRUE");
                verify(response).sendRedirect("/createaccount.html");

                assertEquals(stringWriter.toString(), "<b>Proceed  email@test.com</b>\n");
            }
            finally {
                LoginServiceFactory.setInstance(null);
            }
        }
}
