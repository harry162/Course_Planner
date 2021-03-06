<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EditCourse.jsp</title>
</head>
<body>
	<a href="Logout">Logout</a>

	<h3>Edit Course</h3>
	<form action='EditCourse' method='post'>
		<table border=1>
			<tr>
				<td>Code :</td>
				<td><input type='text' name='courseCode'
					value="${entry.courseCode} " /></td>
			</tr>
			<tr>
				<td>Title :</td>
				<td><input type='text' name='courseName'
					value="${entry.courseName}" /></td>
			</tr>
			<tr>
				<td>Prerequisite(s) :</td>
				<td><UL>
						<c:forEach items="${datalist}" var="dt" varStatus="status">
							<c:choose>
								<c:when test="${strprereq.contains(dt.courseCode)}">
									<li>${dt.courseCode}<input type='checkbox' name='prereq'
										value='${dt.courseCode}' checked></li>
								</c:when>
								<c:otherwise>
									<li>${dt.courseCode}<input type='checkbox' name='prereq'
										value='${dt.courseCode}'></li>
								
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</UL></td>
			</tr>
		</table>
		<input type="hidden" name="id" value="${id}" />
		<input type='submit' name='edit' value='Edit Course' />
	</form>
</body>
</html>