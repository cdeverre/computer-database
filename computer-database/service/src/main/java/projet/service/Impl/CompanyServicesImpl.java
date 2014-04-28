package projet.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projet.dao.CompanyDao;
import projet.exception.CompanyNonexistentException;
import projet.model.Company;
import projet.service.CompanyServices;

@Service
public class CompanyServicesImpl implements CompanyServices{

	@Autowired
	private CompanyDao companyDao;
	/* *******************************************************/
	/* ***               Constructors                    *** */
	/* *******************************************************/
	
	public CompanyServicesImpl() {
		super();
	}
	
	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/


	@Transactional(readOnly=true)
	public List<Company> getAll() {
		return(companyDao.findAll());
	}
	
	@Transactional(readOnly=true)
	public String getName(long id) {
		return(companyDao.findOne(id).getName());
	}

	@Transactional(readOnly=true)
	public void exist(Company company) {
		if (company!=null) {
			if (company.getName()==null) {
				throw new CompanyNonexistentException();
			}
		}
		
	}


	
	
	
	
}
