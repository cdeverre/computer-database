package projet.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projet.dao.ComputerDao;
import projet.exception.TransactionException;
import projet.model.Computer;


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
		sessionFactory.getCurrentSession().persist(computer);
	}
	
	public void update(Computer computer) throws TransactionException {
		sessionFactory.getCurrentSession().merge(computer);	
	}
	

	
	public void delete(long id) throws TransactionException {
		
		String query ="delete Computer where id= :idComputer" ;
		sessionFactory.getCurrentSession().createQuery(query).setLong("idComputer", id).executeUpdate();
	}
	
	
	
	public List<Computer> getAllPagination(int currentPage,String orderByColumns,boolean orderByType) {
		
		List<Computer> result = null;
			
		Order order;
		if(orderByType) {
			order=Order.asc(orderByColumns);
		} else {
			order=Order.desc(orderByColumns);
		}
		
		Criteria c=sessionFactory.getCurrentSession().createCriteria(Computer.class).addOrder(order);
		c.setFirstResult((currentPage-1)*ComputerDaoImpl.LIMIT);
		c.setMaxResults(ComputerDaoImpl.LIMIT);
		logger.debug("Sending query to list all the computers  "  );
		
		result=(ArrayList<Computer>) c.list();

		return result;
	}
	
	public List<Computer> search(String pattern,int currentPage,String orderByColumns,boolean orderByType) {
		List<Computer> result = null;
		
		String searchPattern="%"+pattern+"%";
		
		Order order;
		if(orderByType) {
			order=Order.asc(orderByColumns);
		} else {
			order=Order.desc(orderByColumns);
		}
		
		Criteria c=sessionFactory.getCurrentSession().createCriteria(Computer.class,"cr").createAlias("cr.company","cy",JoinType.LEFT_OUTER_JOIN);
		c.add(Restrictions.or(Restrictions.like("cr.name", searchPattern),Restrictions.like("cy.name", searchPattern))).addOrder(order);
		c.setFirstResult((currentPage-1)*ComputerDaoImpl.LIMIT);
		c.setMaxResults(ComputerDaoImpl.LIMIT);
		logger.debug("Sending query to list all the computers  "  );
		result=(ArrayList<Computer>) c.list();

		System.out.println(result);
		return result;
		
	}
	
	public int count() {
		int res = 0;
		
		Criteria c=sessionFactory.getCurrentSession().createCriteria(Computer.class);
		res=((Long) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		logger.debug("Query count sended succesfully");

		return res;
	}
	
	
	public int count(String pattern) {
		int res = 0;
		String searchPattern="%"+pattern+"%";
		
		Criteria c=sessionFactory.getCurrentSession().createCriteria(Computer.class,"cr");
		c.createAlias("cr.company", "cy",JoinType.LEFT_OUTER_JOIN);
		c.add(Restrictions.or(Restrictions.like("cr.name", searchPattern),Restrictions.like("cy.name", searchPattern)));
		logger.debug("Sending query to count all the computer ");
		res=((Long) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		logger.debug("Query sended succesfully");
 
		return res;
	}
	
	
	public Computer find(long id) {
		Computer res=null;

		res=(Computer) sessionFactory.getCurrentSession().createCriteria(Computer.class).add(Restrictions.eq("id",id)).uniqueResult();
		return res;
	}
	
	
	

	
	
	
	
}
