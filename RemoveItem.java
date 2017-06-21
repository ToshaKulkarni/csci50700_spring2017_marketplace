import java.rmi.RemoteException;
//Activity Remove Item
public class RemoveItem implements Activity {
	Items item;
	ServerInterface server;
	  public RemoveItem(Items item, ServerInterface server) {
	    this.item = item;
	    this.server = server;
	  }
	  public void execute()throws RemoteException{
	    item.RemoveItem(server);
	  }
}
