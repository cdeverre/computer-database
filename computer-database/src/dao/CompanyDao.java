package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domainClasses.Company;

public class CompanyDao {



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
			Statement stmt = connection.createStatement();
			
			String query = "INSERT INTO company SET id=null, name='"+company.getName()+"'";
			stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
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
			Statement stmt = connection.createStatement();
			
			stmt.executeUpdate("UPDATE computer SET name='"+company.getName()+"' WHERE id='"+company.getId()+"'");
			
			DaoFactory.close(connection,rs,stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Company company) {
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			Statement stmt = connection.createStatement();
			
			stmt.executeQuery("DELETE FROM computer WHERE id='"+company.getId()+"'");
			
			DaoFactory.close(connection,rs,stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*public void find(Company company) {
		try {
			this.statement.executeQuery("INSERT INTO computer SET id='"+computer.getId()+"', name='"+computer.getName()+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	
}