/**
 *
 * @author Shaiful Nizam
 * @since 2020-07-11
 * @version 2020-07-11
 */
package zam.portal.cityhall.garbagebag.distribution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

class Helper {
	private static Helper object;
	
	private Helper() {
		
	}
	
	public static synchronized Helper getInstance() {
		if (object == null) {
			object = new Helper();
		}
		return object;
	}
	
	public Object clone() throws CloneNotSupportedException {
		/*
		 * Do not allow this class to be cloned since it is a singleton.
		 */
		throw new CloneNotSupportedException();
	}

	public synchronized Hashtable<String,Integer> prepareBuckets(Connection conn) {
		Hashtable<String,Integer> table = new Hashtable<String,Integer>();
		String sql = "SELECT DISTINCT postcode FROM users";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ResultSet rs = ps.executeQuery();
				try {
					String key = "";
					while (rs.next()) {
						key = rs.getString("postcode");
						if (!key.isEmpty()) {
							table.put(key.replace("-",""), 0);
						}
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
