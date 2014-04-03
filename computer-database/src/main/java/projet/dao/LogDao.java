package projet.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projet.exception.TransactionException;

@Repository
public class LogDao {

	
	 private static Logger logger = LoggerFactory.getLogger(LogDao.class);
	 
	 @Autowired
	 private ConnectionFactory connectionFactory;
	 
	 public LogDao() {
		 
	 }
	 
	 
	 public void insertLogCreate(long id) throws TransactionException {
		Connection connection=connectionFactory.getConnection();	 
		ResultSet rs =null ; 
		Statement stmt=null;
		
		try {
			logger.debug("Creating a statement");
			stmt = connection.createStatement();
			logger.debug("Statement created");
			
			StringBuilder query =new StringBuilder();
			query.append("INSERT INTO log SET id=null, date=null, text='Creating a new computer with the id:");
			query.append(id);
			query.append("'");
			
			stmt.executeUpdate(query.toString());
			
		} catch (SQLException e) {
			throw new TransactionException("SQL Error when trying to log a create",e);
		} finally {
			connectionFactory.close( rs, stmt);
		}
	 }


	public void insertLogUpdate(long id) throws TransactionException {
		Connection connection=connectionFactory.getConnection();	 
		ResultSet rs =null ; 
		Statement stmt=null;
		 
		try {
			logger.debug("Creating a statement");
			stmt = connection.createStatement();
			logger.debug("Statement created");
			
			StringBuilder query =new StringBuilder();
			query.append("INSERT INTO log SET id=null, date=null, text='Updating the computer with the id:");
			query.append(id);
			query.append("'");
			
			stmt.executeUpdate(query.toString());
		} catch (SQLException e) {
			throw new TransactionException("SQL Error when trying to log an update",e);
		} finally {
			connectionFactory.close( rs, stmt);
		}
		
	}


	public void insertLogDelete(long id) throws TransactionException {
		Connection connection=connectionFactory.getConnection();	 
		ResultSet rs =null ; 
		Statement stmt=null;
		
		try {
			logger.debug("Creating a statement");
			stmt = connection.createStatement();
			logger.debug("Statement created");
			
			StringBuilder query =new StringBuilder();
			query.append("INSERT INTO log SET id=null, date=null, text='Deleting the computer with the id:");
			query.append(id);
			query.append("'");
		
			stmt.executeUpdate(query.toString());

		} catch (SQLException e) {
			throw new TransactionException("SQL Error when trying to log a delete",e);
		} finally {
			connectionFactory.close( rs, stmt);
		}
	}
}
