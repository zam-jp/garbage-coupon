/**
 *
 * @author Shaiful Nizam
 * @since 2020-07-01
 * @version 2020-07-01
 */
package zam.portal.admin.manage.role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.portal.helper.UserHelper;
import zam.portal.object.User;

public class GetUserAction extends FrameworkAction {

	public GetUserAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		String userId = getParameter("user_id");
		Vector<User> searchResult = new Vector<User>();
		if (StringUtils.isNotEmpty(userId)) {
			Connection conn = getConnection();
			UserHelper helper = UserHelper.getInstance();
			searchResult = helper.searchUser(conn, userId);
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("SEARCH_RESULT", searchResult);
		return request.getRequestDispatcher("/portal/administrator/manage_role/get_user.jsp");
	}

}
