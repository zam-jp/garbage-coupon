/**
 *
 * @author Shaiful Nizam
 * @since 2020-07-01
 * @version 2020-07-01
 */
package zam.portal.admin.manage.role;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.portal.helper.UserHelper;
import zam.portal.object.User;

public class GetUserRoleAction extends FrameworkAction {

	public GetUserRoleAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		String userId = getParameter("user_id");
		if (StringUtils.isNotEmpty(userId)) {
			Connection conn = getConnection();
			UserHelper userHelper = UserHelper.getInstance();
			User user = userHelper.getUser(conn, userId);
			
			Helper helper = Helper.getInstance();
			request.setAttribute("USER", helper.getUserRoles(conn, user));			
			request.setAttribute("ROLES", helper.getRoles(conn));
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return request.getRequestDispatcher("/portal/administrator/manage_role/get_user_role.jsp");
	}

}
