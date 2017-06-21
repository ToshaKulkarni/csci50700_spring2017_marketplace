import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//import  com.mysql.jdbc.*;
//import  com.mysql.jdbc.Connection;

import java.sql.*;
import java.util.*;
//import DatabaseConnector.java;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
	private static final long serialVersionUID = -5485952710128132535L;
	private Session session =null;
	DatabaseConnector db;
	Connection con;
	public ServerImpl() throws RemoteException {
			db = DatabaseConnector.getDbObject();
			con = DatabaseConnector.getDbObject().getConnection();
	}
	//customer accessible function
	public void rateproduct(Session session) {
		this.session= session;
		System.out.println("CustomeReview");		
	}
	//admin accessible function
	public void update(Session session) {
		this.session= session;
		System.out.println("AdminUpdate");		
	}
	//Login function 
	public Session processLogin(String username,String password) {
		Session session = null;	
		//System.out.println("1");
		String role = login(username, password);
		//System.out.println("2");

                 if(role.split(",")[0].equals("customer"))
		   {	session = new Session("customer",Integer.parseInt(role.split(",")[1]));

		         System.out.println("User is authenticated successfully.");
    		}

          else if(role.split(",")[0].equals("admin"))		   {	
			 session = new Session("admin",Integer.parseInt(role.split(",")[1]));

		         System.out.println("User is authenticated successfully.");	
		  }
		         // session = server.processLogin("customer");

		       		//System.out.println("3");

		return session;
	}
	
	public String login(String username, String password)
	{
		String role = null;
		try
		{
		
		if(con!=null)
		{
		 PreparedStatement pst=null;
		 pst=con.prepareStatement("select * from user where name=? and password=?");
		 pst.setString(1,username);
		 pst.setString(2, password);
		 ResultSet rs=pst.executeQuery();
		if(rs.next())
		{
				int id = rs.getInt("id");
				role = rs.getString("role");
				return role+","+id;
	
        }
		//conn.close();
		}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Unable execute query");
		}
		return null;
	}
	  public List<List<String>> BrowseItems(){
	  System.out.println("Item browsing");
	  List<List<String>> items= getAllItems();
	/* if(items.size()==0)
	{
		System.out.println("No items to display");
	}
	else
	{
	  for(String str:items )
		System.out.println(str);
	}*/
		//buy= false;
	  // return buy;
	  
	return items;
}
	  public  List<List<String>> getAllItems()
		{	
			
			
			List<List<String>> res = new ArrayList<List<String>>();
			try {
			if(con !=null){
				System.out.println(" connected ");
				PreparedStatement pst=null;
				
			 pst=con.prepareStatement("select * from item");
			
			 ResultSet rs=pst.executeQuery();
			 
			 while(rs.next())
			 {
				 List<String> items = new ArrayList<String>();
				
				 items.add(String.valueOf(rs.getInt("itemid")));
				 items.add(rs.getString("item"));
				 items.add(String.valueOf(rs.getInt("quantity")));
				 items.add(String.valueOf(rs.getDouble("price")));
				 
				
	 		    
	 		     
	 		     res.add(new ArrayList<String>(items));
	 		     
	 		     

	                }
			}
			else{
			System.out.println("Connection done");

			}
			
	      } catch (Exception e) {
	            e.printStackTrace();
	        }
			return res;
	 	}
	  public  boolean UpdateItems(int itemId, String name, int quantity, double price){
		  boolean status=false;
		 
		  System.out.println("Admin is updating product with  product id :"+ itemId);
		 
			
		 if(itemId==-1)
		 {
			  int id=0;
			  PreparedStatement pst=null;
			  try {
				pst=con.prepareStatement("SELECT itemid FROM item ORDER BY itemid DESC LIMIT 1");

				 ResultSet rs=pst.executeQuery();
				if(rs.next())
				{
						
					id = rs.getInt("itemid");
		        }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				try {
					pst=con.prepareStatement("INSERT INTO item (item, quantity, price, itemid) VALUES (?,?,?,?)");
					pst.setString(1, name);
					pst.setInt(2, quantity);
					pst.setDouble(3, price);
					pst.setInt(4, id+1);
					pst.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					  
			 
			  
			
				
		 }
		 else{
			 
			  try {
					
						
				  PreparedStatement	pst=null;
						
				pst=con.prepareStatement("	update  item set quantity = ?, price = ? where itemid=?");
				pst.setInt(1, quantity);
				pst.setDouble(2, price);
				pst.setInt(3,itemId);
				pst.executeUpdate();	
			      } catch (Exception e) {
			            e.printStackTrace();
			        }
			  
		 }
		  
		  
		  return false;
	  }
	  public   boolean BuyItems(int  itemCount, int qty, String itemId) throws RemoteException
	  {
		  
		  System.out.println(session.getUser().getRoleType()+"is purchasing product with product id"+ itemId);
		  int status =0;
		  try {
				if(con !=null){
					
					PreparedStatement pst=null;
					
					pst=con.prepareStatement("	update  item set quantity = ? where itemid=?");
					pst.setInt(1, qty);
					pst.setInt(2, Integer.parseInt(itemId));
					status=pst.executeUpdate();
					
				}   
				else{
					
				System.out.println("Connection done");

				}
				
		      } catch (Exception e) {
		            e.printStackTrace();
		        }
		  
	
		  
		  if(status==1)
		  {
			  
			  int cartId=0;
			  PreparedStatement pst=null;
			  try {
				pst=con.prepareStatement("SELECT cartid FROM cart ORDER BY cartid DESC LIMIT 1");

				 ResultSet rs=pst.executeQuery();
				if(rs.next())
				{
						
					cartId = rs.getInt("cartid");
		        }
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			  pst = null;
				
		try {
			pst=con.prepareStatement("INSERT INTO cart (cartid, itemsid, itemcount, userid) VALUES (?,?,?,?)");
			pst.setInt(1, cartId+1);
			pst.setInt(2, Integer.parseInt(itemId));
			pst.setInt(3, itemCount);
			pst.setInt(4, session.getUser().getUserId());
			status=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				
			  
		  }
		  return status==0?false:true;
		  
	  }
	  
	  public boolean RemoveItem(String itemName)throws RemoteException{
		int status = 0;
		  try {  
			  PreparedStatement pst=null;
				pst=con.prepareStatement("DELETE from item  where item=?");
				pst.setString(1,(itemName));
				status=pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return status==0?false:true;
	  }
	  
	  public List<List<String>> checkout() throws RemoteException
	  {
		  List<List<String>> items= new ArrayList<List<String>>();
		  try {  
			  PreparedStatement pst=null;
				pst=con.prepareStatement("select i.item, c.itemcount, i.price from cart as c inner join item as i on i.itemid = c.itemsid and c.userid = ?");
				pst.setInt(1,(session.getUser().getUserId()));
				ResultSet rs=pst.executeQuery();
				 while(rs.next())
				 {
					 List<String> item = new ArrayList<String>();
					
					 item.add(String.valueOf(rs.getString(1)));
					 item.add(String.valueOf(rs.getInt(2)));
					 item.add(String.valueOf(rs.getDouble(3)));
					 
					 
					
		 		    
		 		     
		 		     items.add(new ArrayList<String>(item));
		 		     
		 		     

		                }
				
		  
		  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
	  
		  
		  int status = 0;
		  try {  
			  PreparedStatement pst=null;
				pst=con.prepareStatement("DELETE  from cart where userid=?");
				pst.setInt(1,(session.getUser().getUserId()));
				status=pst.executeUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		  
		  return items;
	  }
	
}