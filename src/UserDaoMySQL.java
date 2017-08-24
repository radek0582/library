import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;



public class UserDaoMySQL {
	private MySQLDB mySQLDB;

	UserDaoMySQL() {
		this.mySQLDB = mySQLDB.getInstance();
		this.createTable();
	}

	public void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS USERS(" + "id INTEGER PRIMARY KEY AUTO_INCREMENT, "
				+ "name TEXT NOT NULL, " + "surname TEXT NOT NULL, " + "yob INTEGER DEFAULT 1950"
				+ ")";

		try {
			Statement s = mySQLDB.getConnection().createStatement();
			s.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addUser(String name, String surname, int yob) {
		User user = new User(name, surname, yob);

		name = user.getName();
		surname = user.getSurname();
		yob = user.getYob();

		String sql = "INSERT INTO USERS (name, surname, yob) VALUES(" + "'" + name + "','" + surname + "',"
				+ yob + ")";

		try {
			Statement s = mySQLDB.getConnection().createStatement();
			s.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<User> listUsers(){
		String sql = "SELECT * FROM Users";
		
        try {
            Statement s = mySQLDB.getConnection().createStatement();
            ResultSet rs = s.executeQuery(sql);

            List<User> ll = new LinkedList<User>();
            
            // Fetch each row from the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int yob = rs.getInt("yob");

                User u = new User(name, surname, yob);
                u.setId(id);

                ll.add(u);
            }
            return ll;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
}
