package Servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionServer {
	
	Connection conect = null;
	
	public Connection conexion(){
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conect =  DriverManager.getConnection("jdbc:mysql://localhost:8889/comode","root","root");
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conect;
		
	}

}
