<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="controller.ChangeMailServlet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h5 id = "error"><% out.println(ChangeMailServlet.getErrorMsg());  %></h5>
<form action="changeMail" method="post">
Old email: <input type="text" placeholder="enter email" name="oldMail"></br>
New email: <input type="text" placeholder="enter email" name="newMail"></br>
Password: <input type="password" placeholder="enter password" name="password"></br>
Confirm password: <input type="password" placeholder="re-enter password" name="reenteredPass"></br>

<input type="submit" value = "Update"></br>
</form>
</body>
</html>