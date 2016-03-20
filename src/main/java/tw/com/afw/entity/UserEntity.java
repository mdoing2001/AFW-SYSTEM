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
@Table(name = "user", schema="lease")
public class UserEntity implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id" , nullable = false)
	private int user_Id;
	
	@Column(name = "user_count")
	private String user_Count;
	
	@Column(name = "user_password")
	private String user_Password;
	
	@Column(name = "user_name")
	private String user_Name;
	
	@Column(name = "user_cid")
	private String user_Cid;
	
	@Column(name = "user_date")
	private Date user_Date;
	
	@Column(name = "user_address")
	private String user_Address;
	
	@Column(name = "user_mail")
	private String user_Mail;
	
	@Column(name = "user_phone")
	private String user_Phone;
	
	@Column(name = "user_qr")
	private Integer user_Qr;
	
	@Column(name = "user_in")
	private Integer user_In;
	
	@Column(name = "user_up")
	private Integer user_Up;
	
	@Column(name = "user_de", nullable = true)
	private Integer user_De;
	
	@Column(name = "user_max")
	private Integer user_Max;
	
	@Column(name = "user_min")
	private Integer user_Min;

	@ManyToOne	
	@JoinColumn(name="branch_id")
	private BrancEntity branch_id;

	public int getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	
	public String getUser_Count() {
		return user_Count;
	}

	public void setUser_Count(String user_Count) {
		this.user_Count = user_Count;
	}

	public String getUser_Password() {
		return user_Password;
	}

	public void setUser_Password(String user_Password) {
		this.user_Password = user_Password;
	}
	
	public String getUser_Name() {
		return user_Name;
	}
	
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	
	public String getUser_Cid() {
		return user_Cid;
	}

	public void setUser_Cid(String user_Cid) {
		this.user_Cid = user_Cid;
	}

	public Date getUser_Date() {
		return user_Date;
	}

	public void setUser_Date(Date user_Date) {
		this.user_Date = user_Date;
	}

	public String getUser_Address() {
		return user_Address;
	}

	public void setUser_Address(String user_Address) {
		this.user_Address = user_Address;
	}
	
	public String getUser_Mail() {
		return user_Mail;
	}
	
	public void setUser_Mail(String user_Mail) {
		this.user_Mail = user_Mail;
	}

	public String getUser_Phone() {
		return user_Phone;
	}
	
	public void setUser_Phone(String user_Phone) {
		this.user_Phone = user_Phone;
	}

	public Integer getUser_Qr() {
		return user_Qr;
	}
	
	public void setUser_Qr(Integer user_Qr) {
		this.user_Qr = user_Qr;
	}

	public Integer getUser_In() {
		return user_In;
	}
	
	public void setUser_In(Integer user_In) {
		this.user_In = user_In;
	}

	public Integer getUser_Up() {
		return user_Up;
	}

	public void setUser_Up(Integer user_Up) {
		this.user_Up = user_Up;
	}
	
	public Integer getUser_De() {
		return user_De;
	}

	public void setUser_De(Integer user_De) {
		this.user_De = user_De;
	}
	
	public Integer getUser_Max() {
		return user_Max;
	}
	
	public void setUser_Max(Integer user_Max) {
		this.user_Max = user_Max;
	}
	
	public Integer getUser_Min() {
		return user_Min;
	}
	
	public void setUser_Min(Integer user_Min) {
		this.user_Min = user_Min;
	}

	public BrancEntity getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(BrancEntity branch_id) {
		this.branch_id = branch_id;
	}

	@Override
	public String toString() {
		return "UserEntity [id="+ user_Id +"user_Count="+user_Count+" user_Password="+user_Password+
				" user_Name="+user_Name+" user_Cid="+user_Cid+ "user_Date="+user_Date+" user_Address="+user_Address+
				"user_Mail="+user_Mail+" user_Phone="+user_Phone+" user_Qr="+user_Qr+" user_In="+user_In+" user_Up="+user_Up+
				" user_De"+user_De+" user_Max"+user_Max+"user_Min"+user_Min;
	}

}
