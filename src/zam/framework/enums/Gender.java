/**
 * @author Shaiful Nizam
 * @since 2019-06-08
 * @version 2019-06-08
 */
package zam.framework.enums;

public enum Gender {
	MALE("Male"),
	FEMALE("Female"),
	UNSPECIFIED("Unspecified");
	
	private String string;
	
	Gender(String string) {
		this.string = string;
	}
	
	public String getString() {
		return string;
	}
}
