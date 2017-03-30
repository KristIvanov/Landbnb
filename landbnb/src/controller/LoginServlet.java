package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.routines.EmailValidator;

import model.dao.UserDAO;
import model.users.InvalidEmailException;
import model.users.InvalidPasswordException;
import model.users.NotMatchingPasswordsException;
import model.users.User;

@WebServlet("/login")
public class LoginServlet  extends HttpServlet{
	
	public static String fileName = "index.jsp";
	public static String errorMsg = "";
	
	public static String getErrorMsg() {
		return errorMsg;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email;
		String password;
		
		
		email = req.getParameter("email");
		password = req.getParameter("pass");
		
		
		try {
			this.validateData(password, email);
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(5*60);
			User u = UserDAO.getInstance().getAllUsers().get(email);
			session.setAttribute("user", u);
			session.setAttribute("logged", true);
		} catch (InvalidEmailException | InvalidPasswordException | SQLException | NotMatchingPasswordsException e) {
			
		}
		finally {
			resp.sendRedirect(fileName);
			errorMsg = " ";
		}
		
	}
	
	private  void validateData(String inputPass, String email)
			throws InvalidEmailException, InvalidPasswordException, SQLException, NotMatchingPasswordsException{
		
		if(email.isEmpty()||!EmailValidator.getInstance().isValid(email)){
			errorMsg = "Invalid email address";
			fileName = "logIn.jsp";
			throw new InvalidEmailException();
		}
		if(!UserDAO.getInstance().getAllUsers().containsKey(email)){
			errorMsg = "Wrong email address";
			fileName = "logIn.jsp";
		}
		User user = UserDAO.getInstance().getAllUsers().get(email);
		
		if(!UserDAO.getInstance().hashPassword(inputPass).equals(user.getPassword())){
			errorMsg = "Wrong password for email: " + email;
			fileName = "logIn.jsp";
			throw new NotMatchingPasswordsException();
		}
	}
}
