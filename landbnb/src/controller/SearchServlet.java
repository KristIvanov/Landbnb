package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
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
	private static ArrayList<Offer> offersForYou;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String errorMsg = " ";
	
	public static String getErrorMsg() {
		return SearchServlet.errorMsg;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String region = req.getParameter("region");
		String guests = req.getParameter("guests");
		int guestsNum = 0;
		if (isNumber(guests)){
			guestsNum = Integer.parseInt(guests);
		}
		else {
			errorMsg = "Invalid number of guests";
		}
		
		SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
		String parameter = req.getParameter("startDate");
		String parameter2 = req.getParameter("endDate");
		Date date;
		try {
			date = in.parse(parameter);
			LocalDateTime date1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			date = in.parse(parameter2);
			LocalDateTime date2 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			offersForYou = OfferDAO.getInstance().search(region, date1.toLocalDate(), date2.toLocalDate(), guestsNum);
			//	TODO redirect & somehow print each offer in the page
		} catch (ParseException e) {
			System.out.println("Invalid input");
			//TODO different response?
		}
		resp.sendRedirect(filename);
	}
	
	private boolean isNumber(String string){
		boolean isnumber = true;
		for (char c : string.toCharArray()){
			 if (c < '0' || c > '9') isnumber = false;
		}
		return isnumber;
	}
	
	public static ArrayList<Offer> getOffers(){
		ArrayList<Offer> val;
		return val = (ArrayList<Offer>) Collections.unmodifiableCollection(offersForYou);
	}
}
