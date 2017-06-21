import java.io.Serializable;

public class Session implements Serializable {
	private static final long serialVersionUID = -6745473220581903527L;
	
	private User user;
	
	
	public Session(String userType, int userId) 
	{
		this.user = new User(userType, userId);
	}
	
	public User getUser() {
		return user;
	}
}