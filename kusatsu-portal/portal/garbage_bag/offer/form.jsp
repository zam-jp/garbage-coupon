<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../../head_tag.jsp" %>
<script type="text/javascript">
function doSubmit() {
	document.form1.action = "<%=getServletContext().getContextPath() %>/GarbageBag/Offer/SubmitForm.do";
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
User offerTo = (User) request.getAttribute("OFFER_TO");
%>
<div id="wrapper">
	<div id="inner_wrapper">
		<%@include file="../menu.jsp" %>
		<div id="content">
			<%@include file="../../header.jsp" %>
			<%@include file="breadcrumb.jsp" %>
			<fieldset class="input">
			<legend class="input"><%=currUser.getName() %></legend>
			<table class="input">
				<tr>
					<td>Bags Remaining</td>
					<td><%=currUser.getBagsRemaining() %></td>
				</tr>
			</table>
			</fieldset>
			&nbsp;<br>
			<fieldset class="input">
			<legend class="input">Offer</legend>
			<form class="input" name="form1" method="post">
				<input type="hidden" name="offer_to" value="<%=offerTo.getId() %>">
				<table class="input">
					<tr>
						<td>Offer By</td>
						<td><%=currUser.getName() %></td>
					</tr>
					<tr>
						<td>Offer To</td>
						<td><%=offerTo.getName() %><br>(Bags remaining: <%=offerTo.getBagsRemaining() %>)</td>
					</tr>
					<tr>
						<td>Amount</td>
						<td>
							<select name="amount">
<%
for (int i = 0; i <= currUser.getBagsRemaining(); i++) { %>
								<option value="<%=i%>"><%=i%></option>
<%
} %>
							</select>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="button" value="Make Offer" onclick="doSubmit()"></td>
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