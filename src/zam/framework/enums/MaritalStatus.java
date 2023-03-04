/**
 * @author Shaiful Nizam
 * @since 2019-06-08
 * @version 2019-06-08
 */
package zam.framework.enums;

public enum MaritalStatus {
	SINGLE("Single"),
	MARRIED("Married"),
	UNSPECIFIED("Unspecified");
	
	private String string;
	
	MaritalStatus(String string) {
		this.string = string;
	}
	
	public String getString() {
		return string;
	}
}
