package model;

import java.time.LocalDate;

import model.interactions.Booking;
import model.mainsite.MainSite;
import model.places.RentedPlace;
import model.users.Host;
import model.users.User;

public class Offer {
	
	private RentedPlace place;
	private Host host;
	private LocalDate startOfPeriod;
	private LocalDate endOfPeriod;
	private long id;
	
	public RentedPlace getPlace() {
		return place;
	}

	public Offer(RentedPlace place, Host host, LocalDate startOfPeriod, LocalDate endOfPeriod) {
		
		this.place = place;
		this.host = host;
		if (startOfPeriod.isBefore(endOfPeriod)){
			this.startOfPeriod = startOfPeriod;
			this.endOfPeriod = endOfPeriod;
		}
		
		
	}
	
	public LocalDate getEndOfPeriod() {
		return endOfPeriod;
	}
	
	public LocalDate getStartOfPeriod() {
		return startOfPeriod;
	}
	
	public Booking book(User user, Offer offer, MainSite mainsite){
		Booking newBooking = new Booking(offer.host, user, offer);
		mainsite.updateOffers(offer);
		user.addBooking(newBooking);
		offer.host.addBooking(newBooking);
		return newBooking;
	}
	
	public Host getHost() {
		return host;
	}
	
	public void book(User loggedIn, Offer offer){
		//TODO if(!userLogged){ log in };
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	

}
