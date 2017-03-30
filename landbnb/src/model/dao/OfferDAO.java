package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import model.Offer;
import model.places.Address;
import model.places.RentedPlace;
import model.users.Host;
import model.users.User;

public class OfferDAO {
	
	private static OfferDAO instance;
	private static HashMap<LocalDate, HashMap<String, Offer>> allOffers; //subkey address
	
	private OfferDAO() {}
	
	public static synchronized OfferDAO getInstance(){
		if (instance == null){
			instance = new OfferDAO();
			allOffers = new HashMap<>();
			if(allOffers.isEmpty()){
				//take all offers
				String sql = "SELECT offer_id, start_of_period, end_of_period, fk_place_id FROM offers;";
				PreparedStatement st;
				try {
					st = DBManager.getInstance().getConnection().prepareStatement(sql);
					ResultSet res = st.executeQuery();
					while(res.next()){//for each offer
						//take their places
						long offerId = res.getLong("id");
						sql = "SELECT rented_place_id, name, max_guests, beds, rooms, price_per_night, rating, description, is_only_one_room, fk_address_id, fk_owner_id FROM rented_places WHERE rented_place_id = ?;";
						PreparedStatement st1 = DBManager.getInstance().getConnection().prepareStatement(sql);
						st1.setLong(1, offerId);
						ResultSet res1 = st1.executeQuery();
						long addressId = res1.getLong("fk_address_id");
						Address address = AddressDAO.getInstance().getAllAddresses().get(addressId);
						RentedPlace place = RentedPlaceDAO.getInstance().getAllPlaces().get(address);
						User host = place.getHost();
						Offer offer = new Offer(place, (Host) host, res.getDate("start_of_period").toLocalDate(), res.getDate("end_of_period").toLocalDate());
						offer.setId(offerId);
						allOffers.put(res.getDate("start_of_period").toLocalDate(), new HashMap<>());
						allOffers.get(res.getDate("start_of_period").toLocalDate()).put(place.getAddress(), offer);
					}
				} catch (SQLException e) {
					System.out.println("error loading offers");
				}
				
			}

			
		}
		return instance;
	}

	public static synchronized void add(Offer offer) {
		allOffers.put(offer.getStartOfPeriod(), new HashMap<>());
		allOffers.get(offer.getStartOfPeriod()).put(offer.getPlace().getAddress(), offer);
		
	}

	public synchronized void addToDB(Offer offer, LocalDateTime date1, LocalDateTime date2) throws SQLException {
		PreparedStatement st2;
		String sql2 = "INSERT IGNORE INTO offers (start_of_period, end_of_period, fk_place_id) VALUES (?, ?, ?)";
		st2 = DBManager.getInstance().getConnection().prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
		st2.setTimestamp(1, Timestamp.valueOf(date1));
		st2.setTimestamp(2, Timestamp.valueOf(date2));
		st2.setLong(3, offer.getPlace().getId());
		st2.executeUpdate();
		ResultSet rSet = st2.getGeneratedKeys();
		while(rSet.next()){
			offer.setId(rSet.getLong(1));;
		}
	}
	
	public static synchronized ArrayList<Offer> search(String address, LocalDate start, LocalDate end, int guests){
		
		ArrayList<Offer> offersForYou = new ArrayList<>();
		
		for (Iterator<Entry<LocalDate, HashMap<String, Offer>>> it = allOffers.entrySet().iterator(); it.hasNext();){
			Entry<LocalDate, HashMap<String, Offer>> e = it.next();
			for (Iterator<Entry<String, Offer>> it2 = e.getValue().entrySet().iterator(); it2.hasNext();) {
				Entry<String, Offer> e2 = it2.next();
				if (e2.getKey().toLowerCase().contains((address.toLowerCase())) && 
					e2.getValue().getPlace().getMaxGuests() >= guests &&
					!e2.getValue().getStartOfPeriod().isAfter(start) &&
					!e2.getValue().getEndOfPeriod().isBefore(end)) {
					Offer forYou = new Offer(e2.getValue().getPlace(), e2.getValue().getHost(), start, end);
					offersForYou.add(forYou);
				}
			}
		}
		
		return offersForYou;
	}
	
	

}
