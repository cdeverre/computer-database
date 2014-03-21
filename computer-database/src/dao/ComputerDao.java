package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import domainClasses.Company;
import domainClasses.Computer;

public class ComputerDao {

		

	
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
			Statement stmt = connection.createStatement();
		
			@SuppressWarnings("deprecation")
			String query ="INSERT INTO computer SET id=null," +
					" name='"+computer.getName()+"', introduced='"+
					computer.getDateIntroduced().getYear()+"-"+computer.getDateIntroduced().getMonth()+"-"+computer.getDateIntroduced().getDay()+
					"', discontinued='"+computer.getDateDiscontinued().getYear()+"-"+computer.getDateDiscontinued().getMonth()+"-"+computer.getDateDiscontinued().getDay()+
					"', companyid='"+computer.getCompany().getId()+"'";
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
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
			Statement stmt = connection.createStatement();
			
			String query=("UPDATE computer SET name='"+computer.getName()+"', introduced='"+computer.getDateIntroduced()+
					"', discontinued='"+computer.getDateDiscontinued()+"', companyid='"+computer.getCompany()+"'"+"' WHERE id='"+computer.getId()+"'");
			stmt.executeUpdate(query);
			
			DaoFactory.close(connection, rs, stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Computer computer) {
		try {
			Connection connection = DaoFactory.getConnection();
			ResultSet rs=null;
			Statement stmt = connection.createStatement();
					
			stmt.executeUpdate("DELETE FROM computer WHERE id='"+computer.getId()+"'");
			
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
			Statement stmt = connection.createStatement();
			
			String query = "SELECT * from company";
			rs=stmt.executeQuery(query);
			HashMap<Integer, String> companyTable = new HashMap<Integer,String>();
			while(rs.next()){
				companyTable.put(rs.getInt(1), rs.getString(2));
			}
			
			query = "SELECT * from computer";
			rs=stmt.executeQuery(query);
			
			while(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				Date introduced=rs.getDate(3);
				Date discontinued=rs.getDate(4);
				int companyId = rs.getInt(5);
				
				Computer c=new Computer(id,name,introduced,discontinued,new Company(companyId,companyTable.get(companyId)));
				result.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String args[]) {
		System.out.println(DaoFactory.getComputerDao().getAll().toString());

	}
	
	
}
