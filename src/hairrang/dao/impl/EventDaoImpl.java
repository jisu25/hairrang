package hairrang.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hairrang.conn.JdbcUtil;
import hairrang.dao.EventDao;
import hairrang.dto.Event;

public class EventDaoImpl implements EventDao {

	private static final EventDaoImpl instance = new EventDaoImpl();
	
	private EventDaoImpl() {
	}

	public static EventDaoImpl getInstance() {
		return instance;
	}
	
	@Override
	public List<Event> selectEventByAll() {
		
		String sql =  "SELECT * FROM EVENT";
		
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement ptst =  con.prepareStatement(sql);
			ResultSet rs = ptst.executeQuery()){
			
			if(rs.next()) {
				List<Event> list = new ArrayList<Event>();
				do {
					list.add(getEvent(rs));
				}while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new  RuntimeException(e);
		}
		
		return null;
	}

	
	private Event getEvent(ResultSet rs) throws SQLException {
		int no = rs.getInt("EVENT_NO");
		String name = rs.getString("EVENT_NAME");
		double sale = rs.getDouble("EVENT_SALE");
		
		return new Event(no, name, sale);
	}

	
	@Override
	public List<String> selectEventNames() {
		String sql =  "SELECT EVENT_NAME FROM EVENT";
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement ptst =  con.prepareStatement(sql);
			ResultSet rs = ptst.executeQuery()){
		
			if(rs.next()) {
				List<String> list = new ArrayList<String>();
				do {
					list.add(rs.getString("EVENT_NAME"));
				}while(rs.next());
				
				return list;
			}
		} catch (SQLException e) {
			throw new  RuntimeException(e);
		}
		return null;
	}

	
	@Override
	public Event selectEventByEventNo(Event event) {
		String sql =  "SELECT * FROM EVENT WHERE EVENT_NO = ?";
		
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt =  con.prepareStatement(sql)) {
	
			pstmt.setInt(1, event.getEventNo());
			
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					return getEvent(rs);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	
	@Override
	public int insertEvent(Event event) {
		String sql = "INSERT INTO EVENT VALUES(?, ?, ?)";
		
		try(Connection con = JdbcUtil.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1, event.getEventNo());
			pstmt.setString(2, event.getEventName());
			pstmt.setDouble(3, event.getSale());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateEvent(Event event) {
		String sql = "UPDATE EVENT SET EVENT_NAME = ?, EVENT_SALE = ? WHERE EVENT_NO =?";
		try(Connection con = JdbcUtil.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, event.getEventName());
			pstmt.setDouble(2, event.getSale());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteEvent(Event event) {
		String sql = "DELETE EVENT WHERE EVENT_NO =?";
		try(Connection con = JdbcUtil.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1, event.getEventNo());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
