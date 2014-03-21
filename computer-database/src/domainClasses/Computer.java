package domainClasses;

import java.util.Date;

public class Computer {
	
	private int id;
	private String name;
	private Date dateIntroduced;
	private Date dateDiscontinued;
	private Company company;
	
	
	public Computer() {
		super();
	}
	
	public Computer(String _name, Date _dateIntroduced,Date _dateDiscontinued, Company _compagny) {
		this.name=_name;
		this.dateIntroduced=_dateIntroduced;
		this.dateDiscontinued=_dateDiscontinued;
		this.company=_compagny; 
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the date_introduced
	 */
	public Date getDateIntroduced() {
		return dateIntroduced;
	}
	
	/**
	 * @param date_introduced the date_introduced to set
	 */
	public void setDateIntroduced(Date date_introduced) {
		this.dateIntroduced = date_introduced;
	}
	
	/**
	 * @return the date_discontinued
	 */
	public Date getDateDiscontinued() {
		return dateDiscontinued;
	}
	
	/**
	 * @param date_discontinued the date_discontinued to set
	 */
	public void setDateDiscontinued(Date date_discontinued) {
		this.dateDiscontinued = date_discontinued;
	}
	
	/**
	 * @return the compagny_id
	 */
	public Company getCompany() {
		return company;
	}
	
	/**
	 * @param compagny_id the compagny_id to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return "Id="+id+" name="+name+" Introduced="+dateIntroduced+" Discontinued="+dateDiscontinued+" CompanyId="+company.getId();
	}
	
	
	public boolean equals(Computer _computer) {
		return (this.getId()==_computer.getId());
	}
	
	

}
