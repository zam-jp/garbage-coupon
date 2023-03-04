/**
 *
 * @author Shaiful Nizam
 * @since 2020-06-28
 * @version 2020-06-28
 */
package zam.portal.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import zam.portal.object.User;

public class UserHelper {
	private static UserHelper object;
	
	private UserHelper() {
		
	}
	
	public static synchronized UserHelper getInstance() {
		if (object == null) {
			object = new UserHelper();
		}
		return object;
	}
	
	public Object clone() throws CloneNotSupportedException {
		/*
		 * Do not allow this class to be cloned since it is a singleton.
		 */
		throw new CloneNotSupportedException();
	}
	
	public synchronized User getUser(Connection conn, String userId) {
		User user = null;
		String sql = "SELECT name, email, postcode, address, city, "
				+ "prefecture, household_size, bags_remaining, "
				+ "registered_on, last_login FROM users WHERE user_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ps.setString(1, userId);
				ResultSet rs = ps.executeQuery();
				try {
					if (rs.next()) {
						user = new User();
						user.setId(userId);
						user.setName(rs.getString("name"));
						user.setEmail(rs.getString("email"));
						user.setPostcode(rs.getString("postcode"));
						user.setAddress(rs.getString("address"));
						user.setCity(rs.getString("city"));
						user.setPrefecture(rs.getString("prefecture"));
						user.setHouseholdSize(rs.getInt("household_size"));
						user.setBagsRemaining(rs.getInt("bags_remaining"));
						user.setRegisteredOn(rs.getTimestamp("registered_on"));
						user.setLastLogin(rs.getTimestamp("last_login"));
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
		return user;
	}
	
	public synchronized Vector<User> searchUser(Connection conn, String userId) {
		Vector<User> list = new Vector<User>();
		String sql = "SELECT user_id, name FROM users WHERE user_id like ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ps.setString(1, userId+"%");
				ResultSet rs = ps.executeQuery();
				try {
					User user = null;
					while (rs.next()) {
						user = new User();
						user.setId(rs.getString("user_id"));
						user.setName(rs.getString("name"));
						
						list.add(user);
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
		return list;
	}
	
}
