package projet.dto;


public class ComputerDto {
	
	private String name;
	private String dateIntroduced;
	private String dateDiscontinued;
	private String company;
	private String id;
	
	
	public ComputerDto() {
		super();
	}
	
	
	public ComputerDto(String name, String DateIntroduced,String DateDiscontinued, String compagny) {
		this.id="null";
		this.name=name;
		this.dateDiscontinued=DateDiscontinued;
		this.dateIntroduced=DateIntroduced;
		this.company=compagny;
	}
	
	public ComputerDto(String id,String name, String DateIntroduced,String DateDiscontinued, String compagny) {
		this.name=name;
		this.dateDiscontinued=DateDiscontinued;
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
	 * @return the dateIntroduced
	 */
	public String getDateIntroduced() {
		return dateIntroduced;
	}


	/**
	 * @return the dateDiscontinued
	 */
	public String getDateDiscontinued() {
		return dateDiscontinued;
	}


	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}





	
	

}
