import java.util.Scanner;
import java.rmi.RemoteException;
/**
 * Generic Admin View
 */
public class AdminView {
	private ServerInterface  server;
	// Generic method for displaying the Admin View...
	
	public void showView(ServerInterface server) {
		this.server = server;
		try{
		Scanner sc=new Scanner(System.in);
		System.out.println("Welcome to the Admin Page!");
		int n =0;
		while(n<7){
		System.out.println("Choose operation to perform");
		System.out.println("1.BrowseItems");
		System.out.println("2.BuyItems");
		System.out.println("3.UpdateItems");
		System.out.println("4.Remove Item");
		System.out.println("5.Checkout");
		System.out.println("6. Exit!");
		 n=sc.nextInt();
		ActivityControl act=new ActivityControl ();
		Items item=new Items();
		Activity browse = new BrowseItems(item,server);
		Activity buy = new BuyItems(item,server);
		Activity update = new UpdateItems(item,server);
		Activity remove=new RemoveItem(item,server);
		Activity checkout=new Checkout(item,server);
		if(n==1){
			
		  act.setActivity(browse);
		   act.choose(); 
		}
		if(n==2){
			
			  act.setActivity(buy);
			   act.choose(); 
			}
		if(n==3){
			
			  act.setActivity(update);
			   act.choose(); 
			}
		if(n==4)
		{
			act.setActivity(remove);
			act.choose();
		}
		if(n==5)
		{
			act.setActivity(checkout);
			act.choose();
		}
		if(n==6)
		{
			break;
		}
		}
	}catch(RemoteException e){
		System.out.println("inside admin");
	}
}
}