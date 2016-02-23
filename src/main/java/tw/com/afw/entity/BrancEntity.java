package tw.com.afw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "branc")
public class BrancEntity implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "branch_id",nullable = false)
	private int branch_id;
	
	@Column(name = "branch_name")
	private String branch_name;
	
	@Column(name = "branch_address")
	private String branch_address;
	
	@Column(name = "branch_phone")
	private String branch_phone;
	
	@Column(name = "branch_code")
	private String branch_code;

	public int getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getBranch_address() {
		return branch_address;
	}

	public void setBranch_address(String branch_address) {
		this.branch_address = branch_address;
	}

	public String getBranch_phone() {
		return branch_phone;
	}

	public void setBranch_phone(String branch_phone) {
		this.branch_phone = branch_phone;
	}

	public String getBranch_code() {
		return branch_code;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}
	
	
	
	
}
