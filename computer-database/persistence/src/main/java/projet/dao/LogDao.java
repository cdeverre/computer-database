package projet.dao;

import projet.exception.TransactionException;


public interface LogDao {

	 
	 
	 public void insertLog(long id,String message) throws TransactionException;

}
