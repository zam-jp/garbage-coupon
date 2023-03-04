/**
 *
 * @author Shaiful Nizam
 * @since 2020-06-28
 * @version 2020-06-28
 */
package zam.portal.garbagebag.request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

import zam.portal.helper.UserHelper;
import zam.portal.object.GarbageBag;
import zam.portal.object.GarbageBagRequest;
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
	
	public synchronized boolean createRequest(Connection conn, User user, 
			Vector<GarbageBag> bagList) {
		String sql = "INSERT INTO garbage_bag_requests (requested_by, "
				+ "requested_on) VALUES (?, NOW())";
		boolean isSuccess = true;
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ps.setString(1, user.getId());
				ps.executeUpdate();
				
				sql = "INSERT INTO request_items (request_id, bag_type, amount) "
						+ "VALUES (LAST_INSERT_ID(),?,?)";
				ps = conn.prepareStatement(sql);
				try {
					for (GarbageBag bagReq : bagList) {
						ps.setString(1, bagReq.getType());
						ps.setInt(2, bagReq.getAmount());
						ps.addBatch();
					}
					ps.executeBatch();
				} catch (SQLException e) {
					e.printStackTrace();
					isSuccess = false;
					conn.rollback();
				}
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
		
	public synchronized boolean updateBagsRemaining(Connection conn, User user) {
		String sql = "UPDATE users SET bags_remaining = ? WHERE user_id = ?";
		boolean isSuccess = true;
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ps.setInt(1, user.getBagsRemaining());
				ps.setString(2, user.getId());
				ps.executeUpdate();
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

	public synchronized Vector<GarbageBagRequest> getListOfRequests(
			Connection conn, User user, boolean hasProcessed) {
		Vector<GarbageBagRequest> list = new Vector<GarbageBagRequest>();
		String sql = "SELECT id, requested_by, requested_on, processed_by, "
				+ "processed_on, has_processed FROM garbage_bag_requests "
				+ "WHERE requested_by = ? AND has_processed = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ps.setString(1, user.getId());
				if (hasProcessed) {
					ps.setInt(2, 1);
				} else {
					ps.setInt(2, 0);					
				}
				ResultSet rs = ps.executeQuery();
				try {
					GarbageBagRequest bagReq = null;
					UserHelper userHelper = UserHelper.getInstance();
					while (rs.next()) {
						bagReq = new GarbageBagRequest();
						bagReq.setId(rs.getString("id"));
						bagReq.setRequestedBy(user);
						bagReq.setRequestedOn(rs.getTimestamp("requested_on"));
						if (StringUtils.isNotEmpty(rs.getString("processed_by"))) {
							bagReq.setProcessedBy(userHelper.getUser(conn, rs.getString("processed_by")));
						}
						bagReq.setProcessedOn(rs.getTimestamp("processed_on"));

						if (rs.getInt("has_processed") == 0) {
							bagReq.setHasProcessed(false);
						} else {
							bagReq.setHasProcessed(true);
						}
						list.add(bagReq);
					}
					
					sql = "SELECT bag_type, amount FROM request_items "
							+ "WHERE request_id = ?";
					ps = conn.prepareStatement(sql);
					Vector<GarbageBag> bagList = null;
					for (GarbageBagRequest bagRequest : list) {
						ps.setString(1, bagRequest.getId());
						rs = ps.executeQuery();
						
						GarbageBag bag = null;
						bagList = new Vector<GarbageBag>();
						while (rs.next()) {
							bag = new GarbageBag();
							bag.setType(rs.getString("bag_type"));
							bag.setAmount(rs.getInt("amount"));
							bagList.add(bag);
						}
						bagRequest.setListOfBags(bagList);
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
