package team.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class HiveDBUtil {
	private static final String DRIVER_NAME="org.apache.hive.jdbc.HiveDriver";
	private static final String CON_URL="jdbc:hive2://210.114.91.91:23395/default";
	private static final String CON_NAME="eduuser";
	private static final String CON_PWD="hello.edu";

	static {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	HiveDBUtil(){
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(CON_URL,CON_NAME,CON_PWD);
	}

	public static void close(Connection con, PreparedStatement pstmt) throws SQLException {
		pstmt.close();
		con.close();
	}
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		rs.close();
		pstmt.close();
		con.close();
	}
}
