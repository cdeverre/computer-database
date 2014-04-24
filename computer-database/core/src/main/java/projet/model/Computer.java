package projet.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;


@Entity
@Table( name = "computer" )
public class Computer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name = "introduced")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime introduced;
	
	@Column(name = "discontinued")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime discontinued;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="company_id")
	private Company company;
	
	
	public Computer() {
		super();
	}
	public Computer(long id,String name, DateTime DateIntroduced,DateTime DateDiscontinued, Company compagny) {
		this.id=id;
		this.name=name;
		this.introduced=DateIntroduced;
		this.discontinued=DateDiscontinued;
		this.company=compagny; 
	}
	
	public Computer(String name, DateTime DateIntroduced,DateTime DateDiscontinued, Company compagny) {
		this.name=name;
		this.introduced=DateIntroduced;
		this.discontinued=DateDiscontinued;
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
	public DateTime getIntroduced() {
		return introduced;
	}
	
	/**
	 * @param Dateintroduced the DateIntroduced to set
	 */
	public void setIntroduced(DateTime Dateintroduced) {
		this.introduced = Dateintroduced;
	}
	
	/**
	 * @return the DateDiscontinued
	 */
	public DateTime getDiscontinued() {
		return discontinued;
	}
	
	/**
	 * @param Datediscontinued the DateDiscontinued to set
	 */
	public void setDiscontinued(DateTime Datediscontinued) {
		this.discontinued = Datediscontinued;
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
		StringBuilder res =new StringBuilder();
		res.append(" [ Id="+id+" name="+name+" ; Introduced="+introduced+" ;  Discontinued="+discontinued+" ;");
		if (company!=null) {
			res.append("CompanyId="+company.getId()+" CompanyName="+company.getName() );
		}
		res.append("]");
		return res.toString();
	}
	
	@Override
	public boolean equals(Object computer) {
		return (this.getId()==((Computer) computer).getId());
	}

	

}
