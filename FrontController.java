import java.rmi.RemoteException;

/**
 * Java implementation of Front Controller Design Pattern.
 */
public class FrontController {
	// Dispatcher instance...
	private Dispatcher dispatcher;

	/**
	 * Front Controller Constructor
	 */
	private ServerInterface  server;
public FrontController()
{
dispatcher = new Dispatcher();

}
//Parameterised FrontController constructor
	public FrontController(ServerInterface server) {
		dispatcher = new Dispatcher();
		this.server = server;
	}
	public void dispatchRequest(String name,String email,String add)
	{
		RegistrationController controller=new RegistrationController(); //Object of controller
		try {
			controller.processRequest(name,email,add);//Transfer to controller
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * Responsible for dispatching the request to the Dispatcher.
	
	 */
	public void dispatchRequest(String request,String response) throws Throwable {
		
		System.out.println("Authenticate user: " + request);   
		LoginController control=new LoginController(server);
		control.isAuthenticUser(request, response);
		
	
	}
}