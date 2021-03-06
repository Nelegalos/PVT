<%@ include file="/jsp/include/head.jspf"%>
<html>
<head>
<title><fmt:message key="label.dispatcher" /></title>
<%@ include file="/jsp/include/style.jspf"%>
</head>
<body id="page1">
	<div class="main">
		<header>
			<div class="wrapper">
				<ctg:header />
				<table class="top_bottons">
					<tr>
						<td style="width: 420px;"><p
								style="line-height: 20px; padding: 0; margin-bottom: 20px;">
								${user}</p></td>
						<td><ctg:logout /></td>
					</tr>
				</table>
			</div>
		</header>
		<section id="content">
			<div class="for_banners">
				<article class="col1">
					<div class="tabs"></div>
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
												<input type="hidden" name="command" value="team" /> <input
													type="hidden" name="flight" value="${flight.id}" /> <input
													class="button1" type="submit"
													value=<fmt:message key="option.team" /> />
											</form>
										</td>
									</tr>
								</c:forEach>
							</table>
							<div>
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
													<input type="hidden" name="command" value="nextFlight" />
													<input class="button1" type="submit"
														value="<fmt:message key="flight.next" />" />
												</form></td>
										</c:if>
									</tr>
								</table>
							</div>
							<span> <c:if test="${not empty teamFormed}">
									<fmt:message key="${teamFormed}" />
								</c:if> <c:if test="${not empty teamNotFormed}">
									<fmt:message key="${teamNotFormed}" />
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