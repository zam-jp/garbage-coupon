/**
 *
 * @author Shaiful Nizam
 * @since 2020-06-20
 * @version 2020-06-20
 */
package zam.portal.admin.register.user;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.framework.object.dialogbox.DialogBox;
import zam.framework.object.dialogbox.MessageBox;
import zam.framework.object.dialogbox.UnOrderedBulletList;
import zam.portal.object.User;

public class SubmitFormAction extends FrameworkAction {

	public SubmitFormAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		User user = new User();
		user.setId(getParameter("user_id"));
		user.setPassword(getParameter("user_password"));
		user.setName(getParameter("user_name"));
		user.setEmail(getParameter("email"));
		user.setPostcode(getParameter("postcode"));
		user.setAddress(getParameter("address"));
		user.setCity(getParameter("city"));
		user.setPrefecture(getParameter("prefecture"));
		user.setHouseholdSize(Integer.parseInt(getParameter("household_size")));
		
		String url = "";
		if (StringUtils.isEmpty(user.getId()) ||
				StringUtils.isEmpty(user.getPassword()) ||
				StringUtils.isEmpty(user.getName())) {
			DialogBox dbox = new MessageBox(new UnOrderedBulletList());
			dbox.setTitle(MessageBox.ATTENTION);
			dbox.setText("The following field(s) are required:");
			if (StringUtils.isEmpty(user.getId())) {
				dbox.addBullet("User Id");
			}
			if (StringUtils.isEmpty(user.getPassword())) {
				dbox.addBullet("Password");
			}
			if (StringUtils.isEmpty(user.getName())) {
				dbox.addBullet("Name");
			}
			request.setAttribute("DIALOG_BOX", dbox);
			url = "/Admin/RegisterUser/GetForm.do";
		} else {
			Connection conn = getConnection();
			Helper helper = Helper.getInstance();
			int bagQuota = helper.getGarbageBagQuota(conn, user.getHouseholdSize());
			user.setBagsRemaining(bagQuota);
			if (helper.registerUser(conn, user)) {
				// Successful
				url = "/Admin/RegisterUser/RegisterSuccess.do";
			} else {
				// Unable to register, return to registration form
				DialogBox dbox = new MessageBox(new UnOrderedBulletList());
				dbox.setTitle(MessageBox.ATTENTION);
				dbox.setText("Due to an unforseen error, the registration was not successful.");
				request.setAttribute("DIALOG_BOX", dbox);
				url = "/Admin/RegisterUser/GetForm.do";
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("REG_USER", user);
		return request.getRequestDispatcher(url);
	}

}
