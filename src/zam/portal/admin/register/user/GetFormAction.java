/**
 *
 * @author Shaiful Nizam
 * @since 2020-06-19
 * @version 2020-06-19
 */
package zam.portal.admin.register.user;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;

public class GetFormAction extends FrameworkAction {

	public GetFormAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		return request.getRequestDispatcher("/portal/administrator/register_user/form.jsp");
	}

}
