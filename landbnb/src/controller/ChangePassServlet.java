package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.UserDAO;
import model.users.InvalidPasswordException;
import model.users.NotMatchingPasswordsException;
import model.users.User;
import model.users.Validator;

/**
 * Servlet implementation class ChangePassServlet
 */
@WebServlet("/changePass")
public class ChangePassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String fileName = "changePass.html";
	private static String errorMsg = " ";

	public static String getErrorMsg() {
		return ChangePassServlet.errorMsg;
	}
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession ses = request.getSession();
		if(ses.getAttribute("logged")!= null){
			boolean logged = (Boolean) request.getSession().getAttribute("logged");
			if(logged){
				User user = (User) request.getSession().getAttribute("user");
				String oldpassword = request.getParameter("oldPassword");
				String password = request.getParameter("password");
				String reenteredPass = request.getParameter("reenteredPass");
				try {
					this.validateData(oldpassword, password, reenteredPass, user);
				} catch (InvalidPasswordException | NotMatchingPasswordsException | SQLException e) {
					response.sendRedirect("changePass.html");
				}
			}
			else{
				response.sendRedirect("login.jsp");
			}
		}
		else{
			response.sendRedirect("login.jsp");
		}
		
	}
	
	private  void validateData(String oldPass, String pass, String reenteredPass, User user)
			throws InvalidPasswordException, NotMatchingPasswordsException, SQLException{
		
		
		if(!Validator.isValidPassword(pass)){
			errorMsg = "Password must conain atleast 8 symobls (atleast one Upper case, lower case and digit)";
			fileName = "changePass.html";
			throw new InvalidPasswordException();
		}
		if(!pass.equals(reenteredPass)){
			errorMsg = "Passwords not matching";
			fileName = "changePass.html";
			throw new NotMatchingPasswordsException();
		}
		
		if(!UserDAO.getInstance().hashPassword(oldPass).equals(user.getPassword())){
			errorMsg = "Wrong password for user: " + user.getName();
			fileName = "changePass.html";
			throw new NotMatchingPasswordsException();
		}
		
	}

}
