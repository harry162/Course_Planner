<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<h2>Registration Form</h2>

	<form action='Registration' method='post'>


		<c:forEach items="${error}" var="err" varStatus="status">
			<font color='red'>${err}</font>
			<br />
		</c:forEach>

		<table border=1>


			<tr>
				<td>UserName :</td>
				<td><input name='username' type='text' /></td>
			</tr>

			<tr>
				<td>Password :</td>
				<td><input name='password' type='password' /></td>
			</tr>

			<tr>
				<td>Re-Type Password :</td>
				<td><input name='repassword' type='password' /></td>
			</tr>

			<tr>
				<td>First Name(Optional) :</td>
				<td><input name='firstname' type='text' /></td>
			</tr>
			<tr>
				<td>Last Name(Optional) :</td>
				<td><input name='lastname' type='text' /></td>
			</tr>
		</table>

		<br>
		<input type='submit' name='Register' value='Register' /> <br />
	</form>


</body>
</html>