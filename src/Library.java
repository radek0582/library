
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Library extends JFrame implements ActionListener {
	private JPanel rootPanel = new JPanel();
	private JPanel bookPanel = new JPanel();
	private JPanel userPanel = new JPanel();
	private JPanel borrowingPanel = new JPanel();
	private JPanel statusPanel = new JPanel();
	BookDaoMySQL bookDao = new BookDaoMySQL();
	UserDaoMySQL userDao = new UserDaoMySQL();

	private String title = "";
	private String author = "";
	private String name = "";
	private String surname = "";
	private int id;
	int pages = 0;
	int year = 1900;
	int yob = 1950;

	Button addBookButton = new Button(1);
	Button listBookButton = new Button(2);
	Button findBookButton = new Button(5);
	Button delBookButton = new Button(6);
	
	Button addUserButton = new Button(3);
	Button listUserButton = new Button(4);

	Library(BookDaoMySQL bookDao) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(700, 400));
		setContentPane(rootPanel);
		bookPanel.setPreferredSize(new Dimension(300, 250));
		userPanel.setPreferredSize(new Dimension(200, 250));
		borrowingPanel.setPreferredSize(new Dimension(100, 200));
		statusPanel.setPreferredSize(new Dimension(700, 200));

		bookPanel.add(addBookButton);
		addBookButton.setAddBook();
		addBookButton.addActionListener(this);
		
		bookPanel.add(delBookButton);
		delBookButton.setDelBook();
		delBookButton.addActionListener(this);
		
		bookPanel.add(findBookButton);
		findBookButton.setFindBook();
		findBookButton.addActionListener(this);

		bookPanel.add(listBookButton);
		listBookButton.setListBook();
		listBookButton.addActionListener(this);
		
		userPanel.add(addUserButton);
		addUserButton.setAddUser();
		addUserButton.addActionListener(this);
		
		userPanel.add(listUserButton);
		listUserButton.setListUser();
		listUserButton.addActionListener(this);

		getContentPane().add(bookPanel);
		getContentPane().add(userPanel);
		getContentPane().add(statusPanel);
		statusPanel.setLocation(10, 300);
		pack();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source instanceof Button) {
			Button button = (Button) source;

			int choice = button.getButtonId();

			if (choice == 1) {
				if (optionPanel("book", "Title", "Author", "Pages", "Year") == 1) {
					bookDao.addBook(title, author, pages, year);
					putToStatusPanel ("Added a book " + title + ", "+ author);
				}
			} else if (choice == 2) {
				listAllBooks(bookDao.listBooks());
			}
			else if (choice == 3) {
				
				if (optionPanel("user", "Name", "Surname", "Year of birth") == 1) {
					userDao.addUser(name, surname, yob);
					putToStatusPanel ("Added a user " + name + ", "+ surname);
				}
			}
			else if (choice == 4) {
				listAllUsers(userDao.listUsers());
			}
			else if (choice == 5) {
				if (optionPanel("find book", "Title", "Author") == 1){
					listAllBooks(bookDao.findBooks(title, author));
					putToStatusPanel ("Found a book.");
				}
			}
			else if (choice == 6) {
				if (optionPanel("delete book", "ID") == 1){
					bookDao.deleteBook(id);
					putToStatusPanel ("Book erased.");
				}
			}
		}
	}

	public void listAllBooks(List<Book> ll) {
		Map<Integer, Book> map = new HashMap<Integer, Book>();
		ArrayList<String> listToSend = new ArrayList<String>();

		for (Book i : ll)
			map.put(i.getId(), i);

		Iterator<Map.Entry<Integer, Book>> it = map.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<Integer, Book> entry = it.next();

			Book book = entry.getValue();
			Integer count = entry.getKey();

			System.out.println(book.toString());
			listToSend.add(Integer.toString(book.getId()) + ". " + book.getTitle() + ", " + book.getAuthor() + ", "
			+ book.getPages() + ", " + book.getYear());
			// listToSend.add(book.getAuthor());
		}
		DisplayGuiHelp gui = new DisplayGuiHelp(listToSend);
	}
	
	public void listAllUsers(List<User> ll) {
		Map<Integer, User> map = new HashMap<Integer, User>();
		ArrayList<String> listToSend = new ArrayList<String>();

		for (User i : ll)
			map.put(i.getId(), i);

		Iterator<Map.Entry<Integer, User>> it = map.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<Integer, User> entry = it.next();

			User user = entry.getValue();
			Integer count = entry.getKey();

			System.out.println(user.toString());
			listToSend.add(Integer.toString(user.getId()) + ". " + user.getName() + ", " + user.getSurname() + ", "
					+ user.getYob());
			// listToSend.add(book.getAuthor());
		}
		DisplayGuiHelp gui = new DisplayGuiHelp(listToSend);
	}

	public void putToStatusPanel(String txt) {
		statusPanel.removeAll();
		JLabel text = new JLabel(txt);
		statusPanel.add(text);
		statusPanel.revalidate();
	}

	public int optionPanel(String panelName, String... string) {
		JTextField _0Field = new JTextField(5);
		JTextField _1Field = new JTextField(5);
		JTextField _2Field = new JTextField(5);
		JTextField _3Field = new JTextField(5);

		JPanel myPanel = new JPanel();
		
		myPanel.add(new JLabel(string[0]));
		myPanel.add(_0Field);
		
		if (panelName != "delete book"){
			myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			myPanel.add(new JLabel(string[1]));
			myPanel.add(_1Field);
		}
		
		if (panelName != "find book" && panelName != "delete book"){
			myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			myPanel.add(new JLabel(string[2]));
			myPanel.add(_2Field);
		}
		
		if (panelName == "book"){
			myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			myPanel.add(new JLabel(string[3]));
			myPanel.add(_3Field);
		}

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please enter " + panelName + " data.",
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			if (panelName == "book"){
				title = _0Field.getText();
				author = _1Field.getText();
				pages = Integer.parseInt(_2Field.getText());
				year = Integer.parseInt(_3Field.getText());
			}
			else if (panelName == "find book"){
				title = _0Field.getText();
				author = _1Field.getText();
			}
			else if (panelName == "user"){
				name = _0Field.getText();
				surname = _1Field.getText();
				yob = Integer.parseInt(_2Field.getText());
			}
			else if (panelName == "delete book"){
				id = Integer.parseInt(_0Field.getText());
			}
		} else
			return -1;

		return 1;
	}
}
