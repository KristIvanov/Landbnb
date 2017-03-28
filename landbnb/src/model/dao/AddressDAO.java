package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.places.Address;

public class AddressDAO {
	
	private static AddressDAO instance;
	private static HashMap<Long, Address> allAddresses;//key id
	
	private AddressDAO() {}
	
	public static synchronized AddressDAO getInstance(){
		if (instance == null){
			instance = new AddressDAO();
			allAddresses = new HashMap<>();
			if(allAddresses.isEmpty()){
				String sql = "SELECT address_id, region, city, street, number, apartment FROM addresses;";
				PreparedStatement st;
				try {
					st = DBManager.getInstance().getConnection().prepareStatement(sql);
					ResultSet res = st.executeQuery();
					while(res.next()){
						Address address = new Address(res.getString("region"), res.getString("city"), res.getString("street"), res.getString("number"), res.getInt("apartment"));
						address.setId(res.getLong("id"));
						allAddresses.put(address.getId(), address);
					}
				} catch (SQLException e) {
					System.out.println("loading all addresses failed");
				}
				
			}

			
		}
		return instance;
	}

	public synchronized void addToDB(Address address) throws SQLException {
		PreparedStatement st;
		String sql = "INSERT IGNORE INTO addresses (region, city, street, number, apartment) VALUES (?, ?, ?, ?, ?)";
		st = DBManager.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		st.setString(1, address.getRegion().toString());
		st.setString(2, address.getCity());
		st.setString(3, address.getStreet());
		st.setString(4, address.getNumber());
		st.setInt(5, address.getApartment());
		st.executeUpdate();
		ResultSet rSet = st.getGeneratedKeys();
		while(rSet.next()){
			address.setId(rSet.getLong(1));;
		}
		
	}
	
	public synchronized static Map<Long, Address> getAllAddresses() {
		return Collections.unmodifiableMap(allAddresses);
	}
	
	

}
