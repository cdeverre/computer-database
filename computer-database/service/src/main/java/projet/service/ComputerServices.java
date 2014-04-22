package projet.service;

import java.util.List;

import projet.model.Computer;


public interface ComputerServices {
	
	
	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	public void create(Computer computer) ;
	
	
	public void update(Computer computer) ;

	public void delete(long id) ;
		
	public Computer find(long id) ;
	
	public int count() ;

	public List<Computer> getAllPagination(int currentPage,String orderByColumns,boolean orderByType);
	
	public int getNumberOfPage(int count) ;
	
	public List<Computer> search(String pattern, int currentPage,String orderByColumns,boolean orderByType);

	public int count(String pattern) ;
	
	
	
}
