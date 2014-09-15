<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	<form action="Test" method="post">
		<input name="page" type="hidden" value="lang" />
		<table class="lang">
			<tr>
				<td>
					<fieldset>
						<legend>Select language: </legend>
						
							<input type="radio" name="language" value="en" checked="checked">en<br>
							<input type="radio" name="language" value="ru">ru<br>
							<input type="submit" value="Enter">
					</fieldset>
				</td>
			</tr>
		</table>		
	</form>	
	</div>
</body>
</html>