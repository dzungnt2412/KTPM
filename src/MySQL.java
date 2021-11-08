
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.sun.net.httpserver.Authenticator.Result;

public class MySQL {
	
	
	
	public static Connection getConnection() {
		
		 String serverName = "localhost";
		 String username = "root";
		 String dbName = "mysql_db";
		 Integer port = 3306;
		 String password = "";
		
		Connection conn = null;
		
		Result rs = null ;
		Statement st= null;
		
		try {
			String dbURL = "jdbc:mysql://localhost/mysql_db";
			conn = DriverManager.getConnection(dbURL, username, password);
			if (conn != null) {
		    	System.out.print("success");
		     }
		} catch (SQLException e) {
			Logger.getLogger("Get connection ->" + MySQL.class.getName()).log(Level.SEVERE, null, e);
		}
		return conn;
	}
}