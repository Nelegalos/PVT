<%@ include file="/jsp/fragment/head.jspf"%>
<div class="box1" style="margin-bottom: 20px;">
	<div class="notes">
		<fmt:message key="employee.modify" />
	</div>
	<div class="tab-content" id="Flight">
		<form id="form_4" action="controller" method="post">
			<input type="hidden" name="command" value="modifyEmployee" />
			<div>
				<div class="row" style="margin-top: 20px;">
					<span class="left"><fmt:message key="employee.employee" />
						: </span> <select class="input" name="employeeId">
						<option disabled><fmt:message key="position.select" /></option>
						<c:forEach var="modifiedEmployee" items="${employees}">
							<option value="${modifiedEmployee.id}">
								<c:choose>
									<c:when test="${modifiedEmployee.position eq 'PILOT' }">
										<fmt:message key="PILOT" />
									</c:when>
									<c:when test="${modifiedEmployee.position eq 'NAVIGATOR'}">
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
					<span class="right relative"> <input type="submit"
						value=<fmt:message key="employee.modify" /> class="button1" />
					</span>
				</div>
				<span class="right relative"> <c:if
						test="${not empty employeeWasModified}">
						<fmt:message key="${employeeWasModified}" />
					</c:if> <c:if test="${not empty employeeWasntModified}">
						<fmt:message key="${employeeWasntModified}" />
					</c:if>
				</span>
			</div>
		</form>
	</div>
</div>