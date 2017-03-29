<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="controller.AddAPlaceServlet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LandBNB - Add a place</title>
</head>
<body>
<!--  -->
<%@ page import="controller.AddAPlaceServlet" %>

<h5 id = "error"><% out.println(AddAPlaceServlet.getErrorMsg());  %></h5>
<form action="addaplace" method="post">
<h3>Please enter short name</h3>
Name: <input type="text" placeholder="enter name" name="name"></br>
<h3>Please enter Address</h3>
Region: <input type="text" placeholder="enter first name" name="region">
City: <input type="text" placeholder="enter city" name="city">
Street: <input type="text" placeholder="enter street" name="street">
Number: <input type="text" placeholder="enter number" name="number">
Apartment: <input type="text" placeholder="enter apartment" name="apartment"> 
<h3>Please enter place details</h3></br>
Max guests: <select name="maxGuests">
<% for(int i=1; i<51; i++){ %>
		<option value="<%out.print(i);%>"><%out.print(i);%></option>
	<%} %>
</select>
Rooms: <select name="rooms">
<% for(int i=1; i<13; i++){ %>
		<option value="<%out.print(i);%>"><%out.print(i);%></option>
	<%} %>
</select>
Beds: <select name="beds">
<% for(int i=1; i<25; i++){ %>
			<option value="<%out.print(i);%>"><%out.print(i);%></option>
		<%} %>
</select>
Price per night: <input type="text" placeholder="enter price" name="pricePerNight"></br>
Description: </br><textarea name="description" style="width:250px;height:150px;"></textarea>
</br>
<h3>Please specify dates available</h3></br>
Available from: <input type="date" placeholder="enter number" name="startDate">
to: <input type="date" placeholder="enter number" name="endDate">
<input type="submit" value = "AddAPlace"></br>
</form>
<a href="index.html">Back to home page.</a>
</body>
</html>