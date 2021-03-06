<%@ include file="/jsp/include/head.jspf"%>
<html>
<head>
<title><fmt:message key="label.admin" /></title>
<%@ include file="/jsp/include/style.jspf"%>
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
								<input type="hidden" name="command" value="backToAdmin" /><input
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
								<fmt:message key="employee.modify" />
							</div>
							<div class="tab-content" id="Flight">
								<form id="form_4" action="controller" method="post">
									<input type="hidden" name="command" value="changeEmployee" />
									<div>
										<div class="row" style="margin-top: 20px;">
											<span class="left"><fmt:message key="position.name" />
												: </span> <select class="input" name="modifiedPosition">
												<option disabled><fmt:message key="position.select" /></option>
												<option value="1"><fmt:message key="PILOT" /></option>
												<option value="2"><fmt:message key="NAVIGATOR" /></option>
												<option value="3"><fmt:message key="RADIOMAN" /></option>
												<option value="4"><fmt:message key="STEWARD" /></option>
											</select>
										</div>
										<div class="row">
											<input type="hidden" name="employeeIdToModify"
												value="${employeeToModify.id}" /> <span class="left"><fmt:message
													key="employee.name" /> : </span> <input required="required"
												type="text" name="modifiedName"
												value="${employeeToModify.name}" class="input" />
										</div>
										<div class="row">
											<span class="left"><fmt:message key="employee.surname" />
												: </span> <input required="required" type="text"
												name="modifiedSurname" value="${employeeToModify.surname}"
												class="input" />
										</div>
										<div class="wrapper">
											<span class="right relative"> <input type="submit"
												value=<fmt:message key="employee.modify" /> class="button1" />
											</span>
										</div>
										<span class="right relative"> <c:if
												test="${not empty employeeWasntModified}">
												<fmt:message key="${employeeWasntModified}" />
											</c:if>
										</span>
									</div>
								</form>
								<div class="wrapper">
									<form action="controller" method="post">
										<input type="hidden" name="command" value="manageStaff" /> <span
											class="right relative"
											style="margin-bottom: 20px; margin-right: 30px;"> <input
											type="submit" value=<fmt:message key="back.staff" />
											class="button1" />
										</span>
									</form>
								</div>
							</div>
						</div>
					</div>
				</article>
			</div>
		</section>
		<ctg:footer />
	</div>
</body>
</html>