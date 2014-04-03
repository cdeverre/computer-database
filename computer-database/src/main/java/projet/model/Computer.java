package projet.model;

import java.util.Calendar;

public class Computer {
	
	private long id;
	private String name;
	private Calendar DateIntroduced;
	private Calendar DateDiscontinued;
	private Company company;
	
	
	public Computer() {
		super();
	}
	public Computer(long id,String name, Calendar DateIntroduced,Calendar DateDiscontinued, Company compagny) {
		this.id=id;
		this.name=name;
		this.DateIntroduced=DateIntroduced;
		this.DateDiscontinued=DateDiscontinued;
		this.company=compagny; 
	}
	
	public Computer(String name, Calendar DateIntroduced,Calendar DateDiscontinued, Company compagny) {
		this.id=-1;
		this.name=name;
		this.DateIntroduced=DateIntroduced;
		this.DateDiscontinued=DateDiscontinued;
		this.company=compagny; 
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
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
	 * @return the DateIntroduced
	 */
	public Calendar getDateIntroduced() {
		return DateIntroduced;
	}
	
	/**
	 * @param Dateintroduced the DateIntroduced to set
	 */
	public void setDateIntroduced(Calendar Dateintroduced) {
		this.DateIntroduced = Dateintroduced;
	}
	
	/**
	 * @return the DateDiscontinued
	 */
	public Calendar getDateDiscontinued() {
		return DateDiscontinued;
	}
	
	/**
	 * @param Datediscontinued the DateDiscontinued to set
	 */
	public void setDateDiscontinued(Calendar Datediscontinued) {
		this.DateDiscontinued = Datediscontinued;
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
		return " [ Id="+id+" name="+name+" Introduced="+DateIntroduced+" Discontinued="+DateDiscontinued+" CompanyId="+company.getId()+" ] ";
	}
	
	@Override
	public boolean equals(Object computer) {
		return (this.getId()==((Computer) computer).getId());
	}

	

}
