package services;

public class ServiceFactory {
	
	/* *******************************************************/
	/* ***               Attributs                       *** */
	/* *******************************************************/
	
	private final static ComputerServices computerServices =new ComputerServices();
	
	private final static CompanyServices companyServices = new CompanyServices();
	

	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	
	public static ComputerServices getComputerServices() {
		return computerServices;
	}
	
	public static CompanyServices getCompanyServices() {
		return companyServices;
	}
}
