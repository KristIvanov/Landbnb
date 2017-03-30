<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="controller.SearchServlet,model.Offer" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LandBNB - Search results</title>
</head>
<body>
	<jsp:include page="header.jsp">
	<br>
	<br>
	<h2>Results</h2>
	<% for(Offer f : SearchServlet.getOffers()){ %>
			<form method = "get" action = "offer">
			snimchica
			<h2>
			<%out.print(f.getPlace().getName());%><br></h2>
			<h3>
			Host: <%out.print(f.getHost().toString()); %><br> 
			Address: <%out.print(f.getPlace().getAddress().toString()); %><br>
			Max Guests: <%out.print(f.getPlace().getMaxGuests()); %><br>
			Rooms: <%out.print(f.getPlace().getRooms()); %><br>
			Beds: <%out.print(f.getPlace().getBeds()); %><br>
			Rating :<%out.print(f.getPlace().getRating()); %><br>
			Price per Night: <%out.print(f.getPlace().getPricePerNight()); %><br></h3>
			<input type = "hidden" value = "<%out.print(f.getId());%>">
			<input type = "submit" value = "View more" >
			</form>
		<%} %>>
</body>
</html>