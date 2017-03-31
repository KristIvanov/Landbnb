package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;
import model.users.Host;
import model.users.User;

@WebServlet("/beahost")
public class BeAHostServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.setHeader("Pragma", "No-cache");
		resp.setDateHeader("Expires", 0);
		resp.setHeader("Cache-Control", "no-cache");
		HttpSession ses = req.getSession();
		if(ses.getAttribute("logged")!= null){
			boolean logged = (Boolean) req.getSession().getAttribute("logged");
			if(logged){
				HttpSession session = req.getSession();
				User user = (User) session.getAttribute("user");
				//create Host
				Host host = user.beHost();
				
				try {
					UserDAO.getInstance().removeUser(user);
					UserDAO.getInstance().addUser(host);
				} catch (SQLException e) {
					System.out.println("sql error");
				}
				//redirect to AddAPlace
				resp.sendRedirect("addaplace.html");
			}
			else{
				System.out.println("tuk");
				resp.sendRedirect("logIn.jsp");
			}
		}
		else{
			System.out.println("ili tuk");
			resp.sendRedirect("logIn.jsp");
		}
		
		
	}
	
	
}
