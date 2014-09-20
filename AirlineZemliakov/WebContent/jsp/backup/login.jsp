<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${ lang }" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />

<html>
<head>
<title><fmt:message key="option.login" /></title>
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
			<div class="for_banners">
				<article class="col1">
					<div class="tabs">
						<div class="content">
							<div class="tab-content" id="Rental">

								<form name="loginForm" method="POST" action="controller">
									<input type="hidden" name="command" value="login" />
									<fmt:message key="option.nick" />
									<br /> <input type="text" name="login" value="" /> <br />
									<fmt:message key="option.pass" />
									<br /> <input type="password" name="password" value="" /> <br />
									<input type="submit" value="<fmt:message key="option.login" />"
										class="button1" /> ${errorLoginPassMessage} <br />
									${wrongAction} <br /> ${nullPage}
								</form>
							</div>
						</div>
					</div>
				</article>
			</div>
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