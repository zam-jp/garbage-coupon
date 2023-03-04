<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="head_tag.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=getServletContext().getContextPath() %>/login.css">
<script type="text/javascript">
function doSubmit() {
	document.form1.action = "j_security_check";
	document.form1.submit();
}
</script>
</head>
<%if (hasDialogBox) { %>
<body onload="displayDialogBox()">
<%@include file="dialog_box.jsp" %>
<%} else { %>
<body>
<%}
String username = (String) request.getAttribute("USERNAME");
if (username == null) {
	username = "";
}
%>
<div class="login">
	<header class="login">
	<h1 class="login">草津市役所へようこそ<br>
	Welcome to <%=(String) getServletContext().getInitParameter("WEB_TITLE") %></h1>
	</header>
	<form name="form1" method="post">
		<fieldset class="login">
			<legend class="login">Login</legend>
			<div class="login_row">
				<div class="login_label">
					<label class="login">ユーザーＩＤ<br>User ID</label>
				</div>
				<div class="login_input_text">
					<input class="login" type="text" name="j_username" value="<%=username%>">
				</div>
			</div>
			<div class="login_row">
				<div class="login_label">
					<label class="login">パスワード <br>Password</label>
				</div>
				<div class="login_input_text">
					<input class="login" type="password" name="j_password"><br>
				</div>
			</div>
			<div class="login_row">
				<input type="button" class="login" value="Login" onclick="doSubmit()">				
			</div>
		</fieldset>
	</form>
	<footer class="login">
		<h5 class="login">Created and developed by PBL3 Team 4, June 2020</h5>
	</footer>
</div>
</body>
</html>