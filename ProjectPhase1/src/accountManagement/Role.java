package accountManagement;

public class Role {
	private String roleName;
	

public Role(String roleName){
	this.roleName = roleName;
	
}

public String getRole() {
	return roleName;
}

public void assignRole(String newRoleName) {
	this.roleName = newRoleName;
}

	
	
	

}
