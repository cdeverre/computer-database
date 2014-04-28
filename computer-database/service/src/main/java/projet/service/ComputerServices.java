package projet.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import projet.model.Computer;


public interface ComputerServices {
	
	
	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	public void create(Computer computer) ;
	
	
	public void update(Computer computer) ;

	public void delete(long id) ;
		
	public Computer find(long id) ;
	
	public int getNumberOfPage(int count) ;
	
	public int count(String pattern) ;

	public List<Computer> search(String pattern, Pageable pageable);
	
	
	
}
