/**
 *
 * @author Shaiful Nizam
 * @since 2020-07-08
 * @version 2020-07-08
 */
package zam.portal.object;

import java.io.Serializable;
import java.sql.Timestamp;

public class GarbageBagOffer implements Serializable {
	private String id;
	private User offeredBy;
	private User offeredTo;
	private Timestamp offeredOn;
	private int amount;
	private Timestamp acceptedOn;
	private boolean hasAccepted;
	
	public GarbageBagOffer() {
		id = "";
		offeredBy = null;
		offeredTo = null;
		offeredOn = null;
		amount = 0;
		acceptedOn = null;
		hasAccepted = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id == null) {
			this.id = "";
		} else {
			this.id = id;
		}
	}

	public User getOfferedBy() {
		return offeredBy;
	}

	public void setOfferedBy(User offeredBy) {
		this.offeredBy = offeredBy;
	}

	public User getOfferedTo() {
		return offeredTo;
	}

	public void setOfferedTo(User offeredTo) {
		this.offeredTo = offeredTo;
	}

	public Timestamp getOfferedOn() {
		return offeredOn;
	}

	public void setOfferedOn(Timestamp offeredOn) {
		this.offeredOn = offeredOn;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Timestamp getAcceptedOn() {
		return acceptedOn;
	}

	public void setAcceptedOn(Timestamp acceptedOn) {
		this.acceptedOn = acceptedOn;
	}

	public boolean hasAccepted() {
		return hasAccepted;
	}

	public void setHasAccepted(boolean hasAccepted) {
		this.hasAccepted = hasAccepted;
	}

	public void setHasAccepted(int hasAccepted) {
		if (hasAccepted == 1) {
			this.hasAccepted = true;
		} else {
			this.hasAccepted = false;
		}
	}
}
