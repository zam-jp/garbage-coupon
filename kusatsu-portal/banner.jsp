<%@ page import="java.util.Calendar" %>
<%@page import="zam.portal.object.User"%>
<%@page import="zam.framework.helpers.DateHelper"%>
<%!DateHelper du = DateHelper.getInstance();%>
<%
String today = du.getCurrentDisplayTime();
User thisUser = (User) session.getAttribute("CURRENT_USER");
%>
<script>
function doLogin() {
	document.form_banner.action = "<%=getServletContext().getContextPath() %>/Login/GetLoginForm.do";
	document.form_banner.submit();
}
function doLogout() {
	document.form_banner.action = "<%=getServletContext().getContextPath() %>/Login/Logout.do";
	document.form_banner.submit();
}
</script>
<form name="form_banner" method="post">
<table style="width: 100%; border-collapse:collapse;">
	<tr>
		<td style="width: 20%;"><img class="banner_logo" src="<%=getServletContext().getContextPath() %>/images/logo.png" border="0" /></td>
		<td style="width: 60%; text-align: center;"><font style="font-size:24px;color:#b6142c;">Project Shiga</font><br><font style="color: #b6142c;">(Development in Progress)</font></td>
		<td style="width: 20%; text-align: right; vertical-align: top;">
			<font size="1">
			<%=today %><br>
<%
if (thisUser != null) {
%>
			<a href="#" onclick="doLogout()" >Logout</a><br />
			Logged in as <%=thisUser.getFamilyName() %> <%=thisUser.getGivenName() %>
<%} %>
			</font>
		</td>
	</tr>
	<tr>
		<td class="banner" colspan="3"></td>
	</tr>
	<tr>
		<td colspan="3"></td>
	</tr>
</table>
</form>