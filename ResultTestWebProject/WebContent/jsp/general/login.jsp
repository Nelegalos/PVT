<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<h2 class="rd"><c:if test="${inputFail != null }"><fmt:message key="${inputFail}"/></c:if>
	</h2>
	
	<table>
		<tr>
			<td class = "alignMidle">
				<form action="Test" method="post">
					<input name="page" type="hidden" value="login" />
					<fieldset>
						<legend><fmt:message key="login" />:</legend>
						<p>
							<fmt:message key="username" />: <input type="text" name="login" /><br>
							<fmt:message key="password" />:<input type="password" name="password" /><br>
							<input type="submit" value="<fmt:message key="enter" />" />
						</p>
					</fieldset>
				</form>
			</td>
			<td class = "alignTop">
				<form action="Test" method="post">
					<input name="page" type="hidden" value="signup" />
					<fieldset>
						<legend><fmt:message key="firstEnter" />:</legend>
						<p>
							<fmt:message key="firstName" />:<input type="text" name="firstName" /><br>
							<fmt:message key="lastName" />:<input type="text" name="lastName" /><br>
							<fmt:message key="university" />*:<input type="text" name="university" /><br>
							<fmt:message key="username" />:<input type="text" name="login" /><br>
							<fmt:message key="password" />: <input type="password" name="password" /><br> 
							<input type="submit" value="<fmt:message key="signUp" />" />
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