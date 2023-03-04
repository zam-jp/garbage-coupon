<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="menu">
<img src="<%=getServletContext().getContextPath() %>/images/2018-c_title.png">
<p><a href="<%=getServletContext().getContextPath() %>/Login/Logout.do">Logout</a></p>
<h3>メニュー</h3>
<%if (request.isUserInRole("Resident")) { %>
<p>マイアカウント<br>My Account</p>
<p><a href="<%=getServletContext().getContextPath() %>/GarbageBag/Main.do">ゴミ袋<br>Garbage Bag</a></p>
<p>質問<br>Enquiry</p>
<p>よくある質問<br>FAQ</p>
<%} %>
<%if (request.isUserInRole("City Hall")) { %>
<p><a href="<%=getServletContext().getContextPath() %>/CityHall/Main.do">市役所<br>City Hall</a></p>
<%} %>
<%if (request.isUserInRole("Administrator")) { %>
<p><a href="<%=getServletContext().getContextPath() %>/Admin/Main.do">アドミニストレータ<br>Administrator</a></p>
<%} %>
&nbsp;<br>
</div>
