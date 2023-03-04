/**
 *
 * @author Shaiful Nizam
 * @since 2019-09-03
 * @version 2019-09-03
 */
package zam.portal.home;

import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.framework.util.DateUtil;
import zam.framework.util.SystemMessage;
import zam.portal.helper.UserHelper;
import zam.portal.object.User;

public class MainAction extends FrameworkAction {

	public MainAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		ServletContext sc = request.getServletContext();
		DateUtil du = (DateUtil) sc.getAttribute("DATEUTIL");
		SystemMessage sm = (SystemMessage) sc.getAttribute("SYSMSG"); 
		String cn = getClass().getName();
		
		Principal principal = request.getUserPrincipal();
		
		if (principal != null) {
			System.out.println(sm.DBG(du.getSysDate(),cn)+" Principal ID: "+principal.getName());
			Connection conn = getConnection();
			UserHelper userHelper = UserHelper.getInstance();
			User user = userHelper.getUser(conn, principal.getName());
			HttpSession session = request.getSession();
			session.setAttribute("CURRENT_USER", user);
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(sm.DBG(du.getSysDate(),cn)+" No Principal Found");
		}
		return request.getRequestDispatcher("/portal/home/main.jsp");
	}

}
