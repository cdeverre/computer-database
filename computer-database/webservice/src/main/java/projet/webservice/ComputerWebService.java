package projet.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import projet.model.Computer;

@WebService
@SOAPBinding(style=Style.RPC)
public interface ComputerWebService {

	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	

	@WebMethod
	public void create(Computer computer) ;
	
	@WebMethod
	public void update(Computer computer) ;

	@WebMethod
	public void delete(long id) ;
		
	@WebMethod
	public Computer find(long id) ;
	
	@WebMethod
	public int getNumberOfPage(int count) ;
	
	@WebMethod
	public int count(String pattern) ;

	@WebMethod
	public List<Computer> getAll(String pattern, int currentPage,int ElementPerPage,String orderByColumns,boolean orderByType) ;
	
	
}

