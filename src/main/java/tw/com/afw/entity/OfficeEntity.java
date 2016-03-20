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
@Table(name = "office")
public class OfficeEntity implements java.io.Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "office_id" ,nullable = false)
	private int officeId;

	@Column(name = "office_type")
	private String officeType;
	

	@Column(name = "office_number")
	private String officeNumber;
	
	@Column(name = "office_rent")
	private String officeRent;
	
	
	@ManyToOne	
	@JoinColumn(name="branch_id")
	private BrancEntity branchId;
	
	
	@ManyToOne	
	@JoinColumn(name="contract_id")
	private ContractEntity contractId;


	public int getOfficeId() {
		return officeId;
	}


	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}


	public String getOfficeType() {
		return officeType;
	}


	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}


	public String getOfficeNumber() {
		return officeNumber;
	}


	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}


	public String getOfficeRent() {
		return officeRent;
	}


	public void setOfficeRent(String officeRent) {
		this.officeRent = officeRent;
	}


	public BrancEntity getBranchId() {
		return branchId;
	}


	public void setBranchId(BrancEntity branchId) {
		this.branchId = branchId;
	}


	public ContractEntity getContractId() {
		return contractId;
	}


	public void setContractId(ContractEntity contractId) {
		this.contractId = contractId;
	}


	
	
	
}
