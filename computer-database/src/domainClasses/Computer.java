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
	public Computer(int id,String name, Date dateIntroduced,Date dateDiscontinued, Company compagny) {
		this.id=id;
		this.name=name;
		this.dateIntroduced=dateIntroduced;
		this.dateDiscontinued=dateDiscontinued;
		this.company=compagny; 
	}
	
	public Computer(String name, Date dateIntroduced,Date dateDiscontinued, Company compagny) {
		this.name=name;
		this.dateIntroduced=dateIntroduced;
		this.dateDiscontinued=dateDiscontinued;
		this.company=compagny; 
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
	 * @return the dateIntroduced
	 */
	public Date getDateIntroduced() {
		return dateIntroduced;
	}
	
	/**
	 * @param dateintroduced the dateIntroduced to set
	 */
	public void setDateIntroduced(Date dateintroduced) {
		this.dateIntroduced = dateintroduced;
	}
	
	/**
	 * @return the dateDiscontinued
	 */
	public Date getDateDiscontinued() {
		return dateDiscontinued;
	}
	
	/**
	 * @param datediscontinued the dateDiscontinued to set
	 */
	public void setDateDiscontinued(Date datediscontinued) {
		this.dateDiscontinued = datediscontinued;
	}
	
	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}
	
	/**
	 * @param compagnyid the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return " [ Id="+id+" name="+name+" Introduced="+dateIntroduced+" Discontinued="+dateDiscontinued+" CompanyId="+company.getId()+" ] ";
	}
	
	@Override
	public boolean equals(Object computer) {
		return (this.getId()==((Computer) computer).getId());
	}

	

}
