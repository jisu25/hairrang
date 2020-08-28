package hairrang.chart;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import hairrang.conn.JdbcUtil;
import hairrang.dao.impl.HairDaoImpl;
import hairrang.dto.Event;
import hairrang.dto.Guest;
import hairrang.dto.Hair;
import hairrang.dto.Sales;

public class ChartDaoImpl implements ChartDaoo {
	private static final ChartDaoImpl instance = new ChartDaoImpl();
	private ChartDaoImpl() {};
	  
	public static ChartDaoImpl getInstance() {
		return instance;
	}
	@Override
	public Vector<HairrangDto> selectHairrangDtoByAll() {
		String sql = "SELECT * FROM SALES s JOIN HAIR h ON (s.HAIR_NO = h.HAIR_NO ) JOIN GUEST g ON (g.GUEST_NO = s.GUEST_NO) JOIN EVENT e ON (s.EVENT_NO = e.EVENT_NO)";
		try(Connection con = JdbcUtil.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				Vector<HairrangDto> list = new Vector<HairrangDto>();
				do {
					list.add(getHairrangDto(rs));
				}while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return null;
	}

	private HairrangDto getHairrangDto(ResultSet rs) throws SQLException {
		HairDaoImpl hdao = HairDaoImpl.getInstance();
		Hair hair = hdao.getHair(rs);
		
		//이후 헤어처럼 밑에 코드 수정
		int no = rs.getInt("EVENT_NO");
		String name = rs.getString("EVENT_NAME");
		
		Event event =  new Event(no, name, 0);
		
		int gno = rs.getInt("GUEST_NO");
		String gname = rs.getString("GUEST_NAME");
		Guest guset = new Guest(gno, gname, null, null, null, 0, null);
		
		int sno = rs.getInt("SALES_NO");
		Date day = rs.getDate("SALES_DAY");
		Sales sales = new Sales(sno,day);
		
		
		HairrangDto hairrangDto = new HairrangDto (sales, guset, hair, event);
		
		return hairrangDto;
	}

	public Vector<HairrangDto> selectHairrangDtoByYear(int startYear, int endYear) {
		String sql = "SELECT s.SALES_NO ,TO_CHAR( s.SALES_DAY ,'YYYY-MM-DD') day,g.GUEST_NAME,h.HAIR_NAME  ,e.EVENT_NAME ,h.PRICE " + 
				"FROM SALES s" + 
				"	LEFT OUTER JOIN GUEST g ON S.GUEST_NO = G.GUEST_NO " + 
				"	LEFT OUTER JOIN HAIR h  ON S.SALES_NO =  H.HAIR_NO " + 
				"	LEFT OUTER JOIN EVENT e ON S.EVENT_NO = E.EVENT_NO " + 
				"WHERE TO_CHAR(S.SALES_DAY,'YYYY') BETWEEN ? AND ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, startYear);
			pstmt.setInt(2, endYear);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					Vector<HairrangDto> list = new Vector<HairrangDto>();
					do {
						Sales sales = new Sales(rs.getInt("SALES_NO"),rs.getDate("day"));
						Guest guest = new Guest(rs.getString("GUEST_NAME"));
						Hair hair = new Hair(rs.getString("HAIR_NAME"),rs.getInt("PRICE"));
						Event event = new Event(rs.getString("EVENT_NAME"));
						
						HairrangDto hairrangDto=new HairrangDto(sales,guest,hair,event);
						list.add(getHairrangDto(rs));
					}while(rs.next());
					return list;
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	

	@Override
	public Vector<HairrangDto> selectHairrangDtoByMonth(int year) {
		String sql = "SELECT s.SALES_NO ,TO_CHAR( s.SALES_DAY ,'YYYY-MM-DD') day,g.GUEST_NAME,h.HAIR_NAME  ,e.EVENT_NAME ,h.PRICE " + 
				"FROM SALES s" + 
				"	LEFT OUTER JOIN GUEST g ON S.GUEST_NO = G.GUEST_NO " + 
				"	LEFT OUTER JOIN HAIR h  ON S.SALES_NO =  H.HAIR_NO " + 
				"	LEFT OUTER JOIN EVENT e ON S.EVENT_NO = E.EVENT_NO " + 
				"WHERE TO_CHAR(S.SALES_DAY,'YYYY') =  ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, year);
			
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					Vector<HairrangDto> list = new Vector<HairrangDto>();
					do {
						Sales sales = new Sales(rs.getInt("SALES_NO"),rs.getDate("day"));
						Guest guest = new Guest(rs.getString("GUEST_NAME"));
						Hair hair = new Hair(rs.getString("HAIR_NAME"),rs.getInt("PRICE"));
						Event event = new Event(rs.getString("EVENT_NAME"));
						
						HairrangDto hairrangDto=new HairrangDto(sales,guest,hair,event);
						list.add(getHairrangDto(rs));
					}while(rs.next());
					return list;
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	
 
	
}