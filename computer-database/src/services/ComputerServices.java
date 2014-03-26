package services;

import java.util.ArrayList;

import dao.DaoFactory;
import domainClasses.Computer;

public class ComputerServices {
	
	
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
		DaoFactory.getComputerDao().create(computer);
	}
	
	public void update(Computer computer) {
		DaoFactory.getComputerDao().update(computer);
	}
	
	public void delete(Computer computer) {
		DaoFactory.getComputerDao().delete(computer);
	}
	
	public void delete(int id) {
		DaoFactory.getComputerDao().delete(id);
	}
		
	public Computer find(int id) {
		return (DaoFactory.getComputerDao().find(id));
	}
	public int count() {
		return (DaoFactory.getComputerDao().count());
	}


	public ArrayList<Computer> getAllPagination(int offset,String orderByColumns,boolean orderByType) {
		return (DaoFactory.getComputerDao().getAllPagination(offset,orderByColumns, orderByType));
	}


	public int getNumberOfPage(int count) {
		return DaoFactory.getComputerDao().getNumberOfPage(count);
	}


	public ArrayList<Computer> search(String pattern, int currentPage,String orderByColumns,boolean orderByType) {
		return DaoFactory.getComputerDao().search(pattern,currentPage,orderByColumns,orderByType);

	}


	public int count(String pattern) {
		return (DaoFactory.getComputerDao().count(pattern));
	}
	
}
