/**
 *
 * @author Shaiful Nizam
 * @since 2019-06-12
 * @version 2019-06-12
 */
package zam.framework.object.dialogbox;

import java.io.Serializable;

public class DeleteBox extends DialogBox implements Serializable {
	static String CONFIRM = "Confirm";
	
	public DeleteBox(BulletList bulletList) {
		super(bulletList);
	}

}
