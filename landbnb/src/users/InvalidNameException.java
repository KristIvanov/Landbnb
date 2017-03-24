package users;

public class InvalidNameException extends Exception{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Please, enter a valid name!";
	}
}
