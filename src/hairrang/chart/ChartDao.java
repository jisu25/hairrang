package hairrang.chart;


	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import hairrang.conn.JdbcUtil;

	public class ChartDao {


	/*
		StatDao : 통계 DB 관련 메소드 정의
	*/

		// DB연결된 상태(세션)을 담은 객체
		Connection conn = null;

		// 쿼리문에 사용하는 객체
		PreparedStatement ps = null;

		Statement st = null;
		ResultSet rs = null;

		// 쿼리문 결과 (1행) 담을 HairrangDto 객체
		HairrangDto hairrnagDto = null;

		// [메소드]

		
		

		//////////////////////////////// 기간별 통계 ////////////////////////////////
		// <연도별 매출내역 select> 메소드
		// : 년 입력받아 조회

		public Vector<HairrangDto> findYearSell(int startYear, int endYear) {
			// 쿼리문 결과 (여러 행) 담을 HairrangDto 객체
			Vector<HairrangDto> list = new Vector<HairrangDto>();

			try {
				// DB 연결
				conn = JdbcUtil.getConnection();

				// 쿼리문 세팅
				String query = "SELECT S.SALES_NO AS 영업번호 ,TO_CHAR( S.SALES_DAY ,'YYYY-MM-DD')AS 영업일자 ,g.GUEST_NAME AS 고객명  ,E.EVENT_NAME AS 이벤트명 ,H.PRICE AS 가격\r\n" + 
						"FROM SALES s\r\n" + 
						"	LEFT OUTER JOIN GUEST g ON S.GUEST_NO = G.GUEST_NO \r\n" + 
						"	LEFT OUTER JOIN HAIR h  ON S.SALES_NO =  H.HAIR_NO \r\n" + 
						"	LEFT OUTER JOIN EVENT e ON S.EVENT_NO = E.EVENT_NO \r\n" + 
						"	WHERE TO_CHAR(S.SALES_DAY,'YYYY') BETWEEN ? AND ?";
				ps = conn.prepareStatement(query);
				ps.setInt(1, startYear);
				ps.setInt(2, endYear);
				

				// 쿼리문 실행
				rs = ps.executeQuery();

				// 결과 저장
				while (rs.next()) {
					HairrangDto hairrangDto = new HairrangDto();
					
					hairrangDto.setSalesNo(rs.getInt(1));
					hairrangDto.setSalesDay(rs.getString(2));
					hairrangDto.setGuestName(rs.getString(3));
					hairrangDto.setHairName(rs.getString(4));
					hairrangDto.setEventName(rs.getString(5));
					hairrangDto.setPrice(rs.getInt(6));
					
					list.add(hairrangDto);
					
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					// DB 연결 종료
					JdbcUtil.dbClose(rs, ps, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// 결과 리턴
			return list;
		}

		// <월별 매출내역 select> 메소드
		// : 년 입력받아 조회
		public Vector<HairrangDto> findMonthSell(int year) {
			// 쿼리문 결과 (여러 행) 담을 HairrangDto 객체
			Vector<HairrangDto> list = new Vector<HairrangDto>();

			try {
				// DB 연결
				conn = JdbcUtil.getConnection();

				// 쿼리문 세팅
				String query = "SELECT S.SALES_NO AS 영업번호 ,TO_CHAR( S.SALES_DAY ,'YYYY-MM-DD')AS 영업일자 ,g.GUEST_NAME AS 고객명  ,E.EVENT_NAME AS 이벤트명 ,H.PRICE AS 가격\r\n" + 
						"FROM SALES s\r\n" + 
						"	LEFT OUTER JOIN GUEST g ON S.GUEST_NO = G.GUEST_NO \r\n" + 
						"	LEFT OUTER JOIN HAIR h  ON S.SALES_NO =  H.HAIR_NO \r\n" + 
						"	LEFT OUTER JOIN EVENT e ON S.EVENT_NO = E.EVENT_NO \r\n" + 
						"	WHERE TO_CHAR(S.SALES_DAY,'YYYY') =  ?";
				ps = conn.prepareStatement(query);
				ps.setInt(1, year);

				// 쿼리문 실행
				rs = ps.executeQuery();

				// 결과 저장
				while (rs.next()) {
					HairrangDto hairrangDto = new HairrangDto();
					
					hairrangDto.setSalesNo(rs.getInt(1));
					hairrangDto.setSalesDay(rs.getString(2));
					hairrangDto.setGuestName(rs.getString(3));
					hairrangDto.setHairName(rs.getString(4));
					hairrangDto.setEventName(rs.getString(5));
					hairrangDto.setPrice(rs.getInt(6));
					
					list.add(hairrangDto);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					// DB 연결 종료
					JdbcUtil.dbClose(rs, ps, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// 결과 리턴
			return list;
		}

		
		
		////////////////////////////////////// [static 메소드] /////////////////////////////////////////////
		
		// 테이블 행 모두 지우기 (화면단에서만)
		public static void clearRows(int rowSize, DefaultTableModel dtm) {
			if (rowSize > 0) {
				for (int i = rowSize - 1; i >= 0; i--) {
					dtm.removeRow(i);
				}
			}
		}

	}




