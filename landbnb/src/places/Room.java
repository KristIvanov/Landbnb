package places;

import users.User;

public class Room extends RentedPlace{

	public Room(String name, User host, Address address, int maxGuests, int beds, double pricePerNight, String description) {
		super(name, host, address, maxGuests, beds, pricePerNight, description);
		this.isEntireHome = false;
	}

}
