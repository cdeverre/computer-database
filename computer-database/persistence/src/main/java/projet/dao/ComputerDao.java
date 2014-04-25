package projet.dao;

import java.util.List;

import projet.exception.TransactionException;
import projet.model.Computer;
import projet.wrapper.PageWrapper;


public interface ComputerDao {

	public static final int LIMIT=13;
	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	public void create(final Computer computer) throws TransactionException ;
	
	
	public void update(Computer computer) throws TransactionException;
	
	
	public void delete(long id) throws TransactionException ;
		
	
	public int count(String pattern);
	
	
	public Computer find(long id);
	
	
	public List<Computer> search(PageWrapper page);
	
	
	
	
}
