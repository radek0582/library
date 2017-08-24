
public class App {

	public static void main(String[] args) {
		BookDaoMySQL bookDao = new BookDaoMySQL ();
		Library library = new Library (bookDao);
		
	}

}
