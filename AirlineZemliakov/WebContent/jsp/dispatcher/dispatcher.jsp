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
					<a href="index.jsp" id="logo"></a>
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
					<form name="manageStaffForm" method="POST" action="controller">
						<input type="hidden" name="command" value="manageStaff" /> <input
							type="submit" value=<fmt:message key="manage.user" /> />
					</form>
					<br>
					<table border=1>
						<tr>
							<td><fmt:message key="flight.flight" /></td>
							<td><fmt:message key="flight.to" /></td>
							<td><fmt:message key="flight.from" /></td>
							<td><fmt:message key="flight.date" /></td>
							<td><fmt:message key="flight.action" /></td>
						</tr>
						<c:forEach var="flight" items="${flights}">
							<tr>
								<td><c:out value="${ flight.id }" /></td>
								<td><c:out value="${ flight.to }" /></td>
								<td><c:out value="${ flight.from }" /></td>
								<td><c:out value="${ flight.date }" /></td>
								<td><form name="teamForm" method="POST" action="controller">
										<input type="hidden" name="command" value="team" /> 
										<input type="hidden" name="flight" value="${ flight.id }" /> 
										<input type="submit" value=<fmt:message key="option.team" /> />
									</form></td>
							</tr>
						</c:forEach>
					</table>
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