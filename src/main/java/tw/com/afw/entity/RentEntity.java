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
@Table(name = "rent")
public class RentEntity  implements java.io.Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "rent_id" ,nullable = false)
	private int rentId;
	
	
	@Column(name = "rent_year")
	private Integer rentYear;
		
	@Column(name = "rent_month")
	private Integer rentMonth;
	
	@Column(name = "rent_receipt")
	private String rentReceipt;
	
	@Column(name = "rent_mode")
	private String rentMode;
	
	@Column(name = "rent_money")
	private double rentMoney;
	
	@Column(name = "rent_management")
	private double rentManagement;
	
	@Column(name = "rent_power")
	private double rentPower;
	
	@Column(name = "rent_adsl")
	private double rentAdsl;
	
	@Column(name = "rent_business")
	private double rentBusiness;
	
	@Column(name = "rent_other")
	private double rentOther;
	
	@Column(name = "rent_list")
	private double rentList;
	
	@Column(name = "rent_remark")
	private String rentRemark;
	
	@Column(name = "rent_shortage")
	private double rentShortage;
	
	@ManyToOne	
	@JoinColumn(name="company_id")
	private CompanyEntity companyId;

	public int getRentId() {
		return rentId;
	}

	public void setRentId(int rentId) {
		this.rentId = rentId;
	}

	public Integer getRentYear() {
		return rentYear;
	}

	public void setRentYear(Integer rentYear) {
		this.rentYear = rentYear;
	}

	public Integer getRentMonth() {
		return rentMonth;
	}

	public void setRentMonth(Integer rentMonth) {
		this.rentMonth = rentMonth;
	}

	public String getRentReceipt() {
		return rentReceipt;
	}

	public void setRentReceipt(String rentReceipt) {
		this.rentReceipt = rentReceipt;
	}

	public String getRentMode() {
		return rentMode;
	}

	public void setRentMode(String rentMode) {
		this.rentMode = rentMode;
	}

	public double getRentMoney() {
		return rentMoney;
	}

	public void setRentMoney(double rentMoney) {
		this.rentMoney = rentMoney;
	}

	public double getRentManagement() {
		return rentManagement;
	}

	public void setRentManagement(double rentManagement) {
		this.rentManagement = rentManagement;
	}

	public double getRentPower() {
		return rentPower;
	}

	public void setRentPower(double rentPower) {
		this.rentPower = rentPower;
	}

	public double getRentAdsl() {
		return rentAdsl;
	}

	public void setRentAdsl(double rentAdsl) {
		this.rentAdsl = rentAdsl;
	}

	public double getRentBusiness() {
		return rentBusiness;
	}

	public void setRentBusiness(double rentBusiness) {
		this.rentBusiness = rentBusiness;
	}

	public double getRentOther() {
		return rentOther;
	}

	public void setRentOther(double rentOther) {
		this.rentOther = rentOther;
	}

	public double getRentList() {
		return rentList;
	}

	public void setRentList(double rentList) {
		this.rentList = rentList;
	}

	public String getRentRemark() {
		return rentRemark;
	}

	public void setRentRemark(String rentRemark) {
		this.rentRemark = rentRemark;
	}

	public double getRentShortage() {
		return rentShortage;
	}

	public void setRentShortage(double rentShortage) {
		this.rentShortage = rentShortage;
	}

	public CompanyEntity getCompanyId() {
		return companyId;
	}

	public void setCompanyId(CompanyEntity companyId) {
		this.companyId = companyId;
	}
	
	
	

}
