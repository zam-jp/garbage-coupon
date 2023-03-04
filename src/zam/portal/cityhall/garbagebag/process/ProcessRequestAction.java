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
import zam.portal.object.GarbageBagRequest;

public class ProcessRequestAction extends FrameworkAction {

	public ProcessRequestAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		String requestId = getParameter("request_id");
		Connection conn = getConnection();
		Helper helper = Helper.getInstance();
		GarbageBagRequest bagReq = helper.getRequest(conn, requestId);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		request.setAttribute("GARBAGE_BAG_REQUEST", bagReq);
		return request.getRequestDispatcher("/portal/cityhall/garbage_bag/process/process_request.jsp");
	}

}
