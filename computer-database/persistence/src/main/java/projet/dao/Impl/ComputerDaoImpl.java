package projet.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
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

//		throw new TransactionException("SQL Error when trying to create a computer",e);
	}
	
	public void update(Computer computer) throws TransactionException {
		
		sessionFactory.getCurrentSession().merge(computer);

//		throw new TransactionException("SQL Error when trying to update a computer",e);
		
	}
	

	
	public void delete(long id) throws TransactionException {
		
		String query ="delete Computer where id= :idComputer" ;
		sessionFactory.getCurrentSession().createQuery(query).setLong("idComputer", id).executeUpdate();
		
//		throw new TransactionException("SQL Error when trying to delete a computer",e);
		
	}
	
	
	
	public List<Computer> getAllPagination(int currentPage,String orderByColumns,boolean orderByType) {
		
		List<Computer> result = null;
			
		StringBuilder query = new StringBuilder();
		query.append("from Computer as cr order by cr.");
		query.append(orderByColumns);
		if(orderByType) {
			query.append(" asc");
		} else {
			query.append(" desc");
		}
		
		Query q=sessionFactory.getCurrentSession().createQuery(query.toString());
		q.setFirstResult((currentPage-1)*ComputerDaoImpl.LIMIT);
		q.setMaxResults(ComputerDaoImpl.LIMIT);
		logger.debug("Sending query to list all the computers  "  );
		
		result=(ArrayList<Computer>) q.list();

		return result;
	}
	
	public List<Computer> search(String pattern,int currentPage,String orderByColumns,boolean orderByType) {
		List<Computer> result = null;
		
		StringBuilder query = new StringBuilder();
		query.append("select cr from Computer as cr left join cr.company as cy where cr.name like :pattern ");
		query.append(" or cy.name like :pattern order by cr.");
		query.append(orderByColumns);
		if(orderByType) {
			query.append(" asc");
		} else {
			query.append(" desc");
		}
		
		Query q=sessionFactory.getCurrentSession().createQuery(query.toString());
		q.setFirstResult((currentPage-1)*ComputerDaoImpl.LIMIT);
		q.setMaxResults(ComputerDaoImpl.LIMIT);
		q.setString("pattern", "%"+pattern+"%");
		logger.debug("Sending query to list all the computers  "  );
		System.out.println(q.toString());
		result=(ArrayList<Computer>) q.list();

		System.out.println(result);
		return result;
		
	}
	
	public int count() {
		int res = 0;

		String query="SELECT COUNT(*) from Computer ";
		logger.debug("Sending query to count all the computer ");

		res=((Long) sessionFactory.getCurrentSession().createQuery(query).list().get(0)).intValue();
		logger.debug("Query sended succesfully");

		return res;
	}
	
	
	public int count(String pattern) {
		int res = 0;
		
		String query ="SELECT COUNT(*) from Computer as cr LEFT JOIN cr.company as cy where cr.name like :pattern or cy.name like :pattern";
		
		logger.debug("Sending query to count all the computer ");
		res=((Long) sessionFactory.getCurrentSession().createQuery(query).setString("pattern", "%"+pattern+"%").list().get(0)).intValue();
		logger.debug("Query sended succesfully");

		 
		return res;
	}
	
	public Computer find(long id) {
		Computer res=null;
		
		StringBuilder query=new StringBuilder();
		query.append("SELECT cr from Computer as cr where cr.id= :id");

		res=(Computer) sessionFactory.getCurrentSession().createQuery(query.toString()).setLong("id", id).list().get(0);
		return res;
	}
	
	
	

	
	
	
	
}
