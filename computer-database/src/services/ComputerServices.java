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
	
	public ArrayList<Computer> getAll() {
		return(DaoFactory.getComputerDao().getAll());
	}
	
}
