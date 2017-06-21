import java.rmi.RemoteException;
//Activity checkout
public class Checkout implements Activity {

	Items item;
	ServerInterface server;
	  public Checkout(Items item, ServerInterface server) {
	    this.item = item;
	    this.server = server;
	  }
	 
	   
	  
	@Override
	public void execute() throws RemoteException {
		// TODO Auto-generated method stub
		 item.Checkout(server);	
	}

}
