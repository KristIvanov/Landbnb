package model.users;

public class UserAlreadyExistsException extends Exception{

	@Override
	public String getMessage() {
		return "This user alrady exists";
	}
}
