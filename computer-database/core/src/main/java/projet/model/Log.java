package projet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name="log")
public class Log {

	
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name = "date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime date;
	
	@Column(name="text")
	private String text;
	
	public Log(Long id, String message) {
		StringBuilder textTmp= new StringBuilder();
		textTmp.append(message);
		textTmp.append(" a new computer with the id:");
		textTmp.append(id);
		this.text=textTmp.toString();
		this.date=new DateTime();
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
	 * @return the date
	 */
	public DateTime getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(DateTime date) {
		this.date = date;
	}

	/**
	 * @return the test
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param test the test to set
	 */
	public void setText(String test) {
		this.text = test;
	}
}
