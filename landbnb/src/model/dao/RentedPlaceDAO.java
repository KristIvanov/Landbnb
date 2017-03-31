package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.places.Address;
import model.places.EntireHome;
import model.places.RentedPlace;
import model.places.Room;
import model.users.Host;
import model.users.User;

public class RentedPlaceDAO{
	
	private static RentedPlaceDAO instance = new RentedPlaceDAO();
	private static HashMap<String, RentedPlace> allPlaces;//key id
	private static ArrayList<RentedPlace> placeslist;
	
	private RentedPlaceDAO() {
		allPlaces = new HashMap<>();
		placeslist = new ArrayList<>();
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
					long hostId = res.getLong("fk_host_id");
					long addressId = res.getLong("fk_address_id");
					
					Host host = null;
					for(User e : UserDAO.getInstance().getUseresList()){
						if(e.getId()==hostId){
							 host = e.beHost(); 
						}
					}
					
					Address address = null;
					for(Address a : AddressDAO.getInstance().getAddressList()){
						if(a.getId()==addressId){
							 address = a; 
						}
					}
					
					
					if (onlyOneRoom == 1) place = new Room(name, host, address, maxGuests, beds, pricePerNight, description);
					else place = new EntireHome(name, host, address, rooms, maxGuests, beds, pricePerNight, description);
					
					place.setId(res.getLong("id"));
					allPlaces.put(place.getAddress(), place);
					placeslist.add(place);
				}
			} catch (SQLException e) {
				System.out.println("loading all places failed");
			}
			
		}

	}
	
	public static synchronized RentedPlaceDAO getInstance(){
		return instance;
	}

	public synchronized void addToDB(RentedPlace place) throws SQLException {
		PreparedStatement st1;
		String sql = "INSERT IGNORE INTO rented_places (name, max_guests, beds, rooms, price_per_night, rented_places.rating, description, is_only_one_room, fk_address_id, fk_host_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println("1");
		st1 = DBManager.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		System.out.println("2");
		st1.setString(1, place.getName());
		System.out.println("1");
		st1.setInt(2, place.getMaxGuests());
		st1.setInt(3, place.getBeds());
		System.out.println("1");
		st1.setInt(4, place.getRooms());
		st1.setDouble(5, place.getPricePerNight());
		System.out.println("1");
		st1.setDouble(6, 0.0);
		st1.setString(7, place.getDescription());
		System.out.println("1");
		st1.setInt(8, (place.getRooms()==1)?1:0);
		st1.setLong(9, place.getAddressObject().getId());
		System.out.println("1");
		System.out.println(place.getHost().getFamilyName());
		System.out.println(place.getHost().getId());
		st1.setLong(10, place.getHost().getId());
		st1.executeUpdate();
		System.out.println("8");
		ResultSet rSet = st1.getGeneratedKeys();
		while(rSet.next()){
			place.setId(rSet.getLong(1));
		}
		
	}
	
	public synchronized static Map<String, RentedPlace> getAllPlaces() {
		return Collections.unmodifiableMap(allPlaces);
	}
	

	public List<RentedPlace> getPlacesList(){
		return Collections.unmodifiableList(placeslist);
	}
	
}
