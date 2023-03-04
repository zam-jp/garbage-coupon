<%@page import="zam.portal.object.User"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../../head_tag.jsp" %>
<script type="text/javascript">
function doSearch() {
	document.form1.action = "<%=getServletContext().getContextPath() %>/Admin/ManageRole/GetUser.do";
	document.form1.submit();
}

function getUser(usrid) {
	document.form2.user_id.value = usrid;
	document.form2.action = "<%=getServletContext().getContextPath() %>/Admin/ManageRole/GetUserRole.do";
	document.form2.submit();
}
</script>
</head>
<%if (hasDialogBox) { %>
<body onload="displayDialogBox()">
<%@include file="../../../dialog_box.jsp" %>
<%} else { %>
<body>
<%}
Vector<User> searchResult = (Vector<User>) request.getAttribute("SEARCH_RESULT"); %>
<div id="wrapper">
	<div id="inner_wrapper">
		<%@include file="../menu.jsp" %>
		<div id="content">
			<%@include file="../../header.jsp" %>
			<%@include file="breadcrumb.jsp" %>
			<fieldset class="input">
			<legend class="input">Search User</legend>
			<form class="input" name="form1" method="post">
				<table class="input">
					<tr>
						<td>User ID</td>
						<td><input type="text" name="user_id"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="button" value="Search" onclick="doSearch()"></td>
					</tr>
				</table>
			</form>
			</fieldset>
			&nbsp;<br>
<%
if (!searchResult.isEmpty()) { %>
			<fieldset class="input">
			<legend class="input">Search Result</legend>
			<form class="input" name="form2" method="post">
			<input type="hidden" name="user_id">
			<table class="data_row">
				<tr>
					<th>User ID</th>
					<th>Name</th>
					<th></th>
				</tr>
<%
	for (User user : searchResult) { %>
				<tr>
					<td><%=user.getId() %></td>
					<td><%=user.getName() %></td>
					<td><input type="button" value="Get" onclick="getUser('<%=user.getId() %>')"></td>
				</tr>
<%
} %>
			</table>
			</form>
			</fieldset>
<%
} %>
		</div>
	</div>
</div>
<%@include file="../../footer.jsp" %>
</body>
</html>