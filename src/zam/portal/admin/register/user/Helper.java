/**
 *
 * @author Shaiful Nizam
 * @since 2020-06-20
 * @version 2020-06-20
 */
package zam.portal.admin.register.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public synchronized boolean registerUser(Connection conn, User user) {
		String sql = "INSERT INTO users (user_id, password, name, email, "
				+ "postcode, address, city, prefecture, "
				+ "household_size, bags_remaining, registered_on) VALUES "
				+ "(?,SHA1(?),?,?,?,?,?,?,?,?,NOW())";
		boolean isSuccess = true;
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ps.setString(1, user.getId());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getName());
				ps.setString(4, user.getEmail());
				ps.setString(5, user.getPostcode());
				ps.setString(6, user.getAddress());
				ps.setString(7, user.getCity());
				ps.setString(8, user.getPrefecture());
				ps.setInt(9, user.getHouseholdSize());
				ps.setInt(10, user.getBagsRemaining());
				ps.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				isSuccess = false;
				conn.rollback();
			} finally {
				ps.close();
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
	
	public synchronized int getGarbageBagQuota(Connection conn, int householdSize) {
		String sql = "SELECT num_of_bags FROM garbage_bag_quota "
				+ "WHERE household_min <= ? AND household_max > ?";		
		int numOfBags = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ps.setInt(1, householdSize);
				ps.setInt(2, householdSize);
				
				ResultSet rs = ps.executeQuery();
				try {
					if (rs.next()) {
						numOfBags = rs.getInt("num_of_bags");
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
		return numOfBags;
	}
}
