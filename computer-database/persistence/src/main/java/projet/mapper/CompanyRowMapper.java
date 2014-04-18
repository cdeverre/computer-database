package projet.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import projet.model.Company;

public class CompanyRowMapper implements RowMapper<Company> {

	@Override
	public Company mapRow(ResultSet rs, int line) throws SQLException {
		CompanyResultSetExtractor extractor = new CompanyResultSetExtractor();
	    return extractor.extractData(rs);
	}

}
