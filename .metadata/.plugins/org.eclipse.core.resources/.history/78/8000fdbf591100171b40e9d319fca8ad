package places;

import java.util.ArrayList;

import interactions.Review;
import users.IReceiveReview;
import users.User;

public abstract class RentedPlace implements IReceiveReview{
	
	protected String name;
	protected User host;
	protected Address address;
	protected int maxGuests;
	protected int beds;
	protected double pricePerNight;
	protected String description;
	protected boolean available;//not sure kak raboti za razlichnite dati
	protected boolean isEntireHome;
	protected double rating;
	protected ArrayList<Review> receivedReviews;
	
	//TODO STRING TO ENUM address!!!
	
	public RentedPlace(String name, User host, Address address, int maxGuests, int beds, double pricePerNight, String description) {
		this.host = host;
		if (address != null) this.address = address;
		if (name != null && !name.isEmpty())this.name = name;
		if (maxGuests > 0) this.maxGuests = maxGuests;
		if (beds > 0) this.beds = beds;
		if (pricePerNight > 0) this.pricePerNight = pricePerNight;
		this.description = description;
		this.available = true;
		this.rating = 0;
		this.receivedReviews = new ArrayList<>();
	}

	public boolean isAvailable() {
		return available;
	}
	
	public int getMaxGuests() {
		return maxGuests;
	}
	
	public double getPricePerNight() {
		return pricePerNight;
	}
	
	public String getAddress() {
		return address.toString();
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getBeds() {
		return beds;
	}
	public User getHost() {
		return host;
	}
	
	public double getRating() {
		return rating;
	}

	public void addToReviews(Review review) {
		this.receivedReviews.add(review);
		
	}

	public void showAllReviews() {
		System.out.println("All reviews for: " + this.getName());
		System.out.println("Registered with " + this.getAddress());
		for (Review review : receivedReviews) {
			System.out.println(review.getGiver().getName());
			System.out.println(review.getRating());
			System.out.println(review.getDescription());
		}
		
	}
	
	public String getName() {
		return name;
	}
	
	

}
