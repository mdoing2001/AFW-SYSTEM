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
	private Double rentMoney;
	
	@Column(name = "rent_management")
	private Double rentManagement;
	
	@Column(name = "rent_power")
	private Double rentPower;
	
	@Column(name = "rent_adsl")
	private Double rentAdsl;
	
	@Column(name = "rent_business")
	private Double rentBusiness;
	
	@Column(name = "rent_other")
	private Double rentOther;
	
	@Column(name = "rent_list")
	private Double rentList;
	
	@Column(name = "rent_remark")
	private String rentRemark;
	
	@Column(name = "rent_shortage")
	private Double rentShortage;
	
	@Column(name = "rent_date")
	private Date rentDate;
	
	@Column(name = "rent_add_date")
	private Date rentAddDate;
	
	@Column(name = "rent_pay_type")
	private String rentPayType;
	
	@ManyToOne	
	@JoinColumn(name="contract_id")
	private ContractEntity contractId;

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

	public Double getRentMoney() {
		return rentMoney;
	}

	public void setRentMoney(Double rentMoney) {
		this.rentMoney = rentMoney;
	}

	public Double getRentManagement() {
		return rentManagement;
	}

	public void setRentManagement(Double rentManagement) {
		this.rentManagement = rentManagement;
	}

	public Double getRentPower() {
		return rentPower;
	}

	public void setRentPower(Double rentPower) {
		this.rentPower = rentPower;
	}

	public Double getRentAdsl() {
		return rentAdsl;
	}

	public void setRentAdsl(Double rentAdsl) {
		this.rentAdsl = rentAdsl;
	}

	public Double getRentBusiness() {
		return rentBusiness;
	}

	public void setRentBusiness(Double rentBusiness) {
		this.rentBusiness = rentBusiness;
	}

	public Double getRentOther() {
		return rentOther;
	}

	public void setRentOther(Double rentOther) {
		this.rentOther = rentOther;
	}

	public Double getRentList() {
		return rentList;
	}

	public void setRentList(Double rentList) {
		this.rentList = rentList;
	}

	public String getRentRemark() {
		return rentRemark;
	}

	public void setRentRemark(String rentRemark) {
		this.rentRemark = rentRemark;
	}

	public Double getRentShortage() {
		return rentShortage;
	}

	public void setRentShortage(Double rentShortage) {
		this.rentShortage = rentShortage;
	}

	public ContractEntity getContractId() {
		return contractId;
	}

	public void setContractId(ContractEntity contractId) {
		this.contractId = contractId;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}
	
	public String getRentPayType() {
		return rentPayType;
	}

	public void setRentPayType(String rentPayType) {
		this.rentPayType = rentPayType;
	}
	
	public Date getRentAddDate() {
		return rentAddDate;
	}

	public void setRentAddDate(Date rentAddDate) {
		this.rentAddDate = rentAddDate;
	}

	@Override
	public String toString() {
		return "RentEntity [rentId=" + rentId + ", rentYear=" + rentYear
				+ ", rentMonth=" + rentMonth + ", rentReceipt=" + rentReceipt
				+ ", rentMode=" + rentMode + ", rentMoney=" + rentMoney
				+ ", rentManagement=" + rentManagement + ", rentPower="
				+ rentPower + ", rentAdsl=" + rentAdsl + ", rentBusiness="
				+ rentBusiness + ", rentOther=" + rentOther + ", rentList="
				+ rentList + ", rentRemark=" + rentRemark + ", rentShortage="
				+ rentShortage + ", rentDate=" + rentDate + ", rentAddDate="
				+ rentAddDate + ", rentPayType=" + rentPayType
				+ ", contractId=" + contractId + "]";
	}
	
}
