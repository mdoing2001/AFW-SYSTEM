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
@Table(name = "remit")
public class RemitEntity implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "remit_id" ,nullable = false)
	private int remitId;
	
	@Column(name = "remit_account")
	private String remitAccount;
	
	@Column(name = "remit_deposit")
	private String remitDeposit;
	
	@Column(name = "remit_mode")
	private String remitMode;
	
	@ManyToOne	
	@JoinColumn(name="company_id")
	private CompanyEntity companyId;


	public int getRemitId() {
		return remitId;
	}


	public void setRemitId(int remitId) {
		this.remitId = remitId;
	}


	public String getRemitAccount() {
		return remitAccount;
	}


	public void setRemitAccount(String remitAccount) {
		this.remitAccount = remitAccount;
	}


	public String getRemitDeposit() {
		return remitDeposit;
	}


	public void setRemitDeposit(String remitDeposit) {
		this.remitDeposit = remitDeposit;
	}


	public String getRemitMode() {
		return remitMode;
	}


	public void setRemitMode(String remitMode) {
		this.remitMode = remitMode;
	}


	public CompanyEntity getCompanyId() {
		return companyId;
	}


	public void setCompanyId(CompanyEntity companyId) {
		this.companyId = companyId;
	}
	
	
	
	
	
	

}
