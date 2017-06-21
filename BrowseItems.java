//Activity Browse Items
import java.rmi.RemoteException;
public class BrowseItems implements Activity{
	Items item;
	private ServerInterface  server;
	  public BrowseItems(Items item, ServerInterface server) {   
	    this.item = item;           //points item tothe current item reference
	    this.server = server;
	  }
	  
	public void execute() throws RemoteException{
	    item.BrowseItems(server);
	  }
}
