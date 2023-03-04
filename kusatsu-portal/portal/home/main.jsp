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
<%} %>
<div id="wrapper">
<div id="inner_wrapper">
<%@include file="menu.jsp" %>
<div id="content">
<%@include file="../header.jsp" %>
</div>
</div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>