package services;

import java.util.ArrayList;

import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ComputerDao;
import dao.ConnectionFactory;
import dao.LogDao;
import exceptions.TransactionException;

@Service
public class ComputerServices {
	
	
	private static Logger logger = LoggerFactory.getLogger(ComputerServices.class);
	
	@Autowired
	private ConnectionFactory connectionFactory;
	
	@Autowired
	private ComputerDao computerDao;
	
	@Autowired
	private LogDao logDao;
	
	/* *******************************************************/
	/* ***               Constructors                    *** */
	/* *******************************************************/
	
	public ComputerServices() {
		super();
	}
	
	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	public void create(Computer computer) {
		connectionFactory.startTransaction();
		try {
			computerDao.create(computer);
			logDao.insertLogCreate(computer.getId());
			
			connectionFactory.commitTransaction();
			logger.debug("Computer created succesfully");
		} catch (TransactionException e) {
			connectionFactory.rollbackTransaction();
			throw e;
		} finally {
			connectionFactory.closeConnection();
		}
	}
	
	public void update(Computer computer) {
		connectionFactory.startTransaction();
		try {
			computerDao.update(computer);
			logDao.insertLogUpdate(computer.getId());
			connectionFactory.commitTransaction();
			logger.debug("Computer updated succesfully");
		} catch (TransactionException e) {
			connectionFactory.rollbackTransaction();
			throw e;
		}  finally {
			connectionFactory.closeConnection();
		}
		
	}

	
	public void delete(int id) {
		connectionFactory.startTransaction();
		try {
			computerDao.delete(id);
			logDao.insertLogDelete(id);
			connectionFactory.commitTransaction();
			logger.debug("Computer deleted succesfully");
		} catch (TransactionException e) {
			connectionFactory.rollbackTransaction();
			throw e;
		}  finally {
			connectionFactory.closeConnection();
		}
	}
		
	public Computer find(int id) {

		return (computerDao.find(id));
	}
	
	public int count() {
		return (computerDao.count());
	}


	public ArrayList<Computer> getAllPagination(int currentPage,String orderByColumns,boolean orderByType) {
		return (computerDao.getAllPagination(currentPage,orderByColumns, orderByType));
	}


	public int getNumberOfPage(int count) {
		return (int)Math.ceil((double)count/(double)ComputerDao.LIMIT);
	}


	public ArrayList<Computer> search(String pattern, int currentPage,String orderByColumns,boolean orderByType) {
		return computerDao.search(pattern,currentPage,orderByColumns,orderByType);

	}


	public int count(String pattern) {
		return (computerDao.count(pattern));
	}
	
	
//	public void delete(Computer computer) {
//		computerDao.delete(computer);
//	}
	
}
