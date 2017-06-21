import java.rmi.Naming;
import java.rmi.RemoteException;

public class LoginController {
	
	/**
	 * Attempt to authenticate user login.
	
	 */
	

private ServerInterface  server;
Session session;
public LoginController (ServerInterface server) {
		this.server = server;
	}


public void isAuthenticUser(String request,String response)
{
Dispatcher disp = new Dispatcher();
try{

session=server.processLogin(request,response);
}
catch(Exception e)
{ 
System.out.println("Exception");
}


try{	
			server.update(session);
			disp.dispatch("admin",server);

		} catch(Exception e){
			//System.out.println("Client Exception: " + e.getMessage());
		}

try{	
			server.rateproduct(session);
			disp.dispatch("customer",server);

		} catch(Exception e){
			e.printStackTrace();
			//System.out.println("Client Exception: " + e.getMessage());
		}
}
}