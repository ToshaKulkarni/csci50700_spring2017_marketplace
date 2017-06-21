import java.util.Scanner;
import java.rmi.RemoteException;
/**
 * Generic Customer View
 */
public class CustomerView {
	private ServerInterface  server;
	// Generic method for displaying the Customer View...
	public void showView(ServerInterface server) {
		this.server = server;
		try{
		Scanner sc=new Scanner(System.in);
		int n =0;
		do{
		System.out.println("Welcome to the Customer Page!");
		System.out.println("Choose operation to perform");
		System.out.println("1.BrowseItems");
		System.out.println("2.BuyItems");
		System.out.println("3. CheckOut");
		System.out.println("4.Exit!!!");
		n=sc.nextInt();
		ActivityControl act=new ActivityControl ();
		Items item=new Items();
		Activity browse = new BrowseItems(item,server);
		Activity buy = new BuyItems(item, server);
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
			
			  act.setActivity(checkout);
			   act.choose(); 
			}
		
		}
		
		while(n!=4);
		} catch(RemoteException e){
			System.out.println("inside customer view");
		}
	}
}