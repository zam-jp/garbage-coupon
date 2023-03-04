<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="head_tag.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=getServletContext().getContextPath() %>/login.css">
<script>
function doEnter() {
	document.form1.action = "<%=getServletContext().getContextPath() %>/";
	document.form1.submit();
}
</script>
</head>
<%if (hasDialogBox) { %>
<body onload="displayDialogBox()">
<%@include file="dialog_box.jsp" %>
<%} else { %>
<body>
<%} %>
<div class="login">
	<header class="login">
	<h2>You do not have access to this content</h2>
	</header>
	<form name="form1" method="post">
	<div id="login_enter">
	<input type="button" value="OK" onclick="doEnter()" style="width:200px; height:30px; font-size:18px;">
	</div>
	</form>
	<footer class="login">
		<h5>Created and developed by PBL3 Team 4, June 2020</h5>
	</footer>
</div>
</body>
</html>