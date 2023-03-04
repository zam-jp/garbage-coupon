/**
 * @author Shaiful Nizam
 * @since 2019-06-08
 * @version 2019-06-08
 */
package zam.framework.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;

import zam.framework.util.DateUtil;
import zam.framework.util.SystemMessage;

public class Connector {
	private String cn = getClass().getName();
	private static Connector object;
	
	private Connector() {

	}
	
	public static synchronized Connector getInstance() {
		if (object == null) {
			object = new Connector();
		}
		return object;
	}

    public Object clone() throws CloneNotSupportedException {
    	/*
    	 * Do not allow this class to be cloned since
    	 * it is a singleton.
    	 */
    	throw new CloneNotSupportedException();
    }

    /**
	 * This method will first try to get a database connection from a connection 
	 * pool via the datasource. If no connection can be obtained, next it will 
	 * try to create a non-pooled database connection object. If still no 
	 * connection is obtained it will throw a NoConnectionException exception.  
     * @param sc
     * @return Connection
     * @throws NoConnectionException
     */
    public synchronized Connection getConnection(ServletContext sc) 
		throws NoConnectionException {
		SystemMessage sm = (SystemMessage) sc.getAttribute("SYSMSG");
		DataSource ds = (DataSource) sc.getAttribute("DATASOURCE");
		DateUtil du = (DateUtil) sc.getAttribute("DATEUTIL");
		Connection conn = null;
		try {
			conn = ds.getConnection();
			System.out.println(sm.INF(du.getSysDate(), cn)+", Connection obtained from DataSource.");
		} catch(SQLException ex) {
			System.out.println(sm.ERR(du.getSysDate(), cn)+", Unable to obtain a connection from connection pool.");
			System.out.println(sm.ERR(du.getSysDate(), cn)+" "+ex.getMessage());
		}
		// Unable to get a connection from connection pool, therefore create a
		// connection using DriverManager.
		if (conn == null) {
			String url = sc.getInitParameter("DB_URL");
			String driver = sc.getInitParameter("DB_DRIVER");
			String user = sc.getInitParameter("DB_USER");
			String password = sc.getInitParameter("DB_PASSWORD");
			System.out.println(sm.INF(du.getSysDate(), cn)+", url = " + url);
			System.out.println(sm.INF(du.getSysDate(), cn)+", driver = " + driver);
			System.out.println(sm.INF(du.getSysDate(), cn)+", user = " + user);
			System.out.println(sm.INF(du.getSysDate(), cn)+", password = " + password);
			
			conn = create(url, driver, user, password);
			if (conn != null) {
				System.out.println(sm.INF(du.getSysDate(), cn)+", Connection obtained from DriverManager.");
			}
		}
		if (conn == null) {
			throw new NoConnectionException();
		}
		return conn;
	}
	
	private Connection create(String url, String driver, String user, String password) {
		Connection connection = null;
		try {
			Class.forName(driver).newInstance();
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(IllegalAccessException ex) {
			ex.printStackTrace();
		} catch(InstantiationException ex) {
			ex.printStackTrace();
		}
		try {
			if (StringUtils.isEmpty(user) && StringUtils.isEmpty(password)) {
				connection = DriverManager.getConnection(url);
			} else {
				connection = DriverManager.getConnection(url, user, password);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return connection;
	}
}
