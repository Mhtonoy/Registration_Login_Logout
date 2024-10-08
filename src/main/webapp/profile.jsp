<%@page import="in.mh.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile Page</title>
</head>
<body>
	<h1>Profile Page</h1>
	
	<%
		User user = (User) session.getAttribute("session_user");
	%>
	
	<h2>Welcome</h2>
	
	<h3>Name	: <%= user.getName() %></h3>
	<h3>Email	: <%= user.getEmail() %></h3>
	<h3>Gender	: <%= user.getGender() %></h3>
	
	<a href="logout">Logout</a>
	
</body>
</html>