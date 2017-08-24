import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;



public class BookDaoMySQL {
	private MySQLDB mySQLDB;

	BookDaoMySQL() {
		this.mySQLDB = mySQLDB.getInstance();
		this.createTable();
	}

	public void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS BOOKS(" + "id INTEGER PRIMARY KEY AUTO_INCREMENT, "
				+ "title TEXT NOT NULL, " + "author TEXT NOT NULL, " + "pages INTEGER DEFAULT 0, "
				+ "year INTEGER DEFAULT 1900" + ")";

		try {
			Statement s = mySQLDB.getConnection().createStatement();
			s.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addBook(String author, String title, int pages, int year) {
		Book book = new Book(author, title, pages, year);

		title = book.getTitle();
		author = book.getAuthor();
		year = book.getYear();
		pages = book.getPages();

		String sql = "INSERT INTO BOOKS (title, author, pages, year) VALUES(" + "'" + title + "','" + author + "',"
				+ pages + "," + year + ")";

		try {
			Statement s = mySQLDB.getConnection().createStatement();
			s.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBook (int id) {
		String sql = "DELETE FROM BOOKS WHERE ID = " + id
				+ "SET @a:=0; UPDATE books SET id=@a:=@a+1;";

		try {
			Statement s = mySQLDB.getConnection().createStatement();
			s.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Book> findBooks(String title, String author){
		String sql;
		
		if (author.length() == 0){
			sql = "SELECT * FROM Books WHERE title = '" + title + "'";
		}
		else if (title.length() == 0){
			sql = "SELECT * FROM Books WHERE author = '" + author + "'";
		}
		else{
			sql = "SELECT * FROM Books WHERE author = '" + author + "' and title = '" + title + "'";
		}
		
        try {
            Statement s = mySQLDB.getConnection().createStatement();
            ResultSet rs = s.executeQuery(sql);

            List<Book> ll = new LinkedList<Book>();
            
            // Fetch each row from the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                title = rs.getString("title");
                author = rs.getString("author");
                int pages = rs.getInt("pages");
                int year = rs.getInt("year");

                Book b = new Book(title, author, pages, year);
                b.setId(id);

                ll.add(b);
            }
            return ll;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	public List<Book> listBooks(){
		String sql = "SELECT * FROM Books";
		
        try {
            Statement s = mySQLDB.getConnection().createStatement();
            ResultSet rs = s.executeQuery(sql);

            List<Book> ll = new LinkedList<Book>();
            
            // Fetch each row from the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int pages = rs.getInt("pages");
                int year = rs.getInt("year");

                Book b = new Book(title, author, pages, year);
                b.setId(id);

                ll.add(b);
            }
            return ll;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
}
