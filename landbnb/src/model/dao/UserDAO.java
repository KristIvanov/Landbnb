package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import model.users.User;

public class UserDAO {
	
	private static UserDAO instance = new UserDAO();
	private static HashMap<String, User> users ;//key email
	private static ArrayList<User> userslist;
	
	private UserDAO() {
		users = new HashMap<>();
		userslist = new ArrayList();
		if(users.isEmpty()){
			String sql = "SELECT user_id, email_address, password, first_name, last_name, phone, rating  FROM users;";
			PreparedStatement st;
			try {
				st = DBManager.getInstance().getConnection().prepareStatement(sql);
				ResultSet res = st.executeQuery();
				while(res.next()){
					User u = new User(res.getString("first_name"), res.getString("last_name"), res.getString("email_address"), res.getString("phone"), res.getString("password"), res.getDouble("rating"));
					u.setId(res.getLong("user_id"));
					users.put(u.getMail(), u);
					userslist.add(u);
					} 
				}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			}
		
	}
	
	public static synchronized UserDAO getInstance(){
		if (instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	public HashMap<String, User> getAllUsers() throws SQLException{
		return users;
	}
	
	public synchronized void addUser(User user) throws SQLException{ 
		String sql = "INSERT INTO users (email_address, password, first_name, last_name, phone, rating) values (?, ?, ?, ?, ?, 0.0)";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, user.getMail());
		st.setString(2, user.getPassword());
		st.setString(3, user.getFirstName());//TODO hash pass
		st.setString(4, user.getFamilyName());//TODO hash pass
		st.setString(5, user.getPhoneNumber());//TODO hash pass
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		long id = res.getLong(1);
		user.setId(id);
		users.put(user.getMail(), user);
		userslist.add(user);
	}
	
	public String hashPassword(String password){
		return "" + (password.hashCode()*31+203)*19;
	}

	public synchronized void removeUser(User user) throws SQLException {
		String sql = "DELETE FROM users WHERE email_address = " + user.getMail();
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.executeUpdate();
	}
	
	public List<User> getUseresList(){
		return Collections.unmodifiableList(userslist);
	}
	

}
