package application;

public class UserDto {
	int no;
	String id;
	String password;
	String name;
	String mail;
	
	public UserDto(String id, String password, String name, String mail) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.mail = mail;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	

}
