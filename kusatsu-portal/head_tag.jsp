<%@page import="zam.framework.object.dialogbox.DialogBox"%>
<%@page import="zam.framework.object.Application"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%
	boolean hasDialogBox = false;
String dialogBoxTitle = "";
String dialogBoxText = "";
String dialogBoxBullet = "";
DialogBox dialogBox = (DialogBox) request.getAttribute("DIALOG_BOX");
if (dialogBox == null) {
//	Application currentModule = (Application) session.getAttribute("CURRENT_MODULE");
//	if (currentModule == null) {
//		dialogBoxTitle = (String) getServletContext().getInitParameter("WEB_TITLE");
//	} else {
//		dialogBoxTitle = currentModule.getName();
//	}	
} else {
	hasDialogBox = true;
	dialogBoxTitle = dialogBox.getTitle();
	dialogBoxText = dialogBox.getText();
	dialogBoxBullet = dialogBox.getBulletList(); 
	if (StringUtils.isEmpty(dialogBoxTitle)) {
		dialogBoxTitle = (String) getServletContext().getInitParameter("WEB_TITLE");
	}
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title><%=(String) getServletContext().getInitParameter("WEB_TITLE") %></title>
<link rel="stylesheet" type="text/css" href="<%=getServletContext().getContextPath() %>/dialog_box.css">
<link rel="stylesheet" type="text/css" href="<%=getServletContext().getContextPath() %>/portal.css">
<%-- <link rel="shortcut icon" type="image/x-icon" href="<%=getServletContext().getContextPath() %>/images/favicon.ico"> --%>
<script type="text/javascript">
function displayDialogBox() {
	dboxTitle = document.getElementById("dbox_title"); 
	dboxText = document.getElementById("dbox_text");
	dboxBullet = document.getElementById("dbox_bullet");
	dboxTable = document.getElementById("dbox_table");
	el1 = document.getElementById("overlay");
	el2 = document.getElementById("dialogBox");

	/* 
	This is to dynamically set the position the dialog box table
	to about 30% from the top of the browser.
	 */
	var h = document.documentElement.clientHeight;
	var h2 = h*0.3;
	var yPos = document.documentElement.scrollTop + h2 + "px";
	dboxTable.style.marginTop = yPos;
	
	dboxTitle.innerHTML = "<%=dialogBoxTitle %>";
	dboxText.innerHTML = "<%=dialogBoxText %>";
	dboxBullet.innerHTML = "<%=dialogBoxBullet%>";
	el1.style.visibility = "visible";
	el2.style.visibility = "visible";
}

function hideDialogBox(respond) {
	el1 = document.getElementById("overlay");
	el2 = document.getElementById("dialogBox");
	el1.style.visibility = "hidden";
	el2.style.visibility = "hidden";
	if (respond == 'Y') {
		doConfirm(); // Only for DeleteBox
	}
	
}

</script>
