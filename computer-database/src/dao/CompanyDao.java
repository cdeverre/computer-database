package dao;

import java.sql.SQLException;
import java.sql.Statement;

import domainClasses.Company;

public class CompanyDao {


	private final static CompanyDao _instance = new CompanyDao(DaoConnection.getInstance());
	
	private Statement statement;

	
	public CompanyDao(DaoConnection _connectionSingleton) {
		try {
			this.setStatement(_connectionSingleton.getConnection().createStatement());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @return the Instance
	 */
	public static CompanyDao getInstance() {
		return _instance;
	}


	/**
	 * @return the statement
	 */
	public Statement getStatement() {
		return statement;
	}


	/**
	 * @param statement the statement to set
	 */
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	
	public void create(Company _company) {
		try {
			this.statement.executeQuery("INSERT INTO company SET id='"+_company.getId()+"', name='"+_company.getName()+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Company _company) {
		try {
			this.statement.executeQuery("UPDATE computer SET name='"+_company.getName()+"' WHERE id='"+_company.getId()+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Company _company) {
		try {
			this.statement.executeQuery("DELETE FROM computer WHERE id='"+_company.getId()+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*public void find(Company _company) {
		try {
			this.statement.executeQuery("INSERT INTO computer SET id='"+_computer.getId()+"', name='"+_computer.getName()+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	
}