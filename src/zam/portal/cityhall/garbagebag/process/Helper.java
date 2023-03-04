/**
 *
 * @author Shaiful Nizam
 * @since 2020-07-04
 * @version 2020-07-04
 */
package zam.portal.cityhall.garbagebag.process;

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
		
	public synchronized GarbageBagRequest getRequest(Connection conn, 
			String requestId) {
		String sql = "SELECT requested_by, requested_on, processed_by, "
				+ "processed_on, has_processed FROM garbage_bag_requests "
				+ "WHERE id = ?";
		GarbageBagRequest bagReq = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ps.setString(1, requestId);
				ResultSet rs = ps.executeQuery();
				try {
					if (rs.next()) {
						UserHelper userHelper = UserHelper.getInstance();
						bagReq = new GarbageBagRequest();
						bagReq.setId(requestId);
						bagReq.setRequestedBy(userHelper.getUser(conn, rs.getString("requested_by")));						
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
					}
					
					sql = "SELECT A.bag_type, amount, hex_color_code "
							+ "FROM request_items as A, garbage_bag_types as B "
							+ "WHERE request_id = ? AND A.bag_type = B.bag_type";
					ps = conn.prepareStatement(sql);
					ps.setString(1, bagReq.getId());
					rs = ps.executeQuery();
						
					Vector<GarbageBag> bagList = new Vector<GarbageBag>();
					GarbageBag bag = null;
					while (rs.next()) {
						bag = new GarbageBag();
						bag.setType(rs.getString("A.bag_type"));
						bag.setAmount(rs.getInt("amount"));
						bag.setHexColor(rs.getString("hex_color_code"));
						bagList.add(bag);
					}
					bagReq.setListOfBags(bagList);
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
		return bagReq;
	}
	
	public synchronized boolean updateProcessStatus(Connection conn, 
			GarbageBagRequest bagReq, User updatedBy) {
		boolean isSuccess = true;
		String sql = "UPDATE garbage_bag_requests SET processed_by = ?, "
				+ "processed_on = NOW(), has_processed = ? WHERE id = ?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ps.setString(1, updatedBy.getId());
				if (bagReq.hasProcessed()) {
					ps.setInt(2, 1);
				} else {
					ps.setInt(2, 0);					
				}
				ps.setString(3, bagReq.getId());
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
}
