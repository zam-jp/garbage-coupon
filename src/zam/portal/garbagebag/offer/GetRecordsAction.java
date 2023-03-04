/**
 *
 * @author Shaiful Nizam
 * @since 2020-07-08
 * @version 2020-07-08
 */
package zam.portal.garbagebag.offer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.portal.object.GarbageBagOffer;
import zam.portal.object.User;

public class GetRecordsAction extends FrameworkAction {

	public GetRecordsAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		User user = (User) request.getSession(false).getAttribute("CURRENT_USER");
		Helper helper = Helper.getInstance();
		Connection conn = getConnection();
		Vector<GarbageBagOffer> offersGiven = helper.getOfferGivenRecords(conn, user);
		Vector<GarbageBagOffer> offersReceived = helper.getOfferReceivedRecords(conn, user);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("OFFERS_GIVEN", offersGiven);
		request.setAttribute("OFFERS_RECEIVED", offersReceived);
		return request.getRequestDispatcher("/portal/garbage_bag/offer_records/main.jsp");
	}

}
