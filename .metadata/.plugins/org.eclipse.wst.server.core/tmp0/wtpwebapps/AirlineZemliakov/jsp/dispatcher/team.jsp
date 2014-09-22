<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${ lang }" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />

<html>
<head>
<title><fmt:message key="label.dispatcher" /></title>
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
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
				<!-- блок с двумя кнопками сверху -->
				<table class="top_bottons">
					<tr>
						<td style="width: 300px;"><p
								style="line-height: 20px; padding: 0; margin-bottom: 20px;">
								${user}</p></td>
						<td>
							<form action="controller" method="post">
								<input type="hidden" name="command" value="logout" /><input
									class="button1" type="submit"
									value=<fmt:message key="option.logout" /> />
							</form>
						</td>
						<td>
							<form action="controller" method="post">
								<input type="hidden" name="command" value="manageUsers" /><input
									class="button1" type="submit"
									value=<fmt:message key="manage.staff" /> />
							</form>
						</td>
					</tr>
				</table>
				<!-- конец блока с двумя кнопками сверху -->
			</div>
		</header>
		<!-- / header -->
		<!--content -->
		<section id="content">
			<div class="for_banners">
				<article class="col1">
					<div class="tabs">
						<!--отдельная форма  -->
						<div class="box1" style="margin-bottom: 20px;">
							<div class="notes">
								<!--заголовок  -->
								<fmt:message key="crew.new" />
							</div>
							<div class="tab-content" id="Flight">
								<!--форма с кнопкой  -->
								<form id="form_4" action="controller" method="post">
									<input type="hidden" name="command" value="addEmployee" />
									<div>
										<div class="row" style="margin-top: 20px;">

											<select style="width: 85%; margin-left: 5px;" size="1"
												name="employeeId">
												<option disabled>Select employee for the team</option>
												<c:forEach var="pos" items="${employees}">
													<option value="${ pos.id }">${ pos.position }:${ pos.name }
														${ pos.surname }</option>
												</c:forEach>
											</select>
										</div>
										<div class="wrapper">
											<span style="margin-right: 145px;" class="right relative">
												<input type="submit" value=<fmt:message key="team.add" />
												class="button1" />
											</span>
										</div>
										<span class="right relative"> ${ wrongEmployee }</span>
									</div>
								</form>
							</div>
						</div>
						<!--конец отдельной формы  -->
					</div>
				</article>
				<article class="col2">
					<div class="box1" style="margin-top: 245px;">
						<div class="notes">
							<!--заголовок  -->
							<fmt:message key="flight.flight" />
						</div>
						<div>
							<!--таблица -->
							<table class="flight_table">
								<tr>
									<td><fmt:message key="position.name" /></td>
									<td><fmt:message key="employee.name" /></td>
									<td><fmt:message key="employee.surname" /></td>
								</tr>
								<c:forEach var="employee" items="${crew}">
									<tr>
										<td><c:out value="${ employee.position }" /></td>
										<td><c:out value="${ employee.name }" /></td>
										<td><c:out value="${ employee.surname }" /></td>
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
						</div>
					</div>
				</article>
			</div>
		</section>
		<!--content end-->
		<!--footer -->
		<footer>
			<div class="wrapper">
				<div class="links">
					<fmt:message key="label.admin" />
					${user}
				</div>
			</div>
		</footer>
		<!--footer end-->
	</div>
</body>
</html>