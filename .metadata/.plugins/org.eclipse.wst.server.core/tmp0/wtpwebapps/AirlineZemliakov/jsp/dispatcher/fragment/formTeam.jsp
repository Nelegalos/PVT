<%@ include file="/jsp/include/head.jspf"%>
<div class="box1" style="margin-top: 245px;">
	<div class="notes">
		<fmt:message key="flight.flight" />
	</div>
	<div>
		<table class="flight_table">
			<tr>
				<td><fmt:message key="position.name" /></td>
				<td><fmt:message key="employee.name" /></td>
				<td><fmt:message key="employee.surname" /></td>
			</tr>
			<c:forEach var="employee" items="${crew}">
				<tr>
					<td><c:choose>
							<c:when test="${employee.position eq 'PILOT'}">
								<fmt:message key="PILOT" />
							</c:when>
							<c:when test="${employee.position eq 'NAVIGATOR'}">
								<fmt:message key="NAVIGATOR" />
							</c:when>
							<c:when test="${employee.position eq 'RADIOMAN'}">
								<fmt:message key="RADIOMAN" />
							</c:when>
							<c:when test="${employee.position eq 'STEWARD'}">
								<fmt:message key="STEWARD" />
							</c:when>
						</c:choose></td>
					<td><c:out value="${employee.name}" /></td>
					<td><c:out value="${employee.surname}" /></td>
				</tr>
			</c:forEach>
		</table>
		<div style="width: 100%;">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="formTeam" /> <input
					class="button1" style="margin-left: 410px;" type="submit"
					value=<fmt:message key="option.team" /> />
			</form>
		</div>
		<span><c:if test="${not empty  teamEmpty}">
				<fmt:message key="${teamEmpty}" />
			</c:if></span>
	</div>
</div>