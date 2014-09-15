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
			<td class= "alignTop">			
				<mnv:logOut firstName="${user.firstName}"/>
			</td>
		</tr>
	</table>
	
	<h2 class="rd"><c:if test="${inputFail != null }"><fmt:message key="${inputFail}"/> </c:if></h2>
	<h2 class="gr"><c:if test="${inputSuccessful != null }"><fmt:message key="${inputSuccessful}"/> </c:if>
	<c:if test="${mark != null }"> ${mark}</c:if></h2>
	
	<table>
		<tr>
			<td class = "bord1px">
				<form action="Test" method="post">
					<input name="page" type="hidden" value="${user.role}functional" />
					<fieldset>
						<legend><fmt:message key="choose" />:</legend>
						<p>
							<c:choose>
								<c:when test="${user.role eq 'ADMIN'}">
									<input name="option" type="radio" checked="checked"
										value="addUser" /><fmt:message key="addUser" /><br>
									<input name="option" type="radio" value="modifyUser" /><fmt:message key="modifyUser" /><br>
								</c:when>
								<c:when test="${user.role eq 'TUTOR'}">
									<input name="option" type="radio" checked="checked"
										value="addTest" /><fmt:message key="addTest" /><br>
									<input name="option" type="radio" value="modifyTest" /><fmt:message key="modifyTest" /><br>
									<input name="option" type="radio" value="getAllResults" /><fmt:message key="results" /><br>
									<input name="option" type="radio" value="getAllStudents" /><fmt:message key="students" /><br>
								</c:when>
								<c:when test="${user.role eq 'STUDENT'}">
									<input name="option" type="radio" checked="checked"
										value="showTests" /><fmt:message key="tests" /><br>
									<input name="option" type="radio" value="getMyResults" /><fmt:message key="myResults" /><br>
								</c:when>
							</c:choose>
							<input type="submit" value="<fmt:message key="next" />" />
						</p>
					</fieldset>
				</form>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>