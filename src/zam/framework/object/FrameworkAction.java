/**
 * This abstract class need to be implemented by a class to be recognized
 * by the framework as an Action Object.
 * An Action Object is a class that carries out a process and directs the
 * respond to a JSP page or another Action Object (chaining).
 * 
 * Note: The class name MUST end with the word "Action", 
 * for example: LoginAction.
 * 
 * @author Shaiful Nizam
 * @since 2019-06-08
 * @version 2019-06-08
 */
package zam.framework.object;

import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import zam.framework.db.Connector;
import zam.framework.db.NoConnectionException;
import zam.framework.enums.SysOutTag;
import zam.framework.util.DateUtil;

public abstract class FrameworkAction {

	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public FrameworkAction(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
		ServletContext sc = request.getSession().getServletContext();
		DateUtil du = (DateUtil) sc.getAttribute("DATEUTIL");
		String cn = getClass().getName();
		String pn = sc.getInitParameter("PROJECT_NAME");
		String INFO = SysOutTag.INFO+" ["+pn+"] "+cn;

		System.out.println(du.getSysDate()+" "+INFO+", Instantiated.");
	}
	
	protected Connection getConnection() throws NoConnectionException {
		ServletContext sc = request.getSession().getServletContext();
		Connector connector = Connector.getInstance();
		return connector.getConnection(sc);
	}

	protected String getParameter(String param) {
		String s = request.getParameter(param);
		if (StringUtils.isBlank(s)) {
			return "";
		} else {
			return s;
		}
	}

	abstract public RequestDispatcher execute() throws NoConnectionException;
}
