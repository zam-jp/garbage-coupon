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

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.framework.object.dialogbox.DialogBox;
import zam.framework.object.dialogbox.MessageBox;
import zam.framework.object.dialogbox.UnOrderedBulletList;
import zam.portal.helper.UserHelper;
import zam.portal.object.User;

public class UpdateUserRoleAction extends FrameworkAction {

	public UpdateUserRoleAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		String[] roles = request.getParameterValues("role_name");
		Connection conn = getConnection();
		
		UserHelper userHelper = UserHelper.getInstance();
		User user = userHelper.getUser(conn, getParameter("user_id"));
		
		Helper helper = Helper.getInstance();

		DialogBox dbox = new MessageBox(new UnOrderedBulletList());
		dbox.setTitle(MessageBox.ATTENTION);
		if (helper.updateUserRole(conn, user, roles)) {
			dbox.setTitle(MessageBox.ATTENTION);
			dbox.setText("User role have been updated.");
		} else {
			dbox.setTitle(MessageBox.WARNING);
			dbox.setText("An error occured. Unable to update user role.");			
		}
		request.setAttribute("DIALOG_BOX", dbox);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request.getRequestDispatcher("/Admin/ManageRole/GetUserRole.do");
	}

}
