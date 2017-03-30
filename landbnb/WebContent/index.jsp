<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.places.Address" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LandBNB</title>
</head>
<body>
	<H4 align = "right">
		<a href="logIn.jsp">Log in</a>
		<a href="register.jsp">Register</a>
		<a href="addAPlace.jsp">Become a Host</a>
		<a href="profile.jsp">Profile</a>
	</H4>
	<font face="Verdana" size="7">LandBNB</font><br />
	<form action="search" method = "get">
		Region <select name = "region">
		<% for(int i=0; i<Address.regions.length; i++){%>
			<option value="<%out.print(Address.regions[i]);%>"><%out.print(Address.regions[i]);%></option>
		<% }%>
		</select></br>
		Check in <input type="date" placeholder="Check in" name="startDate"></br>
		Check out <input type="date" placeholder="Check out" name="endDate"></br>
		Guests <select name = "guests">
		<% for(int i=1; i<51; i++){ %>
			<option value="<%out.print(i);%>"><%out.print(i);%></option>
		<%} %>
		</select></br>
		<input type="submit" value="Search">
	</form>
</body>
</html>