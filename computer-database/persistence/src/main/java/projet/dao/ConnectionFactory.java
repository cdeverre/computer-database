package projet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class ConnectionFactory{

	/* *******************************************************/
	/* ***               Attributs                       *** */
	/* *******************************************************/
	
	
	
    private static Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

	
	public static void close( ResultSet rs, java.sql.Statement stmt) {
		try{
			if(rs!=null) {
				logger.debug("Trying to close the result set");
				rs.close();
				logger.debug("Result set closed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} try {
			if(stmt!=null) {
				logger.debug("Trying to close the statement");
				stmt.close();
				logger.debug("Statement closed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	
}
