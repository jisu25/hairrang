package hairrang.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hairrang.conn.JdbcUtil;
import hairrang.dao.BookingDao;
import hairrang.dao.GuestDao;
import hairrang.dao.HairDao;
import hairrang.dto.Booking;
import hairrang.dto.Guest;
import hairrang.dto.Hair;

public class BookingDaoImpl implements BookingDao {

	private static final BookingDaoImpl instance = new BookingDaoImpl();
	
	private BookingDaoImpl() {
	}
	
	public static BookingDaoImpl getInstance() {
		return instance;
	}
	

	@Override
	public List<Booking> selectBookByAll() {
		
		String sql = "SELECT BOOK_NO, GUEST_NO, BOOK_DATE, HAIR_NO, BOOK_NOTE FROM BOOKING";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery() ) {
			
			if(rs.next()) {
				List<Booking> list = new ArrayList<>();
				
				do {
					list.add(getBook(rs));
				} while(rs.next());
				
				return list;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return null;
	}


	@Override
	public List<Booking> selectBookByGuestNo(Guest guest) {
		
		String sql = "SELECT BOOK_NO, GUEST_NO, BOOK_DAY, HAIR_NO, BOOK_NOTE FROM BOOKING WHERE GUEST_NO = ?";
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, guest.getGuestNo());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				List<Booking> list = new ArrayList<>();
				
				do {
					list.add(getBook(rs));
				} while(rs.next());
				
				return list;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return null;
	}

	@Override
	public int insertBook(Booking book) {
//		INSERT INTO BOOKING
//		values(BOOKING_SEQ.NEXTVAL, 1, SYSDATE, 1, '10분 늦을 수도 있다고 하심');
		String sql = "INSERT INTO BOOKING VALUES(BOOKING_SEQ.NEXTVAL, ?, ?, ?, ?)";
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1, book.getGuestNo().getGuestNo());
			pstmt.setDate(2, new java.sql.Date(book.getBookDate().getTime()));
			pstmt.setInt(3, book.getHairNo().getHairNo());
			pstmt.setString(4, book.getBookNote());
			
			return pstmt.executeUpdate();
	
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public int updateBook(Booking book) {
		String sql = "UPDATE BOOKING SET GUEST_NO = ?, BOOK_DATE = ?, HAIR_NO = ?, BOOK_NOTE = ? WHERE BOOK_NO= ?";
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){

			pstmt.setInt(1, book.getGuestNo().getGuestNo());
			pstmt.setDate(2, new java.sql.Date(book.getBookDate().getTime()));
			pstmt.setInt(3, book.getHairNo().getHairNo());
			pstmt.setString(4, book.getBookNote());
			pstmt.setInt(5, book.getBookNo());
			
			return pstmt.executeUpdate();
	
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public int deleteBook(Booking book) {
		String sql = "DELETE BOOKING WHERE BOOK_NO = ?";
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){

			pstmt.setInt(1, book.getBookNo());
			
			return pstmt.executeUpdate();
	
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	private Booking getBook(ResultSet rs) throws SQLException {
		
		/*
		 * java.util.Date utilDate = new java.util.Date(rs.getDate("regdate").getTime());
			java.util.Date utilDate = rs.getTimestamp("regdate");
			  //->java.sql.Timestamp가  Date을 상속하기 때문에 이렇게 쓸 수 있음
		 */
		
		GuestDao gDao = GuestDaoImpl.getInstance();
		HairDao hDao = HairDaoImpl.getInstance();
		
		Guest guestNo = gDao.selectGuestByNo(new Guest(rs.getInt("GUEST_NO")));
		Hair hairNo = hDao.selectHairByNo(new Hair(rs.getInt("HAIR_NO")));
		
		int no = rs.getInt("BOOK_NO");
		Date bookDate = new Date(rs.getDate("BOOK_DAY").getTime());
		String note = rs.getString("BOOK_NOTE");
		
		Booking book = new Booking(no, guestNo, bookDate, hairNo, note);

//		System.out.println(book);
//		System.out.println(book.getBookDateStr() + " " + book.getBookTimeStr());
		
		return book;
	}
	
}
