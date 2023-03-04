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
import zam.framework.object.dialogbox.UnOrderedBulletList;
import zam.portal.helper.GarbageBagHelper;
import zam.portal.object.GarbageBag;

public class AddRequestAction extends FrameworkAction {

	public AddRequestAction(HttpServletRequest request, HttpServletResponse response) {
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
			int amount = Integer.parseInt(getParameter("amount"));
			if (amount == 0) {
				DialogBox dbox = new MessageBox(new UnOrderedBulletList());
				dbox.setTitle(MessageBox.ATTENTION);
				dbox.setText("Requested bag amount must not be zero.");
				request.setAttribute("DIALOG_BOX", dbox);
			} else {
				String bagType = getParameter("bag_type");
				boolean isNewBag = true;
				for (GarbageBag bag : bagList) {
					if (bag.getType().equals(bagType)) {
						bag.setAmount(bag.getAmount() + amount);
						isNewBag = false;
						break;
					}
				}
				if (isNewBag) {
					GarbageBag bag = new GarbageBag();
					bag.setType(bagType);
					bag.setAmount(amount);
					bagList.add(bag);
				}
			}
			
			Integer amountLimit = (Integer) session.getAttribute("AMOUNT_LIMIT");
			session.setAttribute("AMOUNT_LIMIT", amountLimit - amount);
			request.setAttribute("AMOUNT_LIMIT", amountLimit - amount);
			
			Integer bagsRemaining = (Integer) session.getAttribute("BAGS_REMAINING");
			session.setAttribute("BAGS_REMAINING", bagsRemaining - amount);
			request.setAttribute("BAGS_REMAINING", bagsRemaining - amount);
			
			session.setAttribute("GARBAGE_BAG_LIST", bagList);
			request.setAttribute("GARBAGE_BAG_LIST", bagList);		
			request.setAttribute("GARBAGE_BAG_TYPES", typeOfBags);
		} // End of synchronized block.
		return request.getRequestDispatcher("/portal/garbage_bag/request/form.jsp");
	}

}
