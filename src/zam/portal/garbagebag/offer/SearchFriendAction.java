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

import org.apache.commons.lang.StringUtils;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.portal.helper.UserHelper;
import zam.portal.object.User;

public class SearchFriendAction extends FrameworkAction {

	public SearchFriendAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		String userId = getParameter("user_id");
		Vector<User> searchResult = new Vector<User>();
		if (StringUtils.isNotEmpty(userId)) {
			Connection conn = getConnection();
			UserHelper helper = UserHelper.getInstance();
			searchResult = helper.searchUser(conn, userId);
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("SEARCH_RESULT", searchResult);
		return request.getRequestDispatcher("/portal/garbage_bag/offer/search_friend.jsp");
	}

}
