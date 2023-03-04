/**
 *	This action will try to determine if a user have left a field empty, and
 *	therefore redirect them to the login page and inform them what they had
 *	not filled in. If they failed the authentication process, they will be
 *	redirected to the landing page.
 *
 * @author Shaiful Nizam
 * @since 2019-09-03
 * @version 2019-09-03
 */
package zam.framework.login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.framework.object.dialogbox.DialogBox;
import zam.framework.object.dialogbox.MessageBox;
import zam.framework.object.dialogbox.UnOrderedBulletList;
import zam.framework.util.DateUtil;
import zam.framework.util.SystemMessage;

public class LoginFailAction extends FrameworkAction {
	
	public LoginFailAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		ServletContext sc = request.getServletContext();
		DateUtil du = (DateUtil) sc.getAttribute("DATEUTIL");
		SystemMessage sm = (SystemMessage) sc.getAttribute("SYSMSG");
		String cn = getClass().getName();

		String username = getParameter("j_username");
		String password = getParameter("j_password");

		System.out.println(sm.INF(du.getSysDate(), cn)+" username = "+username);
		System.out.println(sm.INF(du.getSysDate(), cn)+" password = "+password);
		
		DialogBox dbox = new MessageBox(new UnOrderedBulletList());
		if (StringUtils.isEmpty(username) ||
				StringUtils.isEmpty(password)) {
			dbox.setTitle(MessageBox.ATTENTION);
			if (StringUtils.isEmpty(username)) {
				dbox.addBullet("You have not entered your User Id");
			}
			if (StringUtils.isEmpty(password)) {
				dbox.addBullet("You have not entered your password");
			}
		} else {			
			dbox.setTitle(MessageBox.NOT_SUCCESSFUL);
			dbox.setText("Login was not successful.<br><br>"
					+ "Please check the following:");
			dbox.addBullet("Your User Id is correct");
			dbox.addBullet("You have entered your password correctly");
			dbox.addBullet("The Caps Lock is not activated");
		}
		request.setAttribute("DIALOG_BOX", dbox);
		request.setAttribute("USERNAME", username);
		return request.getRequestDispatcher("/login_page.jsp");
	}

}
