/**
 *
 * @author Shaiful Nizam
 * @since 2020-07-08
 * @version 2020-07-08
 */
package zam.portal.garbagebag.offer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import zam.portal.helper.UserHelper;
import zam.portal.object.GarbageBagOffer;
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
	
	public synchronized boolean createOffer(Connection conn, User offerBy, 
			User offerTo, int amount) {
		String sql1 = "INSERT INTO garbage_bag_tradings (offered_by, offered_on, "
				+ "offered_to, amount) VALUES (?,NOW(),?,?)";
		String sql2 = "UPDATE users SET bags_remaining = ? WHERE user_id = ?";
		boolean isSuccess = true;
		try {
			conn.setAutoCommit(false);
			PreparedStatement insertOffer = conn.prepareStatement(sql1);
			PreparedStatement updateUser = conn.prepareStatement(sql2);
			try {
				insertOffer.setString(1, offerBy.getId());
				insertOffer.setString(2, offerTo.getId());
				insertOffer.setInt(3, amount);
				insertOffer.executeUpdate();
				
				updateUser.setInt(1, offerBy.getBagsRemaining() - amount);
				updateUser.setString(2, offerBy.getId());
				updateUser.executeUpdate();
				
				conn.commit();
				
			} catch (SQLException e) {
				e.printStackTrace();
				isSuccess = false;
				conn.rollback();
			} finally {
				insertOffer.close();
				updateUser.close();
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
	
	public synchronized Vector<GarbageBagOffer> getOfferGivenRecords(
			Connection conn, User user) {
		Vector<GarbageBagOffer> list = new Vector<GarbageBagOffer>();
		String sql = "SELECT id, offered_by, offered_on, offered_to, "
				+ "amount, accepted_on, has_accepted "
				+ "FROM garbage_bag_tradings WHERE offered_by = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ps.setString(1, user.getId());
				ResultSet rs = ps.executeQuery();
				try {
					UserHelper userHelper = UserHelper.getInstance();
					GarbageBagOffer offer = null;
					while (rs.next()) {
						offer = new GarbageBagOffer();
						offer.setId(rs.getString("id"));
						offer.setOfferedBy(userHelper.getUser(conn, rs.getString("offered_by")));
						offer.setOfferedOn(rs.getTimestamp("offered_on"));
						offer.setOfferedTo(userHelper.getUser(conn, rs.getString("offered_to")));
						offer.setAmount(rs.getInt("amount"));
						offer.setAcceptedOn(rs.getTimestamp("accepted_on"));
						offer.setHasAccepted(rs.getInt("has_accepted"));
						list.add(offer);
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

	public synchronized Vector<GarbageBagOffer> getOfferReceivedRecords(
			Connection conn, User user) {
		Vector<GarbageBagOffer> list = new Vector<GarbageBagOffer>();
		String sql = "SELECT id, offered_by, offered_on, offered_to, "
				+ "amount, accepted_on, has_accepted "
				+ "FROM garbage_bag_tradings WHERE offered_to = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ps.setString(1, user.getId());
				ResultSet rs = ps.executeQuery();
				try {
					UserHelper userHelper = UserHelper.getInstance();
					GarbageBagOffer offer = null;
					while (rs.next()) {
						offer = new GarbageBagOffer();
						offer.setId(rs.getString("id"));
						offer.setOfferedBy(userHelper.getUser(conn, rs.getString("offered_by")));
						offer.setOfferedOn(rs.getTimestamp("offered_on"));
						offer.setOfferedTo(userHelper.getUser(conn, rs.getString("offered_to")));
						offer.setAmount(rs.getInt("amount"));
						offer.setAcceptedOn(rs.getTimestamp("accepted_on"));
						offer.setHasAccepted(rs.getInt("has_accepted"));
						list.add(offer);
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
	
	public synchronized GarbageBagOffer getOffer(Connection conn, 
			String offerId) {
		GarbageBagOffer offer = null;
		String sql = "SELECT id, offered_by, offered_on, offered_to, "
				+ "amount, accepted_on, has_accepted "
				+ "FROM garbage_bag_tradings WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			try {
				ps.setString(1, offerId);
				ResultSet rs = ps.executeQuery();
				try {
					UserHelper userHelper = UserHelper.getInstance();
					if (rs.next()) {
						offer = new GarbageBagOffer();
						offer.setId(rs.getString("id"));
						offer.setOfferedBy(userHelper.getUser(conn, rs.getString("offered_by")));
						offer.setOfferedOn(rs.getTimestamp("offered_on"));
						offer.setOfferedTo(userHelper.getUser(conn, rs.getString("offered_to")));
						offer.setAmount(rs.getInt("amount"));
						offer.setAcceptedOn(rs.getTimestamp("accepted_on"));
						offer.setHasAccepted(rs.getInt("has_accepted"));
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
		return offer;
	}

	public synchronized boolean acceptOffer(Connection conn, 
			GarbageBagOffer offer) {
		String sql1 = "UPDATE garbage_bag_tradings SET accepted_on = NOW(), "
				+ "has_accepted = 1 WHERE id = ?";
		String sql2 = "UPDATE users SET bags_remaining = ? WHERE user_id = ?";
		boolean isSuccess = true;
		try {
			conn.setAutoCommit(false);
			PreparedStatement updateTrading = conn.prepareStatement(sql1);
			PreparedStatement updateUser = conn.prepareStatement(sql2);
			try {
				updateTrading.setString(1, offer.getId());
				updateTrading.executeUpdate();
				
				User offeredTo = offer.getOfferedTo();
				updateUser.setInt(1, offeredTo.getBagsRemaining() + offer.getAmount());
				updateUser.setString(2, offeredTo.getId());
				updateUser.executeUpdate();
				
				conn.commit();
				
			} catch (SQLException e) {
				e.printStackTrace();
				isSuccess = false;
				conn.rollback();
			} finally {
				updateTrading.close();
				updateUser.close();
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
	
	public synchronized boolean cancelOffer(Connection conn, 
			GarbageBagOffer offer) {
		String sql1 = "DELETE FROM garbage_bag_tradings WHERE id = ?";
		String sql2 = "UPDATE users SET bags_remaining = ? WHERE user_id = ?";
		boolean isSuccess = true;
		try {
			conn.setAutoCommit(false);
			PreparedStatement deleteTrading = conn.prepareStatement(sql1);
			PreparedStatement updateUser = conn.prepareStatement(sql2);
			try {
				deleteTrading.setString(1, offer.getId());
				deleteTrading.executeUpdate();
				
				User offeredBy = offer.getOfferedBy();
				updateUser.setInt(1, offeredBy.getBagsRemaining() + offer.getAmount());
				updateUser.setString(2, offeredBy.getId());
				updateUser.executeUpdate();
								
				conn.commit();
				
			} catch (SQLException e) {
				e.printStackTrace();
				isSuccess = false;
				conn.rollback();
			} finally {
				deleteTrading.close();
				updateUser.close();
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
