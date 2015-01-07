<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="dt" class="hw5.servlet.DisplayCourse" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Course</title>
</head>
<body>
	<c:if test="${empty sessionScope.user}">
		<a href="Login">Login</a>  OR  <a href="Registration">Registration</a>
	</c:if>
	<c:if test="${!empty sessionScope.user}">
	<a href="Logout">Logout</a>
	</c:if>
	<br>
	<c:if test="${user ne null}"><a href="SavedPlans">Saved CoursePlans</a>	</c:if>	
	<h2>Display Course</h2><H4 align='center'><a href='CoursePlanner'>Course Planner</a></H4>
	<table border='1'>
		<tr>
			<th>Code</th>
			<th>Title</th>
			<th>Prerequisite</th>
			<th>Operation</th>
		</tr>
		<c:forEach items="${datalist}" var="datalist" varStatus="status">
			<tr>
				<td>${datalist.courseCode}</td>
				<td>${datalist.courseName}</td>
				<td>${datalist.prereq}</td>
				<td><a href="EditCourse?id=${status.index+1}">Edit</a></td>
			</tr>
		</c:forEach>
	</table>
	<p>
		<a href='AddCourse'>Add Course</a>
	</p>
</body>
</html>