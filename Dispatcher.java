/**
 * Dispatcher Class
 */
public class Dispatcher {
	// Concrete Views...
	private CustomerView customerView;
	private AdminView adminView;

	/**
	 * Dispatcher Constructor
	 */
	public Dispatcher() {
		customerView = new CustomerView();
	    adminView = new AdminView();
	}
String role;
public Dispatcher(String role) {
		this.role=role;
	////	this.server = server;
	}


	/**
	 * Based upon the request - dispatch the view.
	 * 
	 * @param request
	 */

	public void dispatch(String role,ServerInterface server) {
		// Admin or Customer Views...
		if(role.equals("customer")) {
			
			customerView.showView(server);}
		if(role.equals("admin"))

	    {	adminView.showView(server);
	    }	
	}
}