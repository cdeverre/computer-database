package projet.dao.Impl;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projet.dao.LogDao;
import projet.exception.TransactionException;
import projet.model.Log;

@Repository
public class LogDaoImpl implements LogDao {

	
	 private static Logger logger = LoggerFactory.getLogger(LogDaoImpl.class);
	 
	 @Autowired
	    private SessionFactory sessionFactory;
	 	 
	 public LogDaoImpl() {
		 
	 }
	 
	 public void insertLog(long id,String message) throws TransactionException{
		
		Log log=new Log(id,message);
			
		logger.debug("Creating a log in the database");
		sessionFactory.getCurrentSession().persist(log);
			
	
	 }


}
