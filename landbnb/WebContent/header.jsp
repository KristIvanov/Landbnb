<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="controller.LoginServlet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%! boolean logged;
	String button1 = "Register";
	String button2 = "Log in";
	String button3 = "Become a Host";
	String link1 = "register.jsp";
	String link2 = "logIn.jsp";
	String link3 = "logIn.jsp";%>
	<div align = "right">
	<% if(session.getAttribute("logged")!= null){
		logged = (Boolean) session.getAttribute("logged");
		if(logged){
			button1 = "View profile";
			button2 = "Log out";
			button3 = "Become a Host";
			link1 = "profile.jsp";
			link2 = "logout.jsp";
			link3 = "addAPlace.jsp";
		}
	}
	%>
		<%= session.getAttribute("mail") %>
		<a href = "<%= link1%>" ><%= button1%></a>
		<a href = "<%= link2%>" ><%= button2%></a>
		<a href = "<%= link3%>" ><%= button3%></a>
	</div>
	<font face="Verdana" size="7">LandBNB</font><br>
	<hr>
</body>
</html>