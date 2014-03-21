package domainClasses;

public class Company {

	private int id;
	private String name;
	
	public Company() {
		super();
	}
	
	public Company(String _name) {
		this.name=_name;
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
	
	@Override
	public String toString() {
		return "Id="+id+" name="+name;
	}
	
	
	public boolean equals(Company _company) {
		return (this.id==_company.getId());
	}
	
	
	
}
