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
	
	<table>
		<tr>
			<td class = "bord1px">
				<form action="Test" method="post">
					<input name="page" type="hidden" value="modifyUser" />
					<input name="userId" type="hidden" value="${modifyUser.id}" />
					<fieldset>
						<legend><fmt:message key="userManager" />:</legend>
						<p>
							<fmt:message key="firstName" />:<input name="firstName"  type="text" value="${modifyUser.firstName}"/><br> 
							<fmt:message key="lastName" />:<input name="lastName"  type="text" value="${modifyUser.lastName}"/><br>
							<c:if test="${modifyUser.role == 'STUDENT'}">
								<fmt:message key="university" />*:<input name="other"  type="text" value="${modifyUser.university}"/><br>
							</c:if>
							<c:if test="${modifyUser.role == 'TUTOR'}">
								<fmt:message key="experience" />*:<input name="other"  type="text" value="${modifyUser.experience}"/><br>
							</c:if>
						</p>
						<input type="submit" name="modifyButton" value="<fmt:message key="save" />" />
					</fieldset>
				</form>
				<form action="Test" method="post">
					<input name="page" type="hidden" value="deleteUser" />
					<input name="userId" type="hidden" value="${modifyUser.id}" />
					<input type="submit" name="modifyButton" value="<fmt:message key="delete" />" />
				</form>
			</td>
		</tr>
	</table>
	<h5>* <fmt:message key="generalInfo" /></h5>
	</div>
</body>
</html>