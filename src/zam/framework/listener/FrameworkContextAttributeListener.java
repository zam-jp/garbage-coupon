/**
 *
 * @author Shaiful Nizam
 * @since 2020-06-19
 * @version 2020-06-19
 */
package zam.framework.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class FrameworkContextAttributeListener implements ServletContextAttributeListener {

	public void attributeAdded(ServletContextAttributeEvent event) {
		System.out.println("["+event.getName()+"] has been added to the servlet context...");
	}
	
	public void attributeRemoved(ServletContextAttributeEvent event) {
		System.out.println("["+event.getName()+"] has been removed from the servlet context...");		
	}
}
