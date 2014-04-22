package projet.dao;

import java.util.List;

import projet.model.Company;


public interface CompanyDao {


	
	public List<Company> getAll() ;
	
	
	public String getName(long id);

	
	
		
	
}