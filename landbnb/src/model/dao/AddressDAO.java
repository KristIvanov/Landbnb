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
import model.users.User;

public class AddressDAO {
	
	private static AddressDAO instance = new AddressDAO();
	private static HashMap<Long, Address> allAddresses;//key id
	private static ArrayList<Address> addresslist;
	private AddressDAO() {
		System.out.println("hoho");
			addresslist = new ArrayList<>();
			allAddresses = new HashMap<>();
			if(allAddresses.isEmpty()){
				
				String sql = "SELECT address_id, region, city, street, number, apartment FROM addresses;";
				PreparedStatement st;
				
				try {
					st = DBManager.getInstance().getConnection().prepareStatement(sql);
					
					ResultSet res = st.executeQuery();
					while(res.next()){
						System.out.println("yo");
						Address address = new Address(res.getString("region"), res.getString("city"), res.getString("street"), res.getInt("number"), res.getInt("apartment"));
						address.setId(res.getLong("address_id"));
						allAddresses.put(address.getId(), address);
						addresslist.add(address);
					}
				} catch (SQLException e) {
					System.out.println("loading all addresses failed");
				}
				
			}

			
		
	}

	public synchronized void addToDB(Address address) throws SQLException {
		PreparedStatement st;
		String sql = "INSERT IGNORE INTO addresses (region, city, street, number, apartment) VALUES (?, ?, ?, ?, ?)";
		st = DBManager.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		st.setString(1, address.getRegion().toString());
		st.setString(2, address.getCity());
		st.setString(3, address.getStreet());
		st.setInt(4, address.getNumber());
		st.setInt(5, address.getApartment());
		st.executeUpdate();
		ResultSet rSet = st.getGeneratedKeys();
		System.out.println("dali?");
		while(rSet.next()){
			address.setId(rSet.getLong(1));;
		}
		
	}
	
	public static synchronized AddressDAO getInstance(){
		return instance;
	}
	
	public synchronized static Map<Long, Address> getAllAddresses() {
		return Collections.unmodifiableMap(allAddresses);
	}
	
	public List<Address> getAddressList(){
		return Collections.unmodifiableList(addresslist);
	}
	
	

}
