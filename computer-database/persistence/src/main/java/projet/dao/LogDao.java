package projet.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.jolbox.bonecp.BoneCPDataSource;

import projet.exception.TransactionException;

@Repository
public class LogDao {

	
	 private static Logger logger = LoggerFactory.getLogger(LogDao.class);
	 
	 @Autowired
	 private BoneCPDataSource boneCP;
	 	 
	 public LogDao() {
		 
	 }
	 
	 
	 public void insertLog(long id,String message) throws TransactionException{
		 
		Connection connection=DataSourceUtils.getConnection(boneCP);	 
		ResultSet rs =null ; 
		Statement stmt=null;
	
		
		
		try {
			logger.debug("Creating a statement");
			stmt = connection.createStatement();
			logger.debug("Statement created");
			
			StringBuilder query =new StringBuilder();
			query.append("INSERT INTO log SET id=null, date=null, text='"+message+" a new computer with the id:");
			query.append(id);
			query.append("'");
			
			stmt.executeUpdate(query.toString());
			
		} catch (SQLException e) {
			throw new TransactionException("SQL Error when trying to log a create",e);
		} finally {
			ConnectionFactory.close( rs, stmt);
		}
	 }


}
