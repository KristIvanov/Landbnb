package mainsite;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import model.Offer;

import java.util.Scanner;

import places.Address;
import places.RentedPlace;
import users.InvalidEmailException;
import users.InvalidNameException;
import users.InvalidPasswordException;
import users.InvalidPhoneNumberException;
import users.NotMatchingPasswordsException;
import users.User;

public class MainSite {
	
	private HashMap<String, User> users;//key email
	private HashMap<LocalDate, HashMap<String, Offer>> offers;//key address
	private ArrayList<Offer> offersForYou;//filtered offers for each user 
	private boolean userLogged;

	
	public MainSite() {
		this.users = new HashMap<>();
		this.offers = new HashMap<>();
		this.offersForYou = new ArrayList<>();
		this.userLogged = false;
	}



	public ArrayList<Offer> search(Address.Region address, LocalDate start, LocalDate end, int guests){
		System.out.println("SEARCH STARTS");
		for (Iterator<Entry<LocalDate, HashMap<String, Offer>>> it = this.offers.entrySet().iterator(); it.hasNext();){
			Entry<LocalDate, HashMap<String, Offer>> e = it.next();
			for (Iterator<Entry<String, Offer>> it2 = e.getValue().entrySet().iterator(); it2.hasNext();) {
				Entry<String, Offer> e2 = it2.next();
				//System.out.println(e2.getKey());
				//System.out.println(e2.getValue().getHost().getName());
				if (e2.getKey().contains((address.toString())) && 
					e2.getValue().getPlace().getMaxGuests() >= guests &&
					!e2.getValue().getStartOfPeriod().isAfter(start) &&
					!e2.getValue().getEndOfPeriod().isBefore(end)) {
					Offer forYou = new Offer(e2.getValue().getPlace(), e2.getValue().getHost(), start, end);
					this.offersForYou.add(forYou);
				}
			}
		}
		System.out.println("Default search results");
		print(offersForYou);
		System.out.println("SEARCH ENDS");
		return offersForYou;
	}



	private void print(ArrayList<Offer> offersForYou) {
		System.out.println("OFFERS FOR YOU");
		for (Offer offer : offersForYou) {
			System.out.println("you can come to: " + offer.getPlace().getAddress());
			System.out.println("from: " + offer.getStartOfPeriod().toString());
			System.out.println("to: " + offer.getEndOfPeriod().toString());
			System.out.println(offer.getPlace().getDescription());
			System.out.println(offer.getPlace().getPricePerNight() + " per night");
			System.out.println(offer.getPlace().getBeds() + " beds available");
			System.out.println("Your host is " + offer.getPlace().getHost().getName());
		}
	}
	
	
	public void printByPrice(){
		Collections.sort(this.offersForYou, new Comparator<Offer>() {

			@Override
			public int compare(Offer o1, Offer o2) {
				if (o1.getPlace().getPricePerNight() == o2.getPlace().getPricePerNight()) return o1.getPlace().getAddress().compareTo(o2.getPlace().getAddress());
				else if (o1.getPlace().getPricePerNight() > o2.getPlace().getPricePerNight()) return 1;
				return -1;
			}
		});
		System.out.println("BY PRICE");
		print(this.offersForYou);
	}
	
	public void printByPriceReversedOrder(){
		Collections.sort(this.offersForYou, new Comparator<Offer>() {

			@Override
			public int compare(Offer o1, Offer o2) {
				if (o1.getPlace().getPricePerNight() == o2.getPlace().getPricePerNight()) return o1.getPlace().getAddress().compareTo(o2.getPlace().getAddress());
				else if (o1.getPlace().getPricePerNight() > o2.getPlace().getPricePerNight()) return -1;
				return 1;
			}
		});
		System.out.println("BY PRICED REVERSED ORDER");
		print(this.offersForYou);
	}
	
	
	public void printByRating() {
		Collections.sort(this.offersForYou, new Comparator<Offer>() {

			@Override
			public int compare(Offer o1, Offer o2) {
				if (o1.getPlace().getRating() > o2.getPlace().getRating()) return 1;
				if (o1.getPlace().getRating() < o2.getPlace().getRating()) return -1;
				return o1.getPlace().getAddress().compareTo(o2.getPlace().getAddress());
			}
			
		});
		System.out.println("BY RATING");
		print(this.offersForYou);
	}


	public void updateOffers(Offer offer) {
		Offer temp1 = null;
		Offer temp2 = null;
		Offer old = null;
		for (Iterator<Entry<LocalDate, HashMap<String, Offer>>> it = this.offers.entrySet().iterator(); it.hasNext();){
			Entry<LocalDate, HashMap<String, Offer>> e = it.next();
			for (Iterator<Entry<String, Offer>> it2 = e.getValue().entrySet().iterator(); it2.hasNext();) {
				Entry<String, Offer> e2 = it2.next();
				if (e2.getKey().equals(offer.getPlace().getAddress()) && 
					e2.getValue().getPlace().getMaxGuests() >= offer.getPlace().getMaxGuests() &&
					!e2.getValue().getStartOfPeriod().isAfter(offer.getStartOfPeriod()) &&
					!e2.getValue().getEndOfPeriod().isBefore(offer.getEndOfPeriod())) {
					//create 2 new offers - one before the booked period and one after it
					temp1 = new Offer(offer.getPlace(), offer.getHost(), e2.getValue().getStartOfPeriod(), offer.getStartOfPeriod());
					temp2 = new Offer(offer.getPlace(), offer.getHost(), offer.getEndOfPeriod(), e2.getValue().getEndOfPeriod());
					//old offer not available anymore
					old = this.offers.get(e.getKey()).get(e2.getKey());
				}
			}
		}
		if (old != null){
			this.offers.get(old.getStartOfPeriod()).remove(old.getPlace().getAddress(), old);
			System.out.println("removed");
		}
		
		if (temp1 != null){
			this.offers.get(temp1.getStartOfPeriod()).put(temp1.getPlace().getAddress(), temp1);
			System.out.println("added1");
		}
		if (temp2 != null){
			if(!this.offers.containsKey(temp2.getStartOfPeriod())){
				this.offers.put(temp2.getStartOfPeriod(), new HashMap<>());
			}
			this.offers.get(temp2.getStartOfPeriod()).put(temp2.getPlace().getAddress(), temp2);
			System.out.println("added2");
		}
		
	}



	public void createAccount(User user) {
		if(!users.containsKey(user.getMail())) users.put(user.getMail(), user);
	}
	
	public void addOffer(Offer offer){
		if (!this.offers.containsKey(offer.getStartOfPeriod())){
			this.offers.put(offer.getStartOfPeriod(), new HashMap<>());
		}
		this.offers.get(offer.getStartOfPeriod()).put(offer.getPlace().getAddress(), offer);
	}
	
	
	// Dobaveni metodi!
	// V metodi addUser i logIn nqma finally{sc.close}, zashtoto kato se close-ne v ediniq metod,
	// sled towa hvarlq NoSuchElementException, kato go pravim s failove shte ima STREAM.close
	public void addUser(){  // Ako ne vavede pravilni danni pak se izvikva tozi metod ( v catch block-a)
		String password;
		String reenteredPass;
		String name;
		String mail;
		String phone;
		Scanner sc = new Scanner(System.in); // Tuk predpolagam che shte se otvarq nqkakav fail, koito clienta ni e izpratil
		System.out.println("SIGN UP");
		System.out.println("Please, enter name");
		name = sc.nextLine();
		System.out.println("Please, enter email");
		mail = sc.nextLine();
		System.out.println("Please, enter phone number");
		phone = sc.nextLine();
		System.out.println("Please, enter password");
		password = sc.nextLine();
		System.out.println("Please, re-enter password");
		reenteredPass = sc.nextLine();
		User user;
		try {
			user = new User(name,mail,phone,password,reenteredPass);
			if(user!=null){
				users.put(user.getMail(), user);
			}
		} catch (NotMatchingPasswordsException | InvalidPasswordException | InvalidEmailException | InvalidNameException
				| InvalidPhoneNumberException e) {
			System.out.println(e.getMessage());
			this.addUser();
		}
	}
	
	public void logIn(){
		Scanner sc = new Scanner(System.in); // Tuk predpolagam che shte se otvarq nqkakav fail, koito clienta ni e izpratil
		String mail;
		String pass;
		System.out.println("SIGN IN");
		System.out.println("Please, enter email");
		mail = sc.nextLine();
		System.out.println("Please, enter password");
		pass = sc.nextLine();
		if(this.users.containsKey(mail)){ // Proverqva za parola samo ako emaila e v hashmap-a users
			if(this.users.get(mail).validateLogIn(mail, pass)){
				this.userLogged = true;
				System.out.println("You logged in");
				return;
			}
			System.out.println("Wrong password");
			this.logIn();
			return;
		}
		System.out.println("Not found user with that email");
		this.logIn();
	}
	
	public void clearSearchResults(){
		this.offersForYou = new ArrayList<>();
	}

}
