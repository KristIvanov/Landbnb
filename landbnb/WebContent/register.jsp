<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="controller.RegisterServlet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
<jsp:include page="header.jsp" />
<br>
<br>

<h2>Please register a new account</h2>
<h5 id = "error"><% out.println(RegisterServlet.getErrorMsg());  %></h5>
<form action="register" method="post">
Email: <input type="text" placeholder="enter email" name="email"></br>
First Name: <input type="text" placeholder="enter first name" name="firstName"></br>
Family Name: <input type="text" placeholder="enter family name" name="familyName"></br>
Password: <input type="password" placeholder="enter password" name="password"></br>
Confirm password: <input type="password" placeholder="re-enter password" name="reenteredPass"></br>
Phone Number: <input type="text" placeholder="enter phone number" name="phoneNumber"></br>
<input type="submit" value = "Register"></br>
</form>
<a href="logIn.jsp">Already a registered user? Login here.</a>
</body>
</html>