package hairrang.dao;

import java.util.List;

import hairrang.dto.Booking;
import hairrang.dto.Guest;

public interface BookingDao {

		List<Booking> selectBookAll();

		List<Booking> selectBookByGuestNo(Guest guest);

		int insertBook(Booking book);

		int updateBook(Booking book);

		int deleteBook(Booking book);
		
		int getBookCurrVal();
		
		List<Booking> selectTodayBook();
		
	
}
