/**
 *
 * @author Shaiful Nizam
 * @since 2020-06-28
 * @version 2020-06-28
 */
package zam.portal.object;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Vector;

public class GarbageBagRequest implements Serializable {
	private String id;
	private Vector<GarbageBag> listOfBags;
	private User requestedBy;
	private Timestamp requestedOn;
	private User processedBy;
	private Timestamp processedOn;
	private boolean hasProcessed;
	
	
	public GarbageBagRequest() {
		id = "";
		listOfBags = new Vector<GarbageBag>();
		requestedBy = null;
		requestedOn = null;
		processedBy = null;
		processedOn = null;
		hasProcessed = false;
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

	public Vector<GarbageBag> getListOfBags() {
		return listOfBags;
	}

	public void setListOfBags(Vector<GarbageBag> listOfBags) {
		if (listOfBags == null) {
			this.listOfBags = new Vector<GarbageBag>();
		} else {
			this.listOfBags = listOfBags;
		}
	}

	public User getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(User requestedBy) {
		this.requestedBy = requestedBy;
	}

	public Timestamp getRequestedOn() {
		return requestedOn;
	}

	public void setRequestedOn(Timestamp requestedOn) {
		this.requestedOn = requestedOn;
	}

	public User getProcessedBy() {
		return processedBy;
	}

	public void setProcessedBy(User processedBy) {
		this.processedBy = processedBy;
	}

	public Timestamp getProcessedOn() {
		return processedOn;
	}

	public void setProcessedOn(Timestamp processedOn) {
		this.processedOn = processedOn;
	}

	public boolean hasProcessed() {
		return hasProcessed;
	}

	public void setHasProcessed(boolean hasProcessed) {
		this.hasProcessed = hasProcessed;
	}

}
