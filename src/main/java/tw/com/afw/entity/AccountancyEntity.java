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
	private int accc_id;
	
	@Column(name = "acc_name")
	private String acc_name;
	
	@Column(name = "acc_contact")
	private String acc_contact;
	
	@Column(name = "acc_phone")
	private String acc_phone;
	
	@Column(name = "acc_address")
	private String acc_address;

	public int getAccc_id() {
		return accc_id;
	}

	public void setAccc_id(int accc_id) {
		this.accc_id = accc_id;
	}

	public String getAcc_name() {
		return acc_name;
	}

	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}

	public String getAcc_contact() {
		return acc_contact;
	}

	public void setAcc_contact(String acc_contact) {
		this.acc_contact = acc_contact;
	}

	public String getAcc_phone() {
		return acc_phone;
	}

	public void setAcc_phone(String acc_phone) {
		this.acc_phone = acc_phone;
	}

	public String getAcc_address() {
		return acc_address;
	}

	public void setAcc_address(String acc_address) {
		this.acc_address = acc_address;
	}
	
	
	

}
