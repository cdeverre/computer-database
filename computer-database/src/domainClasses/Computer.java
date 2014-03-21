package domainClasses;

import java.util.Date;

public class Computer {
	
	private int id;
	private String name;
	private Date date_introduced;
	private Date date_discontinued;
	private int compagny_id;
	
	
	public Computer(String _name, Date _date_introduced,Date _date_discontinued, int _compagny_id) {
		this.name=_name;
		this.date_introduced=_date_introduced;
		this.date_discontinued=_date_discontinued;
		this.compagny_id=_compagny_id; 
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
	public Date getDate_introduced() {
		return date_introduced;
	}
	
	/**
	 * @param date_introduced the date_introduced to set
	 */
	public void setDate_introduced(Date date_introduced) {
		this.date_introduced = date_introduced;
	}
	
	/**
	 * @return the date_discontinued
	 */
	public Date getDate_discontinued() {
		return date_discontinued;
	}
	
	/**
	 * @param date_discontinued the date_discontinued to set
	 */
	public void setDate_discontinued(Date date_discontinued) {
		this.date_discontinued = date_discontinued;
	}
	
	/**
	 * @return the compagny_id
	 */
	public int getCompagny_id() {
		return compagny_id;
	}
	
	/**
	 * @param compagny_id the compagny_id to set
	 */
	public void setCompagny_id(int compagny_id) {
		this.compagny_id = compagny_id;
	}
	
	

}
