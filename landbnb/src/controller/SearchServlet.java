package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Offer;
import model.dao.OfferDAO;

@WebServlet("/search")
public class SearchServlet extends HttpServlet{
	
	private static String filename = "searchResults.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO take args
		String region = req.getParameter("region");
		int guests = Integer.parseInt(req.getParameter("guests"));
		SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
		String parameter = req.getParameter("startDate");
		String parameter2 = req.getParameter("endDate");
		Date date;
		try {
			date = in.parse(parameter);
			LocalDateTime date1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			date = in.parse(parameter2);
			LocalDateTime date2 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			ArrayList<Offer> offersforYou = OfferDAO.getInstance().search(region, date1.toLocalDate(), date2.toLocalDate(), guests);
		} catch (ParseException e) {
			System.out.println("Invalid input");
			//TODO different response?
		}
		resp.sendRedirect(filename);
	}
}
