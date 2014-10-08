<%@ include file="/jsp/fragment/head.jspf"%>
<html>
<head>
<title><fmt:message key="label.dispatcher" /></title>
<%@ include file="/jsp/fragment/style.jspf"%>
</head>
<body id="page1">
	<div class="main">
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
								<input type="hidden" name="command" value="backToDispatcher" /><input
									class="button1" type="submit"
									value=<fmt:message key="back.flights" /> />
							</form>
						</td>
					</tr>
				</table>
			</div>
		</header>
		<section id="content">
			<div class="for_banners">
				<article class="col1">
					<div class="tabs">
						<div class="box1" style="margin-bottom: 20px;">
							<div class="notes">
								<fmt:message key="crew.new" />
							</div>
							<div class="tab-content" id="Flight">
								<form id="form_4" action="controller" method="post">
									<input type="hidden" name="command" value="teamAddEmployee" />
									<div>
										<div class="row" style="margin-top: 20px;">
											<select style="width: 85%; margin-left: 5px;" size="1"
												name="employeeId">
												<c:forEach var="modifiedEmployee" items="${employees}">
													<option value="${modifiedEmployee.id}">
														<c:choose>
															<c:when test="${modifiedEmployee.position eq 'PILOT' }">
																<fmt:message key="PILOT" />
															</c:when>
															<c:when
																test="${modifiedEmployee.position eq 'NAVIGATOR'}">
																<fmt:message key="NAVIGATOR" />
															</c:when>
															<c:when test="${modifiedEmployee.position eq 'RADIOMAN'}">
																<fmt:message key="RADIOMAN" />
															</c:when>
															<c:when test="${modifiedEmployee.position eq 'STEWARD'}">
																<fmt:message key="STEWARD" />
															</c:when>
														</c:choose> : ${modifiedEmployee.name} ${modifiedEmployee.surname}
													</option>
												</c:forEach>
											</select>

										</div>
										<div class="wrapper">
											<span style="margin-right: 145px;" class="right relative">
												<input type="submit" value=<fmt:message key="team.add" />
												class="button1" />
											</span>
										</div>
										<span class="right relative"> <c:if
												test="${not empty employeeAdded}">
												<fmt:message key="${employeeAdded}" />
											</c:if> <c:if test="${not empty  employeeNotAdded}">
												<fmt:message key="${employeeNotAdded}" />
											</c:if>
										</span>
									</div>
								</form>
							</div>
						</div>
					</div>
				</article>
				<article class="col2">
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
				</article>
			</div>
		</section>
		<ctg:footer />
	</div>
</body>
</html>