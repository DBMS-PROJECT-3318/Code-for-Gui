import java.sql.*;
import javax.swing.*;

public class SqliteConnect {
	Connection conn = null;
	public static Connection dbConnector() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn =DriverManager.getConnection("jdbc:sqlite:/home/erikmoctezuma/Desktop/DBMS/PROJECT/DBMSPROJ");
			JOptionPane.showMessageDialog(null, "Connection Successful!");

			return conn;
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
}
