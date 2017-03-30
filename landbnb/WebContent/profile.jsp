<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="controller.ProfileServlet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
</head>
<body>
<!--  -->

<h3>Hello</h3>
<h5 id = "error"><% out.println(ProfileServlet.getMsg());  %></h5>
<a href="changeMail.html">Change Your email</a>
<br>
<a href="changePass.html">Change your password</a>
<br>
<form action="logout" method="get">
<input type="submit" value="Log Out">
</form>

</body>
</html>