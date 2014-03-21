package services;

import dao.DaoFactory;
import domainClasses.Company;


public class CompanyServices {

	/* *******************************************************/
	/* ***               Constructors                    *** */
	/* *******************************************************/
	
	protected CompanyServices() {
		super();
	}
	
	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/


	public void create(Company company) {
		DaoFactory.getCompanyDao().create(company);
	}
	
	public void update(Company company) {
		DaoFactory.getCompanyDao().update(company);
	}
	
	public void delete(Company company) {
		DaoFactory.getCompanyDao().delete(company);
	}
}
