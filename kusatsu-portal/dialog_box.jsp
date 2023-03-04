<%@page import="zam.framework.object.dialogbox.DeleteBox"%>
<%@page import="zam.framework.object.dialogbox.MessageBox"%>
<div id="overlay"></div>
<div id="dialogBox">
	<div id="dbox_table">
		<div id="dbox_row">
			<fieldset id="dbox_frame">
				<legend id="dbox_title"></legend>
				<p id="dbox_text"></p>
				<p id="dbox_bullet"></p><br>
				<div id="dbox_button_cell">
		<%
		if (dialogBox instanceof MessageBox) {
		%>
					<input id="dbox_button" type="button" value="OK" onclick="hideDialogBox('')" />
		<%
		} else if (dialogBox instanceof DeleteBox) {
		%>
					<input id="dbox_button" type="button" value="OK" onclick="hideDialogBox('Y')" />
					<input id="dbox_button" type="button" value="Cancel" onclick="hideDialogBox('N')" />
		<%
		}
		%>
				</div> <!-- cell -->
			</fieldset>
		</div> <!-- row -->
	</div> <!-- table -->
</div>
