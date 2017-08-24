
public class User {
	private int id;
	private String name;
	private String surname;
	private int yob;
	
	User (String name, String surname, int yob){
		this.name = name;
		this.surname = surname;
		this.yob = yob;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getYob() {
		return yob;
	}

	public void setYob(int yob) {
		this.yob = yob;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", yob=" + yob + "]";
	}
	
	
}
