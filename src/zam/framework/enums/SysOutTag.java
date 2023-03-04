/**
 * This enum is used to standardize the tags used in messages printed out on
 * the terminal console. 
 * 
 * @author Shaiful Nizam
 * @since 2019-06-08
 * @version 2019-06-08
 */
package zam.framework.enums;

public enum SysOutTag {
	INFO("[INFO] "),
	ACTION("[ACTION] "),
	ERROR("[ERROR] "),
	DEBUG("[DEBUG] ");
	
	private String string;
	
	SysOutTag(String string) {
		this.string = string;
	}
	
	public String getString() {
		return string;
	}
}
