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
	private int contractId;
	
	@Column(name = "contract_start")
	private Date contractStart;
	
	@Column(name = "contract_end")
	private Date contractEnd;
	
	@Column(name = "contract_deposit")
	private double contractDeposit;
	
	@Column(name = "contract_rent")
	private double contractRent;
	
	@ManyToOne	
	@JoinColumn(name="branch_id")
	private BrancEntity branchId;
	
	@Column(name = "contract_date")
	private Date contractDate;
	
	@Column(name = "contract_type")
	private String contractType;
	
	@ManyToOne	
	@JoinColumn(name="user_id")
	private UserEntity userId;
	
	@ManyToOne	
	@JoinColumn(name="user_id2")
	private UserEntity userId2;
	
	@ManyToOne	
	@JoinColumn(name="company_id")
	private CompanyEntity companyId;
	
	
	@Column(name = "contract_remarks")
	private String contractRemarks;
	
	@Column(name = "contract_del")
	private String contractDel;

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public Date getContractStart() {
		return contractStart;
	}

	public void setContractStart(Date contractStart) {
		this.contractStart = contractStart;
	}

	public Date getContractEnd() {
		return contractEnd;
	}

	public void setContractEnd(Date contractEnd) {
		this.contractEnd = contractEnd;
	}

	public double getContractDeposit() {
		return contractDeposit;
	}

	public void setContractDeposit(double contractDeposit) {
		this.contractDeposit = contractDeposit;
	}

	public double getContractRent() {
		return contractRent;
	}

	public void setContractRent(double contractRent) {
		this.contractRent = contractRent;
	}

	public BrancEntity getBranchId() {
		return branchId;
	}

	public void setBranchId(BrancEntity branchId) {
		this.branchId = branchId;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public UserEntity getUserId() {
		return userId;
	}

	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}

	public UserEntity getUserId2() {
		return userId2;
	}

	public void setUserId2(UserEntity userId2) {
		this.userId2 = userId2;
	}

	public CompanyEntity getCompanyId() {
		return companyId;
	}

	public void setCompanyId(CompanyEntity companyId) {
		this.companyId = companyId;
	}

	public String getContractRemarks() {
		return contractRemarks;
	}

	public void setContractRemarks(String contractRemarks) {
		this.contractRemarks = contractRemarks;
	}

	public String getContractDel() {
		return contractDel;
	}

	public void setContractDel(String contractDel) {
		this.contractDel = contractDel;
	}

	
	
	

}
