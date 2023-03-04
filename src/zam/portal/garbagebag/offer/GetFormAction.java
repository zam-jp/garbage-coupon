/**
 *
 * @author Shaiful Nizam
 * @since 2020-07-08
 * @version 2020-07-08
 */
package zam.portal.garbagebag.offer;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.portal.helper.UserHelper;
import zam.portal.object.User;

public class GetFormAction extends FrameworkAction {

	public GetFormAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		Connection conn = getConnection();
		UserHelper helper = UserHelper.getInstance();
		User offerTo = helper.getUser(conn, getParameter("user_id"));
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("OFFER_TO", offerTo);		
		return request.getRequestDispatcher("/portal/garbage_bag/offer/form.jsp");
	}

}
