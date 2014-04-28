package projet.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import projet.model.Computer;


public interface ComputerDao extends CrudRepository<Computer,Long>{

	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	public Page<Computer> findByNameContainingOrCompanyNameContaining(String name, String companyName, Pageable pageable);
	
	public Long countByNameContainingOrCompanyNameContaining(String name, String companyName);
	
}
