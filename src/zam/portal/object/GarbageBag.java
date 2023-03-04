/**
 *
 * @author Shaiful Nizam
 * @since 2020-06-28
 * @version 2020-06-28
 */
package zam.portal.object;

import java.io.Serializable;

public class GarbageBag implements Serializable {
	private int amount;
	private String requestId;
	private String type;
	private String hexColor;
	
	public GarbageBag() {
		amount = 0;
		requestId = "";
		type = "";
		hexColor = "";
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		if (requestId == null) {
			this.requestId = "";
		} else {
			this.requestId = requestId;
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (type == null) {
			this.type = "";
		} else {
			this.type = type;
		}
	}

	public String getHexColor() {
		return hexColor;
	}

	public void setHexColor(String hexColor) {
		if (hexColor == null) {
			this.hexColor = "";
		} else {
			this.hexColor = hexColor;
		}
	}
	
}
