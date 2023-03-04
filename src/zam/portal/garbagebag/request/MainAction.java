/**
 *
 * @author Shaiful Nizam
 * @since 2020-06-19
 * @version 2020-06-19
 */
package zam.portal.garbagebag.request;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.portal.object.GarbageBagRequest;
import zam.portal.object.User;

public class MainAction extends FrameworkAction {

	public MainAction(HttpServletRequest request, 
			HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		User user = (User) request.getSession(false).
				getAttribute("CURRENT_USER");
		Helper helper = Helper.getInstance();
		Connection conn = getConnection();
		Vector<GarbageBagRequest> list = new Vector<GarbageBagRequest>();
		list.addAll(helper.getListOfRequests(conn, user, false));
		list.addAll(helper.getListOfRequests(conn, user, true));
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("REQUESTS_LIST", list);
		return request.getRequestDispatcher("/portal/garbage_bag/main.jsp");
	}

}
