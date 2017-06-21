

import java.util.*;


import java.rmi.RemoteException;




public class Items {
private boolean buy;

public  synchronized boolean BuyItems(ServerInterface server)  {                    //both admin and customer can perform this                                    

	try
	{
	List<List<String>> items= BrowseItems(server);
	Scanner sc = new Scanner(System.in);

	System.out.println("Enter the item to purchase");    //take input from user for the item to purchase
	String itemname=sc.next();

	System.out.println("Enter the quantity of item to purchase");   //take input from the user for the qty of item to be purchased
	int qty=sc.nextInt();
   //open and read file

int quantity=0;

double price=0.0;
List<String> lines = new ArrayList<String>();

for(List<String> itemDetails:  items)
   {    
	
	if(itemDetails.get(1).equals(itemname))         //compare the validity of input with the item present in the store(text file)
	{
	
		
			quantity = (int)Double.parseDouble(itemDetails.get(2));
			
		
		
	

	price=Double.parseDouble( itemDetails.get(3));
	
	System.out.println(itemname+", "+ qty +", "+ price);

	 if( qty<=(quantity) && quantity!=0)  //ensure that the quantity of item to be bought is available in the marketplace
	 {
		 server.BuyItems(qty,quantity-qty, itemDetails.get(0)  );
		 
		System.out.println("Item Added in cart : "+itemname);
		System.out.println("Quantity of the product : "+qty);
		System.out.println("Total price : "+ (qty*price));
		System.out.println("Go to cart for checkout");
		//String data = itemname+"              "+(quantity-qty)+"        "+price;  //reduce the qty in the marketplace 
		
		buy = true;
		
	 }
     else
	{
		System.out.println("Not enough items in storage");    //if the quantity to be bought from the marketplace is not enough
		buy= false;
		
	}
	
	}
}
	
} 
catch( Exception e){
e.printStackTrace();
}
return buy;
	}
	public synchronized List<List<String>> BrowseItems(ServerInterface server)  {
	 try{
	 List<List<String>> items=server.BrowseItems();
	 if(items.size()==0)
	{
		System.out.println("No items to display");
	}
	else
	{
	  for(List<String> item:items )
		System.out.println(item.get(1)+"		 "+	item.get(2)+" 			"+item.get(3)		);
	}
		
         return items;
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	 return  null;
	 
	  }

	  public synchronized boolean UpdateItems(ServerInterface server){
try{
	
	List<List<String>> items= BrowseItems(server);
	Scanner sc = new Scanner(System.in);
	
	

	System.out.println("Enter the item to update");    //take input from user for the item to purchase
	String itemname=sc.next();

	int quantity=0;
	
	double price=0;
	List<String> lines = new ArrayList<String>();
	for(List<String> str: items)
	{    
	
       String  item = str.get(1);
	if(itemname.equals(item))         //compare the validity of input with the item present in the store(text file)
	{

		quantity = (int)Double.parseDouble(str.get(2));
		
		price=Double.parseDouble( str.get(3));
	int qty = 0;
	System.out.println("Enter the your choice to update quantity -'Y/N'");   //take input from the user for the qty of item to be purchased
	String choice = sc.next();
	if(choice.equals("Y"))
	{
		System.out.println("Enter quantity for this item");
		qty=sc.nextInt();
	
	}
	System.out.println("Enter the your choice to update price -'Y/N'"); 
	choice = sc.next();
	double prc =0;
	if(choice.equals("Y"))
	{
		System.out.println("Enter price for this item");
		prc=sc.nextDouble();
	
	}
		if(prc==0)
		prc = price;
		if(qty==0)
		qty = quantity;
		
		server.UpdateItems(Integer.parseInt(str.get(0)), item, qty, prc);
		
		System.out.println("Item updated: "+item);
		System.out.println("Quantity of the product : "+qty);
	
		buy = true;
		
		
	
	}

}
	if(!buy)                                          
        {
		System.out.println("Item not found, Enter quantity for this new item");
		
		int qty=sc.nextInt();

		System.out.println("Enter price for this item");
		double prc = sc.nextDouble();
		server.UpdateItems(-1, itemname, qty, prc); 
		System.out.println("New item :" +itemname+" added");
		

	}                                                   //update marketplace after successful purchase
}catch(Exception e)
{
}

return buy;

	  }
	  public boolean RemoveItem(ServerInterface server)throws RemoteException {
		  Scanner sc = new Scanner(System.in);
		  System.out.println("Enter the item to remove");
		  String itemName=sc.next();
		  if(server.RemoveItem(itemName))
			  System.out.println("Item deleted successfully");
		  else
			  System.out.println("Item Does not exist");
		  return false;
	  }
	public void Checkout(ServerInterface server) {
		// TODO Auto-generated method stub
		
		try {
			List<List<String>> res=server.checkout();
			if(res.size()==0)
			System.out.println("Cart is Empty");
			else
			{
				System.out.println("Cart Items are:");
				double total =0;
				System.out.println("Item Name                count             price");
				for(List<String> str: res)
				{
					System.out.print(str.get(0)+"                 ");
					int count = Integer.parseInt(str.get(1));
					double price = Double.parseDouble(str.get(2));
					total += count* price;
					
					System.out.println(count+"              "+ count*price	);
				}
				System.out.println("Total Amount = "+ total);
				
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

