package projet.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import projet.exception.TransactionException;

import com.jolbox.bonecp.BoneCPDataSource;

@Repository
public class LogDao {

	
	 private static Logger logger = LoggerFactory.getLogger(LogDao.class);
	 
	 @Autowired
	 private BoneCPDataSource boneCP;
	 	 
	 public LogDao() {
		 
	 }
	 
	 
	 public void insertLog(long id,String message) throws TransactionException{
		 
		 JdbcTemplate jdbcTemplate= new JdbcTemplate(boneCP);	
		
		try {
			
			StringBuilder query =new StringBuilder();
			query.append("INSERT INTO log SET id=null, date=null, text='");
			query.append(message);
			query.append(" a new computer with the id:");
			query.append(id);
			query.append("'");
			
			logger.debug("Creating a log in the database");
			jdbcTemplate.update(query.toString());
			
		} catch (DataAccessException e) {
			throw new TransactionException("SQL Error when trying to log a create",e);
		} 
	 }


}
