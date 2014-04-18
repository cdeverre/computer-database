package projet.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import projet.model.Company;

public class CompanyResultSetExtractor implements ResultSetExtractor<Company> {

	@Override
	public Company extractData(ResultSet rs) throws SQLException, DataAccessException {
		long id=rs.getLong(1);
		String name=rs.getString(2);
		return new Company(id,name);
	}

}
