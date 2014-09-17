<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Administrator</title>
</head>
<body>
	${user}
	<form name="logoutForm" method="POST" action="controller">
		<input type="hidden" name="command" value="logout" /> <input
			type="submit" value="Log out" />
	</form>
	<hr />
	<form name="manageUsersForm" method="POST" action="controller">
		<input type="hidden" name="command" value="manageUsers" /> <input
			type="submit" value="Manage Users" />
	</form>
	<hr />
	<form name="addFlightForm" method="POST" action="controller">
		<input type="hidden" name="command" value="addFlight" /> 
		Flight:
		<input type="text" name="flight" value="" /> <br />
		To:
		<input type="text" name="to" value="" /> <br />
		From:
		<input type="text" name="from" value="" /> <br />
		Date: 
		<input type="text" name="date" value="" /> <br />
		Plane:
		<input type="text" name="plane" value="" /> <br />
		${flightWasAdded} <br /> 		
		<input type="submit" value="Add Flight" />
	</form>
	<hr />
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
				<td><form name="completeForm" method="POST" action="controller">
						<input type="hidden" name="command" value="complete" /> <input
							type="submit" value="Complete flight" /></form></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>