<%@ include file="/jsp/include/head.jspf"%>
<html>
<head>
<title><fmt:message key="label.dispatcher" /></title>
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
								<input type="hidden" name="command" value="backToDispatcher" /><input
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
					<jsp:include page="/jsp/dispatcher/fragment/addToTeam.jsp" />
				</article>
				<article class="col2">
					<jsp:include page="/jsp/dispatcher/fragment/formTeam.jsp" />
				</article>
			</div>
		</section>
		<ctg:footer />
	</div>
</body>
</html>