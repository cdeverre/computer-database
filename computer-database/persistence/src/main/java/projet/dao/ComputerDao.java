package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import projet.exception.TransactionException;
import projet.mapper.ComputerRowMapper;
import projet.model.Computer;

import com.jolbox.bonecp.BoneCPDataSource;


@Repository
public class ComputerDao {

		
    private static Logger logger = LoggerFactory.getLogger(ComputerDao.class);
    
    public static final int LIMIT=13;

    
    @Autowired
	private BoneCPDataSource boneCP;
	
	/* *******************************************************/
	/* ***               Constructors                    *** */
	/* *******************************************************/
	
	public ComputerDao() {
		super();
	}
	
	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	public void create(final Computer computer) throws TransactionException  {
		
		JdbcTemplate jdbcTemplate= new JdbcTemplate(boneCP);

		KeyHolder holder = new GeneratedKeyHolder();
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {           
		
		                @Override
		                public PreparedStatement createPreparedStatement(Connection connection)
		                        throws SQLException {
		            		String query="INSERT into computer SET id=null,name= ? , introduced=FROM_UNIXTIME( ? ),"
		            				+ " discontinued=FROM_UNIXTIME( ? ), company_id= ?";
		                    PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		                    stmt.setString(1, computer.getName());
		        			if (computer.getDateIntroduced()!=null) {
		        				stmt.setLong(2,computer.getDateIntroduced().getMillis()/1000);
		        			} else {
		        				stmt.setNull(2, Types.NULL);
		        			}
		        			
		        			if (computer.getDateDiscontinued()!=null) {
		        				stmt.setLong(3,computer.getDateDiscontinued().getMillis()/1000);
		        			} else {
		        				stmt.setNull(3, Types.NULL);
		        			}
		        			
		        			if (computer.getCompany()!=null) {
		        				stmt.setLong(4, computer.getCompany().getId());
		        			} else {
		        				stmt.setNull(4, Types.NULL);
		        			}
		                    return stmt;
		                }
		            }, holder);
		
			
			computer.setId(holder.getKey().longValue());

		} catch (DataAccessException e) {
			throw new TransactionException("SQL Error when trying to create a computer",e);
		} 
	}
	
	public void update(Computer computer) throws TransactionException {
		
		JdbcTemplate jdbcTemplate= new JdbcTemplate(boneCP);

		String query="UPDATE computer SET name= ? , introduced=FROM_UNIXTIME( ? )"
					+ " ,discontinued=FROM_UNIXTIME( ? ), company_id= ?  WHERE id= ?  ";
		
		Object[] args=new Object[5];

		args[0]= computer.getName();
		if (computer.getDateIntroduced()!=null) {
			args[1]=computer.getDateIntroduced().getMillis()/1000;
		} else {
			args[1]=null;
		}
		
		if (computer.getDateDiscontinued()!=null) {
			args[2]=computer.getDateDiscontinued().getMillis()/1000;
		} else {
			args[2]= null;
		}
		
		if (computer.getCompany()!=null) {
			args[3]= computer.getCompany().getId();
		} else {
			args[3]= null;
		}
		args[4]= computer.getId();
		
		try {
			jdbcTemplate.update(query, args);
			logger.debug("Query update sended succesfully");
		
		} catch (DataAccessException e) {
			throw new TransactionException("SQL Error when trying to update a computer",e);
		} 
		
	}
	

	
	
	public void delete(long id) throws TransactionException {
		
		JdbcTemplate jdbcTemplate= new JdbcTemplate(boneCP);
		String query ="DELETE FROM computer WHERE id=?";
		try {
			Object[] args=new Object[1];
			args[0]=id;
			jdbcTemplate.update(query, args);
	
			logger.debug("Query delete sended succesfully");
		
		} catch (DataAccessException e) {
			throw new TransactionException("SQL Error when trying to delete a computer",e);
		} 
		
	}
	
	
	
	public List<Computer> getAllPagination(int currentPage,String orderByColumns,boolean orderByType) {
		List<Computer> result = null;
		
		JdbcTemplate jdbcTemplate= new JdbcTemplate(boneCP);

		StringBuilder query = new StringBuilder();
		query.append("SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cr.company_id, cy.name ");
		query.append("from computer cr LEFT JOIN company cy ON cr.company_id=cy.id ORDER BY cr.");
		query.append(orderByColumns);
		if(orderByType) {
			query.append(" ASC");
		} else {
			query.append(" DESC");
		}
		query.append(" LIMIT ? OFFSET ?  ");
		
		Object[] args=new Object[2];
		args[0]=ComputerDao.LIMIT;
		args[1]=(currentPage-1)*ComputerDao.LIMIT;
		
		logger.debug("Sending query to list all the computers  "  );

		try {
			result = jdbcTemplate.query(query.toString(), args,new ComputerRowMapper());
			logger.debug("Query sended succesfully");
		} catch (DataAccessException e) {
			throw new TransactionException("SQL Error when trying to get the list of computer",e);
		}


		return result;
	}
	
	public int count() {
		int res = 0;
		JdbcTemplate jdbcTemplate= new JdbcTemplate(boneCP);

		try {
			String query="SELECT COUNT(*) from computer ";
	
			logger.debug("Sending query to count all the computer ");

			res=jdbcTemplate.queryForObject(query, Integer.class);
			logger.debug("Query sended succesfully");

		} catch (DataAccessException e) {
			throw new TransactionException("SQL Error when trying to create a computer",e);
		}
		return res;
	}
	
	
	public int count(String pattern) {
		int res = 0;
		JdbcTemplate jdbcTemplate= new JdbcTemplate(boneCP);
		String query ="SELECT COUNT(*) from computer cr LEFT JOIN company cy on cr.company_id=cy.id where cr.name like ? or cy.name like ?";
		
		Object[] args=new Object[2];
		
		args[0]= "%"+pattern+"%";
		args[1]= "%"+pattern+"%";
		try {

			logger.debug("Sending query to count all the computer ");
			res=jdbcTemplate.queryForObject(query,args, Integer.class);
			logger.debug("Query sended succesfully");

		} catch (DataAccessException e) {
			throw new TransactionException("SQL Error when trying to create a computer",e);
		} 
		return res;
	}
	
	public Computer find(long id) {
		Computer res=null;
		JdbcTemplate jdbcTemplate= new JdbcTemplate(boneCP);
		StringBuilder query=new StringBuilder();
		query.append("SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cr.company_id, cy.name ");
		query.append("from computer cr LEFT JOIN company cy on cr.company_id=cy.id where cr.id= ?");
		Object[] args=new Object[1];
		args[0]=id;
		try {

			logger.debug("Sending query to list all the company " );
			res=jdbcTemplate.query(query.toString(),args, new ComputerRowMapper()).get(0);
			logger.debug("Query sended succesfully");

		} catch (DataAccessException e) {
			throw new TransactionException("SQL Error when trying to create a computer",e);
		} 
		return res;
	}
	
	
	
	public List<Computer> search(String pattern,int currentPage,String orderByColumns,boolean orderByType) {
		List<Computer> result = null;
		JdbcTemplate jdbcTemplate= new JdbcTemplate(boneCP);

		StringBuilder query = new StringBuilder();
		query.append("SELECT * from computer cr LEFT JOIN company cy on cr.company_id=cy.id where cr.name like ? or cy.name like ? ORDER BY cr.");
		query.append(orderByColumns);
		if(orderByType) {
			query.append(" ASC");
		} else {
			query.append(" DESC");
		}
		query.append(" LIMIT ? OFFSET ?");
		Object[] args=new Object[4];
		args[0]="%"+pattern+"%";
		args[1]="%"+pattern+"%";
		args[2]=ComputerDao.LIMIT;
		args[3]=(currentPage-1)*ComputerDao.LIMIT;
		
		try {
			result = jdbcTemplate.query(query.toString(), args,new ComputerRowMapper());
			logger.debug("Query sended succesfully");
		} catch (DataAccessException e) {
			throw new TransactionException("SQL Error when trying to get the list of computer",e);
		}
		return result;
		
	}
	
	
	
	
}
