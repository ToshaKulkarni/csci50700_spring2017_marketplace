

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote {
	@RequiresRole("admin")   //ensures that only admin can perform update function
    public void update(Session session) throws RemoteException;
	
	@RequiresRole("customer")   //ensures that only customer can perform rateproduct function
    public void rateproduct(Session session) throws RemoteException;
	
	public Session processLogin(String request,String response) throws RemoteException;
	
	public List<List<String>> BrowseItems() throws RemoteException;
	
	public List<List<String>> checkout() throws RemoteException;
	
	public boolean BuyItems(int itemCount, int qty, String string) throws RemoteException;
	public boolean RemoveItem(String itemName)throws RemoteException;
	public boolean UpdateItems(int itemid, String name, int quantity, double price)throws RemoteException;
	
	
//public List<String> getAllItems() throws RemoteException;



}