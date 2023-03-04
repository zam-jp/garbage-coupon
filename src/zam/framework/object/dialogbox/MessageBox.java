/**
 *
 * @author Shaiful Nizam
 * @since 2019-06-12
 * @version 2019-06-12
 */
package zam.framework.object.dialogbox;

import java.io.Serializable;

public class MessageBox extends DialogBox implements Serializable {
	public static String WARNING = "Warning";
	public static String ATTENTION = "Attention";
	public static String SUCCESSFUL = "Successful";
	public static String NOT_SUCCESSFUL = "Not Successful";
	public static String ERROR = "Error";
	
	public MessageBox() {
		super(new UnOrderedBulletList());
	}
	
	public MessageBox(BulletList bulletList) {
		super(bulletList);
	}

}
