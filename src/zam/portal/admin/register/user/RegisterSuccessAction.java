/**
 *
 * @author Shaiful Nizam
 * @since 2020-06-21
 * @version 2020-06-21
 */
package zam.portal.admin.register.user;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.portal.helper.UserHelper;
import zam.portal.object.User;

public class RegisterSuccessAction extends FrameworkAction {

	public RegisterSuccessAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		User newUser = (User) request.getAttribute("REG_USER");
		Connection conn = getConnection();
		UserHelper helper = UserHelper.getInstance();
		newUser = helper.getUser(conn, newUser.getId());
		request.setAttribute("NEW_USER", newUser);
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request.getRequestDispatcher("/portal/administrator/register_user/success.jsp");
	}

}
