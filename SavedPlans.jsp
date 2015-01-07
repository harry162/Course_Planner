<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Saved Plans</title>
</head>
<body>
<h1>Course Plans saved by ${user}</h1>
<br>
<c:forEach items="${allsaved}" var="row" varStatus="status">
			<a href="FDisplay?user=${user}&date=${row}">Saved at ${row}</a>
			<br>
					</c:forEach>
					<br>
					<a href="DisplayCourse">Back to Home</a>
</body>
</html>