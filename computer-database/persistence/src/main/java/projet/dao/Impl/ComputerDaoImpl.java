package projet.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projet.dao.ComputerDao;
import projet.exception.TransactionException;
import projet.model.Computer;
import projet.model.QCompany;
import projet.model.QComputer;
import projet.wrapper.PageWrapper;

import com.mysema.query.jpa.hibernate.HibernateQuery;


@Repository
public class ComputerDaoImpl implements ComputerDao {

		
    private static Logger logger = LoggerFactory.getLogger(ComputerDaoImpl.class);
        
    
    @Autowired
    private SessionFactory sessionFactory;
	
	/* *******************************************************/
	/* ***               Constructors                    *** */
	/* *******************************************************/
	
	public ComputerDaoImpl() {
		super();
	}
	
	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	public void create(final Computer computer) throws TransactionException  {
		logger.debug("Entering create");
		sessionFactory.getCurrentSession().persist(computer);
		logger.debug("Leaving create");
	}
	
	public void update(Computer computer) throws TransactionException {
		logger.debug("Entering update");
		sessionFactory.getCurrentSession().merge(computer);	
		logger.debug("Leaving update");
	}
	

	
	public void delete(long id) throws TransactionException {
		logger.debug("Entering delete");
		String query ="delete Computer where id= :idComputer" ;
		sessionFactory.getCurrentSession().createQuery(query).setLong("idComputer", id).executeUpdate();
		logger.debug("Leaving delete");
	}
	
	
	public List<Computer> search(PageWrapper page) {
		List<Computer> result = null;
		
		String searchPattern="%"+page.getPattern()+"%";
		QComputer computer= QComputer.computer;
		QCompany company = QCompany.company;
		
		HibernateQuery q=new HibernateQuery(sessionFactory.getCurrentSession());
		q.from(computer).leftJoin(computer.company,company);
		
		if(	page.getPattern()!=null && !"".equals(page.getPattern())) {
			q.where(computer.name.like(searchPattern).or( company.name.like(searchPattern)));
		}
		
		switch (page.getOrderByColumns()) {
		case "name" :
			if(page.getOrderByType()) {
				q.orderBy(computer.name.asc());
			} else {
				q.orderBy(computer.name.desc());
			}
			break;
		case "introduced":
			if(page.getOrderByType()) {
				q.orderBy(computer.introduced.asc());
			} else {
				q.orderBy(computer.introduced.desc());
			}
			break;
		case "discontinued":
			if(page.getOrderByType()) {
				q.orderBy(computer.discontinued.asc());
			} else {
				q.orderBy(computer.discontinued.desc());
			}
			break;
		case "company":
			if(page.getOrderByType()) {
				q.orderBy(computer.company.name.asc());
			} else {
				q.orderBy(computer.company.name.desc());
			}
			break;
		default:
		}
		
		q.offset((page.getCurrentPage()-1)*ComputerDaoImpl.LIMIT);
		q.limit(ComputerDaoImpl.LIMIT);

		result=(ArrayList<Computer>) q.list(computer);

		return result;
		
	}
		
	
	public int count(String pattern) {
		int res = 0;
		
		String searchPattern="%"+pattern+"%";
		QComputer computer= QComputer.computer;
		QCompany company = QCompany.company;
		
		HibernateQuery q=new HibernateQuery(sessionFactory.getCurrentSession());
		q.from(computer).leftJoin(computer.company,company);
		
		if(	pattern!=null && !"".equals(pattern)) {
			q.where(computer.name.like(searchPattern).or( company.name.like(searchPattern)));
		}
		
		res=((Long)q.count()).intValue();
		logger.debug("Query sended succesfully");

		return res;
	}
	
	
	public Computer find(long id) {
		Computer res=null;

		QComputer computer= QComputer.computer;
		
		HibernateQuery q=new HibernateQuery(sessionFactory.getCurrentSession());
		res=(Computer)q.from(computer).where(computer.id.eq(id)).uniqueResult();
		
		return res;
	}
	
	
	

	
	
	
	
}
