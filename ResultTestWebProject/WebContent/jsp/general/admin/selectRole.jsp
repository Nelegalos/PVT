<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
					<input name="page" type="hidden" value="selectRole" />
					<fieldset>
						<legend><fmt:message key="selectRole" />:</legend>
						<p>
							<input type="radio" name="role" value="Student" checked="checked" /><fmt:message key="student" /><br>
							<input type="radio" name="role" value="Tutor" /><fmt:message key="tutor" /><br> 
							<input type="radio" name="role" value="Admin" /><fmt:message key="admin" /><br> 
							<input type="submit" value="<fmt:message key="next" />" /><br>
						</p>
					</fieldset>
				</form>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>