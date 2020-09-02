package hairrang.service;

import java.util.Date;
import java.util.List;

import hairrang.dao.BookingDao;
import hairrang.dao.impl.BookingDaoImpl;
import hairrang.dto.Booking;
import hairrang.dto.Guest;

public class BookingService {
	
		private BookingDao dao = BookingDaoImpl.getInstance();
		
		public void addBook(Booking book) {
			dao.insertBook(book);
		}
		
		public void updateBook(Booking book) {
			dao.updateBook(book);
		}
		
		public void deleteBook(Booking book) {
			dao.deleteBook(book);
		}
		
		public List<Booking> getBookList(){
			return dao.selectBookAll();
		}
		
		public List<Booking> getBookListByGuestNo(Guest guest) {
			return dao.selectBookByGuestNo(guest);
		}
		
		
		public int getBookingCurrVal() {
			return dao.getBookCurrVal();
		}
		
		public List<Booking> getTodayBookList() {
			return dao.selectTodayBook();
		}
		
		public List<Booking> getBookListByDate(Date from, Date to) {
			return dao.selectBookByDate(from, to);
		}
		
		/*
		public List<Booking> selectBookByName(Booking book) {
			return dao.selectBookByName(book);
		}
		
		public List<Booking> searchBookingByName(Booking book){
			return dao.searchBookByName(book);
		}
		
		*/
}
