package model.interactions;

import model.Offer;
import model.users.User;

public class Booking {
	
	private User host;
	private User guest;
	private Offer offer;
	
	public Booking(User host, User guest, Offer offer) {
		this.host = host;
		this.guest = guest;
		this.offer = offer;
	}
	
	

	public void printReservation() {
		System.out.println("***RESERVATION***");
		System.out.println("Hello, " + this.guest.getName());
		System.out.println("registered with: " + this.guest.getMail());
		System.out.println("We are waiting you on " + this.offer.getStartOfPeriod().toString());
		System.out.println("at " + this.offer.getPlace().getAddress());
		System.out.println("Kind regards: " + this.host.getName());
		System.out.println("registered with: " + this.host.getMail());
	}
	
	public Offer getOffer() {
		return offer;
	}
	
	public User getGuest() {
		return guest;
	}
	

}
