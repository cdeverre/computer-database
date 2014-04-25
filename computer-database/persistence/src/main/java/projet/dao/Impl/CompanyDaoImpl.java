package projet.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.hibernate.HibernateQuery;

import projet.dao.CompanyDao;
import projet.model.Company;
import projet.model.QCompany;

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
		QCompany company = QCompany.company;		
		HibernateQuery q=new HibernateQuery(sessionFactory.getCurrentSession());
		
		result = (ArrayList<Company>) q.from(company).list(company);
		
		logger.debug("Leaving getAll");
		return result;
	}
	
	
	public String getName(long id) {
		String res=null;		
		QCompany company = QCompany.company;
		HibernateQuery q=new HibernateQuery(sessionFactory.getCurrentSession());
		
		res=((Company) q.from(company).where(company.id.eq(id)).uniqueResult()).getName();
		
		return res;
	
	}

	
	
		
	
}