package dao;

import java.sql.SQLException;
import java.sql.Statement;


public class DaoService {

	private Statement statement;
	
	
	public DaoService(DaoConnection _connectionSingleton) {
		try {
			this.setStatement(_connectionSingleton.getConnection().createStatement());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * @return the connectionSingleton
	 */
	public Statement getStatement() {
		return statement;
	}

	/**
	 * @param connectionSingleton the connectionSingleton to set
	 */
	public void setStatement(Statement _statement) {
		this.statement = _statement;
	}
	
	
	public void create(String query) {
		
	}
	
	public void select() {
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}
	
}
