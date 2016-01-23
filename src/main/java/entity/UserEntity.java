package entity;

public class UserEntity {
	
	private int user_Id;
	private String user_Count;
	private String user_Password;
	private String user_Name;
	private String user_Cid;
	private String user_Date;
	private String user_Address;
	private String user_Mail;
	private int user_Qr;
	private int user_In;
	private int user_Up;
	private int user_De;
	private int user_Max;
	private int user_Min;



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

	public String getUser_Date() {
		return user_Date;
	}


	public void setUser_Date(String user_Date) {
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

	public int getUser_Qr() {
		return user_Qr;
	}



	public void setUser_Qr(int user_Qr) {
		this.user_Qr = user_Qr;
	}

	public int getUser_In() {
		return user_In;
	}


	public void setUser_In(int user_In) {
		this.user_In = user_In;
	}

	public int getUser_Up() {
		return user_Up;
	}

	public void setUser_Up(int user_Up) {
		this.user_Up = user_Up;
	}


	public int getUser_De() {
		return user_De;
	}

	public void setUser_De(int user_De) {
		this.user_De = user_De;
	}


	public int getUser_Max() {
		return user_Max;
	}



	public void setUser_Max(int user_Max) {
		this.user_Max = user_Max;
	}



	public int getUser_Min() {
		return user_Min;
	}



	public void setUser_Min(int user_Min) {
		this.user_Min = user_Min;
	}


	@Override
	public String toString() {
		return "UserEntity [id="+ user_Id +"user_Count="+user_Count+" user_Password="+user_Password+
				" user_Name="+user_Name+" user_Cid="+user_Cid+ "user_Date="+user_Date+" user_Address="+user_Address+
				"user_Mail="+user_Mail+" user_Phone="+user_Phone+" user_Qr="+user_Qr+" user_In="+user_In+" user_Up="+user_Up+
				" user_De"+user_De+" user_Max"+user_Max+"user_Min"+user_Min;
	}

}
