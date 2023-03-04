<%@page import="zam.portal.object.GarbageBagRequest"%>
<%@page import="zam.portal.object.GarbageBag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../../../head_tag.jsp" %>
<script type="text/javascript">
function doCancel() {
	document.form1.action = "<%=getServletContext().getContextPath() %>/CityHall/ProcessGarbageBag/ViewRequests.do";
	document.form1.submit();
}
function doUpdate() {
	document.form1.action = "<%=getServletContext().getContextPath() %>/CityHall/ProcessGarbageBag/UpdateRequest.do";
	document.form1.submit();
}
</script>
</head>
<%if (hasDialogBox) { %>
<body onload="displayDialogBox()">
<%@include file="../../../../dialog_box.jsp" %>
<%} else { %>
<body>
<%}
GarbageBagRequest bagReq = (GarbageBagRequest) request.getAttribute("GARBAGE_BAG_REQUEST");
User resident = bagReq.getRequestedBy();
%>
<div id="wrapper">
	<div id="inner_wrapper">
		<%@include file="../../menu.jsp" %>
		<div id="content">
			<%@include file="../../../header.jsp" %>
			<%@include file="breadcrumb.jsp" %>
			<fieldset class="input">
			<legend class="input">Garbage Bag Request</legend>
			<form class="input" name="form1" method="post">
			<input type="hidden" name="request_id" value="<%=bagReq.getId() %>">
			<input type="hidden" name="has_processed" value="1">
				<table class="data">
					<tr>
						<td>Name</td>
						<td><%=resident.getName() %></td>
					</tr>
					<tr>
						<td>Bags Remaining</td>
						<td><%=resident.getBagsRemaining() %></td>
					</tr>
					<tr>
						<td>Address</td>
						<td>
						<%=resident.getPostcode() %>,<br>
						<%=resident.getPrefecture() %>,<br>
						<%=resident.getCity() %>,<br>
						<%=resident.getAddress() %>
						</td>
					</tr>
					<tr>
						<td>Request Date</td>
						<td><%=bagReq.getRequestedOn() %></td>
					</tr>
					<tr>
						<td>Bags requested</td>
						<td>
							<table class="data_row">
								<tr>
									<th style="width:2%;"></th>
									<th style="width:60%;">Type</th>
									<th>Amount</th>
								</tr>
<%
int total = 0;
for (GarbageBag bag : bagReq.getListOfBags()) {
	total = total + bag.getAmount();
%>
								<tr>
									<td style="width:2%;"></td>
									<td style="width:40%; background-color:<%=bag.getHexColor() %>;"><%=bag.getType() %></td>
									<td><%=bag.getAmount() %></td>
								</tr>
<% 
} %>
								<tr>
									<td colspan="2">Total</td>
									<td><%=total %></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="button" value="Cancel" onclick="doCancel()" style="width:49%;">
							<input type="button" value="Mark as Processed" onclick="doUpdate()" style="width:49%;">
						</td>
					</tr>
				</table>
			</form>
			</fieldset>
			&nbsp;<br>
		</div>
	</div>
</div>
<%@include file="../../../footer.jsp" %>
</body>
</html>