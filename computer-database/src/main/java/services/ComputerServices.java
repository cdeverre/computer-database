package services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Computer;
import dao.ComputerDao;
import dao.DaoFactory;
import exceptions.TransactionException;

public class ComputerServices {
	
	private static Logger logger = LoggerFactory.getLogger(ComputerServices.class);
	
	/* *******************************************************/
	/* ***               Constructors                    *** */
	/* *******************************************************/
	
	protected ComputerServices() {
		super();
	}
	
	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	public void create(Computer computer) {
		DaoFactory.startTransaction();
		try {
			DaoFactory.getComputerDao().create(computer);
			DaoFactory.getLogdao().insertLogCreate(computer.getId());
			
			DaoFactory.commitTransaction();
			logger.debug("Computer created succesfully");
		} catch (TransactionException e) {
			DaoFactory.rollbackTransaction();
			throw e;
		} finally {
			DaoFactory.closeConnection();
		}
	}
	
	public void update(Computer computer) {
		DaoFactory.startTransaction();
		try {
			DaoFactory.getComputerDao().update(computer);
			DaoFactory.getLogdao().insertLogUpdate(computer.getId());
			DaoFactory.commitTransaction();
			logger.debug("Computer updated succesfully");
		} catch (TransactionException e) {
			DaoFactory.rollbackTransaction();
			throw e;
		}  finally {
			DaoFactory.closeConnection();
		}
		
	}

	
	public void delete(int id) {
		DaoFactory.startTransaction();
		try {
			DaoFactory.getComputerDao().delete(id);
			DaoFactory.getLogdao().insertLogDelete(id);
			DaoFactory.commitTransaction();
			logger.debug("Computer deleted succesfully");
		} catch (TransactionException e) {
			DaoFactory.rollbackTransaction();
			throw e;
		}  finally {
			DaoFactory.closeConnection();
		}
	}
		
	public Computer find(int id) {

		return (DaoFactory.getComputerDao().find(id));
	}
	
	public int count() {
		return (DaoFactory.getComputerDao().count());
	}


	public ArrayList<Computer> getAllPagination(int currentPage,String orderByColumns,boolean orderByType) {
		return (DaoFactory.getComputerDao().getAllPagination(currentPage,orderByColumns, orderByType));
	}


	public int getNumberOfPage(int count) {
		return (int)Math.ceil((double)count/(double)ComputerDao.LIMIT);
	}


	public ArrayList<Computer> search(String pattern, int currentPage,String orderByColumns,boolean orderByType) {
		return DaoFactory.getComputerDao().search(pattern,currentPage,orderByColumns,orderByType);

	}


	public int count(String pattern) {
		return (DaoFactory.getComputerDao().count(pattern));
	}
	
	
//	public void delete(Computer computer) {
//		DaoFactory.getComputerDao().delete(computer);
//	}
	
}
