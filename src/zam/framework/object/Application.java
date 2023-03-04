/**
 * @author Shaiful Nizam
 * @since 2019-06-08
 * @version 2019-06-08
 */
package zam.framework.object;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Application implements Serializable {

	private String id;
	private String name;
	private String description;
	private String servletName;
	private String iconURL;
	private Timestamp registeredOn;
	private String registeredBy;
	private Timestamp updatedOn;
	private String updatedBy;
	
	public Application() {
		id = "";
		name = "";
		servletName = "";
		iconURL = "";
		description = "";
		registeredOn = null;
		registeredBy = "";
		updatedOn = null;
		updatedBy = "";
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if (name == null) {
			this.name = "";
		} else {
			this.name = name;
		}
	}
	public String getServletName() {
		return servletName;
	}
	public void setServletName(String servletName) {
		if (servletName == null) {
			this.servletName = "";
		} else {
			this.servletName = servletName;
		}
	}
	public String getIconURL() {
		return iconURL;
	}
	public void setIconURL(String iconURL) {
		if (iconURL == null) {
			this.iconURL = "";
		} else {
			this.iconURL = iconURL;
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

	public Date getRegisteredOn() {
		return registeredOn;
	}

	public void setRegisteredOn(Timestamp registeredOn) {
		this.registeredOn = registeredOn;
	}
	
	public String getRegisteredBy() {
		return registeredBy;
	}

	public void setRegisteredBy(String registeredBy) {
		if (registeredBy == null) {
			this.registeredBy = "";
		} else {
			this.registeredBy = registeredBy;
		}
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		if (updatedBy == null) {
			this.updatedBy = "";
		} else {
			this.updatedBy = updatedBy;
		}
	}
}
