package hairrang.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
	
	private int bookNo;
	private Guest guestNo;
	private Date bookDay;
	private String bookDayStr;
	private String bookTimeStr;
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
		
		setBookDayStr(bookDay);
		setBookTimeStr(bookDay);
	}

	public Date getBookDay() {
		return bookDay;
	}

	public void setBookDay(Date bookDay) {
		this.bookDay = bookDay;
		setBookDayStr(bookDay);
		setBookTimeStr(bookDay);
	}

	public String getBookDateStr() {
		return bookDayStr;
	}

	
	public void setBookDayStr(Date bookDay) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		this.bookDayStr = dateFormat.format(bookDay);
	}
	
	public void setBookDayStr(String bookDayStr) {
		this.bookDayStr = bookDayStr;
	}

	public String getBookTimeStr() {
		return bookTimeStr;
	}

	public void setBookTimeStr(Date bookDay) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("a HH:mm");
		this.bookTimeStr = timeFormat.format(bookDay);
	}
	
	public void setBookTimeStr(String bookTimeStr) {
		this.bookTimeStr = bookTimeStr;
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

	@Override
	public String toString() {
		return String.format("Booking [bookNo=%s, guestNo=%s, bookDay=%s, bookDayStr=%s, bookTimeStr=%s, hairNo=%s, bookNote=%s]", bookNo,
				guestNo, bookDay, bookDayStr, bookTimeStr, hairNo, bookNote);
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
