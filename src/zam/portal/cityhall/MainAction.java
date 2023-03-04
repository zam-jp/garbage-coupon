/**
 *
 * @author Shaiful Nizam
 * @since 2020-07-04
 * @version 2020-07-04
 */
package zam.portal.cityhall;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;

public class MainAction extends FrameworkAction {

	public MainAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		return request.getRequestDispatcher("/portal/cityhall/main.jsp");
	}

}
