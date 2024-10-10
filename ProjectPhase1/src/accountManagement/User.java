package accountManagement;

public class User {
	private String username;
	private String password;
	private String email;
	

	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		
	}
	
	public void login() {
		
	}
	
	//method to create your account
	public void setUpAccount() {
		
	}
	
	//method to make a new password
	public void resetPassword(String newPassword) {
		this.password = newPassword;
		
	}
}