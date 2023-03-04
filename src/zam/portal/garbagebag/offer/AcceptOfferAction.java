/**
 *
 * @author Shaiful Nizam
 * @since 2020-07-09
 * @version 2020-07-09
 */
package zam.portal.garbagebag.offer;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.framework.object.dialogbox.DialogBox;
import zam.framework.object.dialogbox.MessageBox;
import zam.portal.helper.UserHelper;
import zam.portal.object.GarbageBagOffer;

public class AcceptOfferAction extends FrameworkAction {

	public AcceptOfferAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		Helper helper = Helper.getInstance();
		Connection conn = getConnection();
		GarbageBagOffer offer = helper.getOffer(conn, getParameter("offer_id"));
		DialogBox dbox = new MessageBox();
		if (helper.acceptOffer(conn, offer)) {
			UserHelper userHelper = UserHelper.getInstance();
			request.getSession(false).setAttribute("CURRENT_USER", 
					userHelper.getUser(conn, offer.getOfferedTo().getId()));
			dbox.setTitle(MessageBox.SUCCESSFUL);
			dbox.setText("Offer has been accepted.");			
		} else {
			dbox.setTitle(MessageBox.ERROR);
			dbox.setText("An error occured. Unable to accept offer. "
					+ "Please try again.");						
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("DIALOG_BOX", dbox);
		return request.getRequestDispatcher("/GarbageBag/Offer/GetRecords.do");
	}

}
