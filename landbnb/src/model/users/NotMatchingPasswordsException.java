package model.users;

public class NotMatchingPasswordsException extends Exception{

	@Override
	public String getMessage() {
		return "Passwords not matching!";
	}
}
