package tw.com.afw.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "contract")
public class ContractEntity implements java.io.Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "contract_id" ,nullable = false)
	private int contract_id;
	
	@Column(name = "contract_start")
	private Date contract_start;
	
	@Column(name = "contract_end")
	private Date contract_end;
	
	@Column(name = "contract_deposit")
	private double contract_deposit;
	
	@Column(name = "contract_rent")
	private double contract_rent;
	
	@ManyToOne	
	@JoinColumn(name="branch_id")
	private BrancEntity branch_id;
	
	@Column(name = "contract_date")
	private Date contract_date;
	
	@Column(name = "contract_type")
	private String contract_type;
	
	@ManyToOne	
	@JoinColumn(name="user_id")
	private UserEntity user_id;
	
	@ManyToOne	
	@JoinColumn(name="user_id2")
	private UserEntity user_id2;
	
	@ManyToOne	
	@JoinColumn(name="company_id")
	private CompanyEntity company_id;
	
	
	@Column(name = "contract_remarks")
	private String contract_remarks;
	
	@Column(name = "contract_del")
	private String contract_del;

	public int getContract_id() {
		return contract_id;
	}

	public void setContract_id(int contract_id) {
		this.contract_id = contract_id;
	}

	public Date getContract_start() {
		return contract_start;
	}

	public void setContract_start(Date contract_start) {
		this.contract_start = contract_start;
	}

	public Date getContract_end() {
		return contract_end;
	}

	public void setContract_end(Date contract_end) {
		this.contract_end = contract_end;
	}

	public double getContract_deposit() {
		return contract_deposit;
	}

	public void setContract_deposit(double contract_deposit) {
		this.contract_deposit = contract_deposit;
	}

	public double getContract_rent() {
		return contract_rent;
	}

	public void setContract_rent(double contract_rent) {
		this.contract_rent = contract_rent;
	}

	public BrancEntity getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(BrancEntity branch_id) {
		this.branch_id = branch_id;
	}

	public Date getContract_date() {
		return contract_date;
	}

	public void setContract_date(Date contract_date) {
		this.contract_date = contract_date;
	}

	public String getContract_type() {
		return contract_type;
	}

	public void setContract_type(String contract_type) {
		this.contract_type = contract_type;
	}

	public UserEntity getUser_id() {
		return user_id;
	}

	public void setUser_id(UserEntity user_id) {
		this.user_id = user_id;
	}

	public UserEntity getUser_id2() {
		return user_id2;
	}

	public void setUser_id2(UserEntity user_id2) {
		this.user_id2 = user_id2;
	}

	public CompanyEntity getCompany_id() {
		return company_id;
	}

	public void setCompany_id(CompanyEntity company_id) {
		this.company_id = company_id;
	}

	public String getContract_remarks() {
		return contract_remarks;
	}

	public void setContract_remarks(String contract_remarks) {
		this.contract_remarks = contract_remarks;
	}

	public String getContract_del() {
		return contract_del;
	}

	public void setContract_del(String contract_del) {
		this.contract_del = contract_del;
	}
	
	
	

}
