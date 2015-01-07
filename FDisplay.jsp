<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Saved Course Plan</title>
</head>
<body>
	<h4>Saved Course Plan by ${user} on ${date}</h4>
	<br>
	<c:forEach items="${qlist}" var="qlist" varStatus="status">
		<p>
			<i><b><u>${qlist}</u></b></i>
		</p>
		<TABLE border=1 width=70%>
		<tr>
			<td width=10%>Code</td>
			<td width=35%>Course Tile</td>
			<td width=25%>PreRequisite</td>
		</tr>


			<c:forEach items="${finallist}" var="finallist" varStatus="status">

				<c:if test="${finallist.quarter eq qlist }">
					<tr>
						<td width=10%>${finallist.code}</td>
						<td width=35%>${finallist.name}</td>
						<td width=25%>${finallist.prereq}</td>
					</tr>
				</c:if>

			</c:forEach>

		</TABLE>
	</c:forEach>
	<a href="DisplayCourse">Back to Home</a>
</body>
</html>