/**
 *
 * @author Shaiful Nizam
 * @since 2020-06-28
 * @version 2020-06-28
 */
package zam.framework.login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;

public class LogoutAction extends FrameworkAction {

	public LogoutAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		HttpSession session = request.getSession();
		session.invalidate();
		return request.getRequestDispatcher("/");
	}

}
