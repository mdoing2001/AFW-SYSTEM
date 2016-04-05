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
@Table(name = "user", schema="lease")
public class UserEntity implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id" , nullable = false)
	private int userId;
	
	@Column(name = "user_count")
	private String userCount;
	
	@Column(name = "user_password")
	private String userPassword;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_cid")
	private String userCid;
	
	@Column(name = "user_date")
	private Date userDate;
	
	@Column(name = "user_address")
	private String userAddress;
	
	@Column(name = "user_mail")
	private String userMail;
	
	@Column(name = "user_phone")
	private String userPhone;
	
	@Column(name = "user_qr")
	private Integer userQr;
	
	@Column(name = "user_in")
	private Integer userIn;
	
	@Column(name = "user_up")
	private Integer userUp;
	
	@Column(name = "user_de", nullable = true)
	private Integer userDe;
	
	@Column(name = "user_max")
	private Integer userMax;
	
	@Column(name = "user_min")
	private Integer userMin;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="branch_id")
	private BranchEntity branchId;

	

	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getUserCount() {
		return userCount;
	}



	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}



	public String getUserPassword() {
		return userPassword;
	}



	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUserCid() {
		return userCid;
	}



	public void setUserCid(String userCid) {
		this.userCid = userCid;
	}



	public Date getUserDate() {
		return userDate;
	}



	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}



	public String getUserAddress() {
		return userAddress;
	}



	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}



	public String getUserMail() {
		return userMail;
	}



	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}



	public String getUserPhone() {
		return userPhone;
	}



	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}



	public Integer getUserQr() {
		return userQr;
	}



	public void setUserQr(Integer userQr) {
		this.userQr = userQr;
	}



	public Integer getUserIn() {
		return userIn;
	}



	public void setUserIn(Integer userIn) {
		this.userIn = userIn;
	}



	public Integer getUserUp() {
		return userUp;
	}



	public void setUserUp(Integer userUp) {
		this.userUp = userUp;
	}



	public Integer getUserDe() {
		return userDe;
	}



	public void setUserDe(Integer userDe) {
		this.userDe = userDe;
	}



	public Integer getUserMax() {
		return userMax;
	}



	public void setUserMax(Integer userMax) {
		this.userMax = userMax;
	}



	public Integer getUserMin() {
		return userMin;
	}



	public void setUserMin(Integer userMin) {
		this.userMin = userMin;
	}



	public BranchEntity getBranchId() {
		return branchId;
	}



	public void setBranchId(BranchEntity branchId) {
		this.branchId = branchId;
	}



	@Override
	public String toString() {
		return "UserEntity [id="+ userId +"user_Count="+userCount+" user_Password="+userPassword+
				" user_Name="+userName+" user_Cid="+userCid+ "user_Date="+userDate+" user_Address="+userAddress+
				"user_Mail="+userMail+" user_Phone="+userPhone+" user_Qr="+userQr+" user_In="+userIn+" user_Up="+userUp+
				" user_De"+userDe+" user_Max"+userMax+"user_Min"+userMin;
	}

}
