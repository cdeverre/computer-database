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
			this.statement.executeUpdate("INSERT INTO computer SET id='"+_computer.getId()+"'," +
					" name='"+_computer.getName()+"', introduced='"+_computer.getDate_introduced().getYear()+"-"+_computer.getDate_introduced().getMonth()+"-"+_computer.getDate_introduced().getDay()+
					"'"/*, discontinued="+_computer.getDate_discontinued()+"', company_id='"+_computer.getCompagny_id()+"'"*/);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Computer _computer) {
		try {
			this.statement.executeUpdate("UPDATE computer SET name='"+_computer.getName()+"', introduced='"+_computer.getDate_introduced()+
					"', discontinued="+_computer.getDate_discontinued()+"', company_id='"+_computer.getCompagny_id()+"'"+"' WHERE id='"+_computer.getId()+"'");
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
		Computer c=new Computer("Test",new Date(1999,1,1),new Date(2000,1,1),1);
		ComputerDao.getInstance().create(c);
	}
	
	
}
