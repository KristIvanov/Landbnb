<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="controller.SearchServlet,java.util.ArrayList,model.Offer" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LandBNB - Search results</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<%! ArrayList<Offer> offers = SearchServlet.offersForYou; %>
	<br>
	<br>
	<h2>Results</h2>
	<table border = "1">
	<% if (!offers.isEmpty()){%>
		<%for(Offer f : offers){ %>
			
			
			<tr>
				<td>
			<%out.print(f.getPlace().getName());%>
			Host: <%out.print(f.getHost().toString()); %> 
			Address: <%out.print(f.getPlace().getAddress().toString()); %>
			Max Guests: <%out.print(f.getPlace().getMaxGuests()); %>
			Rooms: <%out.print(f.getPlace().getRooms()); %>
			Beds: <%out.print(f.getPlace().getBeds()); %>
			Rating :<%out.print(f.getPlace().getRating()); %>
			Price per Night: <%out.print(f.getPlace().getPricePerNight()); %>
					<form method = "get" action = "book">
						<input type = "hidden" value = "<%out.print(f.getStartOfPeriod());%>" name = "offerId">
						<input type = "submit" value = "Book" >
					</form>
				</td>
			<tr>
			
		<%} 
		}%>
	</table>
</body>
</html>