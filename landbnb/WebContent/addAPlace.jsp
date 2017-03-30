<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="controller.LoginServlet,model.places.Address" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LandBNB - Add a place</title>
</head>
<body>
<!--  -->
<%@ page import="controller.AddAPlaceServlet" %>
<jsp:include page="header.jsp" />
<br>
<br>
<h2>Add a place</h3>
<h5 id = "error"><% out.println(AddAPlaceServlet.getErrorMsg());  %></h5>
<form action="addaplace" method="post">
<h3>Please enter short name</h3>
Name: <input type="text" placeholder="enter name" name="name" required><br>
<h3>Please enter Address</h3>
Region <select name = "region">
		<% for(int i=0; i<Address.regions.length; i++){%>
			<option value="<%out.print(Address.regions[i]);%>"><%out.print(Address.regions[i]);%></option>
		<% }%>
		</select>
City: <input type="text" placeholder="enter city" name="city" required>
Street: <input type="text" placeholder="enter street" name="street" required>
Number: <input type="text" placeholder="enter number" name="number" required>
Apartment: <input type="text" placeholder="enter apartment" name="apartment" required> 
<h3>Please enter place details</h3><br>
Max guests: <select name="maxGuests" >
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
Price per night: <input type="text" placeholder="enter price" name="pricePerNight" required></br>
Description: </br><textarea name="description" style="width:250px;height:150px; required"></textarea>
</br>
<h3>Please specify dates available</h3></br>
Available from: <input type="date" placeholder="enter number" name="startDate" required>
to: <input type="date" placeholder="enter number" name="endDate" required>
<input type="submit" value = "AddAPlace"></br>
</form>
<a href="index.jsp">Back to home page.</a>
</body>
</html>