package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;



public class DaoFactory{

	/* *******************************************************/
	/* ***               Attributs                       *** */
	/* *******************************************************/
	
	private final static ComputerDao computerDao =new ComputerDao();
	
	private final static CompanyDao companyDao = new CompanyDao();
	
	private final static LogDao logDao = new LogDao();
	
    private static Logger logger = LoggerFactory.getLogger(DaoFactory.class);

    private static BoneCP connectionPool;
    
    private static ThreadLocal<Connection> threadLocal=new ThreadLocal<Connection>() {
		@Override protected Connection initialValue() {
			Connection connection = null;
			
			
			try {
				logger.debug("Trying to create a new connection ...");
				
				connection= connectionPool.getConnection();
				
				logger.debug("Connection established");
				
				if(connection==null) System.out.println("aa");
				if(connection.isClosed()) System.out.println("aa");
					
				} catch (SQLException e) {
					e.printStackTrace();
			}
			return connection;
    };
    };
    
    static{

		logger.debug("Search for Driver JDC..");
		try {
			Class.forName("com.mysql.jdbc.Driver");

			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl("jdbc:mysql://localhost/computer-database-db?zeroDateTimeBehavior=convertToNull"); 
			config.setUsername("root"); 
			config.setPassword("toor");
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(1);
			        
			connectionPool = new BoneCP(config); 
	
		} catch (ClassNotFoundException e) {
			logger.error("Driver not found");
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error("Error in the DaoFactory, when trying to create a BoneCP");
			e.printStackTrace();
		}
		
		logger.debug("Driver found");
    	
    }

	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	public static ComputerDao getComputerDao() {
		return computerDao;
	}
	
	public static CompanyDao getCompanyDao() {
		return companyDao;
	}
	

	public static LogDao getLogdao() {
		return logDao;
	}
	
	public static Connection getConnection() {
		return threadLocal.get();
	}
	
	
	
	public static void closeConnection() {
		try {
			logger.debug("Closing the connection");
			DaoFactory.getConnection().close();
			logger.debug("Connection closed");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			logger.debug("Removing the ThreadLocal");
			DaoFactory.threadLocal.remove();
		}
	}
	
	public static void close( ResultSet rs, java.sql.Statement stmt) {
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
