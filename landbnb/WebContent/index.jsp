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

	<jsp:include page="header.jsp" />
	<br>
	<br>
	<h2>Search</h2>

	<form action="search" method = "get">
		Region <select name = "region">
		<% for(int i=0; i<Address.regions.length; i++){%>
			<option value="<%out.print(Address.regions[i]);%>"><%out.print(Address.regions[i]);%></option>
		<% }%>
		</select>
		Check in <input type="date" placeholder="Check in" name="startDate" required>
		Check out <input type="date" placeholder="Check out" name="endDate" required>
		Guests <select name = "guests">
		<% for(int i=1; i<51; i++){ %>
			<option value="<%out.print(i);%>"><%out.print(i);%></option>
		<%} %>
		</select>
		<input type="submit" value="Search">
	</form>
</body>
</html>