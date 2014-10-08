<%@ include file="/jsp/include/head.jspf"%>
<html>
<head>
<title><fmt:message key="option.login" /></title>
<%@ include file="/jsp/include/style.jspf"%>
</head>
<body id="page1">
	<div class="main">
		<header>
			<div class="wrapper">
				<ctg:header />
			</div>
		</header>
		<section id="content">
			<div class="for_banners">
				<article class="col1">
					<div class="tabs">
						<div class="box1">
							<div class="notes">
								<fmt:message key="lable.reg" />
							</div>
							<div class="tab-content" id="Flight">
								<form id="form_4" name="loginForm" method="POST"
									action="controller">
									<input type="hidden" name="command" value="login" />
									<div>
										<div class="row" style="margin-top: 20px;">
											<span class="left"><fmt:message key="option.nick" /></span>
											<input type="text" name="login" value="" class="input" />
										</div>
										<div class="row">
											<span class="left"><fmt:message key="option.pass" /></span>
											<input type="password" name="password" value="" class="input" />
										</div>
										<div class="wrapper">
											<span class="right relative"> <input type="submit"
												value="<fmt:message key="option.login" />" class="button1" />
											</span>
										</div>
										<div class="wrapper"
											style="text-align: center; margin-top: 10px;">
											<c:if test="${not empty errorLoginPassMessage}">
												<fmt:message key="${errorLoginPassMessage}" />
											</c:if>
											<c:if test="${not empty  wrongAction}">
												<fmt:message key="${wrongAction}" />
											</c:if>
											<c:if test="${not empty error}">
												<fmt:message key="${error}" />
											</c:if>
										</div>
									</div>
								</form>
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