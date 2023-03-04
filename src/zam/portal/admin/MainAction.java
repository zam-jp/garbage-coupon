/**
 *
 * @author Shaiful Nizam
 * @since 2020-06-19
 * @version 2020-06-19
 */
package zam.portal.admin;

import java.security.Principal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.framework.util.DateUtil;
import zam.framework.util.SystemMessage;

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
		} else {
			System.out.println(sm.DBG(du.getSysDate(),cn)+" No Principal Found");
		}
		return request.getRequestDispatcher("/portal/administrator/main.jsp");
	}

}
