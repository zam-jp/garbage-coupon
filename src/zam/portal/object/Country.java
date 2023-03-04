/**
 * @author Shaiful Nizam
 * @since 2019-06-08
 * @version 2019-06-08
 */
package zam.portal.object;

import java.io.Serializable;

public class Country implements Serializable {

	private String id;
	private String name;
	private String code;
	
	public Country() {
		id = "";
		name = "";
		code = "";
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (code == null) {
			this.code = "";
		} else {
			this.code = code;
		}
	}
}
