package model.places;

import model.users.User;

public class EntireHome extends RentedPlace{
	
	private int rooms;

	public EntireHome(String name, User host, Address address, int rooms, int maxGuests, int beds, double pricePerNight, String description) {
		super(name, host, address, maxGuests, beds, pricePerNight, description);
		this.isEntireHome = true;
		this.rooms = rooms;
	}

	@Override
	public int getRooms() {
		return this.rooms;
	}

}
