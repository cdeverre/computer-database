package projet.dto;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;

public class ComputerDto {
	
	@NotEmpty
	private String name;
	
	
	private String dateIntroduced;
	
	
	private String dateDiscontinued;
	
	
	@Digits(fraction = 255, integer = 255)
	private String company;
	
	@Digits(fraction = 255, integer = 255)
	private String id;
	
	
	/**
	 * @return the dateIntroduced
	 */
	public String getDateIntroduced() {
		return dateIntroduced;
	}


	/**
	 * @param dateIntroduced the dateIntroduced to set
	 */
	public void setDateIntroduced(String dateIntroduced) {
		this.dateIntroduced = dateIntroduced;
	}


	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}


	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	public ComputerDto() {
		super();
	}
	
	
	public ComputerDto(String name, String DateIntroduced,String DateDiscontinued, String compagny) {
		this.id="null";
		this.setName(name);
		this.setDateDiscontinued(DateDiscontinued);
		this.dateIntroduced=DateIntroduced;
		this.company=compagny;
	}
	
	public ComputerDto(String id,String name, String DateIntroduced,String DateDiscontinued, String compagny) {
		this.setName(name);
		this.setDateDiscontinued(DateDiscontinued);
		this.dateIntroduced=DateIntroduced;
		this.company=compagny;
		this.id=id;
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
	 * @return the dateDiscontinued
	 */
	public String getDateDiscontinued() {
		return dateDiscontinued;
	}


	/**
	 * @param dateDiscontinued the dateDiscontinued to set
	 */
	public void setDateDiscontinued(String dateDiscontinued) {
		this.dateDiscontinued = dateDiscontinued;
	}







	
	

}
