/**
 *
 * @author Shaiful Nizam
 * @since 2020-07-08
 * @version 2020-07-08
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
import zam.portal.object.User;

public class SubmitFormAction extends FrameworkAction {

	public SubmitFormAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		int amount = Integer.parseInt(getParameter("amount"));
		UserHelper userHelper = UserHelper.getInstance();
		Connection conn = getConnection();
		User offerTo = userHelper.getUser(conn, getParameter("offer_to"));
		String url = "";
		if (amount == 0) {
			DialogBox dbox = new MessageBox();
			dbox.setTitle(MessageBox.ATTENTION);
			dbox.setText("Amount to be offered cannot be zero.");			
			request.setAttribute("DIALOG_BOX", dbox);
			request.setAttribute("OFFER_TO", offerTo);
			url = "/portal/garbage_bag/offer/form.jsp";
		} else {
			User offerBy = (User) request.getSession(false).getAttribute("CURRENT_USER");
			Helper helper = Helper.getInstance();
			DialogBox dbox = new MessageBox();
			if (helper.createOffer(conn, offerBy, offerTo, amount)) {
				request.getSession(false).setAttribute("CURRENT_USER", 
						userHelper.getUser(conn, offerBy.getId()));
				dbox.setTitle(MessageBox.SUCCESSFUL);
				dbox.setText("Offer has been successfully created.");							
			} else {
				dbox.setTitle(MessageBox.WARNING);
				dbox.setText("An error has occured. Unable to create the "
						+ "Offer. Please try again.");											
			}
			request.setAttribute("DIALOG_BOX", dbox);
			url = "/GarbageBag/Offer/GetRecords.do";
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request.getRequestDispatcher(url);
	}

}
