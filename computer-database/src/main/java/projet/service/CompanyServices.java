package projet.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.dao.CompanyDao;
import projet.exception.CompanyNonexistentException;
import projet.model.Company;

@Service
public class CompanyServices {

	@Autowired
	private CompanyDao companyDao;
	/* *******************************************************/
	/* ***               Constructors                    *** */
	/* *******************************************************/
	
	public CompanyServices() {
		super();
	}
	
	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/



	public ArrayList<Company> getAll() {
		return(companyDao.getAll());
	}
	
	public String getName(long id) {
		return(companyDao.getName(id));
	}


	public void exist(Company company) {
		if (company!=null) {
			if (company.getName()==null) {
				throw new CompanyNonexistentException();
			}
		}
		
	}


	
	
	
	
}
