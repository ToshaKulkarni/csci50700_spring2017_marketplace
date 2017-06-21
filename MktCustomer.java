//Model Class
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
public class MktCustomer {
	private int customerId;  //attributes
	private String name;
	private String address;
	private int userId;
	private String emailId;
	private String password;
	private String payId;
//Defining the getters and setters
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public MktCustomer(int customerId, String name, String address, int userId, String emailId, String password,
			String payId) throws RemoteException {
		super() ;
		this.customerId = customerId;
		this.name = name;
		this.address = address;
		this.userId = userId;
		this.emailId = emailId;
		this.password = password;
		this.payId = payId;
	}
	public MktCustomer() throws RemoteException {
		// TODO Auto-generated constructor stub
	}
}
