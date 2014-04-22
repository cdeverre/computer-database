package projet.dao.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import projet.dao.CompanyDao;
import projet.exception.TransactionException;
import projet.mapper.CompanyRowMapper;
import projet.model.Company;

@Repository
public class CompanyDaoImpl implements CompanyDao {

    private Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    
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
		List<Company> result=null;
		logger.debug("Creating a jdbc template");

		
		String query = "SELECT * from company";
		try {
			result = jdbcTemplate.query(query, new CompanyRowMapper());
		} catch (DataAccessException e) {
			logger.error("SQL error when getting the list of company");
			throw new TransactionException("SQL Error when trying to get the list of company",e);
		}
		
		return result;
	}
	
	
	public String getName(long id) {
		String res=null;
		
		logger.debug("Creating a jdbc template");
		
		
		Object[] args=new Object[] {id};
		String query = "SELECT cr.name from company cr where id= ?";
		try {
			res=jdbcTemplate.queryForObject(query, String.class , args);	
		}catch (DataAccessException e) {
			logger.error("SQL error when getting the list of company");
		}
		return res;
	
	}

	
	
		
	
}