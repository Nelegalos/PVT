<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mnv" uri="/WEB-INF/MobilityTags.tld"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
	<h2 class="rd"><c:if test="${inputFail != null }"><fmt:message key="${inputFail}"/></c:if></h2>
	<h2 class="gr"><c:if test="${inputSuccessful != null }"><fmt:message key="${inputSuccessful}"/> </c:if></h2>
	
	<table width="40%">
		<tr>
			<td class = "bord1px">
				<form action="Test" method="post">
					<input name="page" type="hidden" value="addQuestion" />
					<fieldset>
						<legend><fmt:message key="questionManager" />:</legend>
						<p>
							<fmt:message key="question" />:<input style="width: 95%;" type="text" name="text" /><br>
							<fmt:message key="answers" />:<br> 
							<input type="radio" name="answer" checked="checked" value="0" /><input type="text" name="answerA" /><br> 
							<input type="radio" name="answer" value="1" /><input type="text" name="answerB" /><br>
							<input type="radio" name="answer" value="2" /><input type="text" name="answerC" /><br>
							<input type="radio" name="answer" value="3" /> <input type="text" name="answerD" /><br>
							<fmt:message key="questionMark" />: <input type="text" name="mark" /><br>
							<input type="submit" name="modifyButton" value="<fmt:message key="next" />" /><br>
						</p>
					</fieldset>
				</form>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>