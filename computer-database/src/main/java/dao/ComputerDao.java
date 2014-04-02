package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import model.Company;
import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import exceptions.TransactionException;


@Repository
public class ComputerDao {

		
    private static Logger logger = LoggerFactory.getLogger(ComputerDao.class);
    
    public static final int LIMIT=13;

    @Autowired
    private ConnectionFactory connectionFactory;
	
	/* *******************************************************/
	/* ***               Constructors                    *** */
	/* *******************************************************/
	
	public ComputerDao() {
		super();
	}
	
	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	public void create(Computer computer) throws TransactionException  {
		
			
		Connection connection = connectionFactory.getConnection();
		
		ResultSet rs=null;
		PreparedStatement stmt=null;
		try {
			String query="INSERT into computer SET id=null,name= ? , introduced=FROM_UNIXTIME( ? ), discontinued=FROM_UNIXTIME( ? ), company_id= ?";
			
			logger.debug("Creating a statement");
			stmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			logger.debug("Statement created");
		
			stmt.setString(1, computer.getName());
			if (computer.getDateIntroduced()!=null) {
				stmt.setLong(2,computer.getDateIntroduced().getTimeInMillis()/1000);
			} else {
				stmt.setNull(2, Types.NULL);
			}
			
			if (computer.getDateDiscontinued()!=null) {
				stmt.setLong(3,computer.getDateDiscontinued().getTimeInMillis()/1000);
			} else {
				stmt.setNull(3, Types.NULL);
			}
			
			if (computer.getCompany()!=null) {
				stmt.setLong(4, computer.getCompany().getId());
			} else {
				stmt.setNull(4, Types.NULL);
			}
			
				
			logger.debug("Sending query to create a computer :\n " + query );
			stmt.execute();
			logger.debug("Query sended succesfully");
			
			rs=stmt.getGeneratedKeys();
	
			if(rs.next()) {
	
				computer.setId(rs.getInt(1));
	
			}
		} catch (SQLException e) {
			throw new TransactionException("SQL Error when trying to create a computer",e);
		} finally {
			connectionFactory.close( rs, stmt);
		}
	}
	
	public void update(Computer computer) throws TransactionException {
		Connection connection = connectionFactory.getConnection();
		ResultSet rs=null;
		PreparedStatement stmt=null;

		try {
			String query="UPDATE computer SET name= ? , introduced=FROM_UNIXTIME( ? ), discontinued=FROM_UNIXTIME( ? ), company_id= ?  WHERE id= ?  ";
			
			
			logger.debug("Creating a statement");
			stmt = connection.prepareStatement(query);
			logger.debug("Statement created");
			
			stmt.setString(1, computer.getName());
			if (computer.getDateIntroduced()!=null) {
				stmt.setLong(2,computer.getDateIntroduced().getTimeInMillis()/1000);
			} else {
				stmt.setNull(2, Types.NULL);
			}
			
			if (computer.getDateDiscontinued()!=null) {
				stmt.setLong(3,computer.getDateDiscontinued().getTimeInMillis()/1000);
			} else {
				stmt.setNull(3, Types.NULL);
			}
			
			if (computer.getCompany()!=null) {
				stmt.setLong(4, computer.getCompany().getId());
			} else {
				stmt.setNull(4, Types.NULL);
			}
			
			stmt.setLong(5, computer.getId());
			
			stmt.executeUpdate();
			logger.debug("Query sended succesfully");
		
		} catch (SQLException e) {
			throw new TransactionException("SQL Error when trying to update a computer",e);
		} finally {
			connectionFactory.close( rs, stmt);
		}
		
	}
	

	
	
	public void delete(int id) throws TransactionException {
		Connection connection = connectionFactory.getConnection();
		ResultSet rs=null;
		PreparedStatement stmt=null;

		try {
			String query ="DELETE FROM computer WHERE id=?";
			
			logger.debug("Creating a statement");
			stmt = connection.prepareStatement(query);
			logger.debug("Statement created");
	
			stmt.setLong(1, id);
			
			stmt.executeUpdate();
			logger.debug("Query sended succesfully");
		
		} catch (SQLException e) {
			throw new TransactionException("SQL Error when trying to delete a computer",e);
		} finally {
			connectionFactory.close( rs, stmt);
		}
		
	}
	
	
	
	public ArrayList<Computer> getAllPagination(int currentPage,String orderByColumns,boolean orderByType) {
		ArrayList<Computer> result = new ArrayList<Computer>();
		Connection connection = connectionFactory.getConnection();
		ResultSet rs=null;
		PreparedStatement stmt=null;
		try {
			
			
			stmt = connection.prepareStatement("SELECT * from company");
			rs=stmt.executeQuery();
			
			HashMap<Integer, String> companyTable = new HashMap<Integer,String>();
			while(rs.next()){
				companyTable.put(rs.getInt(1), rs.getString(2));
			}
			
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * from computer ORDER BY ");
			query.append(orderByColumns);
			if(orderByType) {
				query.append(" ASC");
			} else {
				query.append(" DESC");
			}
			query.append(" LIMIT ? OFFSET ? ");
			
			connectionFactory.close( rs, stmt);
			
			logger.debug("Creating a statement");
			stmt = connection.prepareStatement(query.toString());
			logger.debug("Statement created");
			
			stmt.setLong(1,ComputerDao.LIMIT);
			stmt.setLong(2,(currentPage-1)*ComputerDao.LIMIT);
					
			logger.debug("Sending query to list all the computers  "  );
			rs=stmt.executeQuery();
			logger.debug("Query sended succesfully");
			
			while(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				Calendar introduced=null;
				if(rs.getDate(3)!=null) {
					introduced=new GregorianCalendar();
					introduced.setTime(rs.getDate(3));
				}
				Calendar discontinued=null;
				if(rs.getDate(4)!=null) {
					discontinued=new GregorianCalendar();
					discontinued.setTime(rs.getDate(4));
				}
				int companyId = rs.getInt(5);
				
				Computer c=new Computer(id,name,introduced,discontinued,new Company(companyId,companyTable.get(companyId)));
				result.add(c);
			}
			
		} catch (SQLException e) {
			throw new TransactionException("SQL Error when trying to create a computer",e);
		} finally {
			connectionFactory.close( rs, stmt);
			connectionFactory.closeConnection();
		}
		return result;
	}
	
	public int count() {
		int res = 0;
		Connection connection = connectionFactory.getConnection();
		ResultSet rs=null;
		PreparedStatement stmt=null;
		try {
			logger.debug("Creating a statement");
			stmt = connection.prepareStatement("SELECT COUNT(*) from computer ");
			logger.debug("Statement created");
			
			
			logger.debug("Sending query to count all the computer ");
			rs=stmt.executeQuery();
			logger.debug("Query sended succesfully");
			
			if (rs.next()) {
				res=rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new TransactionException("SQL Error when trying to create a computer",e);
		} finally {
			connectionFactory.close( rs, stmt);
			connectionFactory.closeConnection();
		}
		return res;
	}
	
	public int count(String pattern) {
		int res = 0;
		Connection connection = connectionFactory.getConnection();
		ResultSet rs=null;
		PreparedStatement stmt=null;
		try {
			
		
			
			String query ="SELECT COUNT(*) from computer cr LEFT JOIN company cy on cr.company_id=cy.id where cr.name like ? or cy.name like ?";
			
			logger.debug("Creating a statement");
			stmt = connection.prepareStatement(query);
			logger.debug("Statement created");
			
			stmt.setString(1, "%"+pattern+"%");
			stmt.setString(2, "%"+pattern+"%");
			
			
			logger.debug("Sending query to count all the computer ");
			rs=stmt.executeQuery();
			logger.debug("Query sended succesfully");
			
			
			if (rs.next()) {
				res=rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new TransactionException("SQL Error when trying to create a computer",e);
		} finally {
			connectionFactory.close( rs, stmt);
			connectionFactory.closeConnection();
		}
		return res;
	}
	
	public Computer find(int id) {
		Computer res=null;
		Connection connection = connectionFactory.getConnection();
		ResultSet rs=null;
		PreparedStatement stmt=null;
		try {
			
			
			
			String query;
			query="SELECT * from computer where id= ?";

			logger.debug("Creating a statement");
			stmt = connection.prepareStatement(query);
			logger.debug("Statement created");
			
			stmt.setLong(1, id);
			
			logger.debug("Sending query to list all the company " );
			rs=stmt.executeQuery();
			logger.debug("Query sended succesfully");
			
			if(rs.next()) {
				String name=rs.getString(2);
				Calendar introduced=null;
				if(rs.getDate(3)!=null) {
					introduced=new GregorianCalendar();
					introduced.setTime(rs.getDate(3));
				}
				Calendar discontinued=null;
				if(rs.getDate(4)!=null) {
					discontinued=new GregorianCalendar();
					discontinued.setTime(rs.getDate(4));
				}
				int companyId = rs.getInt(5);
				String companyName=null;
				connectionFactory.close( rs, stmt);
				
				stmt=connection.prepareStatement(query);
				rs=stmt.executeQuery("SELECT company.name from company company where company.id="+companyId);
				if (rs.next()) {
					companyName=rs.getString(1);
				}
				res=new Computer(id,name,introduced,discontinued,new Company(companyId,companyName));
			}
		} catch (SQLException e) {
			throw new TransactionException("SQL Error when trying to create a computer",e);
		} finally {
			connectionFactory.close( rs, stmt);
			connectionFactory.closeConnection();
		}
		return res;
	}
	
	
	
	public ArrayList<Computer> search(String pattern,int currentPage,String orderByColumns,boolean orderByType) {
		ArrayList<Computer> result = new ArrayList<Computer>();
		Connection connection = connectionFactory.getConnection();
		ResultSet rs=null;
		PreparedStatement stmt=null;
		try {
			
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * from computer cr LEFT JOIN company cy on cr.company_id=cy.id where cr.name like ? or cy.name like ? ORDER BY cr.");
			query.append(orderByColumns);
			if(orderByType) {
				query.append(" ASC");
			} else {
				query.append(" DESC");
			}
			query.append(" LIMIT ? OFFSET ?");
			
			logger.debug("Creating a statement");
			stmt = connection.prepareStatement(query.toString());
			logger.debug("Statement created");

			stmt.setString(1, "%"+pattern+"%");
			stmt.setString(2, "%"+pattern+"%");
			stmt.setLong(3,ComputerDao.LIMIT);
			stmt.setLong(4,(currentPage-1)*ComputerDao.LIMIT);
						
			logger.debug("Sending query to search for computer ");
			rs=stmt.executeQuery();
			logger.debug("Query sended succesfully");
			
			while(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				Calendar introduced=null;
				if(rs.getDate(3)!=null) {
					introduced=new GregorianCalendar();
					introduced.setTime(rs.getDate(3));
				}
				Calendar discontinued=null;
				if(rs.getDate(4)!=null) {
					discontinued=new GregorianCalendar();
					discontinued.setTime(rs.getDate(4));
				}
				int companyId = rs.getInt(5);
				
				Computer c=new Computer(id,name,introduced,discontinued,new Company(companyId,rs.getString(7)));
				result.add(c);
			}
			
		} catch (SQLException e) {
			throw new TransactionException("SQL Error when trying to create a computer",e);
		} finally {
			connectionFactory.close( rs, stmt);
			connectionFactory.closeConnection();
		}
		return result;
		
	}
	
	
	
	
}
