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
	private int personId;
	


	@ManyToOne	
	@JoinColumn(name="company_id")
	private CompanyEntity companyId;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "company_phone")
	private String companyPhone;


	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public CompanyEntity getCompanyId() {
		return companyId;
	}

	public void setCompanyId(CompanyEntity companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	
	
	
	
}
