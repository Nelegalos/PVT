<%@ include file="/jsp/fragment/head.jspf"%>
<html>
<head>
<title><fmt:message key="label.admin" /></title>
<%@ include file="/jsp/fragment/style.jspf"%>
</head>
<body id="page1">
	<div class="main">
		<!--header -->
		<header>
			<div class="wrapper">
				<ctg:header />
				<table class="top_bottons">
					<tr>
						<td style="width: 300px;"><p
								style="line-height: 20px; padding: 0; margin-bottom: 20px;">
								${user}</p></td>
						<td><ctg:logout /></td>
						<td>
							<form action="controller" method="post">
								<input type="hidden" name="command" value="manageStaff" /><input
									class="button1" type="submit"
									value=<fmt:message key="manage.staff" /> />
							</form> <c:if test="${not empty employeesNull}">
								<fmt:message key="${employeesNull}" />
							</c:if>
						</td>
					</tr>
				</table>
			</div>
		</header>
		<!-- / header -->
		<!--content -->
		<section id="content">
			<div class="for_banners">
				<article class="col1">
					<div class="tabs">
						<div class="box1" style="margin-bottom: 20px;">
							<div class="notes">
								<fmt:message key="flight.new" />
							</div>
							<div class="tab-content" id="Flight">
								<form id="form_4" action="controller" method="post">
									<input type="hidden" name="command" value="addFlight" />
									<div>
										<div class="row" style="margin-top: 20px;">
											<span class="left"><fmt:message key="flight.flight" />
												: </span> <input type="number" name="addedflight" value=""
												class="input" />
										</div>
										<div class="row">
											<span class="left"><fmt:message key="flight.to" /> :
											</span> <input type="text" name="to" value="" class="input" />
										</div>
										<div class="row">
											<span class="left"><fmt:message key="flight.from" />
												: </span> <input type="text" name="from" value="" class="input" />
										</div>
										<div class="row">
											<span class="left"><fmt:message key="flight.date" />
												: </span> <input type="date" name="date" value="" class="input" />
										</div>
										<div class="row">
											<span class="left"><fmt:message key="flight.plane" />
												: </span> <select class="input" name="plane">
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
						</div>
						<div class="box1" style="margin-bottom: 20px;">
							<div class="notes">
								<fmt:message key="flight.delete" />
							</div>
							<div class="tab-content" id="Flight">
								<form id="form_4" action="controller" method="post">
									<input type="hidden" name="command" value="deleteFlight" />
									<div>
										<div class="row" style="margin-top: 20px;">
											<span class="left"><fmt:message key="flight.flight" />
												: </span> <select required="required" class="input"
												name="delFlightId">
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
						</div>
					</div>
				</article>
				<article class="col2">
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
												<input type="hidden" name="command" value="completeFlight" />
												<input type="hidden" name="flightId" value="${flight.id}" />
												<input class="button1" type="submit"
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
												<input type="hidden" name="command" value="previousFlight" />
												<input class="button1" type="submit"
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
				</article>
			</div>
		</section>
		<ctg:footer />
	</div>
</body>
</html>