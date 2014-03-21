package dao;

import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.util.Date;

import domainClasses.Computer;

public class ComputerDao {

		

	private final static ComputerDao _instance = new ComputerDao(DaoConnection.getInstance());
	
	private Statement statement;

	
	public ComputerDao(DaoConnection _connectionSingleton) {
		try {
			this.setStatement(_connectionSingleton.getConnection().createStatement());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @return the Instance
	 */
	public static ComputerDao getInstance() {
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
	
	
	
	public void create(Computer _computer) {
		try {
			
			String query ="INSERT INTO computer SET id=null," +
					" name='"+_computer.getName()+"', introduced='"+
					_computer.getDateIntroduced().getYear()+"-"+_computer.getDateIntroduced().getMonth()+"-"+_computer.getDateIntroduced().getDay()+
					"', discontinued="+_computer.getDateDiscontinued().getYear()+"-"+_computer.getDateDiscontinued().getMonth()+"-"+_computer.getDateDiscontinued().getDay()+
					"', company_id='"+_computer.getCompany().getId()+"'";
			this.statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			_computer.setId(this.statement.getGeneratedKeys().getInt(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Computer _computer) {
		try {
			String query=("UPDATE computer SET name='"+_computer.getName()+"', introduced='"+_computer.getDateIntroduced()+
					"', discontinued="+_computer.getDateDiscontinued()+"', company_id='"+_computer.getCompany()+"'"+"' WHERE id='"+_computer.getId()+"'");
			this.statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Computer _computer) {
		try {
			this.statement.executeUpdate("DELETE FROM computer WHERE id='"+_computer.getId()+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*public void find(Computer _computer) {
		try {
			this.statement.executeQuery("INSERT INTO computer SET id='"+_computer.getId()+"', name='"+_computer.getName()+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	public static void main(String args[]) {
		//Computer c=new Computer("Test",new Date(1999,1,1),new Date(2000,1,1),1);
		//ComputerDao.getInstance().create(c);
	}
	
	
}
