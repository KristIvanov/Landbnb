package model.users;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	private static final String PHONE_NUMBER_PATTERN = "^(08[987][0-9]{7})$";
	private static final String PASSWORD_PATTERN = "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])\\S{8,}\\z";
	private static final String NAME_VALIDATOR = "^[A-Z]{1}[a-z]{3,}$";
	private static Pattern pattern;
	private static Matcher matcher;
	
	public static boolean 	isPhoneNumber(final String phoneNumber){
		pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
		matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}
	public static boolean 	isValidPassword(final String pass){
		pattern = Pattern.compile(PASSWORD_PATTERN);
		matcher = pattern.matcher(pass);
		return matcher.matches();
	}
	
	public static boolean isValidName(final String name){
		pattern = Pattern.compile(NAME_VALIDATOR);
		matcher = pattern.matcher(name);
		return matcher.matches();
	}
}
