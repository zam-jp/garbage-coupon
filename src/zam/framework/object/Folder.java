/**
 * @author Shaiful Nizam
 * @since 2019-06-08
 * @version 2019-06-08
 */
package zam.framework.object;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

public class Folder implements Serializable {

	private String id;
	private String displayName;
	private String description;
	private String icon;
	private Date createdOn;
	private Vector<Application> listOfApplications;
	private String role;
	
	public Folder() {
		id = "";
		displayName = "";
		description = "";
		icon = "";
		createdOn = Calendar.getInstance().getTime();
		listOfApplications = new Vector<Application>();
		role = "";
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
	
	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		if (displayName == null) {
			this.displayName = "";
		} else {
			this.displayName = displayName;
		}
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(Date createdOn) {
		if (createdOn == null) {
			this.createdOn = Calendar.getInstance().getTime();
		} else {
			this.createdOn = createdOn;
		}
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		if (icon == null) {
			this.icon = "";
		} else {
			this.icon = icon;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null) {
			this.description = "";
		} else {
			this.description = description;
		}
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		if (role == null) {
			this.role = "";
		} else {
			this.role = role;
		}
	}

	public Vector<Application> getListOfApplications() {
		return listOfApplications;
	}

	public void setListOfApplications(Vector<Application> listOfApplications) {
		if (listOfApplications == null) {
			this.listOfApplications = new Vector<Application>();
		} else {
			this.listOfApplications = listOfApplications;
		}
	}
	
	public boolean hasApplication(Application application) {
		boolean flag = false;
		for (Application listItem : listOfApplications) {
			if(StringUtils.equals(application.getId(), listItem.getId())) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
