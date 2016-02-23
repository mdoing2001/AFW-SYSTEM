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
	private int office_id;

	@Column(name = "office_type")
	private String office_type;
	

	@Column(name = "office_number")
	private String office_number;
	
	@Column(name = "office_rent")
	private String office_rent;
	
	
	@ManyToOne	
	@JoinColumn(name="branch_id")
	private BrancEntity branch_id;
	
	
	@ManyToOne	
	@JoinColumn(name="contract_id")
	private ContractEntity contract_id;


	public int getOffice_id() {
		return office_id;
	}


	public void setOffice_id(int office_id) {
		this.office_id = office_id;
	}


	public String getOffice_type() {
		return office_type;
	}


	public void setOffice_type(String office_type) {
		this.office_type = office_type;
	}


	public String getOffice_number() {
		return office_number;
	}


	public void setOffice_number(String office_number) {
		this.office_number = office_number;
	}


	public String getOffice_rent() {
		return office_rent;
	}


	public void setOffice_rent(String office_rent) {
		this.office_rent = office_rent;
	}


	public BrancEntity getBranch_id() {
		return branch_id;
	}


	public void setBranch_id(BrancEntity branch_id) {
		this.branch_id = branch_id;
	}


	public ContractEntity getContract_id() {
		return contract_id;
	}


	public void setContract_id(ContractEntity contract_id) {
		this.contract_id = contract_id;
	}
	
	
}
