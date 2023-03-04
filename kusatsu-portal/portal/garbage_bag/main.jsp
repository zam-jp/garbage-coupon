<%@page import="zam.portal.object.GarbageBag"%>
<%@page import="zam.portal.object.GarbageBagRequest"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../head_tag.jsp" %>
</head>
<%if (hasDialogBox) { %>
<body onload="displayDialogBox()">
<%@include file="../../dialog_box.jsp" %>
<%} else { %>
<body>
<%} 
Vector<GarbageBagRequest> requestsList = (Vector<GarbageBagRequest>) request.getAttribute("REQUESTS_LIST"); %>
<div id="wrapper">
<div id="inner_wrapper">
<%@include file="menu.jsp" %>
<div id="content">
<%@include file="../header.jsp" %>
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
<fieldset class="input" style="width:650px;">
	<legend class="input">Garbage Bag Requests</legend>
	<table class="data_row">
		<tr>
			<th></th>
			<th style="width:110px;">Request Date</th>
			<th style="width:80px;">Burnable</th>
			<th style="width:80px;">PET Bottles</th>
			<th style="width:80px;">Plastics</th>
			<th style="width:50px;">Total</th>
			<th style="width:110px;">Processed On</th>
			<th style="width:50px;">Status</th>
		</tr>
<%
int total = 0;
if (requestsList.isEmpty()) { %>
		<tr>
			<td colspan="8" style="text-align:center;">-- No Request --</td>
		</tr>
<%
} else { 
	int rowCount = 0;
	int bag1Amount;
	int bag2Amount;
	int bag3Amount;
	int subTotal;
	Vector<GarbageBag> bagList = null;
	String status = "";
	String processedOn = "";
	for (GarbageBagRequest bagReq : requestsList) {
		rowCount++;
		bag1Amount = 0;
		bag2Amount = 0;
		bag3Amount = 0;
		subTotal = 0;
		bagList = bagReq.getListOfBags();
		for (GarbageBag bag : bagList) {
			if (bag.getType().equals("Burnable")) {
				bag1Amount = bag.getAmount();
			} else if (bag.getType().equals("PET Bottles")) {
				bag2Amount = bag.getAmount();
			} else if (bag.getType().equals("Plastics")) {
				bag3Amount = bag.getAmount();
			}
		}
		subTotal = bag1Amount + bag2Amount + bag3Amount;
		total = total + subTotal;
		if (bagReq.hasProcessed()) {
			processedOn = du.getDisplayDate(bagReq.getProcessedOn());
			status = "Processed";
		} else {
			processedOn = "";
			status = "Pending";
		}
			
			%>
		<tr>
			<td><%=rowCount %></td>
			<td style="text-align:center;"><%=du.getDisplayDate(bagReq.getRequestedOn()) %></td>
			<td style="text-align:center; background-color:#33afff;"><%=bag1Amount %></td>
			<td style="text-align:center; background-color:#8dff33;"><%=bag2Amount %></td>
			<td style="text-align:center; background-color:#ff4f33;"><%=bag3Amount %></td>
			<td style="text-align:center;"><%=subTotal %></td>
			<td style="text-align:center;"><%=processedOn %></td>
			<td style="text-align:center;"><%=status %></td>
		</tr>
<%
	} 
} %>
		<tr>
			<td colspan="5" style="text-align:right;">Overall Total</td>
			<td style="text-align:center;"><%=total %></td>
			<td colspan="2"></td>	
		</tr>
	</table>
</fieldset>
&nbsp;<br>
</div>
</div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>