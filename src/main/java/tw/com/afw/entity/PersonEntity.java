package tw.com.afw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "person")
public class PersonEntity implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "person_id" ,nullable = false)
	private int person_id;
	
	@ManyToOne	
	@JoinColumn(name="company_id")
	private CompanyEntity company_id;
	
	@Column(name = "company_name")
	private String company_name;
	
	@Column(name = "company_phone")
	private String company_phone;

	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	public CompanyEntity getCompany_id() {
		return company_id;
	}

	public void setCompany_id(CompanyEntity company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_phone() {
		return company_phone;
	}

	public void setCompany_phone(String company_phone) {
		this.company_phone = company_phone;
	}
	
	
	
	
	
	
}
