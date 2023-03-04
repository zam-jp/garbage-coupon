/**
 *
 * @author Shaiful Nizam
 * @since 2019-08-31
 * @version 2019-08-31
 */
package zam.framework.listener;

import java.security.Principal;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import zam.framework.enums.SysOutTag;
import zam.framework.util.DateUtil;
import zam.framework.util.SystemMessage;

public class FrameworkRequestListener implements ServletRequestListener {
	
	@Override
    public void requestInitialized(ServletRequestEvent event) {
//		DateUtil du = new DateUtil();
		String cn = getClass().getName();
		ServletContext sc = event.getServletContext();
		DateUtil du = (DateUtil) sc.getAttribute("DATEUTIL");
		SystemMessage sm = new SystemMessage(sc.getInitParameter("PROJECT_NAME"));
//		String pn = sc.getInitParameter("PROJECT_NAME");
//		String info = SysOutTag.INFO+" ["+pn+"] "+cn;
//		String debug = SysOutTag.DEBUG+" ["+pn+"] "+cn;

//		HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
//		System.out.println(du.getSysDate()+" "+info+", "+request.getRequestURI());
//		System.out.println(du.getSysDate()+" "+debug+", URI = "+request.getRequestURI());
		System.out.println(sm.DBG(du.getSysDate(), cn)+"Instantiated.");
//		Principal principal = request.getUserPrincipal();
//		if (principal == null) {
//			System.out.println(du.getSysDate()+" "+info+", No Principal Found");
//		} else {
//			System.out.println(du.getSysDate()+" "+info+", Principal Found: "+principal.getName());
//			
//		}
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
//		ServletContext sc = event.getServletContext();
//		pn = sc.getInitParameter("PROJECT_NAME");
//		info = SysOutTag.INFO+" ["+pn+"] "+cn;
//    	String srcCN = event.getSource().getClass().getName();
//		System.out.println(dh.getSysOutDate()+" "+info+", Destroying Request Object: "+srcCN);
    }

}
