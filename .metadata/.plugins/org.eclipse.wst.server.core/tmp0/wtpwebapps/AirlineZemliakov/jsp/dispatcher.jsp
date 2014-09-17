<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Dispatcher</title>
</head>
<body>
	${user}
	<form name="logoutForm" method="POST" action="controller">
		<input type="hidden" name="command" value="logout" /> <input
			type="submit" value="Log out" />
	</form>
	<hr />
	<form name="manageStaffForm" method="POST" action="controller">
		<input type="hidden" name="command" value="manageStaff" /> <input
			type="submit" value="Manage Staff" />
	</form>
	<hr />
	<br>
	<table border=1>
		<tr>
			<td>Flight#</td>
			<td>To</td>
			<td>From</td>
			<td>Date</td>
			<td>Action</td>
		</tr>
		<c:forEach var="flight" items="${flights}">
			<tr>
				<td><c:out value="${ flight.id }" /></td>
				<td><c:out value="${ flight.to }" /></td>
				<td><c:out value="${ flight.from }" /></td>
				<td><c:out value="${ flight.date }" /></td>
				<td><form name="teamForm" method="POST" action="controller">
						<input type="hidden" name="command" value="team" /> <input
							type="submit" value="Team" /></form></td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>