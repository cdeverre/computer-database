package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Computer;
import dao.ComputerDao;
import dao.DaoFactory;

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
		Connection connection=DaoFactory.getConnection();
		try {
			connection.setAutoCommit(false);
			DaoFactory.getComputerDao().create(computer);
			DaoFactory.getLogdao().insertLogCreate(computer.getId());
			connection.commit();
			logger.info("Computer created succesfully");
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DaoFactory.closeConnection();
		}
	}
	
	public void update(Computer computer) {
		Connection connection=DaoFactory.getConnection();
		try {
			connection.setAutoCommit(false);
			DaoFactory.getComputerDao().update(computer);
			DaoFactory.getLogdao().insertLogUpdate(computer.getId());
			connection.commit();
			logger.info("Computer updated succesfully");
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DaoFactory.closeConnection();
		}
		
	}

	
	public void delete(int id) {
		Connection connection=DaoFactory.getConnection();
		try {
			connection.setAutoCommit(false);
			DaoFactory.getComputerDao().delete(id);
			DaoFactory.getLogdao().insertLogDelete(id);
			connection.commit();
			logger.info("Computer deleted succesfully");
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
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
