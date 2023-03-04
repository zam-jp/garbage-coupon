<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../../../head_tag.jsp" %>
<script type="text/javascript">
function makeGraph()
{
    var container = document.getElementById("graph");
    var labels = document.getElementById("labels");
    var dnl = container.getElementsByTagName("li");
    for(var i = 0; i < dnl.length; i++)
    {
        var item = dnl.item(i);
        var value = item.innerHTML;
        var content = value.split(":");
        value = content[0];
        item.style.height = value+"px";
        item.style.top = (199 - value)+"px";
        item.style.left = (i*50+35)+"px";
        item.innerHTML = value;
        item.style.visibility = "visible";	
        left = (i*50 - 20)+"px";
        labels.innerHTML = labels.innerHTML + 
           "<span style='transform:rotateZ(-45deg); width:150px; text-align:right; position:absolute; left:"+left+"'>"+content[1]+"</span>";
    }	
}
window.onload=makeGraph;
</script>
</head>
<%if (hasDialogBox) { %>
<body onload="displayDialogBox()">
<%@include file="../../../../dialog_box.jsp" %>
<%} else { %>
<body>
<%}
Vector<String> listItems = (Vector<String>) request.getAttribute("LIST_ITEMS");
%>
<div id="wrapper">
	<div id="inner_wrapper">
		<%@include file="../../menu.jsp" %>
		<div id="content">
			<%@include file="../../../header.jsp" %>
			<%@include file="breadcrumb.jsp" %>
			<fieldset class="input">
			<legend class="input">Garbage Bag Request Distribution by Towns</legend>
			<div id="graph">
			200<br><br><br>150<br><br><br>100<br><br><br> 50
			<ul>
<%
for (String item : listItems) {
%>
			<li><%=item %></li>
<%
}
%>
			</ul>
			</div>
			<br><br><br>
			<div id="labels" style="position:relative;"></div>
			<br><br><br><br>
			<div style="width:100%; text-align:center;">Towns</div>
			</fieldset>
			&nbsp;<br>
		</div>
	</div>
</div>
<%@include file="../../../footer.jsp" %>
</body>
</html>