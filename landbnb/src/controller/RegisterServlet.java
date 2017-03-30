package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;

import model.dao.UserDAO;
import model.users.InvalidEmailException;
import model.users.InvalidNameException;
import model.users.InvalidPasswordException;
import model.users.InvalidPhoneNumberException;
import model.users.NotMatchingPasswordsException;
import model.users.User;
import model.users.UserAlreadyExistsException;
import model.users.Validator;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	private static String fileName = "index.html";
	private static String errorMsg = " ";

	public static String getErrorMsg() {
		return RegisterServlet.errorMsg;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String firstName = req.getParameter("firstName");
		String familyName = req.getParameter("familyName");
		String password = req.getParameter("password");
		String reenteredPass = req.getParameter("reenteredPass");
		String email = req.getParameter("email");
		String phoneNumber = req.getParameter("phoneNumber");
		
		try {
			this.validateData(firstName, familyName, password, reenteredPass, email, phoneNumber);
			User user = new User(firstName, familyName, email, phoneNumber, UserDAO.getInstance().hashPassword(password), 0.0);
			UserDAO.getInstance().addUser(user);
			UserDAO.getInstance().getAllUsers().put(email, user);
		} catch (InvalidEmailException | InvalidNameException | InvalidPasswordException | NotMatchingPasswordsException
				| InvalidPhoneNumberException | UserAlreadyExistsException | SQLException e) {
		}
		
		RequestDispatcher rd = req.getRequestDispatcher(fileName);
		rd.forward(req, resp);
		errorMsg = " ";
	}
	
	private  void validateData(String firstName, String familyName, String pass, String reenteredPass, String email, String phoneNumber)
			throws InvalidEmailException, InvalidNameException, InvalidPasswordException, NotMatchingPasswordsException, InvalidPhoneNumberException, UserAlreadyExistsException, SQLException{
		
		if(email.isEmpty()||!EmailValidator.getInstance().isValid(email)){
			errorMsg = "Invalid email address";
			fileName = "register.jsp";
			throw new InvalidEmailException();
		}
		//if(UserDAO.getInstance().getAllUsers().containsKey(email)){
		//	errorMsg = "Email already in use";
		//	throw new UserAlreadyExistsException();
		//}
		if(!Validator.isValidName(firstName)||!Validator.isValidName(familyName)){
			errorMsg = "Invalid names. Must start with Upper case and contain only lower cases after";
			fileName = "register.jsp";
			throw new InvalidNameException();
		}
		if(!Validator.isValidPassword(pass)){
			errorMsg = "Password must conain atleast 8 symobls (atleast one Upper case, lower case and digit)";
			fileName = "register.jsp";
			throw new InvalidPasswordException();
		}
		if(!pass.equals(reenteredPass)){
			errorMsg = "Passwords not matching";
			fileName = "register.jsp";
			throw new NotMatchingPasswordsException();
		}
		if(!Validator.isPhoneNumber(phoneNumber)){
			errorMsg = "Invalid phone number";
			fileName = "register.jsp";
			throw new InvalidPhoneNumberException();
		}
	}


}
