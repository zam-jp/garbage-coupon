/**
 *
 * @author Shaiful Nizam
 * @since 2019-08-31
 * @version 2019-08-31
 */
package zam.framework.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import zam.framework.enums.SysOutTag;
import zam.framework.util.DateUtil;
import zam.framework.util.SystemMessage;

public class FrameworkContextListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent event) {
		DateUtil du = new DateUtil();
		String cn = getClass().getName();
		ServletContext sc = event.getServletContext();
		SystemMessage sm = new SystemMessage(sc.getInitParameter("PROJECT_NAME"));
		
		System.out.println(sm.INF(du.getSysDate(), cn)+" Initializing context: "+sc.getContextPath());
		System.out.println(sm.INF(du.getSysDate(), cn)+" Looking up datasource.");

		String lookupName = sc.getInitParameter("DATASOURCE_LOOKUP_NAME");
		
		DataSource ds = null;
		try {
			Context ctx = new InitialContext();
			try {
				ds = (DataSource) ctx.lookup(lookupName);
				System.out.println(sm.INF(du.getSysDate(), cn)+" Datasource found.");
			} catch(NamingException ex) {
				System.err.println(sm.ERR(du.getSysDate(), cn)+" Unable to find datasource.");
				ex.printStackTrace();
			}
			ctx.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		sc.setAttribute("DATASOURCE", ds);
		
		sc.setAttribute("DATEUTIL", du);

		sc.setAttribute("SYSMSG", sm);
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		DateUtil du = new DateUtil();
		String cn = getClass().getName();
		ServletContext sc = event.getServletContext();
		String pn = sc.getInitParameter("PROJECT_NAME");
		String INFO = SysOutTag.INFO+" ["+pn+"] "+cn;
		System.out.println(du.getSysDate()+" "+INFO+", Context destroyed.");
		
		/*
		 * Deregistering drivers causes Datasource not able to create a connection when
		 * context restarted. Have to restart tomcat to overcome it.  
		 */
//		Enumeration<Driver> drivers = DriverManager.getDrivers();
//        while (drivers.hasMoreElements()) {
//            Driver driver = drivers.nextElement();
//            try {
//                DriverManager.deregisterDriver(driver);
//        		System.out.println("[INFO] Deregistering jdbc driver: " + driver);
//            } catch (SQLException e) {
//        		System.out.println("[WARNING] Error deregistering jdbc driver: " + driver);
//        		e.printStackTrace();
//            }
//        }
	}
}
