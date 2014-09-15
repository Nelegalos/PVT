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
		<input name="page" type="hidden" value="allResults" />
		<h3><fmt:message key="results" /></h3>
		<table>
			<c:forEach items="${studentRegister}" var="reg">
				<tr>
					<c:if test="${user.role eq 'TUTOR'}">
						<td class = "bord1px">
							<input type="radio" name="register" value="${reg.id}">
						</td>
					</c:if>
					<td class = "bord1px">
						<fmt:message key="test" />#${reg.test.id}: ${reg.test.name} ${reg.test.subjectArea}<br> 
						<fmt:message key="mark" />: ${reg.mark} \ ${reg.test.maxMark}<br>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${user.role eq 'TUTOR'}">
				<tr>
					<td class = "bord1px">
						<input type="submit" value="<fmt:message key="delete" />" />
					</td>
				</tr>
			</c:if>
		</table>
	</form>
	</div>
</body>
</html>