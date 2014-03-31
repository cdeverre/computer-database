package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exceptions.TransactionException;

public class LogDao {

	
	 private static Logger logger = LoggerFactory.getLogger(LogDao.class);
	 
	 protected LogDao() {
		 
	 }
	 
	 
	 public void insertLogCreate(int id) throws TransactionException {
		Connection connection=DaoFactory.getConnection();	 
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
			DaoFactory.close( rs, stmt);
		}
	 }


	public void insertLogUpdate(int id) throws TransactionException {
		Connection connection=DaoFactory.getConnection();	 
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
			DaoFactory.close( rs, stmt);
		}
		
	}


	public void insertLogDelete(int id) throws TransactionException {
		Connection connection=DaoFactory.getConnection();	 
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
			DaoFactory.close( rs, stmt);
		}
	}
}
