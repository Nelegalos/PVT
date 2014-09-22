<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${ lang }" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />

<html>
<head>
<title><fmt:message key="label.admin" /></title>
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
								<input type="hidden" name="command" value="manageXXXXXXXUsers" /><input
									class="button1" type="submit"
									value=<fmt:message key="manage.user" /> />
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
								<fmt:message key="flight.new" />
							</div>
							<div class="tab-content" id="Flight">
								<!--форма с кнопкой  -->
								<form id="form_4" action="controller" method="post">
									<input type="hidden" name="command" value="addFlight" />
									<div>
										<div class="row" style="margin-top: 20px;">
											<span class="left"><fmt:message key="flight.flight" />
												: </span>
											<!--для первого элемента задаем отступ сверху  -->
											<input type="number" name="addedflight" value=""
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
												: </span> 
												
												
												<select class="input" name="plane">
												<option disabled>Select plane</option>
												<c:forEach var="pl" items="${planes}">
													<option value="${ pl.id }">${ pl.id }</option>
												</c:forEach>
											</select>




										</div>
										<div class="wrapper">
											<span class="right relative"> <input type="submit"
												value=<fmt:message key="flight.add" /> class="button1" />
											</span>
										</div>
										<span class="right relative">${ flightAdded }</span>
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
							<fmt:message key="flight.flights" />
						</div>


						<div>
							<!--таблица -->
							<table class="flight_table">
								<tr class="name">
									<!--заголовок таблицы -->
									<td><fmt:message key="flight.flight" /></td>
									<td><fmt:message key="flight.to" /></td>
									<td><fmt:message key="flight.from" /></td>
									<td><fmt:message key="flight.date" /></td>
									<td><fmt:message key="flight.action" /></td>
								</tr>





								<c:forEach var="flight" items="${flights}">
									<c:set var="status" value="${ flight.status}" />
									<c:if test="${ status == 1}">
										<tr>
											<td><c:out value="${ flight.id }" /></td>
											<td><c:out value="${ flight.to }" /></td>
											<td><c:out value="${ flight.from }" /></td>
											<td><c:out value="${ flight.date }" /></td>

											<td>
												<form style="margin-bottom: 0;" name="teamForm"
													method="POST" action="controller">
													<input type="hidden" name="command" value="completeFlight" />
													<input type="hidden" name="flightId" value="${ flight.id }" />
													<input class="button1" type="submit"
														value=<fmt:message key="flight.complete" /> />
												</form>
											</td>

										</tr>

									</c:if>

								</c:forEach>

							</table>




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













