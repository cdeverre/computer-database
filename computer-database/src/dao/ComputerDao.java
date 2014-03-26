package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domainClasses.Company;
import domainClasses.Computer;

public class ComputerDao {

		
    private static Logger logger = LoggerFactory.getLogger(ComputerDao.class);
    
    private static final int LIMIT=13;

	
	/* *******************************************************/
	/* ***               Constructors                    *** */
	/* *******************************************************/
	
	protected ComputerDao() {
		super();
	}
	
	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	public void create(Computer computer) {
		
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs;
			logger.info("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.info("Statement created");
		
			StringBuilder queryIntroduced=new StringBuilder();
			if (computer.getDateIntroduced()!=null) {
				long introduced=computer.getDateIntroduced().getTimeInMillis()/1000;
				queryIntroduced.append("FROM_UNIXTIME(");
				queryIntroduced.append(introduced);
				queryIntroduced.append(")");
			} else {
				queryIntroduced.append("null");
			}
			
			StringBuilder queryDiscontinued=new StringBuilder();
			if (computer.getDateDiscontinued()!=null) {
				long discontinued = computer.getDateDiscontinued().getTimeInMillis()/1000;
				queryDiscontinued.append("FROM_UNIXTIME(");
				queryDiscontinued.append(discontinued);
				queryDiscontinued.append(")");
			} else {
				queryDiscontinued.append("null");
			}
			
			StringBuilder queryCompagny=new StringBuilder();
			if (computer.getCompany()!=null) {
				queryCompagny.append("'");
				queryCompagny.append(computer.getCompany().getId());
				queryCompagny.append("'");
			} else{
				queryCompagny.append("null");
			}
			StringBuilder query =new StringBuilder();
			query.append("INSERT INTO computer SET id=null, name='");
			query.append(computer.getName());
			query.append("', introduced=");
			query.append(queryIntroduced);
			query.append(", discontinued=");
			query.append(queryDiscontinued);
			query.append(", company_id=");
			query.append(queryCompagny);
			
			
			logger.info("Sending query to create a computer :\n " + query );
			stmt.executeUpdate(query.toString(), Statement.RETURN_GENERATED_KEYS);
			logger.info("Query sended succesfully");
			
			logger.info("Getting the generated keys");
			rs=stmt.getGeneratedKeys();
			if(rs.next()) {
				computer.setId(rs.getInt(1));
			}
			
			DaoFactory.close(connection, rs, stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Computer computer) {
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.info("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.info("Statement created");
			
			StringBuilder queryIntroduced=new StringBuilder();
			if (computer.getDateIntroduced()!=null) {
				long introduced=computer.getDateIntroduced().getTimeInMillis()/1000;
				queryIntroduced.append("FROM_UNIXTIME(");
				queryIntroduced.append(introduced);
				queryIntroduced.append(")");
			} else {
				queryIntroduced.append("null");
			}
			
			StringBuilder queryDiscontinued=new StringBuilder();
			if (computer.getDateDiscontinued()!=null) {
				long discontinued = computer.getDateDiscontinued().getTimeInMillis()/1000;
				queryDiscontinued.append("FROM_UNIXTIME(");
				queryDiscontinued.append(discontinued);
				queryDiscontinued.append(")");
			} else {
				queryDiscontinued.append("null");
			}
			
			StringBuilder queryCompagny=new StringBuilder();
			if (computer.getCompany()!=null) {
				queryCompagny.append("'");
				queryCompagny.append(computer.getCompany().getId());
				queryCompagny.append("'");
			} else{
				queryCompagny.append("null");
			}
			
			StringBuilder query=new StringBuilder();
			query.append("UPDATE computer SET name='");
			query.append(computer.getName());
			query.append("', introduced=");
			query.append(queryIntroduced);
			query.append(", discontinued=");
			query.append(queryDiscontinued);
			query.append(", company_id=");
			query.append(queryCompagny);
			query.append(" WHERE id='");
			query.append(computer.getId());
			query.append("'");
			
			logger.info("Sending query to update a computer :\n " + query );
			stmt.executeUpdate(query.toString());
			logger.info("Query sended succesfully");
			
			DaoFactory.close(connection, rs, stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Computer computer) {
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.info("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.info("Statement created");
					
			StringBuilder query =new StringBuilder();
			query.append("DELETE FROM computer WHERE id='");
			query.append(computer.getId());
			query.append("'");
			
			logger.info("Sending query to delete a computer :\n " + query );
			stmt.executeUpdate(query.toString());
			logger.info("Query sended succesfully");
			
			DaoFactory.close(connection, rs, stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void delete(int id) {
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.info("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.info("Statement created");
					
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM computer WHERE id='");
			query.append(id);
			query.append("'");
			
			logger.info("Sending query to delete a computer :\n " + query );
			stmt.executeUpdate(query.toString());
			logger.info("Query sended succesfully");
			
			DaoFactory.close(connection, rs, stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public ArrayList<Computer> getAllPagination(int currentPage,String orderByColumns,boolean orderByType) {
		ArrayList<Computer> result = new ArrayList<Computer>();
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.info("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.info("Statement created");
			
			StringBuilder query = new StringBuilder();
			
			logger.info("Sending query to list all the company");
			rs=stmt.executeQuery("SELECT * from company ");
			logger.info("Query sended succesfully");
			
			HashMap<Integer, String> companyTable = new HashMap<Integer,String>();
			while(rs.next()){
				companyTable.put(rs.getInt(1), rs.getString(2));
			}
			
			query.append("SELECT * from computer ORDER BY ");
			query.append(orderByColumns);
			if(orderByType) {
				query.append(" ASC");
			} else {
				query.append(" DESC");
			}
			query.append(" LIMIT ");
			query.append(ComputerDao.LIMIT);
			query.append(" OFFSET ");
			query.append(Integer.toString((currentPage-1)*ComputerDao.LIMIT));
			
			logger.info("Sending query to list all the computers :\n " + query );
			rs=stmt.executeQuery(query.toString());
			logger.info("Query sended succesfully");
			
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
			e.printStackTrace();
		}
		return result;
	}
	
	public int count() {
		int res = 0;
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.info("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.info("Statement created");
			
			String query = "SELECT COUNT(*) from computer ";
			
			logger.info("Sending query to count all the computer :\n " + query);
			rs=stmt.executeQuery(query);
			logger.info("Query sended succesfully");
			
			if (rs.next()) {
				res=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int count(String pattern) {
		int res = 0;
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.info("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.info("Statement created");
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT COUNT(*) from computer cr LEFT JOIN company cy on cr.company_id=cy.id where cr.name like '%");
			query.append(pattern);
			query.append("%' or cy.name like '%");
			query.append(pattern);
			query.append("%' ");
			
			logger.info("Sending query to count all the computer :\n " + query);
			rs=stmt.executeQuery(query.toString());
			logger.info("Query sended succesfully");
			
			if (rs.next()) {
				res=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Computer find(int id) {
		Computer res=null;
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.info("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.info("Statement created");
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * from computer where id='");
			query.append(id);
			query.append("'");
			
			logger.info("Sending query to list all the company :\n " + query );
			rs=stmt.executeQuery(query.toString());
			logger.info("Query sended succesfully");
			
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
				
				rs=stmt.executeQuery("SELECT company.name from company company where company.id="+companyId);
				if (rs.next()) {
					companyName=rs.getString(1);
				}
				res=new Computer(id,name,introduced,discontinued,new Company(companyId,companyName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int getNumberOfPage(int count) {
		return (int)Math.ceil((double)count/(double)ComputerDao.LIMIT);
	}
	
	
	public ArrayList<Computer> search(String pattern,int currentPage,String orderByColumns,boolean orderByType) {
		ArrayList<Computer> result = new ArrayList<Computer>();
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.info("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.info("Statement created");
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * from computer cr LEFT JOIN company cy on cr.company_id=cy.id where cr.name like '%");
			query.append(pattern);
			query.append("%' or cy.name like '%");
			query.append(pattern);
			query.append("%' ORDER BY cr.");
			query.append(orderByColumns);
			if(orderByType) {
				query.append(" ASC");
			} else {
				query.append(" DESC");
			}
			query.append(" LIMIT ");
			query.append(ComputerDao.LIMIT);
			query.append(" OFFSET ");
			query.append(Integer.toString((currentPage-1)*ComputerDao.LIMIT));
			
			logger.info("Sending query to search for computer : \n"+query);
			rs=stmt.executeQuery(query.toString());
			logger.info("Query sended succesfully");
			
			
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
			e.printStackTrace();
		}
		return result;
		
	}
	
	public static void main(String args[]) {
		
		System.out.println(Math.ceil(((double)5/(double)2)));
		System.out.println(Math.ceil(5.2));
//		System.out.println(ServiceFactory.getCompanyServices().getName(1));
//		Calendar c1=new GregorianCalendar();
//		c1.set(1999,02,5);
//		System.out.println(c1.getTime());
//		System.out.println(Tools.createStringFromCalendar(c1));
//		System.out.println(c1.getTime());
//		Calendar c2=new GregorianCalendar();
//		c2.set(2002,03,5);
//		Company company=new Company(3,"RCA");
//		Computer c=new Computer("test",c1,c2,company);
//		ServiceFactory.getComputerServices().create(c);*/

	}


	


	
	
	
}
