
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import model.interactions.Booking;
import model.mainsite.MainSite;
import model.Offer;
import model.places.Address;
import model.places.Address.Region;
import model.users.Host;
import model.users.InvalidEmailException;
import model.users.InvalidNameException;
import model.users.InvalidPasswordException;
import model.users.InvalidPhoneNumberException;
import model.users.NotMatchingPasswordsException;
import model.users.User;

public class TheDemo {
	
	

	public static void main(String[] args) {
		
		//MainSite ms = new MainSite();
		//ms.addUser();
		//ms.logIn();
		
		MainSite ourairbnb = new MainSite();
		User user = null;
		try {
			user = new User("pesho petrov", "pesho@abv.bg", "0878449253", "Pass123lol", "Pass123lol");
		} catch (NotMatchingPasswordsException | InvalidPasswordException | InvalidEmailException | InvalidNameException
				| InvalidPhoneNumberException e) {
			
			e.printStackTrace();
		}
		ourairbnb.createAccount(user);
		Host host = user.beHost();
		Offer offer = host.createOffer("Pri pesho", true, new Address(Region.PLOVDIV, "Plovdiv", "Trakia", 262), 4, 4, 80, "Perfect apartment near Lauta area", LocalDate.of(2016, 5, 19), LocalDate.of(2016, 9, 25));
		Offer offer2 = host.createOffer("Pri pesho 2", true, new Address(Region.PLOVDIV, "Plovdiv", "Trakia", 261), 4, 3, 100.0, "Perfect apartment near Lauta area", LocalDate.of(2016, 5, 25), LocalDate.of(2016, 9, 26));
		ourairbnb.addOffer(offer);
		ourairbnb.addOffer(offer2);
		User guest = null;;
		try {
			guest = new User("gosho", "gosho@gmail.com", "0878449254", "Pass123lol", "Pass123lol");
		} catch (NotMatchingPasswordsException | InvalidPasswordException | InvalidEmailException | InvalidNameException
				| InvalidPhoneNumberException e) {
			
			e.printStackTrace();
		}
		ArrayList<Offer> naGosho = ourairbnb.search(Region.PLOVDIV, LocalDate.of(2016, 6, 25), LocalDate.of(2016, 7, 25), 2);//gosho searches
		ourairbnb.printByPrice();
		ourairbnb.printByPriceReversedOrder();
		ourairbnb.printByRating();
		Booking reservation = offer.book(guest, naGosho.get(0), ourairbnb);
		ourairbnb.clearSearchResults();
		reservation.printReservation();
		guest.postReviewForPlace(reservation);
		host.postReviewForGuest(reservation);
		offer.getPlace().showAllReviews();
		guest.showAllReviews();
		guest.viewAllBookings();
		
		//check if booked offers are already not available
		ArrayList<Offer> naGosho2 = ourairbnb.search(Region.PLOVDIV, LocalDate.of(2016, 6, 29), LocalDate.of(2016, 7, 5), 2);//gosho tyrsi
		ourairbnb.printByPrice();
		
		User.close();
		
		
		
	}
}
