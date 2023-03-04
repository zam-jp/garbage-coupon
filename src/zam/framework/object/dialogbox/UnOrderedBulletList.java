/**
 *
 * @author Shaiful Nizam
 * @since 2020-06-19
 * @version 2020-06-19
 */
package zam.framework.object.dialogbox;

public class UnOrderedBulletList extends BulletList {

	@Override
	public String parseList() {
		String msg = "<ul>";
		for (String s : list) {
			msg = msg + s;
		}
		msg = msg + "</ul>";
		return msg;
	}
}
