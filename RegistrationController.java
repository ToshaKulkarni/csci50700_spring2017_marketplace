

import java.rmi.RemoteException;

public class RegistrationController {
	void processRequest(String name,String email,String add) throws RemoteException
	{
		MktCustomer cust=new MktCustomer(); //Creation of object of MktCustomer-the data model class
		cust.setName(name);	 // Accessing the method in model class 
		cust.setEmailId(email);
		cust.setAddress(add);
           System.out.println("Record Added");
	}

}
