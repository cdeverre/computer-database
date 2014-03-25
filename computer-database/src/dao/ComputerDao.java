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
    
    private static final int LIMIT=20;

	
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
			logger.debug("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.debug("Statement created");
		
			String queryIntroduced;
			if (computer.getDateIntroduced()!=null) {
				long introduced=computer.getDateIntroduced().getTimeInMillis()/1000;
				queryIntroduced="FROM_UNIXTIME("+introduced+")";
			} else {
				queryIntroduced="null";
			}
			
			String queryDiscontinued;
			if (computer.getDateDiscontinued()!=null) {
				long discontinued = computer.getDateDiscontinued().getTimeInMillis()/1000;
				queryDiscontinued="FROM_UNIXTIME("+discontinued+")";
			} else {
				queryDiscontinued="null";
			}
			
			String queryCompagny;
			if (computer.getCompany()!=null) {
				queryCompagny="'"+computer.getCompany().getId()+"'";
			} else{
				queryCompagny="null";
			}
			String query ="INSERT INTO computer SET id=null," +
					" name='"+computer.getName()+"', introduced="+queryIntroduced+
					", discontinued="+queryDiscontinued+
					", company_id="+queryCompagny;
			
			
			logger.info("Sending query to create a computer :\n " + query );
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			logger.debug("Query sended succesfully");
			
			logger.debug("Getting the generated keys");
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
			logger.debug("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.debug("Statement created");
			
			long introduced=computer.getDateIntroduced().getTimeInMillis()/1000;
			long discontinued = computer.getDateDiscontinued().getTimeInMillis()/1000;
			
			String query=("UPDATE computer SET name='"+computer.getName()+"', introduced=FROM_UNIXTIME("+
					introduced+"), discontinued=FROM_UNIXTIME("+discontinued+
					"), company_id='"+computer.getCompany().getId()+"' WHERE id='"+computer.getId()+"'");
			
			logger.debug("Sending query to update a computer :\n " + query );
			stmt.executeUpdate(query);
			logger.debug("Query sended succesfully");
			
			DaoFactory.close(connection, rs, stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Computer computer) {
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.debug("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.debug("Statement created");
					
			String query = "DELETE FROM computer WHERE id='"+computer.getId()+"'";
			
			logger.debug("Sending query to delete a computer :\n " + query );
			stmt.executeUpdate(query);
			logger.debug("Query sended succesfully");
			
			DaoFactory.close(connection, rs, stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void delete(int id) {
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.debug("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.debug("Statement created");
					
			String query = "DELETE FROM computer WHERE id='"+id+"'";
			
			logger.debug("Sending query to delete a computer :\n " + query );
			stmt.executeUpdate(query);
			logger.debug("Query sended succesfully");
			
			DaoFactory.close(connection, rs, stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Computer> getAll() {
		ArrayList<Computer> result = new ArrayList<Computer>();
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.debug("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.debug("Statement created");
			
			String query = "SELECT * from company";
			
			logger.debug("Sending query to list all the company");
			rs=stmt.executeQuery(query);
			logger.debug("Query sended succesfully");
			
			HashMap<Integer, String> companyTable = new HashMap<Integer,String>();
			while(rs.next()){
				companyTable.put(rs.getInt(1), rs.getString(2));
			}
			
			query = "SELECT * from computer";
			
			logger.debug("Sending query to list all the computers :\n " + query );
			rs=stmt.executeQuery(query);
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
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<Computer> getAllPagination(int offset) {
		ArrayList<Computer> result = new ArrayList<Computer>();
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.debug("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.debug("Statement created");
			
			String query = "SELECT * from company ";
			
			logger.debug("Sending query to list all the company");
			rs=stmt.executeQuery(query);
			logger.debug("Query sended succesfully");
			
			HashMap<Integer, String> companyTable = new HashMap<Integer,String>();
			while(rs.next()){
				companyTable.put(rs.getInt(1), rs.getString(2));
			}
			
			query = "SELECT * from computer LIMIT"+ComputerDao.LIMIT+" OFFSET "+Integer.toString(offset*ComputerDao.LIMIT);
			
			logger.debug("Sending query to list all the computers :\n " + query );
			rs=stmt.executeQuery(query);
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
			e.printStackTrace();
		}
		return result;
	}
	
	public int count() {
		int res = 0;
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.debug("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.debug("Statement created");
			
			String query = "SELECT COUNT(*) from computer ";
			
			logger.debug("Sending query to count all the computer :\n " + query);
			rs=stmt.executeQuery(query);
			logger.debug("Query sended succesfully");
			
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
			logger.debug("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.debug("Statement created");
			
			String query = "SELECT * from computer where id='"+id+"'";
			
			logger.debug("Sending query to list all the company :\n " + query );
			rs=stmt.executeQuery(query);
			logger.debug("Query sended succesfully");
			
			if(rs.next()) {
				String name=rs.getString(2);
				Calendar introduced=new GregorianCalendar();
				if(rs.getDate(3)!=null) {
					introduced.setTime(rs.getDate(3));
				}
				Calendar discontinued=new GregorianCalendar();
				if(rs.getDate(4)!=null) {
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
