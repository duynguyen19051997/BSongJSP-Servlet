package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionUtil {
	private static Connection connect = null;
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;

	private static final String url = "jdbc:mysql://localhost:3306/bsong?useUnicode=true&characterEncoding=UTF-8";
	private static final String user = "root";
	private static final String password = "";

	public static Connection getConnection() {

		try {
			// nạp driver
			Class.forName("com.mysql.jdbc.Driver");

			// tạo chuỗi kết nối
			connect = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return connect;
	}
	
	/*
	 *  private static String url;
		private static String user;
		private static String password;
		
		public static Connection getConnection() {
		try {
			// nạp driver
			Class.forName("com.mysql.jdbc.Driver");
			Properties properties = PropertiesUtil.readProperties();
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			// tạo chuỗi conn
			connect = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Không thể nạp driver");
			e.printStackTrace();
		}
		return connect;
	}*/

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(PreparedStatement pst) {
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Connection connect) {
		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rs, Statement st, Connection connect) {
		close(rs);
		close(st);
		close(connect);
	}

	public static void close(ResultSet rs, PreparedStatement pst, Connection connect) {
		close(rs);
		close(pst);
		close(connect);
	}

	public static void close(PreparedStatement pst, Connection connect) {
		close(pst);
		close(connect);
	}
	
	public static int countItems(String sql) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		try {
			st = connect.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = Integer.valueOf(rs.getInt("COUNT(*)"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return result;
	}
}
