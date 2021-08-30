package Database;

	

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


	/**
	 * Connect to Database
	 * @author Tagliani Fabio
	 */
	public class ConnectionFactory {

		  private final static String URL = "jdbc:mysql://sql4.freesqldatabase.com:3306/sql4432655";
		    private final static String USER = "sql4432655";
		    private final static String PASS = "LDPnYUazG7";

	    /**
	     * Get a connection to database
	     * @return Connection object
	     */
	    public static Connection getConnection()
	    {
	    	try {
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		return DriverManager.getConnection(URL, USER, PASS);
	    	
	    		      
	    	} catch (SQLException | ClassNotFoundException ex) {
	          throw new RuntimeException("Error connecting to the database", ex);
	      }
	    }

	    /**
	     * Test Connection
	     */
	    public static void main(String[] args) {
	        Connection connection = ConnectionFactory.getConnection();
	    }

	

}
