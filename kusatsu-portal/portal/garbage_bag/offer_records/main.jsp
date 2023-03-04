<%@page import="zam.portal.object.GarbageBagOffer"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../../head_tag.jsp" %>
<script type="text/javascript">
function doAccept(id) {
	document.form2.offer_id.value = id;
	document.form2.action = "<%=getServletContext().getContextPath() %>/GarbageBag/Offer/AcceptOffer.do";
	document.form2.submit();
}

function doCancel(id) {
	document.form1.offer_id.value = id;
	document.form1.action = "<%=getServletContext().getContextPath() %>/GarbageBag/Offer/CancelOffer.do";
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
Vector<GarbageBagOffer> offersGiven = (Vector<GarbageBagOffer>) request.getAttribute("OFFERS_GIVEN");
Vector<GarbageBagOffer> offersReceived = (Vector<GarbageBagOffer>) request.getAttribute("OFFERS_RECEIVED");
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
			<fieldset class="input" style="width:720px;">
				<legend class="input">Offers Given</legend>
				<form class="input" name="form1" method="post">
				<input type="hidden" name="offer_id">
				<table class="data_row">
					<tr>
						<th style="width:30px;"></th>
						<th>Offered To</th>
						<th style="width:110px;">Offered On</th>
						<th style="width:80px;">Amount</th>
						<th style="width:110px;">Accepted On</th>
						<th style="width:90px;">Status</th>
						<th style="width:70px;"></th>
					</tr>
<%
int total = 0;
if (offersGiven.isEmpty()) { %>
					<tr>
						<td colspan="7" style="text-align:center;">-- No Offers Given Record Found --</td>
					</tr>
<%
} else {
	int rowCount = 0;
	String status = "";
	String acceptedOn = "";
	for (GarbageBagOffer offer : offersGiven) {
		rowCount++;
		total = total + offer.getAmount();
		if (offer.hasAccepted()) {
			acceptedOn = du.getDisplayDate(offer.getAcceptedOn());
			status = "Accepted";
		} else {
			acceptedOn = "";
			status = "Pending";
		}
			
%>
					<tr>
						<td><%=rowCount %></td>
						<td style="text-align:center;"><%=offer.getOfferedTo().getName() %></td>
						<td style="text-align:center;"><%=du.getDisplayDate(offer.getOfferedOn()) %></td>
						<td style="text-align:center;"><%=offer.getAmount() %></td>
						<td style="text-align:center;"><%=acceptedOn %></td>
						<td style="text-align:center;"><%=status %></td>
						<td style="text-align:center;">
<%
		if (!offer.hasAccepted()) {
%>
							<input type="button" value="Cancel" onclick="doCancel('<%=offer.getId() %>')">
<%
		}
%>
						</td>
					</tr>
<%
	} 
}
%>
					<tr>
						<td colspan="3" style="text-align:right;">Total</td>
						<td style="text-align:center;"><%=total %></td>
						<td colspan="3"></td>
					</tr>
				</table>
				</form>
			</fieldset>
			&nbsp;<br>
			<fieldset class="input" style="width:720px;">
				<legend class="input">Offers Received</legend>
				<form class="input" name="form2" method="post">
				<input type="hidden" name="offer_id">
				<table class="data_row">
					<tr>
						<th style="width:30px;"></th>
						<th>Offered By</th>
						<th style="width:110px;">Offered On</th>
						<th style="width:80px;">Amount</th>
						<th style="width:110px;">Accepted On</th>
						<th style="width:90px;">Status</th>
						<th style="width:70px;"></th>
					</tr>
<%
total = 0;
if (offersReceived.isEmpty()) { %>
					<tr>
						<td colspan="7" style="text-align:center;">-- No Offers Received Record Found --</td>
					</tr>
<%
} else {
	int rowCount = 0;
	String status = "";
	String acceptedOn = "";
	for (GarbageBagOffer offer : offersReceived) {
		rowCount++;
		total = total + offer.getAmount();
		if (offer.hasAccepted()) {
			acceptedOn = du.getDisplayDate(offer.getAcceptedOn());
			status = "Accepted";
		} else {
			acceptedOn = "";
			status = "Pending";
		}
			
%>
					<tr>
						<td><%=rowCount %></td>
						<td style="text-align:center;"><%=offer.getOfferedBy().getName() %></td>
						<td style="text-align:center;"><%=du.getDisplayDate(offer.getOfferedOn()) %></td>
						<td style="text-align:center;"><%=offer.getAmount() %></td>
						<td style="text-align:center;"><%=acceptedOn %></td>
						<td style="text-align:center;"><%=status %></td>
						<td style="text-align:center;">
<%
		if (!offer.hasAccepted()) {
%>
							<input type="button" value="Accept" onclick="doAccept('<%=offer.getId() %>')">
<%
		}
%>
						</td>
					</tr>
<%
	} 
} %>
					<tr>
						<td colspan="3" style="text-align:right;">Total</td>
						<td style="text-align:center;"><%=total %></td>
						<td colspan="3"></td>
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