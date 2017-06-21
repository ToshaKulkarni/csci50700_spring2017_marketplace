import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection
{

	
	public  static Connection  connect() {
		// Sample Database Connection
		String hostname = "localhost:3306";
		String dbName = "tkulkarn_db";
		
		String url = "jdbc:mysql://" + hostname + "/" + dbName;
		String username = "tkulkarn";
		String password = "tkulkarn";

		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn =  DriverManager.getConnection(url, username, password);	
		    System.out.println("Database connected!");
			
		      		 System.out.println("....Connection enabled..");			
                
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable execute query");
		}
		return conn;
	
	}

} 