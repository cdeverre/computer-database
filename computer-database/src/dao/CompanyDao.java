package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domainClasses.Company;

public class CompanyDao {

    private static Logger logger = LoggerFactory.getLogger(CompanyDao.class);


	/* *******************************************************/
	/* ***               Constructors                    *** */
	/* *******************************************************/
	
	protected CompanyDao() {
		super();
	}
	
		
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	public void create(Company company) {
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.debug("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.debug("Statement created");
			
			String query = "INSERT INTO company SET id=null, name='"+company.getName()+"'";
			
			logger.debug("Sending query to create a company");
			stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			logger.debug("Query sended succesfully");
			
			logger.debug("Getting the generated keys");
			rs=stmt.getGeneratedKeys();
			
			if(rs.next()) {
				company.setId(rs.getInt(1));
			}
			
			DaoFactory.close(connection,rs,stmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Company company) {
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.debug("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.debug("Statement created");
			
			String query="UPDATE computer SET name='"+company.getName()+"' WHERE id='"+company.getId()+"'";
			
			logger.debug("Sending query to update a company :\n"+query);
			stmt.executeUpdate(query);
			logger.debug("Query sended succesfully");
			
			DaoFactory.close(connection,rs,stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Company company) {
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.debug("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.debug("Statement created");
			
			String query= "DELETE FROM computer WHERE id='"+company.getId()+"'";
			
			logger.debug("Sending query to delete a company :\n"+query);
			stmt.executeQuery(query);
			logger.debug("Query sended succesfully");
			
			DaoFactory.close(connection,rs,stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Company> getAll() {
		ArrayList<Company> result = new ArrayList<Company>();
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.debug("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.debug("Statement created");
			
			String query = "SELECT * from company";
			
			logger.debug("Sending query to list all the computers :\n"+query);
			rs=stmt.executeQuery(query);
			logger.debug("Query sended succesfully");
			
						
			while(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				Company c=new Company(id,name);
				result.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getName(int id) {
		String res=null;
		try{
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			logger.debug("Creating a statement");
			Statement stmt = connection.createStatement();
			logger.debug("Statement created");
			
			String query = "SELECT cr.name from company cr where id='"+id+"'";
			
			logger.debug("Sending query to list all the computers :\n"+query);
			rs=stmt.executeQuery(query);
			logger.debug("Query sended succesfully");
			if(rs.next()) {
				res=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	
}