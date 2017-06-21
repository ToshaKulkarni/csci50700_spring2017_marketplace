import java.util.*;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.*;
import com.mysql.jdbc.*;
import  com.mysql.jdbc.Connection;
import  java.sql.PreparedStatement;


public class DatabaseConnector{

	public Connection  conn = null;
	public  static DatabaseConnector db;

	DatabaseConnector(){
		// Sample Database Connection
		String hostname = "localhost:3306";
		String dbName = "tkulkarn_db";//db name
		
		String url = "jdbc:mysql://" + hostname + "/" + dbName;
		String username = "tkulkarn";
		String password = "tkulkarn";
      		 System.out.println("Connecting database...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url, username, password);	
		    System.out.println("Database connected!");    
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable execute query");
		}
	}
	
	public Connection getConnection(){
		return conn;
	}
	
	public static DatabaseConnector getDbObject(){
		if(db == null){
			db = new DatabaseConnector();
		}
		return db;
	}
	
	
}