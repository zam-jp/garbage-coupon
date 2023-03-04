<%@page import="zam.portal.object.GarbageBagRequest"%>
<%@page import="zam.portal.object.GarbageBag"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../../../head_tag.jsp" %>
<script type="text/javascript">
function doGet(id) {
	document.form1.request_id.value = id;
	document.form1.action = "<%=getServletContext().getContextPath() %>/CityHall/ProcessGarbageBag/ProcessRequest.do";
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
Vector<GarbageBagRequest> processedList = (Vector<GarbageBagRequest>) request.getAttribute("PROCESSED_REQUESTS");
%>
<div id="wrapper">
	<div id="inner_wrapper">
		<%@include file="../../menu.jsp" %>
		<div id="content">
			<%@include file="../../../header.jsp" %>
			<%@include file="breadcrumb.jsp" %>
			<fieldset class="input" style="width:1000px;">
			<legend class="input">Processed Garbage Bag Requests</legend>
				<table class="data_row">
					<tr>
						<th style="width:110px;">Processed Date</th>
						<th>Processed By</th>
						<th style="width:110px;">Request Date</th>
						<th>Request By</th>
						<th style="width:80px;">Burnable</th>
						<th style="width:80px;">PET Bottles</th>
						<th style="width:80px;">Plastics</th>
						<th style="width:50px;">Total</th>
					</tr>
<%
if (processedList.isEmpty()) { %>
					<tr>
						<td colspan="8" style="text-align:center;">-- No Processed Request Found --</td>
					</tr>
<%
} else { 
	int bag1Amount;
	int bag2Amount;
	int bag3Amount;
	int subTotal;
	Vector<GarbageBag> bagList = null;
	for (GarbageBagRequest bagReq : processedList) {
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
		subTotal = bag1Amount + bag2Amount + bag3Amount; %>
					<tr>
						<td style="text-align:center;"><%=du.getDisplayDate(bagReq.getProcessedOn()) %></td>
						<td style="text-align:center;"><%=bagReq.getProcessedBy().getName() %></td>
						<td style="text-align:center;"><%=du.getDisplayDate(bagReq.getRequestedOn()) %></td>
						<td style="text-align:center;"><%=bagReq.getRequestedBy().getName() %></td>
						<td style="text-align:center; background-color:#33afff;"><%=bag1Amount %></td>
						<td style="text-align:center; background-color:#8dff33;"><%=bag2Amount %></td>
						<td style="text-align:center; background-color:#ff4f33;"><%=bag3Amount %></td>
						<td style="text-align:center;"><%=subTotal %></td>
					</tr>
<%
	} 
} %>
				</table>
			</fieldset>
			&nbsp;<br>
		</div>
	</div>
</div>
<%@include file="../../../footer.jsp" %>
</body>
</html>