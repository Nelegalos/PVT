<%@ include file="/jsp/include/head.jspf"%>
<div class="notes">
	<fmt:message key="flight.delete" />
</div>
<div class="tab-content" id="Flight">
	<form id="form_4" action="controller" method="post">
		<input type="hidden" name="command" value="deleteFlight" />
		<div>
			<div class="row" style="margin-top: 20px;">
				<span class="left"><fmt:message key="flight.flight" /> : </span> <select
					required="required" class="input" name="delFlightId">
					<c:forEach var="flight" items="${completedFlights}">
						<option value="${flight.id}">${flight.id}-${flight.to}-${flight.from}
							${flight.date}</option>
					</c:forEach>
				</select>
			</div>
			<div class="wrapper">
				<span class="right relative"> <input type="submit"
					value=<fmt:message key="flight.delete" /> class="button1" />
				</span>
			</div>
			<span class="right relative"> <c:if
					test="${not empty flightDeleted}">
					<fmt:message key="${flightDeleted}" />
				</c:if> <c:if test="${not empty flightNotDeleted}">
					<fmt:message key="${flightNotDeleted}" />
				</c:if>
			</span>
		</div>
	</form>
</div>