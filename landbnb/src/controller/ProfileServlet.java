package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.users.User;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	
	private static String Msg = " ";
	
	public static String getMsg() {
		return ProfileServlet.Msg;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession ses = request.getSession();
		if(ses.getAttribute("logged")!= null){
			boolean logged = (Boolean) request.getSession().getAttribute("logged");
			if(logged){
				User user = (User) request.getSession().getAttribute("user");
				String mail = "<h3>" + (String) request.getSession().getAttribute("mail") + "</h3>";
				ProfileServlet.Msg = user.getName();
				response.getWriter().append(mail + "test");
			}
			else{
				response.sendRedirect("logIn.jsp");
			}
		}
		else{
			response.sendRedirect("logIn.jsp");
		}
	}


}
