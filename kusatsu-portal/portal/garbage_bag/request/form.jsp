<%@page import="zam.portal.object.GarbageBagRequest"%>
<%@page import="zam.portal.object.GarbageBag"%>
<%@page import="zam.portal.object.User"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../../head_tag.jsp" %>
<script type="text/javascript">
function doAdd() {
	document.form1.action = "<%=getServletContext().getContextPath() %>/GarbageBag/Request/AddRequest.do";
	document.form1.submit();
}
function doRemove(idx) {
	document.form2.request_id.value = idx;
	document.form2.action = "<%=getServletContext().getContextPath() %>/GarbageBag/Request/RemoveRequest.do";
	document.form2.submit();
}
function doSubmit() {
	document.form1.action = "<%=getServletContext().getContextPath() %>/GarbageBag/Request/SubmitRequest.do";
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
Vector<GarbageBag> typeOfBags = (Vector<GarbageBag>) request.getAttribute("GARBAGE_BAG_TYPES");
Vector<GarbageBag> bagList = (Vector<GarbageBag>) request.getAttribute("GARBAGE_BAG_LIST"); 
Integer bagsRemaining = (Integer) request.getAttribute("BAGS_REMAINING");
Integer amountLimit = (Integer) request.getAttribute("AMOUNT_LIMIT");
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
					<td><%=bagsRemaining %></td>
				</tr>
			</table>
			</fieldset>
			&nbsp;<br>
			<fieldset class="input">
			<legend class="input">Garbage Bag Request</legend>
			<form class="input" name="form1" method="post">
				<input type="hidden" name="user_id" value="<%=currUser.getId() %>">
				<input type="hidden" name="bags_remaining" value="<%=bagsRemaining %>">
				<input type="hidden" name="amount_limit" value="<%=amountLimit %>">
				<table class="input">
					<tr>
						<td>Bag Type</td>
						<td>
							<select name="bag_type">
<%
for (GarbageBag bag : typeOfBags) { %>
								<option value="<%=bag.getType()%>" style="background-color:<%=bag.getHexColor()%>"><%=bag.getType()%></option>
<%
} %>
							</select>
						</td>
					</tr>
					<tr>
						<td>Amount</td>
						<td>
							<select name="amount">
<%
for (int i = 0; i <= amountLimit; i++) { %>
								<option value="<%=i%>"><%=i%></option>
<%
} %>
							</select>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="button" value="Add Request" onclick="doAdd()"></td>
					</tr>
					<tr>
						<td style="font-size:10pt;">Note:</td>
						<td style="font-size:10pt;">You are limited to a total of 15 bags per request, 
						assuming that you have enough bags remaining. Otherwise 
						you are limited to the number of bags you have remaining.</td>
					</tr>
				</table>
			</form>
			</fieldset>
			&nbsp;<br>
			<fieldset class="input">
			<legend class="input">Request List</legend>
			<form class="input" name="form2" method="post">
				<input type="hidden" name="request_id">
				<input type="hidden" name="bags_remaining" value="<%=bagsRemaining %>">
				<input type="hidden" name="amount_limit" value="<%=amountLimit %>">
				<table class="data_row">
					<tr>
						<th>Bag Type</th>
						<th>Amount</th>
						<th style="width:50px;"></th>
					</tr>
<%
if (bagList.isEmpty()) {
%>
					<tr>
						<td colspan="3" style="text-align:center;">-- No Request --</td>
					</tr>
					<tr>
						<td colspan="3" style="text-align:center;"><input type="button" value="Submit Request" disabled></td>
					</tr>
<%
} else {
	int j = 0;
	String img = "";
	int total = 0;
	for (GarbageBag bag : bagList) {
		if (bag.getType().equals("Burnable")) {
			img = "burnable_bag.jpg";
		} else if (bag.getType().equals("PET Bottles")) {
			img = "PET_bag.jpg";
		} else if (bag.getType().equals("Plastics")) {
			img = "plastic_bag.jpg";
		}
		total = total + bag.getAmount();
%>
					<tr>
						<td style="text-align:center;"><%=bag.getType() %><br><img src="../../images/<%=img%>" width="70"></td>
						<td style="text-align:center;"><%=bag.getAmount() %></td>
						<td><input type="button" value="Remove" onclick="doRemove('<%=j%>')"></td>
					</tr>
<%
		j++;
	}
%>
					<tr>
						<td style="text-align:right;">Total</td>
						<td style="text-align:center;"><%=total %></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3" style="text-align:center;"><input type="button" value="Submit Request" onclick="doSubmit()"></td>
					</tr>
<%
}
%>
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