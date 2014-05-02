package projet.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import projet.model.Computer;

@Produces("application/xml")
@Service("ComputerWebService")
@Path("/computers")
public interface ComputerWebService {

	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
		
	@GET
	@Path("/find/{id}")
	public Computer find(@PathParam("id") long id) ;
	
}

