<%@page import="zam.portal.object.User"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../../head_tag.jsp" %>
<script type="text/javascript">
function doSubmit() {
	document.form1.action = "<%=getServletContext().getContextPath() %>/Admin/RegisterUser/GetForm.do";
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
User newUser = (User) request.getAttribute("NEW_USER");
%>
<div id="wrapper">
	<div id="inner_wrapper">
		<%@include file="../menu.jsp" %>
		<div id="content">
			<%@include file="../../header.jsp" %>
			<%@include file="breadcrumb.jsp" %>
			<fieldset class="input">
			<legend class="input">User Registered Successfully</legend>
			<form name="form1" method="post">
				<table class="input">
					<tr>
						<td>User ID</td>
						<td><%=newUser.getId() %></td>
					</tr>
					<tr>
						<td>Name</td>
						<td><%=newUser.getName() %></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><%=newUser.getEmail() %></td>
					</tr>
					<tr>
						<td>Household Size</td>
						<td><%=newUser.getHouseholdSize() %></td>
					</tr>
					<tr>
						<td>Bags Remaining</td>
						<td><%=newUser.getBagsRemaining() %></td>
					</tr>
					<tr>
						<th colspan="2">Address</th>
					</tr>
					<tr>
						<td>Postcode</td>
						<td><%=newUser.getPostcode() %></td>
					</tr>
					<tr>
						<td>Address</td>
						<td><%=newUser.getAddress() %></td>
					</tr>
					<tr>
						<td>City</td>
						<td><%=newUser.getCity() %></td>
					</tr>
					<tr>
						<td>Prefecture</td>
						<td><%=newUser.getPrefecture() %></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="button" value="Register Another User" onclick="doSubmit()"></td>
					</tr>
				</table>
			</form>
			</fieldset>
			&nbsp;<br>
		</div>
	</div>
</div>
<%@include file="../../footer.jsp" %>
</body>
</html>