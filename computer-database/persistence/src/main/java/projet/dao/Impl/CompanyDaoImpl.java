package projet.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projet.dao.CompanyDao;
import projet.model.Company;

@Repository
public class CompanyDaoImpl implements CompanyDao {

    private Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;
    
	/* *******************************************************/
	/* ***               Constructors                    *** */
	/* *******************************************************/
	
	public CompanyDaoImpl() {
		super();
	}
		
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	
	
	public List<Company> getAll() {
		logger.debug("Enter getAll");
		List<Company> result=null;
				
		result = (ArrayList<Company>) sessionFactory.getCurrentSession().createCriteria(Company.class).list();
		
		logger.debug("Leaving getAll");
		return result;
	}
	
	
	public String getName(long id) {
		String res=null;
			
		res=((Company) sessionFactory.getCurrentSession().createCriteria(Company.class).add(Restrictions.eq("id", id)).uniqueResult()).getName();
		
		return res;
	
	}

	
	
		
	
}