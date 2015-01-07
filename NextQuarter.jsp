<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Next Quarter</title>
</head>
<body>

	<p>You may take the following subjects in <u><b>${showqtr}</b></u></p>
	<form action="NextQuarter" method="post">
		<table border="1">
			<tr>
				<th></th>
				<th>Code</th>
				<th>Title</th>
				<th>PreRequisites</th>
			</tr>
			<c:forEach items="${showsubcode}" var="showsubcode"
				varStatus="status">
				<c:if test="${!doneobj.contains(showsubcode[status.index])}">
				<tr>
					<td><input type='checkbox' name='prereq'
						value='${showsubcode}'></td>
					<td>${showsubcode}</td>
					<td>${showsubname[status.index]}</td>
					<td>${showsubpre[status.index]}</td>
				</tr>
              </c:if>
			</c:forEach>
		</table>
		<input type='submit' name='button' value='Next' /> 
		<input type='submit' name='button' value='Finish' />
	</form>

</body>
</html>