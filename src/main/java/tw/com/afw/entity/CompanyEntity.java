package tw.com.afw.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class CompanyEntity implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "company_id",nullable = false)
	private int companyId;
	
	@Column(name = "company_type")
	private String companyType;
	
	@Column(name = "company_status")
	private String companyStatus;
	
	@Column(name = "company_status_change_date")
	private Date companyStatusChangeDate;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "company_Executive")
	private String companyExecutive;
	
	@Column(name = "company_bitrhday")
	private Date companyBitrhday;
	
	@Column(name = "company_ein")
	private String companyEin;
	
	@Column(name = "company_address")
	private String companyAddress;
	
	@Column(name = "company_address2")
	private String companyAddress2;
	
	@Column(name = "company_number")
	private String companyNumber;
	
	@Column(name = "company_number2")
	private String companyNumber2;
	
	@Column(name = "company_line")
	private String companyLine;
	
	@Column(name = "company_fax")
	private String companyFax;
	
	@Column(name = "company_mail")
	private String companyMail;
	
	@ManyToOne(cascade=CascadeType.ALL)	
	@JoinColumn(name="acc_id")
	private AccountancyEntity accId;
	
	@Column(name = "company_remark")
	private String companyRemark;
	
	@Column(name = "company_pay_status")
	private String companyPayStatus;
	
	@Column(name = "company_letter_status")
	private String companyLetterStatus;
	
	@Column(name = "company_code")
	private String companyCode;

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyStatus() {
		return companyStatus;
	}

	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyExecutive() {
		return companyExecutive;
	}

	public void setCompanyExecutive(String companyExecutive) {
		this.companyExecutive = companyExecutive;
	}

	public Date getCompanyBitrhday() {
		return companyBitrhday;
	}

	public void setCompanyBitrhday(Date companyBitrhday) {
		this.companyBitrhday = companyBitrhday;
	}

	public String getCompanyEin() {
		return companyEin;
	}

	public void setCompanyEin(String companyEin) {
		this.companyEin = companyEin;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyAddress2() {
		return companyAddress2;
	}

	public void setCompanyAddress2(String companyAddress2) {
		this.companyAddress2 = companyAddress2;
	}

	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}

	public String getCompanyFax() {
		return companyFax;
	}

	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}

	public String getCompanyMail() {
		return companyMail;
	}

	public void setCompanyMail(String companyMail) {
		this.companyMail = companyMail;
	}

	public AccountancyEntity getAccId() {
		return accId;
	}

	public void setAccId(AccountancyEntity accId) {
		this.accId = accId;
	}

	public String getCompanyRemark() {
		return companyRemark;
	}

	public void setCompanyRemark(String companyRemark) {
		this.companyRemark = companyRemark;
	}

	public String getCompanyPayStatus() {
		return companyPayStatus;
	}

	public void setCompanyPayStatus(String companyPayStatus) {
		this.companyPayStatus = companyPayStatus;
	}

	public String getCompanyLetterStatus() {
		return companyLetterStatus;
	}

	public void setCompanyLetterStatus(String companyLetterStatus) {
		this.companyLetterStatus = companyLetterStatus;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	public Date getCompanyStatusChangeDate() {
		return companyStatusChangeDate;
	}

	public void setCompanyStatusChangeDate(Date companyStatusChangeDate) {
		this.companyStatusChangeDate = companyStatusChangeDate;
	}

	public String getCompanyNumber2() {
		return companyNumber2;
	}

	public void setCompanyNumber2(String companyNumber2) {
		this.companyNumber2 = companyNumber2;
	}

	public String getCompanyLine() {
		return companyLine;
	}

	public void setCompanyLine(String companyLine) {
		this.companyLine = companyLine;
	}

	@Override
	public String toString() {
		return "CompanyEntity [companyId=" + companyId + ", companyType="
				+ companyType + ", companyStatus=" + companyStatus
				+ ", companyStatusChangeDate=" + companyStatusChangeDate
				+ ", companyName=" + companyName + ", companyExecutive="
				+ companyExecutive + ", companyBitrhday=" + companyBitrhday
				+ ", companyEin=" + companyEin + ", companyAddress="
				+ companyAddress + ", companyAddress2=" + companyAddress2
				+ ", companyNumber=" + companyNumber + ", companyNumber2="
				+ companyNumber2 + ", companyLine=" + companyLine
				+ ", companyFax=" + companyFax + ", companyMail=" + companyMail
				+ ", accId=" + accId + ", companyRemark=" + companyRemark
				+ ", companyPayStatus=" + companyPayStatus
				+ ", companyLetterStatus=" + companyLetterStatus
				+ ", companyCode=" + companyCode + "]";
	}
}
