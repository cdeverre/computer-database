package projet.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import projet.model.Company;
import projet.model.Computer;

public class ComputerResultSetExtractor implements ResultSetExtractor<Computer> {

	@Override
	public Computer extractData(ResultSet rs) throws SQLException,DataAccessException {
		long id=rs.getLong(1);
		String name=rs.getString(2);
		DateTime introduced=null;
		if(rs.getDate(3)!=null) {
			introduced=new DateTime(rs.getDate(3));
		}
		DateTime discontinued=null;
		if(rs.getDate(4)!=null) {
			discontinued=new DateTime(rs.getDate(4));
		}
		long companyId = rs.getLong(5);
		String companyName = rs.getString(6);
		
		Computer c=new Computer(id,name,introduced,discontinued,new Company(companyId,companyName));
		return c;
	}

}
