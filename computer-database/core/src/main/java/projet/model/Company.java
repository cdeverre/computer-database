package projet.model;


public class Company {

	private long id;
	private String name;
	
	public Company() {
		super();
	}
	
	
	public Company(String name) {
		this.name=name;
	}
	
	public Company(long id,String name){
		this.id=id;
		this.name=name;
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
	
	@Override
	public String toString() {
		return "Id="+id+" name="+name;
	}
	
	@Override
	public boolean equals(Object company) {
		return (this.id==((Company) company).getId());
	}

	
	
}
