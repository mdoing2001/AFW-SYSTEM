package tw.com.afw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "accountancy")
public class AccountancyEntity implements java.io.Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "accc_id" , nullable = false)
	private int acccId;
	
	@Column(name = "acc_name")
	private String accName;
	
	@Column(name = "acc_contact")
	private String accContact;
	
	@Column(name = "acc_phone")
	private String accPhone;
	
	@Column(name = "acc_address")
	private String accAddress;

	public int getAcccId() {
		return acccId;
	}

	public void setAcccId(int acccId) {
		this.acccId = acccId;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getAccContact() {
		return accContact;
	}

	public void setAccContact(String accContact) {
		this.accContact = accContact;
	}

	public String getAccPhone() {
		return accPhone;
	}

	public void setAccPhone(String accPhone) {
		this.accPhone = accPhone;
	}

	public String getAccAddress() {
		return accAddress;
	}

	public void setAccAddress(String accAddress) {
		this.accAddress = accAddress;
	}

	
	
	
	

}
