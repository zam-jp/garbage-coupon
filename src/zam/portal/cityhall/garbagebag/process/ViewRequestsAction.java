/**
 *
 * @author Shaiful Nizam
 * @since 2020-07-04
 * @version 2020-07-04
 */
package zam.portal.cityhall.garbagebag.process;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.portal.helper.GarbageBagHelper;
import zam.portal.object.GarbageBagRequest;

public class ViewRequestsAction extends FrameworkAction {

	public ViewRequestsAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		Connection conn = getConnection();
		GarbageBagHelper helper = GarbageBagHelper.getInstance();
		Vector<GarbageBagRequest> pendingRequests = helper.getListOfRequests(conn, false);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("PENDING_REQUESTS", pendingRequests);
		return request.getRequestDispatcher("/portal/cityhall/garbage_bag/process/view_requests.jsp");
	}

}
