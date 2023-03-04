/**
 *
 * @author Shaiful Nizam
 * @since 2020-07-05
 * @version 2020-07-05
 */
package zam.portal.cityhall.garbagebag.process;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.framework.object.dialogbox.DialogBox;
import zam.framework.object.dialogbox.MessageBox;
import zam.portal.object.GarbageBagRequest;
import zam.portal.object.User;

public class UpdateRequestAction extends FrameworkAction {

	public UpdateRequestAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		String requestId = getParameter("request_id");
		boolean hasProcessed = false; 
		if (getParameter("has_processed").equals("1")) {
			hasProcessed = true;
		}
		User currUser = (User) request.getSession().getAttribute("CURRENT_USER");
		Connection conn = getConnection();
		Helper helper = Helper.getInstance();
		GarbageBagRequest bagReq = helper.getRequest(conn, requestId);
		bagReq.setHasProcessed(hasProcessed);

		DialogBox dbox = new MessageBox();
		if (helper.updateProcessStatus(conn, bagReq, currUser)) {
			dbox.setTitle(MessageBox.SUCCESSFUL);
			dbox.setText("Garbage Bag Request process status has been updated.");
		} else {
			dbox.setTitle(MessageBox.ERROR);
			dbox.setText("An error occured. Unable to update Garbage Bag "
					+ "Request process status.");			
		}
		request.setAttribute("DIALOG_BOX", dbox);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return request.getRequestDispatcher("/CityHall/ProcessGarbageBag/ViewRequests.do");
	}

}
