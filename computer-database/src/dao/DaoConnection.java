package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;


public class DaoConnection{

	private Connection connection;
	
	private final static DaoConnection _instance = new DaoConnection();

	
	public DaoConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url= "jdbc:mysql://localhost/computer-database-db";
			
			try {
				this.connection= (Connection) DriverManager.getConnection(url,"root","toor");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}


	/**
	 * @param connection the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * @return the Instance
	 */
	public static DaoConnection getInstance() {
		return _instance;
	}
	
	
	public static void main(String args[]) {
		DaoConnection d=new DaoConnection();
		try {
			ResultSet s=d.getConnection().createStatement().executeQuery("select * from company");
			while(s.next()) {
				System.out.println(s.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	
}
