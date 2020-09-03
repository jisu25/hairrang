package hairrang.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Booking {
	
	private int bookNo;
	private Guest guestNo;
	private String bookedBy;
	private String bookPhone;
	private Date bookDay;
	private Hair hairNo;
	private String bookNote;
	
	
	

	public Booking() {
	}

	public Booking(int bookNo) {
		this.bookNo = bookNo;
	}

	public Booking(int bookNo, Guest guestNo, Date bookDay, Hair hairNo, String bookNote) {
		this.bookNo = bookNo;
		this.guestNo = guestNo;
		this.bookDay = bookDay;
		this.hairNo = hairNo;
		this.bookNote = bookNote;
	}

	
	public Booking(int bookNo, Guest guestNo, String bookedBy, String bookPhone, Date bookDay, Hair hairNo, String bookNote) {
		this.bookNo = bookNo;
		this.guestNo = guestNo;
		this.bookedBy = bookedBy;
		this.bookPhone = bookPhone;
		this.bookDay = bookDay;
		this.hairNo = hairNo;
		this.bookNote = bookNote;
	}


	public Date getBookDay() {
		return bookDay;
	}

	public void setBookDay(Date bookDay) {
		this.bookDay = bookDay;
	}

	public String getBookDayStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd E요일", Locale.KOREAN);
		return dateFormat.format(bookDay);
	}

	public String getBookTimeStr() {
		SimpleDateFormat timeFormat = new SimpleDateFormat("a hh:mm");
		return timeFormat.format(bookDay);
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public Guest getGuestNo() {
		return guestNo;
	}

	public void setGuestNo(Guest guestNo) {
		this.guestNo = guestNo;
	}

	public String getBookedBy() {
		return bookedBy;
	}

	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}

	public String getBookPhone() {
		return bookPhone;
	}

	public void setBookPhone(String bookPhone) {
		this.bookPhone = bookPhone;
	}
	
	public Hair getHairNo() {
		return hairNo;
	}

	public void setHairNo(Hair hairNo) {
		this.hairNo = hairNo;
	}

	public String getBookNote() {
		return bookNote;
	}

	public void setBookNote(String bookNote) {
		this.bookNote = bookNote;
	}
	
	public String getBookInfo() {
		Guest guest = getGuestNo();
		
		return String.format("[%s %s] %s님(%s, %s) %s 예약",
				getBookDayStr(), getBookTimeStr(), bookedBy, guest.getGuestNo() ==0 ? "비회원" : guest.getGuestNo(), bookPhone, getHairNo().getHairName());
	}

	@Override
	public String toString() {
		return String.format("Booking [bookNo=%s, guestNo=%s, bookedBy=%s, bookPhone=%s, bookDay=%s, bookDayStr=%s, bookTimeStr=%s, hairNo=%s, bookNote=%s]",
				bookNo, guestNo, bookedBy, bookPhone, bookDay, getBookDayStr(), getBookTimeStr(), hairNo, bookNote);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this.bookNo == ((Booking) obj).bookNo;
	}
	
	
	
}
