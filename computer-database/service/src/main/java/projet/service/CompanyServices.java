package projet.service;

import java.util.List;

import projet.model.Company;

public interface CompanyServices {

	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/


	public List<Company> getAll() ;
	
	public String getName(long id) ;

	public void exist(Company company) ;


	
}
