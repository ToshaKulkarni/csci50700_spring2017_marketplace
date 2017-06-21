//Client which calls market client
import java.rmi.Naming;

public class Client {
	public static void main(String args[]) throws Throwable{
		// RMI Security Manager
		System.setSecurityManager(new SecurityManager());
		
		String name = "//in-csci-rrpc01.cs.iupui.edu:2015/Server";     //10.234.136.55 is set as the server for multiple clients
		ServerInterface server = null;
		Session session = null;
		try{
			MarketClient obj=new MarketClient();
			obj.functionality();
		}
		catch(Exception e){
			//System.out.println("Client Exception: " + e.getMessage());
		}
	
}
}