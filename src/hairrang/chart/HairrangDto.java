package hairrang.chart;

import java.sql.Date;

public class HairrangDto {
	private int hairNo;
	private String hairName;
	private int price;
	
	public  HairrangDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public  HairrangDto(int hairNo, String hairName, int hairPrice) {
		super();
		this.hairNo = hairNo;
		this.hairName = hairName;
		this.price = hairPrice;
	}
	
	public int getHairNo() {
		return hairNo;
	}
	public void setHairNo(int hairNo) {
		this.hairNo = hairNo;
	}
	public String getHairName() {
		return hairName;
	}
	public void setHairName(String hairName) {
		this.hairName = hairName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	//================================================
	
	private int guestNo;
	private String guestName;
	private Date birthday;
	private Date joinDay;
	private String phone;
	private int gender;
	private String guestNote;
	
	

	public HairrangDto(int guestNo) {
		super();
		this.guestNo = guestNo;
	}
	
	public HairrangDto(String guestName) {
		super();
		this.guestName = guestName;
	}

	public HairrangDto(int guestNo, String guestName, Date birthday, Date joinDay, String phone, int gender,
			String guestNote) {
		super();
		this.guestNo = guestNo;
		this.guestName = guestName;
		this.birthday = birthday;
		this.joinDay = joinDay;
		this.phone = phone;
		this.gender = gender;
		this.guestNote = guestNote;
	}

	public int getGuestNo() {
		return guestNo;
	}

	public void setGuestNo(int guestNo) {
		this.guestNo = guestNo;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getJoinDay() {
		return joinDay;
	}

	public void setJoinDay(Date joinDay) {
		this.joinDay = joinDay;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getGender() {
		return gender;
	}
//=======================================================================
	
	private int eventNo;
	private String eventName;
	private double sale;
	
	
	
	public int getEventNo() {
		return eventNo;
	}
	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public double getSale() {
		return sale;
	}
	public void setSale(double eventSale) {
		this.sale = eventSale;
	}
	
//=========================================================================
	private int salesNo;
	private String salesDay;	
	private String salesTime;

	

	public int getSalesNo() {
		return salesNo;
	}

	public void setSalesNo(int salesNo) {
		this.salesNo = salesNo;
	}

	public String getSalesDay() {
		return salesDay;
	}

	public void setSalesDay(String salesDay) {
		this.salesDay = salesDay;
	}
	
	public String getSalesTime() {
		return salesTime;
	}

	public void setSalesTime(String salesTime) {
		this.salesTime = salesTime;
	}

	//================================================================
	
	private int detailNo;
	
	
	
	public int getDetailNo() {
		return detailNo;
	}

	public void setDetailNo(int detailNo) {
		this.detailNo = detailNo;
	}

	public Integer getStatTotalPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	
}
	
