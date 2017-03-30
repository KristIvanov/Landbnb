package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.places.Address;
import model.places.EntireHome;
import model.places.RentedPlace;
import model.places.Room;
import model.users.User;

public class RentedPlaceDAO{
	
	private static RentedPlaceDAO instance;
	private static HashMap<String, RentedPlace> allPlaces;//key id
	
	private RentedPlaceDAO() {
		allPlaces = new HashMap<>();
		if(allPlaces.isEmpty()){
			String sql = "SELECT rented_place_id, name, max_guests, beds, rooms, price_per_night, rating, description, is_only_one_room, fk_address_id, fk_host_id FROM rented_places;";
			PreparedStatement st;
			try {
				st = DBManager.getInstance().getConnection().prepareStatement(sql);
				ResultSet res = st.executeQuery();
				while(res.next()){
					RentedPlace place = null;
					String name = res.getString("name");
					int maxGuests = res.getInt("max_guests");
					int beds = res.getInt("beds");
					int rooms = res.getInt("rooms");
					int onlyOneRoom = res.getInt("is_only_one_room");
					double pricePerNight = res.getDouble("price_per_night");
					String description = res.getString("description");
					
					User host = null;
					String sql2 = "SELECT user_id, email_address, password, first_name, last_name, phone, rating  FROM users JOIN rented_places WHERE user_id = fk_host_id;";
					PreparedStatement st2 = DBManager.getInstance().getConnection().prepareStatement(sql2);
					ResultSet res2 = st2.executeQuery();
					while(res2.next()){
						host = new User(res2.getString("first_name"), res2.getString("last_name"), res2.getString("email"), res2.getString("phone"), res2.getString("password"), res2.getDouble("rating"));
						host.setId(res.getLong("user_id"));
					}
					
					Address address = null;
					String sql3 = "SELECT address_id, region, city, street, number, apartment FROM addresses JOIN rented_places WHERE address_id = fk_address_id;";
					st = DBManager.getInstance().getConnection().prepareStatement(sql3);
					
					ResultSet res3 = st.executeQuery();
					System.out.println("idvame tuk?");
					while(res.next()){
						address = new Address(res3.getString("region"), res3.getString("city"), res3.getString("street"), res3.getString("number"), res3.getInt("apartment"));
						address.setId(res.getLong("id"));
					}
					
					
					if (onlyOneRoom == 1) place = new Room(name, host, address, maxGuests, beds, pricePerNight, description);
					else place = new EntireHome(name, host, address, rooms, maxGuests, beds, pricePerNight, description);
					
					place.setId(res.getLong("id"));
					allPlaces.put(place.getAddress(), place);
				}
			} catch (SQLException e) {
				System.out.println("loading all places failed");
			}
			
		}

	}
	
	public static synchronized RentedPlaceDAO getInstance(){
		if (instance == null){
			instance = new RentedPlaceDAO();
		}
		return instance;
	}

	public synchronized void addToDB(RentedPlace place) throws SQLException {
		PreparedStatement st1;
		String sql = "INSERT IGNORE INTO rented_places (name, max_guests, beds, rooms, price_per_night, rating, description, is_only_one_room, fk_address_id, fk_host_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		st1 = DBManager.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		st1.setString(1, place.getName());
		st1.setInt(2, place.getMaxGuests());
		st1.setInt(3, place.getBeds());
		st1.setInt(4, place.getRooms());
		st1.setDouble(5, place.getPricePerNight());
		st1.setDouble(6, 0.0);
		st1.setString(7, place.getDescription());
		st1.setInt(8, (place.getRooms()==1)?1:0);
		st1.setLong(9, place.getAddressObject().getId());
		System.out.println(place.getHost().getFamilyName());
		System.out.println(place.getHost().getId());
		st1.setLong(10, place.getHost().getId());
		System.out.println("do tuka idvame li");// ne idvame do tuka
		st1.executeUpdate();
		ResultSet rSet = st1.getGeneratedKeys();
		while(rSet.next()){
			place.setId(rSet.getLong(1));
		}
		
	}
	
	public synchronized static Map<String, RentedPlace> getAllPlaces() {
		return Collections.unmodifiableMap(allPlaces);
	}
	

}
