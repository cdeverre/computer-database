package projet.service;

import java.util.List;

import projet.model.Computer;
import projet.wrapper.PageWrapper;


public interface ComputerServices {
	
	
	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	public void create(Computer computer) ;
	
	
	public void update(Computer computer) ;

	public void delete(long id) ;
		
	public Computer find(long id) ;
	
	public int getNumberOfPage(int count) ;
	
	public List<Computer> search(PageWrapper page);

	public int count(String pattern) ;
	
	
	
}
