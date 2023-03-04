/**
 * @author Shaiful Nizam
 * @since 2019-06-08
 * @version 2019-06-08
 */
package zam.framework.object.dialogbox;

public abstract class DialogBox {

	private String title;
	private String text;
	private BulletList bulletList;

	public DialogBox(BulletList bulletList) {
		title = "";
		text = "";
		this.bulletList = bulletList;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		if (title == null) {
			this.title = "";
		} else {
			this.title = title;
		}
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		if (text == null) {
			this.text = "";
		} else {
			this.text = text;
		}
	}
	
	public void addBullet(String text) {
		bulletList.addBullet(text);
	}
	
	public String getBulletList() {
		return bulletList.parseList();
	}
}
