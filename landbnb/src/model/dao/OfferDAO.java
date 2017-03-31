package model.dao;

import java.io.PrintStream;
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
	public static HashMap<LocalDate, HashMap<String, Offer>> allOffers; //subkey address
	public static ArrayList<Offer> allOffers1;
	
	private OfferDAO() {
			allOffers1 = new ArrayList<>();
			System.out.println("0");
			String sql = "SELECT offer_id, start_of_period, end_of_period, fk_place_id FROM offers;";
			PreparedStatement st;
			try {
				st = DBManager.getInstance().getConnection().prepareStatement(sql);
				ResultSet res = st.executeQuery();
				System.out.println("0");
				while(res.next()){//for each offer
					System.out.println("1st");
					long placeId = res.getLong("fk_place_id");
					RentedPlace place = null;
					Host host = null;
					long offerId = res.getLong("offer_id");
					//take their places
					for (RentedPlace r : RentedPlaceDAO.getInstance().getPlacesList()){
						if(r.getId()== placeId){
							place = r;
							host = r.getHost();
						}
					}
					
					
					Offer offer = new Offer(place, host, res.getDate("start_of_period").toLocalDate(), res.getDate("end_of_period").toLocalDate());
					offer.setId(offerId);
					System.out.println("7");
					OfferDAO.add(offer);
					//allOffers.put(res.getDate("start_of_period").toLocalDate(), new HashMap<>());
					//allOffers.get(res.getDate("start_of_period").toLocalDate()).put(place.getAddress(), offer);
				}
			} catch (SQLException e) {
			}
		
	}
	
	public static synchronized OfferDAO getInstance(){
		return instance;
	}

	public static synchronized void add(Offer offer) {
		//allOffers.put(offer.getStartOfPeriod(), new HashMap<>());
		//allOffers.get(offer.getStartOfPeriod()).put(offer.getPlace().getAddress(), offer);
		allOffers1.add(offer);
	}

	public synchronized void addToDB(Offer offer, LocalDateTime date1, LocalDateTime date2) throws SQLException {
		PreparedStatement st2;
		System.out.println(Timestamp.valueOf(date1));
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
	
	public synchronized ArrayList<Offer> search(String address, LocalDate start, LocalDate end, int guests){
		
		ArrayList<Offer> offersForYou = new ArrayList<>();
		
		
		
		
		/*for (Iterator<Entry<LocalDate, HashMap<String, Offer>>> it = allOffers.entrySet().iterator(); it.hasNext();){
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
		}*/
		
		for(Offer f : allOffers1){
			System.out.println(f.getPlace().getAddressObject().getRegion().equals(address));
			System.out.println(!f.getStartOfPeriod().isAfter(start));
			System.out.println(!f.getEndOfPeriod().isBefore(end));
			
			if(f.getPlace().getAddressObject().getRegion().equals(address)
					&&!f.getStartOfPeriod().isAfter(start)
					&&!f.getEndOfPeriod().isBefore(end)){
				offersForYou.add(f);
			}
		}
		
		return offersForYou;
	}
	
	public static void offersSelect(){
		String sql = "SELECT offer_id, start_of_period, end_of_period, fk_place_id FROM offers;";
		PreparedStatement st;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
		}
		catch (SQLException e) {
			
		}
	}
	

}
