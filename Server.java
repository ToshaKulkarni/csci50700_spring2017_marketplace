

import java.lang.reflect.Proxy;
import java.rmi.Naming;

public class Server {	
	public static void main(String args[]) {
		// Set the RMI Security Manager...
		System.setSecurityManager(new SecurityManager());
		
		try {
			System.out.println("Creating a Server!");
			
			// Location of Server
			String name = "//in-csci-rrpc01.cs.iupui.edu:2015/Server";
			
			System.out.println("Server: Binding it to name: " + name);
			
			ServerInterface assignment = (ServerInterface) Proxy.newProxyInstance(ServerInterface.class.getClassLoader(),
	                new Class<?>[] {ServerInterface.class},
	                new AuthorizationInvocationHandler(new ServerImpl()));
						
			// Binds the Server to the RMI Service.
			Naming.rebind(name, assignment);
			
			System.out.println("Server Ready!");
		} catch (Exception e){
			System.out.println("Server Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}