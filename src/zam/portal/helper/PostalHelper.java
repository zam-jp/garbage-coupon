/**
 *
 * @author Shaiful Nizam
 * @since 2020-07-12
 * @version 2020-07-12
 */
package zam.portal.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class PostalHelper {
	private static PostalHelper object;
	
	private PostalHelper() {
		
	}
	
	public static synchronized PostalHelper getInstance() {
		if (object == null) {
			object = new PostalHelper();
		}
		return object;
	}

	public Object clone() throws CloneNotSupportedException {
		/*
		 * Do not allow this class to be cloned since it is a singleton.
		 */
		throw new CloneNotSupportedException();
	}

	public synchronized Hashtable<String,String> getPostalCodeTable(Connection conn) {
		Hashtable<String,String> table = new Hashtable<String,String>();
		String sql = "SELECT postal_code, town FROM postal_data ORDER BY postal_code";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ResultSet rs = ps.executeQuery();
				try {
					String key = "";
					String value = "";
					while (rs.next()) {
						key =  rs.getString("postal_code");
						value = rs.getString("town");
						table.put(key, value);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return table;
	}
}

