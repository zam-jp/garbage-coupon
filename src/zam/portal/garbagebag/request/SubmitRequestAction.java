/**
 *
 * @author Shaiful Nizam
 * @since 2020-06-28
 * @version 2020-06-28
 */
package zam.portal.garbagebag.request;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.framework.object.dialogbox.DialogBox;
import zam.framework.object.dialogbox.MessageBox;
import zam.portal.object.GarbageBag;
import zam.portal.object.User;

public class SubmitRequestAction extends FrameworkAction {

	public SubmitRequestAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		HttpSession session = request.getSession(false); 
		synchronized(session) {
			@SuppressWarnings("unchecked")
			Vector<GarbageBag> bagList = (Vector<GarbageBag>) session.getAttribute("GARBAGE_BAG_LIST");
			if (bagList == null) {
				DialogBox dbox = new MessageBox();
				dbox.setTitle(MessageBox.WARNING);
				dbox.setText("Action aborted. This request has already been submitted.");			
				request.setAttribute("DIALOG_BOX", dbox);

			} else {
				User user = (User) session.getAttribute("CURRENT_USER");
				Connection conn = getConnection();
				Helper helper = Helper.getInstance();
				DialogBox dbox = new MessageBox();
				if (helper.createRequest(conn, user, bagList)) {
					user.setBagsRemaining(Integer.parseInt(getParameter("bags_remaining")));
					helper.updateBagsRemaining(conn, user);
					dbox.setTitle(MessageBox.SUCCESSFUL);
					dbox.setText("Garbage Bag Request has been successfully submitted.");
				} else {
					dbox.setTitle(MessageBox.ERROR);
					dbox.setText("An error occured. Unable to submit Garbage Bag "
							+ "Request. Please try again.");			
				}
				request.setAttribute("DIALOG_BOX", dbox);
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}		
				session.removeAttribute("BAGS_REMAINING");
				session.removeAttribute("AMOUNT_LIMIT");
				session.removeAttribute("GARBAGE_BAG_LIST");
			}
		} // End of synchronized block.
		return request.getRequestDispatcher("/GarbageBag/Main.do");
	}

}
