package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class DaoFactory{

	/* *******************************************************/
	/* ***               Attributs                       *** */
	/* *******************************************************/
	
	private final static ComputerDao computerDao =new ComputerDao();
	
	private final static CompanyDao companyDao = new CompanyDao();
	
    private static Logger logger = LoggerFactory.getLogger(DaoFactory.class);


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
			
			logger.debug("Search for Driver JDC..");
			
			Class.forName("com.mysql.jdbc.Driver");
			
			logger.debug("Driver found");
			String url= "jdbc:mysql://localhost/computer-database-db?zeroDateTimeBehavior=convertToNull";
			
			try {
				logger.debug("Trying to create a new connection ...");
				
				connection= (Connection) DriverManager.getConnection(url,"root","toor");
				
				logger.debug("Connection established");
				
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
				logger.debug("Trying to close the connection");
				connection.close();
				logger.debug("Connection closed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
			if(rs!=null) {
				logger.debug("Trying to close the result set");
				rs.close();
				logger.debug("Result set closed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} try {
			if(stmt!=null) {
				logger.debug("Trying to close the statement");
				stmt.close();
				logger.debug("Statement closed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
