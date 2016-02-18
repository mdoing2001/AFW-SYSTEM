package tw.com.afw.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@Column(name = "company_id")
	private int company_Id;
	
	@Column(name = "company_type")
	private String company_Type;
	
	@Column(name = "company_status")
	private String company_Status;
	
	@Column(name = "company_name")
	private String company_Name;
	
	@Column(name = "company_Executive")
	private String company_Executive;
	
	@Column(name = "company_bitrhday")
	private Date company_Bitrhday;
	
	@Column(name = "company_ein")
	private String company_Ein;
	
	@Column(name = "company_address")
	private String company_Address;
	
	@Column(name = "company_address2")
	private String company_Address2;
	
	@Column(name = "company_number")
	private String company_Number;
	
	@Column(name = "company_fax")
	private String company_Fax;
	
	@Column(name = "company_mail")
	private String company_Mail;
	
	@Column(name = "acc_id")
	private int acc_Id;
	
	@Column(name = "company_remark")
	private String company_Remark;
	
	public int getCompany_Id() {
		return company_Id;
	}
	public void setCompany_Id(int company_Id) {
		this.company_Id = company_Id;
	}
	public String getCompany_Type() {
		return company_Type;
	}
	public void setCompany_Type(String company_Type) {
		this.company_Type = company_Type;
	}
	public String getCompany_Status() {
		return company_Status;
	}
	public void setCompany_Status(String company_Status) {
		this.company_Status = company_Status;
	}
	public String getCompany_Name() {
		return company_Name;
	}
	public void setCompany_Name(String company_Name) {
		this.company_Name = company_Name;
	}
	public String getCompany_Executive() {
		return company_Executive;
	}
	public void setCompany_Executive(String company_Executive) {
		this.company_Executive = company_Executive;
	}
	public Date getCompany_Bitrhday() {
		return company_Bitrhday;
	}
	public void setCompany_Bitrhday(Date company_Bitrhday) {
		this.company_Bitrhday = company_Bitrhday;
	}
	public String getCompany_Ein() {
		return company_Ein;
	}
	public void setCompany_Ein(String company_Ein) {
		this.company_Ein = company_Ein;
	}
	public String getCompany_Address() {
		return company_Address;
	}
	public void setCompany_Address(String company_Address) {
		this.company_Address = company_Address;
	}
	public String getCompany_Address2() {
		return company_Address2;
	}
	public void setCompany_Address2(String company_Address2) {
		this.company_Address2 = company_Address2;
	}
	public String getCompany_Number() {
		return company_Number;
	}
	public void setCompany_Number(String company_Number) {
		this.company_Number = company_Number;
	}
	public String getCompany_Fax() {
		return company_Fax;
	}
	public void setCompany_Fax(String company_Fax) {
		this.company_Fax = company_Fax;
	}
	public String getCompany_Mail() {
		return company_Mail;
	}
	public void setCompany_Mail(String company_Mail) {
		this.company_Mail = company_Mail;
	}
	public int getAcc_Id() {
		return acc_Id;
	}
	public void setAcc_Id(int acc_Id) {
		this.acc_Id = acc_Id;
	}
	public String getCompany_Remark() {
		return company_Remark;
	}
	public void setCompany_Remark(String company_Remark) {
		this.company_Remark = company_Remark;
	}
	
	
	

}
