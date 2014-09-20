<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${ lang }" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />

<html>
<head>
<title><fmt:message key="label.dispatcher" /></title>
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
</head>
<body id="page1">
	<div class="main">
		<!--header -->
		<header>
			<div class="wrapper">
				<h1>
					<a href="" id="logo"></a>
				</h1>
				<span id="slogan"><fmt:message key="header.zemliakov" /></span>
			</div>
		</header>
		<!-- / header -->
		<!--content -->
		<section id="content">
			<article class="col1">
				<div class="content">
					<fmt:message key="label.dispatcher" />
					: ${user}
					<form name="logoutForm" method="POST" action="controller">
						<input type="hidden" name="command" value="logout" /> <input
							type="submit" value=<fmt:message key="option.logout" /> />
					</form>
					<br>

					<table border=1>
						<tr>
							<td><fmt:message key="position.name" /></td>
							<td><fmt:message key="employee.id" /></td>
							<td><fmt:message key="employee.name" /></td>
							<td><fmt:message key="employee.surname" /></td>
						</tr>
						<c:forEach var="employee" items="${crew}">
							<tr>
								<td><c:out value="${ employee.position }" /></td>
								<td><c:out value="${ employee.id }" /></td>
								<td><c:out value="${ employee.name }" /></td>
								<td><c:out value="${ employee.surname }" /></td>
							</tr>
						</c:forEach>
					</table>
					<form name="teamForm" method="POST" action="controller">
						<input type="hidden" name="command" value="formTeam" /> <input
							type="submit" value=<fmt:message key="option.team" /> />
					</form>


					<form name="employeeForm" method="POST" action="controller">
						<input type="hidden" name="command" value="addEmployee" /> <select
							size="1" name="emloyeeId">
							<option disabled>Select employee for the team</option>
							<c:forEach var="pos" items="${employees}">
								<option value="${ pos.id }">${ pos.position }:${ pos.name }
									${ pos.surname }</option>
							</c:forEach>
						</select> <br /> <input type="submit" value=<fmt:message key="team.add" />>
					</form>


				</div>
			</article>
			<div class="wrapper pad1">
				<article class="col1">
					<fmt:message key="contact.email" />
				</article>
				<article class="col2">
					<fmt:message key="contact.phone" />
				</article>
			</div>
		</section>
	</div>
</body>
</html>