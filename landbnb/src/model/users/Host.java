package model.users;

import java.time.LocalDate;
import java.util.HashMap;


import model.interactions.Booking;
import model.interactions.Review;
import model.Offer;
import model.places.Address;
import model.places.EntireHome;
import model.places.RentedPlace;
import model.places.Room;

public class Host extends User{
	
	private HashMap<String, RentedPlace> estates;

	public Host(User user) {
		super(user);
		this.estates = new HashMap<>();
	}
	
	public void addOffer(Offer offer){
		this.estates.put(offer.getPlace().getAddress(), offer.getPlace());
	}
	
	@Override
	public Offer createOffer(String name, boolean entireHome, Address address, int rooms, int maxGuests, int beds, double pricePerNight,
			String description, LocalDate start, LocalDate end) {
		RentedPlace newPlace;
		if (entireHome) {
			newPlace = new EntireHome(name, this, address, rooms, maxGuests, beds, pricePerNight, description);
		}
		else {
			newPlace = new Room(name, this, address, maxGuests, beds, pricePerNight, description);
		}
		Offer newOffer = new Offer(newPlace, this, start, end);
		this.addOffer(newOffer);
		return newOffer;
	}

	public void postReviewForGuest(Booking reservation) {
		
		
		if (LocalDate.now().isAfter(reservation.getOffer().getEndOfPeriod())){
			System.out.println("Please enter something about me: ");
			
			String description = sc.nextLine();
			//TODO validate
			System.out.println("Enter rating from 1 to 10");
			int rating = validInt();
			
			while(rating < 1 || rating > 10){
				System.out.println("Enter rating from 1 to 10");
				rating = sc.nextInt();
			}
			Review review = new Review(this, reservation.getGuest(), rating, description);
			reservation.getGuest().addToReviews(review);
			sc.nextLine();
		}
		
	}
	
	

}
