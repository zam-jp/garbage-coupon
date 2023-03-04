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

import org.apache.commons.lang.StringUtils;

import zam.portal.object.GarbageBag;
import zam.portal.object.GarbageBagRequest;

public class GarbageBagHelper {
	private static GarbageBagHelper object;
	
	private GarbageBagHelper() {
		
	}
	
	public static synchronized GarbageBagHelper getInstance() {
		if (object == null) {
			object = new GarbageBagHelper();
		}
		return object;
	}
	
	public Object clone() throws CloneNotSupportedException {
		/*
		 * Do not allow this class to be cloned since it is a singleton.
		 */
		throw new CloneNotSupportedException();
	}
	
	public synchronized Vector<GarbageBag> getGarbageBagTypes(Connection conn) {
		Vector<GarbageBag> list = new Vector<GarbageBag>();
		String sql = "SELECT bag_type, hex_color_code FROM garbage_bag_types";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ResultSet rs = ps.executeQuery();
				try {
					GarbageBag bag = null;
					while (rs.next()) {
						bag = new GarbageBag();
						bag.setType(rs.getString("bag_type"));
						bag.setHexColor(rs.getString("hex_color_code"));
						list.add(bag);
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
	
	public synchronized Vector<GarbageBagRequest> getListOfRequests(
			Connection conn, boolean hasProcessed) {
		Vector<GarbageBagRequest> list = new Vector<GarbageBagRequest>();
		String sql = "SELECT id, requested_by, requested_on, processed_by, "
				+ "processed_on, has_processed FROM garbage_bag_requests "
				+ "WHERE has_processed = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				if (hasProcessed) {
					ps.setInt(1, 1);
				} else {
					ps.setInt(1, 0);					
				}
				ResultSet rs = ps.executeQuery();
				try {
					GarbageBagRequest bagReq = null;
					UserHelper userHelper = UserHelper.getInstance();
					while (rs.next()) {
						bagReq = new GarbageBagRequest();
						bagReq.setId(rs.getString("id"));
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
