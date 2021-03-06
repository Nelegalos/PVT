<%@ include file="/jsp/include/head.jspf"%>
<div class="notes">
	<fmt:message key="employee.new" />
</div>
<div class="tab-content" id="Flight">
	<form id="form_4" action="controller" method="post">
		<input type="hidden" name="command" value="registerEmployee" />
		<div>
			<div class="row" style="margin-top: 20px;">
				<span class="left"><fmt:message key="position.name" /> : </span> <select
					class="input" name="position">
					<option disabled><fmt:message key="position.select" /></option>
					<option value="1"><fmt:message key="PILOT" /></option>
					<option value="2"><fmt:message key="NAVIGATOR" /></option>
					<option value="3"><fmt:message key="RADIOMAN" /></option>
					<option value="4"><fmt:message key="STEWARD" /></option>
				</select>
			</div>
			<div class="row">
				<span class="left"><fmt:message key="employee.name" /> : </span> <input
					required="required" type="text" name="name" value="" class="input" />
			</div>
			<div class="row">
				<span class="left"><fmt:message key="employee.surname" /> :
				</span> <input required="required" type="text" name="surname" value=""
					class="input" />
			</div>
			<div class="wrapper">
				<span class="right relative"> <input type="submit"
					value=<fmt:message key="employee.register" /> class="button1" />
				</span>
			</div>
			<span class="right relative"> <c:if
					test="${not empty  employeeNotAdded}">
					<fmt:message key="${employeeNotAdded}" />
				</c:if> <c:if test="${not empty employeeAdded}">
					<fmt:message key="${employeeAdded}" />
				</c:if>
			</span>
		</div>
	</form>
</div>
