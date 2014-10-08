<%@ include file="/jsp/include/head.jspf"%>
<div class="notes">
	<fmt:message key="flight.new" />
</div>
<div class="tab-content" id="Flight">
	<form id="form_4" action="controller" method="post">
		<input type="hidden" name="command" value="addFlight" />
		<div>
			<div class="row" style="margin-top: 20px;">
				<span class="left"><fmt:message key="flight.flight" /> : </span> <input
					type="number" name="addedflight" value="" class="input" />
			</div>
			<div class="row">
				<span class="left"><fmt:message key="flight.to" /> : </span> <input
					type="text" name="to" value="" class="input" />
			</div>
			<div class="row">
				<span class="left"><fmt:message key="flight.from" /> : </span> <input
					type="text" name="from" value="" class="input" />
			</div>
			<div class="row">
				<span class="left"><fmt:message key="flight.date" /> : </span> <input
					type="date" name="date" value="" class="input" />
			</div>
			<div class="row">
				<span class="left"><fmt:message key="flight.plane" /> : </span> <select
					class="input" name="plane">
					<option disabled><fmt:message key="position.select" /></option>
					<c:forEach var="pl" items="${planes}">
						<option value="${pl.id}">${pl.id}</option>
					</c:forEach>
				</select>
			</div>
			<div class="wrapper">
				<span class="right relative"> <input type="submit"
					value=<fmt:message key="flight.add" /> class="button1" />
				</span>
			</div>
			<span class="right relative"> <c:if
					test="${not empty flightNotAdded}">
					<fmt:message key="${flightNotAdded}" />
				</c:if> <c:if test="${not empty flightAdded}">
					<fmt:message key="${flightAdded}" />
				</c:if>
			</span>
		</div>
	</form>
</div>