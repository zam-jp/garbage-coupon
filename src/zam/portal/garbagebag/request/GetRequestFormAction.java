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
import zam.portal.helper.GarbageBagHelper;
import zam.portal.object.GarbageBag;
import zam.portal.object.User;

public class GetRequestFormAction extends FrameworkAction {

	public GetRequestFormAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		HttpSession session = request.getSession(false);
		synchronized(session) {
			@SuppressWarnings("unchecked")
			Vector<GarbageBag> bagList = (Vector<GarbageBag>) session.getAttribute("GARBAGE_BAG_LIST");
			if (bagList == null) {
				bagList = new Vector<GarbageBag>();
			}
			session.setAttribute("GARBAGE_BAG_LIST", bagList);
			request.setAttribute("GARBAGE_BAG_LIST", bagList);
			
			Integer bagsRemaining = (Integer) session.getAttribute("BAGS_REMAINING");
			if (bagsRemaining == null) {
				User user = (User) session.getAttribute("CURRENT_USER");
				bagsRemaining = user.getBagsRemaining();
			}
			session.setAttribute("BAGS_REMAINING", bagsRemaining);
			request.setAttribute("BAGS_REMAINING", bagsRemaining);
			
			Integer amountLimit = (Integer) session.getAttribute("AMOUNT_LIMIT");
			if (amountLimit == null) {
				amountLimit = new Integer(15); // Default limit			
			}
			if (bagsRemaining < amountLimit) {
				amountLimit = new Integer(bagsRemaining);
			}
			session.setAttribute("AMOUNT_LIMIT", amountLimit);
			request.setAttribute("AMOUNT_LIMIT", amountLimit);
		} // End of syncronized block.
		
		Connection conn = getConnection();
		
		GarbageBagHelper gbh = GarbageBagHelper.getInstance(); 
		Vector<GarbageBag> typeOfBags = gbh.getGarbageBagTypes(conn);
		request.setAttribute("GARBAGE_BAG_TYPES", typeOfBags);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request.getRequestDispatcher("/portal/garbage_bag/request/form.jsp");
	}

}
