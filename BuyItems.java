import java.rmi.RemoteException;
//Activity BuyItem
public class BuyItems implements Activity{
  //reference to the item
  Items item;
  private ServerInterface  server;
  public BuyItems(Items item, ServerInterface server){
    this.item = item; //points item to the current item referernce
    this.server = server;
  }
  public void execute()throws RemoteException{
    item.BuyItems(server);
  }
}
