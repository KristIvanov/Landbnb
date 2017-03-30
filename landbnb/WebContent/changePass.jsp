<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="controller.ChangePassServlet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h5 id = "error"><% out.println(ChangePassServlet.getErrorMsg());  %></h5>
<form action="changePass" method="post">
Old password: <input type="password" placeholder="enter password" name="oldPassword"></br>
New password: <input type="password" placeholder="enter password" name="password"></br>
Confirm new password: <input type="password" placeholder="re-enter password" name="reenteredPass"></br>

<input type="submit" value = "Update"></br>
</form>
</body>
</html>