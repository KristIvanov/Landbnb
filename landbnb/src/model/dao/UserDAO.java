package model.dao;

public class UserDAO {
	
	private static UserDAO instance;
	
	private UserDAO() {
		
	}
	
	public static synchronized UserDAO getInstance(){
		if (instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	

}
