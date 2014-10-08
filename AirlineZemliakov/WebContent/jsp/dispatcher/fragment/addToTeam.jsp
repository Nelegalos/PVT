<%@ include file="/jsp/include/head.jspf"%>
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