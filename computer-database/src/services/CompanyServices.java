package services;

import java.util.ArrayList;

import model.Company;

import dao.DaoFactory;


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



	public ArrayList<Company> getAll() {
		return(DaoFactory.getCompanyDao().getAll());
	}
	
	public String getName(int id) {
		return(DaoFactory.getCompanyDao().getName(id));
	}
	
	/* 	public void create(Company company) {
		DaoFactory.getCompanyDao().create(company);
	}
	
	public void update(Company company) {
		DaoFactory.getCompanyDao().update(company);
	}
	
	public void delete(Company company) {
		DaoFactory.getCompanyDao().delete(company);
	}
	*/
}
