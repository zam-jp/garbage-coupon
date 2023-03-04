<%@page import="zam.framework.util.DateUtil"%>
<%@page import="zam.portal.object.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
User currUser = (User) request.getSession().getAttribute("CURRENT_USER");
DateUtil du = (DateUtil) request.getServletContext().getAttribute("DATEUTIL");
%>
<div id="header" style="background-image: url(<%=getServletContext().getContextPath() %>/images/header02.png);">
草津市役所へようこそ<br>
Welcome to <%=(String) getServletContext().getInitParameter("WEB_TITLE") %>
</div>
<div id="header_bar">Logged in as: <%=currUser.getName() %>, <%=du.getCurrentDisplayDate() %></div>
