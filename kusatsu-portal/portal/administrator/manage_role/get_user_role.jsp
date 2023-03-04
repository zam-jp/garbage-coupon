<%@page import="zam.portal.object.User"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../../head_tag.jsp" %>
<script type="text/javascript">
function doUpdate() {
	document.form1.action = "<%=getServletContext().getContextPath() %>/Admin/ManageRole/UpdateUserRole.do";
	document.form1.submit();
}
</script>
</head>
<%if (hasDialogBox) { %>
<body onload="displayDialogBox()">
<%@include file="../../../dialog_box.jsp" %>
<%} else { %>
<body>
<%}
Vector<String> roles = (Vector<String>) request.getAttribute("ROLES");
User user = (User) request.getAttribute("USER"); %>
<div id="wrapper">
	<div id="inner_wrapper">
		<%@include file="../menu.jsp" %>
		<div id="content">
			<%@include file="../../header.jsp" %>
			<%@include file="breadcrumb.jsp" %>
			<fieldset class="input">
				<legend class="input">User Role</legend>
				<form class="input" name="form1" method="post">
				<input type="hidden" name="user_id" value="<%=user.getId() %>">
					<table class="input" style="width:500px;">
						<tr>
							<td>User ID</td>
							<td><%=user.getId() %></td>
						</tr>
						<tr>
							<td>Name</td>
							<td><%=user.getName() %></td>
						</tr>
						<tr>
							<td>Role</td>
							<td>
								<table>
<%
for (String role : roles) { %>
								<tr>
									<td><input type="checkbox" name="role_name" value="<%=role %>" <% if(user.getRoles().contains(role)) { %>checked<%} %>></td>
									<td><%=role %></td>
								</tr>
<%
} %>
								</table>
							</td>
						</tr>
						<tr>
							<td></td>
							<td style="text-align:right;"><input type="button" value="Update" onclick="doUpdate()"></td>
						</tr>
					</table>
				</form>
			</fieldset>
		</div>
	</div>
</div>
<%@include file="../../footer.jsp" %>
</body>
</html>