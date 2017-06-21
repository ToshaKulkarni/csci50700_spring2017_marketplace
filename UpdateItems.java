//Update Item Activity
public class UpdateItems implements Activity {
	Items item;
	ServerInterface server;
	  public UpdateItems(Items item, ServerInterface server) {
	    this.item = item;
	    this.server = server;
	  }
	  public void execute(){
	    item.UpdateItems(server);
	  }
}
