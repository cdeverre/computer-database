package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DaoFactory{

	/* *******************************************************/
	/* ***               Attributs                       *** */
	/* *******************************************************/
	
	private final static ComputerDao computerDao =new ComputerDao();
	
	private final static CompanyDao companyDao = new CompanyDao();
	

	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	public static ComputerDao getComputerDao() {
		return computerDao;
	}
	
	public static CompanyDao getCompanyDao() {
		return companyDao;
	}
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url= "jdbc:mysql://localhost/computer-database-db?zeroDateTimeBehavior=convertToNull";
			
			try {
				connection= (Connection) DriverManager.getConnection(url,"root","toor");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	
	public static void close(Connection connection, ResultSet rs, java.sql.Statement stmt) {
		try {
			if(connection!=null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
			if(rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} try {
			if(stmt!=null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
