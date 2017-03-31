package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

/**
 * Servlet implementation class changeMailServlet
 */
@WebServlet("/changeMail")
public class ChangeMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String fileName = "changePass.html";
	private static String errorMsg = " ";

	public static String getErrorMsg() {
		return ChangeMailServlet.errorMsg;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
		HttpSession ses = request.getSession();
		if(ses.getAttribute("logged")!= null){
			boolean logged = (Boolean) request.getSession().getAttribute("logged");
			if(logged){
				User user = (User) request.getSession().getAttribute("user");
				String oldMail = request.getParameter("oldMail");
				String password = request.getParameter("password");
				String reenteredPass = request.getParameter("reenteredPass");
				String email = request.getParameter("newMail");
				try {
					this.validateData(oldMail, email, password, reenteredPass, user);
				} catch (InvalidEmailException | InvalidPasswordException | NotMatchingPasswordsException
						| SQLException e) {
					response.sendRedirect("changeMail.html");
				}
				
			}
			else{
				response.sendRedirect("logIn.jsp");
			}
		}
		else{
			response.sendRedirect("logIn.jsp");
		}
	}
	
	private  void validateData(String oldMail, String email, String pass, String reenteredPass, User user)
			throws InvalidEmailException, InvalidPasswordException, NotMatchingPasswordsException, SQLException{
		
		if(email.isEmpty()||!EmailValidator.getInstance().isValid(email)){
			errorMsg = "Invalid email address";
			fileName = "register.jsp";
			throw new InvalidEmailException();
		}
		//if(UserDAO.getInstance().getAllUsers().containsKey(email)){
		//	errorMsg = "Email already in use";
		//	throw new UserAlreadyExistsException();
		//}
		if(!pass.equals(reenteredPass)){
			errorMsg = "Passwords not matching";
			fileName = "changePass.html";
			throw new NotMatchingPasswordsException();
		}
		
		if(!UserDAO.getInstance().hashPassword(pass).equals(user.getPassword())){
			errorMsg = "Wrong password for user: " + user.getName();
			fileName = "changePass.html";
			throw new NotMatchingPasswordsException();
		}
		
	}

}
