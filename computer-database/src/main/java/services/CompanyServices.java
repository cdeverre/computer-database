package services;

import java.util.ArrayList;

import model.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CompanyDao;

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
	
	public String getName(int id) {
		return(companyDao.getName(id));
	}
	
	
	
	
}
