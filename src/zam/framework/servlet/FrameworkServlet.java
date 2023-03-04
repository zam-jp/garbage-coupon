/**
 * Every servlet must extend this abstract class to enable the framework 
 * to access the Action Objects in their respective packages.
 * 
 * @author Shaiful Nizam
 * @since 2019-06-08
 * @version 2019-06-08
 */
package zam.framework.servlet;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.framework.util.DateUtil;
import zam.framework.util.SystemMessage;

public abstract class FrameworkServlet extends HttpServlet {

	public void init() {
		ServletContext sc = super.getServletContext();
		DateUtil du = (DateUtil) sc.getAttribute("DATEUTIL");
		SystemMessage sm = (SystemMessage) sc.getAttribute("SYSMSG"); 
		String cn = getClass().getName();
		
		System.out.println(sm.INF(du.getSysDate(), cn)+"Instantiated.");
	}

	public final void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		this.doPost(request, response);
	}

	public final void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		ServletContext sc = super.getServletContext();
		DateUtil du = (DateUtil) sc.getAttribute("DATEUTIL");
		SystemMessage sm = (SystemMessage) sc.getAttribute("SYSMSG"); 
		String cn = getClass().getName();

		FrameworkAction action = getAction(request, response);
		RequestDispatcher view = null;
		if (action != null) {
			try {
				view = action.execute();
			} catch (NoConnectionException e) {
				System.err.println(sm.ERR(du.getSysDate(), cn)+"NoConnectionException caught.");
				view = request.getRequestDispatcher(request.getSession().
						getServletContext().getInitParameter("NO_DATABASE_CONNECTION"));
			}
		}
		if (view != null) {
			view.forward(request, response);
		}
	}

	@SuppressWarnings( { "unchecked" })
	private FrameworkAction getAction(HttpServletRequest request, 
			HttpServletResponse response) {
		ServletContext sc = super.getServletContext();
		DateUtil du = (DateUtil) sc.getAttribute("DATEUTIL");
		SystemMessage sm = (SystemMessage) sc.getAttribute("SYSMSG"); 
		String cn = getClass().getName();
		String packageName = getClass().getPackage().getName();

		System.out.println(sm.INF(du.getSysDate(), cn)+"Package = "+packageName);

		String servletPath = request.getServletPath();
		System.out.println(sm.INF(du.getSysDate(), cn)+"ServletPath = "+servletPath);

		int from = servletPath.lastIndexOf("/") + 1;
		int to = servletPath.lastIndexOf(".");
		String actionName = servletPath.substring(from, to);
		System.out.println(sm.INF(du.getSysDate(), cn)+"Action = "+actionName);

		FrameworkAction action = null;
		if (StringUtils.isNotEmpty(actionName)) {
			try {
				@SuppressWarnings("rawtypes")
				Class actionClass = Class.forName(packageName+"."+actionName+"Action");
				try {
					@SuppressWarnings("rawtypes")
					Constructor constructor = actionClass
							.getConstructor(new Class[] { HttpServletRequest.class, 
									HttpServletResponse.class});
					try {
						action = (FrameworkAction) constructor.newInstance(request, 
								response);
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				System.err.println(sm.ERR(du.getSysDate(), cn)+"Action class not found.");
				e.printStackTrace();
			}
		} else {
			System.out.println(sm.INF(du.getSysDate(), cn)+"Action name not found.");			
		}
		return action;
	}
}
