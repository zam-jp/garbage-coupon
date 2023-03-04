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
import zam.portal.helper.GarbageBagHelper;
import zam.portal.object.GarbageBag;

public class RemoveRequestAction extends FrameworkAction {

	public RemoveRequestAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		Connection conn = getConnection();
		GarbageBagHelper gbh = GarbageBagHelper.getInstance(); 
		Vector<GarbageBag> typeOfBags = gbh.getGarbageBagTypes(conn);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		synchronized(session) {
			@SuppressWarnings("unchecked")
			Vector<GarbageBag> bagList = (Vector<GarbageBag>) session.getAttribute("GARBAGE_BAG_LIST");
			try {
				GarbageBag bag = bagList.remove(Integer.parseInt(getParameter("request_id")));
				
				Integer amountLimit = (Integer) session.getAttribute("AMOUNT_LIMIT");
				session.setAttribute("AMOUNT_LIMIT", amountLimit + bag.getAmount());
				request.setAttribute("AMOUNT_LIMIT", amountLimit + bag.getAmount());
		
				Integer bagsRemaining = (Integer) session.getAttribute("BAGS_REMAINING");
				session.setAttribute("BAGS_REMAINING", bagsRemaining + bag.getAmount());
				request.setAttribute("BAGS_REMAINING", bagsRemaining + bag.getAmount());
				
			} catch (ArrayIndexOutOfBoundsException ex) {
				
				Integer amountLimit = (Integer) session.getAttribute("AMOUNT_LIMIT");
				session.setAttribute("AMOUNT_LIMIT", amountLimit);
				request.setAttribute("AMOUNT_LIMIT", amountLimit);
				
				Integer bagsRemaining = (Integer) session.getAttribute("BAGS_REMAINING");
				session.setAttribute("BAGS_REMAINING", bagsRemaining);
				request.setAttribute("BAGS_REMAINING", bagsRemaining);
				
				DialogBox dbox = new MessageBox();
				dbox.setTitle(MessageBox.WARNING);
				dbox.setText("Action aborted. The item had been removed.");
				request.setAttribute("DIALOG_BOX", dbox);
			}
			session.setAttribute("GARBAGE_BAG_LIST", bagList);
			request.setAttribute("GARBAGE_BAG_LIST", bagList);
			request.setAttribute("GARBAGE_BAG_TYPES", typeOfBags);
		} // End of synchronized block.		
		return request.getRequestDispatcher("/portal/garbage_bag/request/form.jsp");
	}

}
