package hairrang.chart;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.hamcrest.Condition.Step;

import hairrang.conn.JdbcUtil;
import hairrang.dao.impl.HairDaoImpl;
import hairrang.dto.Event;
import hairrang.dto.Guest;
import hairrang.dto.Hair;
import hairrang.dto.Sales;

public class ChartDaoImpl implements ChartDaoo {
	private static final ChartDaoImpl instance = new ChartDaoImpl();

	private ChartDaoImpl() {
	};

	public static ChartDaoImpl getInstance() {
		return instance;
	}

	@Override
	public Vector<HairrangDto> selectHairrangDtoByAll() {
		String sql = "SELECT * FROM SALES s JOIN HAIR h ON (s.HAIR_NO = h.HAIR_NO ) JOIN GUEST g ON (g.GUEST_NO = s.GUEST_NO) JOIN EVENT e ON (s.EVENT_NO = e.EVENT_NO)";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				Vector<HairrangDto> list = new Vector<HairrangDto>();
				do {
					list.add(getHairrangDto(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	private HairrangDto getHairrangDto(ResultSet rs) throws SQLException {
		Sales sales = new Sales(rs.getInt("SALES_NO"), rs.getDate("day"), rs.getInt("TOTAL_PRICE"));
		Guest guest = new Guest(rs.getString("GUEST_NAME"));
		Hair hair = new Hair(rs.getString("HAIR_NAME"));
		Event event = new Event(rs.getString("EVENT_NAME"));

		HairrangDto hairrangDto = new HairrangDto(sales, guest, hair, event);
		return hairrangDto;
	}

	public Vector<HairrangDto> selectHairrangDtoByYear(int startYear, int endYear) {
		String sql = "SELECT s.SALES_NO ,TO_CHAR( s.SALES_DAY ,'YYYY-MM-DD') day,g.GUEST_NAME,h.HAIR_NAME  ,e.EVENT_NAME ,s.TOTAL_PRICE "
				+ "FROM SALES s" + "	LEFT OUTER JOIN GUEST g ON S.GUEST_NO = G.GUEST_NO "
				+ "	LEFT OUTER JOIN HAIR h  ON S.SALES_NO =  H.HAIR_NO "
				+ "	LEFT OUTER JOIN EVENT e ON S.EVENT_NO = E.EVENT_NO "
				+ "WHERE TO_CHAR(S.SALES_DAY,'YYYY') BETWEEN ? AND ? " + "ORDER BY S.SALES_NO ";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, startYear);
			pstmt.setInt(2, endYear);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Vector<HairrangDto> list = new Vector<HairrangDto>();
					do {

						list.add(getHairrangDto(rs));
					} while (rs.next());
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
		String sql = "SELECT S.SALES_NO 영업번호 ,TO_CHAR( S.SALES_DAY ,'YYYY-MM-DD') 영업일자 ,g.GUEST_NAME 고객명  ,E.EVENT_NAME 이벤트명 ,s.TOTAL_PRICE  가격\r\n"
				+ "FROM SALES s\r\n" + "	LEFT OUTER JOIN GUEST g ON S.GUEST_NO = G.GUEST_NO \r\n"
				+ "	LEFT OUTER JOIN HAIR h  ON S.SALES_NO =  H.HAIR_NO \r\n"
				+ "	LEFT OUTER JOIN EVENT e ON S.EVENT_NO = E.EVENT_NO \r\n"
				+ "	WHERE TO_CHAR(S.SALES_DAY,'YYYY') =  ?" + "   ORDER BY SALES_NO";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, year);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Vector<HairrangDto> list = new Vector<HairrangDto>();
					do {
						list.add(getHairrangDto(rs));
					} while (rs.next());
					return list;
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public List<Integer> totalPrice(int startYear, int endYear) {
		List<Integer> list = new ArrayList<Integer>();
		String sql = "SELECT SUM(TOTAL_PRICE ) AS TOTAL FROM SALES WHERE TO_CHAR(SALES_DAY , 'yyyy') = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			System.out.println("try시작");
			for (int i= 0; -1<(endYear - startYear);i++) {
				System.out.println("for문 시작");
				pstmt.setInt(1, startYear);
				System.out.println("startyear시작전"+startYear);
				startYear++;
				System.out.println("startyear시작후"+startYear);
				try (ResultSet rs = pstmt.executeQuery()) {
					System.out.println("rs 시작");
					
					if (rs.next()) {
						do {
							System.out.println("결과값"+getInteger(rs));
							list.add(getInteger(rs));
						} while (rs.next());
						
					}
					
				}

			}
			return list;
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private Integer getInteger(ResultSet rs) throws SQLException {
		int res = rs.getInt("TOTAL");
		return res;
	}

}