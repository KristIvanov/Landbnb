package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.OfferDAO;

@WebServlet("/book")
public class BookServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long offerId = Long.parseLong(req.getParameter("offerId"));
		
		Offer offer = OfferDAO.getInstance().allOffers.get(arg0)
		
	}
		
	
	
}
