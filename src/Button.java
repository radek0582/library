import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button extends JButton {
	private int buttonId;

	Button(int buttonId) {
		this.buttonId = buttonId;
	}

	public void setAddBook() {
		this.setIcon(getIcon("addBook.png"));
	}
	
	public void setListBook() {
		this.setIcon(getIcon("listBook.png"));
	}
	
	public void setDelBook() {
		this.setIcon(getIcon("bookDel.png"));
	}
	
	public void setFindBook() {
		this.setIcon(getIcon("findBook.png"));
	}
	
	public void setListUser() {
		this.setIcon(getIcon("userList.png"));
	}
	
	public void setAddUser() {
		this.setIcon(getIcon("userAdd.png"));
	}

	public int getButtonId() {
		return buttonId;
	}

	public void setButtonId(int buttonId) {
		this.buttonId = buttonId;
	}

	public ImageIcon getIcon(String path) {
		return new ImageIcon(this.getClass().getResource(path));
	}
}
