import java.io.Serializable;
//gets user role
public class User implements Serializable {	
	private static final long serialVersionUID = 8084523177681775893L;

	private String roleType;
	private int userId ;
	public User(String roleType, int userId) {
		this.roleType = roleType;
		this.userId = userId;
	}
	
	public String getRoleType() {
		return roleType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}