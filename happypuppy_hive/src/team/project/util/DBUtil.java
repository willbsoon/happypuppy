package team.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	private static final String DRIVER_NAME="com.mysql.jdbc.Driver";
	private static final String CON_URL="jdbc:mysql://chkrdp2.cdmsishpmtue.ap-northeast-2.rds.amazonaws.com:3306/animal";
	private static final String CON_NAME="chkrdp";
	private static final String CON_PWD="cjs123rlf";

	static {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	DBUtil(){

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
