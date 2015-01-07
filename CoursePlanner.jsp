<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Planner</title>
</head>
<body>
	<h4>Course Planner</h4>
	<br>
	<p>Please select the courses you have already taken:</p>
	<form action="CoursePlanner" method="post" >
	<table border='1'>
		<tr>
			<th></th>
			<th>Code</th>
			<th>Title</th>
			<th>Prerequisite</th>

		</tr>
		<c:forEach items="${datalist}" var="datalist" varStatus="status">
			<tr>
				<td><input type='checkbox' name='prereq'
					value='${datalist.courseCode}'></td>
				<td>${datalist.courseCode}</td>
				<td>${datalist.courseName}</td>
				<td>${datalist.prereq}</td>
			</tr>
		</c:forEach>
	</table>
	<input type='submit' name='Next' value='Next' />
	</form>
</body>
</html>