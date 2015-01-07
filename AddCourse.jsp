<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Course</title>
</head>
<body>
		<a href="Logout">Logout</a>

	<h3>Add Course</h3>
	<form action='AddCourse' method='post'>
		<table border=1>
			<tr>
				<td>Code :</td>
				<td><input type='text' name='courseCode' /></td>
			</tr>
			<tr>
				<td>Title :</td>
				<td><input type='text' name='courseName' /></td>
			</tr>
			<tr>
				<td>Prerequisite(s) :</td><td><UL>
				<c:forEach items="${datalist}" var="datalist" >
				<li>${datalist.courseCode}<input type='checkbox' name='prereq' value='${datalist.courseCode}'></li>		
				</c:forEach>
				</UL></td></tr>
				</table>
				<input type='submit' name='add' value='Add Course' />
				</form>
</body>
</html>