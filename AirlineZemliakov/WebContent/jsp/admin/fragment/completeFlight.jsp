
<%@ include file="/jsp/include/head.jspf"%>
<div class="box1" style="margin-top: 220px;">
	<div class="notes">
		<fmt:message key="flight.flights" />
	</div>
	<div>
		<table class="flight_table">
			<tr class="name">
				<td><fmt:message key="flight.flight" /></td>
				<td><fmt:message key="flight.to" /></td>
				<td><fmt:message key="flight.from" /></td>
				<td><fmt:message key="flight.date" /></td>
				<td><fmt:message key="flight.action" /></td>
			</tr>
			<c:forEach var="flight" items="${userFlights}">
				<tr>
					<td><c:out value="${flight.id}" /></td>
					<td><c:out value="${flight.to}" /></td>
					<td><c:out value="${flight.from}" /></td>
					<td><c:out value="${flight.date}" /></td>
					<td>
						<form style="margin-bottom: 0;" name="teamForm" method="POST"
							action="controller">
							<input type="hidden" name="command" value="completeFlight" /> <input
								type="hidden" name="flightId" value="${flight.id}" /> <input
								class="button1" type="submit"
								value=<fmt:message key="flight.complete" /> />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<table>
			<tr>
				<c:if test="${isPreviousFlightsPage}">
					<td><form method="POST" action="controller">
							<input type="hidden" name="command" value="previousFlight" /> <input
								class="button1" type="submit"
								value="<fmt:message key="flight.previous" />" />
						</form></td>
				</c:if>
				<c:if test="${isNextFlightsPage}">
					<td><form method="POST" action="controller">
							<input type="hidden" name="command" value="nextFlight" /> <input
								class="button1" type="submit"
								value="<fmt:message key="flight.next" />" />
						</form></td>
				</c:if>
			</tr>
		</table>
		<span> <c:if test="${not empty flightCompleted}">
				<fmt:message key="${flightCompleted}" />
			</c:if> <c:if test="${not empty flightNotCompleted}">
				<fmt:message key="${flightNotCompleted}" />
			</c:if> <c:if test="${not empty noMore}">
				<fmt:message key="${noMore}" />
			</c:if>
		</span>
	</div>
</div>