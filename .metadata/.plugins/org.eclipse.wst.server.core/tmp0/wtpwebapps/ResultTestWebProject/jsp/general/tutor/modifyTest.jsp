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
	<fmt:setBundle basename="${bundle}" />
	
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
		<input name="page" type="hidden" value="modifyTest" /> 
		<input name="testId" type="hidden" value="${test.id}" />
		<table width="40%">
			<tr>
				<td>
					<input type="text" name="name" value="${test.name}"><br>
					<input type="text" name="subject_area" value="${test.subjectArea}"><br>
					<c:forEach items="${test.questions}" var="question">
						<tr>
							<td class = "bord1px">
								<fmt:message key="mark" />: <input type="text" name="${question.id}mark" value="${question.mark}"><br>
								<fmt:message key="question" />: <input style="width: 95%;" name="${question.id}question" type="text" value="${question.text}"><br> 
								<c:set var="i" scope="page" value="0" />
								<p><fmt:message key="answers" />:</p> 
								<c:forEach items="${question.answers}" var="answer">	
									<c:if test="${question.id * 4 + question.correctIndex - 3 != answer.id}">
										<input type="radio" name="${question.id}index" value="${i}">
									</c:if>
									<c:if test="${question.id * 4 + question.correctIndex - 3 eq answer.id}">
										<input type="radio" name="${question.id}index" value="${i}" checked="checked"> 
									</c:if>
									<input type="text" name="${answer.id}answer" value="${answer.answer}"><br>
									<c:set var="i" scope="page" value="${i+1}" />
								</c:forEach>
							</td>
						</tr>
					</c:forEach>
					<tr><td class = "bord1px">
						<input type="submit" name="modifyButton" value="<fmt:message key="save" />" />
				</td>
			</tr>
		</table>					
	</form>
	<table width="40%" style="border-top: 0;">
		<tr>
			<td>
				<form action="Test" method="post">
					<input name="page" type="hidden" value="deleteTest" /> 
					<input name="testId" type="hidden" value="${test.id}" />
					<p align="left"><input type="submit" name="modifyButton" value="<fmt:message key="delete" />" /></p>
				</form>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>