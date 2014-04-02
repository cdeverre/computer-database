package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import exceptions.TransactionException;


@Repository
public class ConnectionFactory{

	/* *******************************************************/
	/* ***               Attributs                       *** */
	/* *******************************************************/
	
	
    private static Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

    private static BoneCP connectionPool;
    
    private static ThreadLocal<Connection> threadLocal=new ThreadLocal<Connection>() {
		@Override 
		protected Connection initialValue() {
			Connection connection = null;
			
			try {
				logger.debug("Trying to create a new connection ...");
				
				connection= connectionPool.getConnection();
				
				logger.debug("Connection established");
				} catch (SQLException e) {
					e.printStackTrace();
			}
			return connection;
		}
    };
    
    /* *******************************************************/
	/* ***               Constructors                    *** */
	/* *******************************************************/
    
    public ConnectionFactory() {

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

	
	public Connection getConnection() {
		return threadLocal.get();
	}
	
	
	public void startTransaction() {
		try {
			threadLocal.get().setAutoCommit(false);
		} catch (SQLException e) {
			logger.error("Can't set auto commit to false");
			e.printStackTrace();
		}
	}
	
	public void commitTransaction() throws TransactionException{
		try {
			threadLocal.get().commit();
		} catch (SQLException e) {
			throw new TransactionException("Commit error",e);
		}
	}
	
	public void rollbackTransaction() throws TransactionException {
		try {
			threadLocal.get().rollback();
		} catch (SQLException e) {
			throw new TransactionException("Rollback error",e);
		}
	}
	
	public void closeConnection() {
		try {
			logger.debug("Closing the connection");
			threadLocal.get().close();
			logger.debug("Connection closed");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			logger.debug("Removing the ThreadLocal");
			threadLocal.remove();
		}
	}
	
	public void close( ResultSet rs, java.sql.Statement stmt) {
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
