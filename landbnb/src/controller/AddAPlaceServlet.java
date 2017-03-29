package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Offer;
import model.dao.AddressDAO;
import model.dao.OfferDAO;
import model.dao.RentedPlaceDAO;
import model.places.Address;
import model.places.EntireHome;
import model.places.RentedPlace;
import model.places.Room;
import model.users.Host;
import model.users.User;

@WebServlet("/addaplace")
public class AddAPlaceServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String errorMsg = " ";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		//getting parameters
		String name = req.getParameter("name");
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String region = req.getParameter("region");
		String city = req.getParameter("city");
		String street = req.getParameter("street");
		String number = req.getParameter("number");
		String apartment = req.getParameter("apartment");
		int apNumber = 0;
		if (isNumber(apartment)){
			apNumber = Integer.parseInt(apartment);
		}
		//creating address
		Address address = null;
		if (region != null && !region.isEmpty() && city != null && !city.isEmpty() && street != null && !street.isEmpty() && number != null && !number.isEmpty()){
			address = new Address(region, city, street, number, apNumber);
		}
		else {
			errorMsg = "Invalid address";
		}
		
		//creating place
		int maxGuests = Integer.parseInt(req.getParameter("maxGuests"));
		int beds = Integer.parseInt(req.getParameter("beds"));
		double pricePerNight = Double.parseDouble(req.getParameter("pricePerNight"));
		String description = req.getParameter("description");
		boolean isEntirePlace = req.getParameterMap().containsValue("rooms");
		int roomsNum = 1;
		String rooms = req.getParameter("rooms");
		if (isEntirePlace && isNumber(rooms)) roomsNum = Integer.parseInt(rooms);
		
		//create Place
		RentedPlace place = null;
		if (name != null && !name.isEmpty() && maxGuests > 0 && beds >= 0 && pricePerNight >= 0){
			if (isEntirePlace) place = new EntireHome(name, user, address, roomsNum, maxGuests, beds, pricePerNight, description);
			else place = new Room(name, user, address, maxGuests, beds, pricePerNight, description);
		}
		else {
			errorMsg = "Invalid information about your place";
		}
		
		//create offer
		
		SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
		String parameter = req.getParameter("startDate");
		Date date;
		try {
			date = in.parse(parameter);
			LocalDateTime date1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			String parameter2 = req.getParameter("endDate");
			date = in.parse(parameter2);
			LocalDateTime date2 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			Offer offer = new Offer(place, (Host)user, date1.toLocalDate(), date2.toLocalDate());
			OfferDAO.add(offer);
			
			//addAddress to db
			try{
				AddressDAO.getInstance().addToDB(address);
			} catch(SQLException ex){
				System.out.println("Error adding address to DB ");
			}
			
			//addPlace to db
			try{
				RentedPlaceDAO.getInstance().addToDB(place);
			} catch(SQLException ex){
				System.out.println("Error adding place to DB ");
			}
			
			//addOffer to db
			try{
				OfferDAO.getInstance().addToDB(offer, date1, date2);
			} catch(SQLException ex){
				System.out.println("Error adding offer to DB ");
			}
			
			
		} catch (ParseException e) {
			System.out.println("Wrong input details");
		}
		
		
	}
	
	private boolean isNumber(String string){
		boolean isnumber = true;
		for (char c : string.toCharArray()){
			 if (c < '0' || c > '9') isnumber = false;
		}
		return isnumber;
	}
}
