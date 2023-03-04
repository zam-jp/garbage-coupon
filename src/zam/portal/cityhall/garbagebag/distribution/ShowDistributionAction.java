/**
 *
 * @author Shaiful Nizam
 * @since 2020-07-11
 * @version 2020-07-11
 */
package zam.portal.cityhall.garbagebag.distribution;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zam.framework.db.NoConnectionException;
import zam.framework.object.FrameworkAction;
import zam.portal.helper.GarbageBagHelper;
import zam.portal.helper.PostalHelper;
import zam.portal.object.GarbageBag;
import zam.portal.object.GarbageBagRequest;

public class ShowDistributionAction extends FrameworkAction {

	public ShowDistributionAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public RequestDispatcher execute() throws NoConnectionException {
		Connection conn = getConnection();
		GarbageBagHelper gbHelper = GarbageBagHelper.getInstance();
		Vector<GarbageBagRequest> requestList = gbHelper.getListOfRequests(conn, true);
		
		Helper helper = Helper.getInstance();
		Hashtable<String,Integer> buckets = helper.prepareBuckets(conn);
		
		PostalHelper posHelper = PostalHelper.getInstance();
		Hashtable<String,String> postalTable = posHelper.getPostalCodeTable(conn);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int subTotal = 0;
		int total = 0;
		String key = "";
		for (GarbageBagRequest bagReq : requestList) {
			total = 0;
			subTotal = 0;
			key = bagReq.getRequestedBy().getPostcode().replace("-", "");
			for (GarbageBag bag : bagReq.getListOfBags()) {
				subTotal = subTotal + bag.getAmount();
			}
			total = buckets.get(key);
			total = total + subTotal;
			buckets.replace(key, total);
		}
		Vector<String> list = new Vector<String>();
		Enumeration<String> postcodes = buckets.keys();
		while (postcodes.hasMoreElements()) {
			key = (String) postcodes.nextElement();
			total = buckets.get(key);
			list.add(total+":"+postalTable.get(key));
		}
		request.setAttribute("LIST_ITEMS", list);
		return request.getRequestDispatcher("/portal/cityhall/garbage_bag/distribution/show_distribution.jsp");
	}

}
