package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DaoService {

	private Statement statement;
	
	private final static DaoService _instance = new DaoService(DaoConnection.getInstance());
	
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
	
	/**
	 * @return the Instance
	 */
	public static DaoService getInstance() {
		return _instance;
	}
	
	
	public ResultSet query(String _query) {
		ResultSet res=null;
		try {
			res=this.statement.executeQuery(_query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}


	
	
}
