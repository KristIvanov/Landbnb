package users;

public class InvalidPhoneNumberException extends Exception{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Pleae, enter a valid phone number";
	}
}
