/**
 *
 * @author Shaiful Nizam
 * @since 2020-07-01
 * @version 2020-07-01
 */
package zam.portal.admin.manage.role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import zam.portal.object.User;

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
	
	public synchronized Vector<String> getRoles(Connection conn) {
		Vector<String> list = new Vector<String>();
		String sql = "SELECT role_name FROM roles";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ResultSet rs = ps.executeQuery();
				try {
					while (rs.next()) {
						list.add(rs.getString("role_name"));
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

	public synchronized User getUserRoles(Connection conn, User user) {
		String sql = "SELECT role_name FROM user_role WHERE user_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ps.setString(1, user.getId());
				ResultSet rs = ps.executeQuery();
				try {
					Vector<String> roles = new Vector<String>();
					while (rs.next()) {
						roles.add(rs.getString("role_name"));
					}
					user.setRoles(roles);
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
	
	public synchronized boolean updateUserRole(Connection conn, User user, 
			String[] roles) {
		boolean isSuccess = true;
		String sqlDelete = "DELETE FROM user_role WHERE user_id = ?";
		String sqlInsert = "INSERT INTO user_role (user_id, role_name) "
				+ "VALUES (?,?)";
		try {
			conn.setAutoCommit(false);
			PreparedStatement psDelete = conn.prepareStatement(sqlDelete);
			PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
			try {
				psDelete.setString(1, user.getId());
				psDelete.executeUpdate();
				
				if (roles != null) {
					for (String role : roles) {
						psInsert.setString(1, user.getId());
						psInsert.setString(2, role);
						psInsert.addBatch();
					}
					psInsert.executeBatch();
				}
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				isSuccess = false;
				conn.rollback();
			} finally {
				psDelete.close();
				psInsert.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			isSuccess = false;
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isSuccess;
	}
}
