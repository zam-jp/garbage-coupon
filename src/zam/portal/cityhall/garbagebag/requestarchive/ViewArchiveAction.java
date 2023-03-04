/**
 *
 * @author Shaiful Nizam
 * @since 2020-07-05
 * @version 2020-07-05
 */
package zam.portal.cityhall.garbagebag.requestarchive;

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

public class ViewArchiveAction extends FrameworkAction {

	public ViewArchiveAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		Connection conn = getConnection();
		GarbageBagHelper helper = GarbageBagHelper.getInstance();
		Vector<GarbageBagRequest> processedRequests = helper.getListOfRequests(conn, true);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("PROCESSED_REQUESTS", processedRequests);
		return request.getRequestDispatcher("/portal/cityhall/garbage_bag/request_archive/view_archive.jsp");
	}

}
