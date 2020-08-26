package hairrang.conn;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
	public static Connection getConnection() {
		
		Connection con = null;
		String proptiesPath = "Db.properties";
		
		try (InputStream is = ClassLoader.getSystemResourceAsStream(proptiesPath)) {
			
			Properties props = new Properties();
			props.load(is);
			
			String url = props.getProperty("url");
			con = DriverManager.getConnection(url, props);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return con;
	}
	
	// <DB 연결 해제 1> 메소드 : ps + conn
		public static void dbClose(PreparedStatement ps, Connection conn) throws SQLException {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
			System.out.println("자원반납, DB Close!!");
		}

		// <DB 연결 해제 2> 메소드 : st + conn
		public static void dbClose(Statement st, Connection conn) throws SQLException {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
			System.out.println("자원반납, DB Close!!");
		}

		// <DB 연결 해제 3> 메소드 : rs + ps + conn
		public static void dbClose(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
			System.out.println("자원반납, DB Close!!");
		}

		// <DB 연결 해제 4> 메소드 : rs + st + conn
		public static void dbClose(ResultSet rs, Statement st, Connection conn) throws SQLException {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
			System.out.println("자원반납, DB Close!!");
		}
}