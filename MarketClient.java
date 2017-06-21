
import java.rmi.Naming;
import java.util.Scanner;

/**
 * Market Client
 */
public class MarketClient{
	
ServerInterface server = null;

void functionality() throws Throwable
{
		// Try-Catch is necessary for remote exceptions.
		try {
			/*
			 * This is our hostname where our RMI server is running. We
			 * can also specify the port here.
			 */
			String name = "//in-csci-rrpc01.cs.iupui.edu:2015/Server";       //IP name of server for different clients
			server = (ServerInterface) Naming.lookup(name);	
			
			//Taking input from the user
				System.out.println("Hello, Welcome to the marketplace");
				System.out.println("Select the action to perform"+'\n'+"1.Customer Registration"+
				'\n'+"2.Login"+'\n');
				int n;
				System.out.println("Enter your choice");
				Scanner s = new Scanner(System.in);
				n= s.nextInt();
				String act = null;
				if(n==1)      //registration
					{Scanner s1 =new Scanner(System.in);
				
				System.out.println("Enter name");
				String name1=s1.nextLine();
				
			System.out.println("Enter email");
			String email=s1.nextLine();
			
				System.out.println("Enter address");
				String add=s1.nextLine();
				FrontController front=new FrontController();
				
				front.dispatchRequest(name1,email,add); //Transfer to controller
				}
     if(n==2)                                                           //login
{	Scanner s1 =new Scanner(System.in);

System.out.println("Enter username");
String username=s1.nextLine();
System.out.println("Enter password");
String password=s1.nextLine();

	FrontController front=new FrontController(server);
	front.dispatchRequest(username,password);
	}


			
		} catch(Exception e){
		//	System.out.println("MarketClient Exception: " + e.getMessage());
			e.printStackTrace();
		}

		// Terminate the program.
		System.exit(0);
		
	}
}
