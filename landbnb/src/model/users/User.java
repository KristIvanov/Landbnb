package model.users;
import java.time.LocalDate;
//import org.apache.commons.validator.routines.EmailValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.interactions.Booking;
import model.interactions.Review;
import model.mainsite.MainSite;
import model.Offer;
import model.places.Address;
import model.places.EntireHome;
import model.places.RentedPlace;
import model.places.Room;

public class User implements IGiveReview, IReceiveReview{
	
	protected long id;
	protected String password; // Dobaveno!
	protected String firstName;
	protected String familyName;
	protected String mail;
	protected String phone;
	protected ArrayList<Review> receivedReviews;
	protected double rating;
	protected ArrayList<Booking> bookings;
	public static Scanner sc;
	
	
	public User(String firstName, String familyName, String mail, String phone, String password, Double rating) {	
		this.firstName = firstName;
		this.familyName = familyName;
		this.password = password;
		this.mail = mail; //TODO if regex && !OurFirm.users.contains(mail)
		this.phone = phone;
		this.receivedReviews = new ArrayList<>();
		this.bookings = new ArrayList<>();
		this.rating = rating;
	}
	
	public User(User user) {
		this.firstName = user.firstName;
		this.familyName = user.familyName;
		this.phone = user.phone;
		this.rating = user.rating;
		this.receivedReviews = user.receivedReviews;
		this.bookings = user.bookings;
		//TODO remove old user from database
		this.mail = user.mail;
		
	}
	
	public Offer createOffer(String name, boolean entireHome, Address address, int rooms, int maxGuests, int beds, double pricePerNight, String description, LocalDate start, LocalDate end){
		return null;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getFamilyName() {
		return familyName;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void addBooking(Booking newBooking) {
		this.bookings.add(newBooking);
	}
	
	public long getId() {
		return id;
	}

	public String getPhoneNumber(){
		return phone;
	}

	public String getPassword() {
		return password;
	}


	public Host beHost() {
		return new Host(this);
	}


	public void postReviewForPlace(Booking reservation) {
		sc = new Scanner(System.in);
		if (LocalDate.now().isAfter(reservation.getOffer().getEndOfPeriod())){
			System.out.println("Please enter description for our place: ");
			String description = "";
			description = sc.nextLine();
			
			System.out.println("Enter rating from 1 to 10");
			
			int rating = validInt();
			
			while(rating < 1 || rating > 10){
				System.out.println("Enter rating from 1 to 10");
				rating = sc.nextInt();
			}
			Review review = new Review(this, reservation.getOffer().getPlace(), rating, description);
			reservation.getOffer().getPlace().addToReviews(review);
			sc.nextLine();
		}
	}


	public void addToReviews(Review review) {
		this.receivedReviews.add(review);
		
	}


	public void showAllReviews() {
		System.out.println("All reviews for: " + this.getName());
		System.out.println("Registered with " + this.getMail());
		for (Review review : receivedReviews) {
			System.out.println(review.getGiver().getName());
			System.out.println(review.getRating());
			System.out.println(review.getDescription());
		}
		
	}


	public void viewAllBookings() {
		System.out.println("All history of: " + this.getName());
		System.out.println("Registered with " + this.getMail());
		
		for (Booking booking : bookings) {
			System.out.println("from: " + booking.getOffer().getStartOfPeriod());
			System.out.println("to: " + booking.getOffer().getEndOfPeriod());
			System.out.println("address: " + booking.getOffer().getPlace().getAddress());
			System.out.println("price per night: " + booking.getOffer().getPlace().getPricePerNight());
			System.out.println("max guests: " + booking.getOffer().getPlace().getMaxGuests());
		}
	}
	
	public boolean validateLogIn(String mail, String pass){// S tozi metod izbqgvame getPassword(), koito bi bil kofti metod
		if(this.mail.equals(mail)&&this.password.equals(pass)){
			return true;
		}
		return false;
	}
	
	
	public static void close(){
		sc.close();
	}
	
	public int validInt(){
		int rating = 0;
		boolean validint = false;
		while (!validint){
			if (sc.hasNextInt()){
				rating = sc.nextInt();
			}
			else {
				sc.next();
				continue;
			}
			validint = true;
		}
		return rating;
	}


	@Override
	public String getName() {
		return this.firstName +" " + this.familyName;
	}


	public void setId(long id) {
		this.id = id;
		
	}
	
	
}
