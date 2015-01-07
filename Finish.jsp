<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h4>Here is your course plan.</h4>
	<c:forEach items="${qtrshow}" var="qtrshow" varStatus="status">
		<p>
			<i><b><u>${qtrshow}</u></b></i>
		</p>
		<table border=1 width=70%>
				<tr>
				<td width=10%>Code</td>
				<td width=35%>Course Tile</td>
				<td width=25%>PreRequisite</td>
	</tr>
	</table>
		<c:forEach items="${last}" var="last" varStatus="count">
			<table border=1 width=70%>
		
			<c:if test="${qtrshow.equals(last.quarter)}">
					
					<tr>
						<td width=10%>${last.code}</td>
						<td width=35%>${last.name}</td>
						<td width=25%>${last.prereq}</td>
					</tr>
				</c:if>
			</table>
		</c:forEach>
	</c:forEach>
	<br>
	<a href="DisplayCourse">Done</a>
	<form action='finish' method='post'>
	<c:if test="${user ne null}">
	<input type='submit' name='Save' value='Save CoursePlan' />
	</c:if>
	</form>
</body>
</html>