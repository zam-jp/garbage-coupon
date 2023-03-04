/**
 *
 * @author Shaiful Nizam
 * @since 2020-06-19
 * @version 2020-06-19
 */
package zam.framework.object.dialogbox;

import java.util.Vector;

public abstract class BulletList {
	protected Vector<String> list;
	
	public BulletList() {
		list = new Vector<String>();
	}
	
	public void addBullet(String text) {
		list.add("<li>"+text+"</li>");
	}
	
	public Vector<String> getList() {
		return list;
	}
	
	abstract public String parseList();
}
