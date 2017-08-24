import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLDB {
	private Connection connection;
	
	private static final String URL = "jdbc:mysql://localhost:3306/library?autoReconnect=true&useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "radzio7";
	
	private static MySQLDB instance;
	
	public static MySQLDB getInstance (){
		if (instance == null)
			instance = new MySQLDB();
		
		return instance;
	}
	
	public Connection getConnection (){
		return this.connection;
	}
	
	private MySQLDB (){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
