<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
		<h3>Login</h3>
		<form action='Login' method='post'>
		<TABLE><tr><td>
		UserName:</td><td> <input type='text' name='username' /> </td></tr>
	<TR><TD> Password: </td><td> <input type='password' name='password' /> </td></TR>
	
		</TABLE>
		<input type='submit' name='login' value='Login' /> <br />
		</form>
		<br><br>
		<p>New User? -- <a href = 'Registration'>Register</a></p>
		<br><br><a href ='DisplayCourse'>List of Courses</a>


</body>
</html>