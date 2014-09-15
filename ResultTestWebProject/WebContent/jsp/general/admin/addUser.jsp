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
			<td>
				<form action="Test" method="post">
					<input name="page" type="hidden" value="addUser" />
					<fieldset>
						<legend><fmt:message key="userManager" />:</legend>
						<p>
							<fmt:message key="firstName" />:<input type="text" name="firstName" /><br> 
							<fmt:message key="lastName" />:<input type="text" name="lastName" /><br>
							<c:if test="${role == 'STUDENT'}">
								<fmt:message key="university" />*: <input type="text" name="university" /><br>
							</c:if>
							<c:if test="${role == 'TUTOR'}">
								<fmt:message key="experience" />*: <input type="text" name="experience" /><br>
							</c:if>
							<c:if test="${role == 'ADMIN'}">
								<fmt:message key="phone" />*: <input type="text" name="phone" /><br>
							</c:if>
							<fmt:message key="username" />:<input type="text" name="login" /><br> 
							<fmt:message key="password" />: <input type="password" name="password" /><br>
							<input type="submit" value="<fmt:message key="signUp" />" /><br>
						</p>
					</fieldset>
				</form>
			</td>
		</tr>
	</table>
	<h5>* <fmt:message key="generalInfo" /></h5>
	</div>
</body>
</html>