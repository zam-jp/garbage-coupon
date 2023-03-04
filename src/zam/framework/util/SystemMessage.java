/**
 *
 * @author Shaiful Nizam
 * @since 2020-06-19
 * @version 2020-06-19
 */
package zam.framework.util;

import java.io.Serializable;

public class SystemMessage implements Serializable {
	private final String PN;
	
	public SystemMessage(String projectName) {
		PN = projectName;
	}
	
	public synchronized String INF(String datetime, String cn) {
		return datetime+" INFO ["+PN+"]["+cn+"] ";
	}

	public synchronized String DBG(String datetime, String cn) {
		return datetime+" DEBUG ["+PN+"]["+cn+"] ";
	}

	public synchronized String ERR(String datetime, String cn) {
		return datetime+" ERROR ["+PN+"]["+cn+"] ";
	}
}
