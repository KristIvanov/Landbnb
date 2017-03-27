package controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		//TODO User host = getfromsession ;
		User user = null;
		//create Host
		Host host = user.beHost();
		//redirect to AddAPlaceServlet
		RequestDispatcher rd = req.getRequestDispatcher("addaplace.html");
		rd.forward(req, resp);
		
	}
}
