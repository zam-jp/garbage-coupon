/**
 * @author Shaiful Nizam
 * @since 2019-06-08
 * @version 2019-06-08
 */
package zam.portal.object;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Vector;

public class User implements Serializable {

	private String id;
	private String password;
	private String name;
	private String email;
	private String postcode;
	private String address;
	private String city;
	private String prefecture;
	private int householdSize;
	private int bagsRemaining;
	private Timestamp registeredOn;
	private Timestamp lastLogin;
	private Vector<String> roles;

	public User() {
		id = "";
		password = "";
		name = "";
		email = "";
		postcode = "";
		address = "";
		city = "";
		prefecture = "";
		householdSize = 0;
		bagsRemaining = 0;
		registeredOn = null;
		lastLogin = null;
		roles = new Vector<String>();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password == null) {
			this.password = "";
		} else {
			this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null) {
			this.email = "";
		} else {
			this.email = email;
		}
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		if (postcode == null) {
			this.postcode = "";
		} else {
			this.postcode = postcode;
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (address == null) {
			this.address = "";
		} else {
			this.address = address;
		}
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		if (city == null) {
			this.city = "";
		} else {
			this.city = city;
		}
	}

	public String getPrefecture() {
		return prefecture;
	}

	public void setPrefecture(String prefecture) {
		if (prefecture == null) {
			this.prefecture = "";
		} else {
			this.prefecture = prefecture;
		}
	}

	public int getHouseholdSize() {
		return householdSize;
	}

	public void setHouseholdSize(int householdSize) {
		this.householdSize = householdSize;
	}

	public int getBagsRemaining() {
		return bagsRemaining;
	}

	public void setBagsRemaining(int bagsRemaining) {
		this.bagsRemaining = bagsRemaining;
	}

	public Timestamp getRegisteredOn() {
		return registeredOn;
	}

	public void setRegisteredOn(Timestamp registeredOn) {
		this.registeredOn = registeredOn;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Vector<String> getRoles() {
		return roles;
	}

	public void setRoles(Vector<String> roles) {
		if (roles == null) {
			this.roles = new Vector<String>();
		} else {
			this.roles = roles;
		}
	}
}
