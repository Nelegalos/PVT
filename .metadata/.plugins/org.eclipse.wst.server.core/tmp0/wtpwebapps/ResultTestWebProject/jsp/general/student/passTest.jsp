<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mnv" uri="/WEB-INF/MobilityTags.tld"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link rel="stylesheet" type="text/css" href="css/BasicStyle.css">
</head>
<body>
	<div id="top_bar_black"> 
		<div id="logo_container">
			<div id="logo_image"></div>  
			<div id="nav_block"></div>
		</div> 
	</div>
	<div id="content_container"> 
	<fmt:setLocale value="${lang}" />
	<fmt:setBundle basename="${bundle}"/>
	<table class = "navigation">
		<tr>
			<td class = "alignMidle">
				<mnv:back/>
			</td>
			<td class = "alignTop">			
				<mnv:logOut firstName="${user.firstName}"/>
			</td>
		</tr>
	</table>
	
	<form action="Test" method="post">
		<input name="page" type="hidden" value="passTest" />
		<h3><fmt:message key="test" />#${test.id}: ${test.name}	${test.subjectArea}</h3>
		<table>
			<c:forEach items="${test.questions}" var="question">
				<tr>
					<td class = "bord1px">
						<p><fmt:message key="question" />: ${question.text}</p> 
						<c:set var="i" scope="page" value="0" />
						<p><fmt:message key="answers" />:</p> 
						<c:forEach items="${question.answers}" var="answer">
							<p><input type="radio" name="${question.id}" value="${i}">${answer.answer}</p>
							<c:set var="i" scope="page" value="${i+1}" />
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td>
					<input type="submit" value="<fmt:message key="next" />" />
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>