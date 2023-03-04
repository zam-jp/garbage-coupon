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
	document.form1.action = "<%=getServletContext().getContextPath() %>/Admin/RegisterUser/SubmitForm.do";
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
User regUser = (User) request.getAttribute("REG_USER");
if (regUser == null) {
	regUser = new User();
}
%>
<div id="wrapper">
	<div id="inner_wrapper">
		<%@include file="../menu.jsp" %>
		<div id="content">
			<%@include file="../../header.jsp" %>
			<%@include file="breadcrumb.jsp" %>
			<fieldset class="input">
			<legend class="input">Register User</legend>
			<form class="input" name="form1" method="post">
				<table class="input">
					<tr>
						<td>User ID</td>
						<td><input type="text" name="user_id" value="<%=regUser.getId() %>" placeholder="required"></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="text" name="user_password" value="<%=regUser.getPassword() %>" placeholder="required"></td>
					</tr>
					<tr>
						<td>Name</td>
						<td><input type="text" name="user_name" value="<%=regUser.getName() %>" placeholder="required"></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text" name="email" value="<%=regUser.getEmail() %>"></td>
					</tr>
					<tr>
						<td>Household Size</td>
						<td>
							<select name="household_size">
<%
for (int size = 1; size < 15; size++) { %>
								<option value="<%=size%>" <%if(size == regUser.getHouseholdSize()) { %>Selected<%} %>><%=size%></option>
<%
} %>
							</select>
						</td>
					</tr>
					<tr>
						<th colspan="2">Address</th>
					</tr>
					<tr>
						<td>Postcode</td>
						<td><input type="text" name="postcode" value="<%=regUser.getPostcode() %>"></td>
					</tr>
					<tr>
						<td>Address</td>
						<td><input type="text" name="address" value="<%=regUser.getAddress() %>"></td>
					</tr>
					<tr>
						<td>City</td>
						<td><input type="text" name="city" value="<%=regUser.getCity() %>"></td>
					</tr>
					<tr>
						<td>Prefecture</td>
						<td><input type="text" name="prefecture" value="<%=regUser.getPrefecture() %>"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="button" value="Register" onclick="doSubmit()"></td>
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