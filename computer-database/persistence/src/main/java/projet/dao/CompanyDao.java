package projet.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.jolbox.bonecp.BoneCPDataSource;

import projet.exception.TransactionException;
import projet.model.Company;

@Repository
public class CompanyDao {

    private Logger logger = LoggerFactory.getLogger(CompanyDao.class);


    @Autowired
   	private BoneCPDataSource boneCP;
    
	/* *******************************************************/
	/* ***               Constructors                    *** */
	/* *******************************************************/
	
	public CompanyDao() {
		super();
	}
		
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	
	
	public ArrayList<Company> getAll() {
		ArrayList<Company> result = new ArrayList<Company>();
		Connection connection = DataSourceUtils.getConnection(boneCP);
		ResultSet rs=null;
		Statement stmt=null;
		try {
			logger.debug("Creating a statement");
			stmt = connection.createStatement();
			logger.debug("Statement created");
			
			String query = "SELECT * from company";
			
			logger.debug("Sending query to list all the computers :\n"+query);
			rs=stmt.executeQuery(query);
			logger.debug("Query sended succesfully");
			
						
			while(rs.next()) {
				long id=rs.getLong(1);
				String name=rs.getString(2);
				Company c=new Company(id,name);
				result.add(c);
			}
			
		} catch (SQLException e) {
			logger.error("SQL error when getting the list of company");
			throw new TransactionException("SQL Error when trying to create a computer",e);
		} finally {
			ConnectionFactory.close(rs,stmt);
		}
		return result;
	}
	
	public String getName(long id) {
		String res=null;
		Connection connection = DataSourceUtils.getConnection(boneCP);
		ResultSet rs=null;
		Statement stmt=null;
		try{
			logger.debug("Creating a statement");
			stmt = connection.createStatement();
			logger.debug("Statement created");
			
			String query = "SELECT cr.name from company cr where id='"+id+"'";
			
			logger.debug("Sending query to list all the computers :\n"+query);
			rs=stmt.executeQuery(query);
			logger.debug("Query sended succesfully");
			if(rs.next()) {
				res=rs.getString(1);
			}
		} catch (SQLException e) {
			logger.error("SQL error when getting a company by his id");
			throw new TransactionException("SQL Error when trying to create a computer",e);
		} finally {
			ConnectionFactory.close(rs,stmt);
		}
		return res;
		
	}

	
	
		
	
}