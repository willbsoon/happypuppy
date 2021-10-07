package team.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import team.project.dto.Admin;
import team.project.dto.Time_now;
import team.project.util.DBUtil;

public class AdminDao {
	// 싱글턴패턴
	private static AdminDao dao = new AdminDao();
	private AdminDao() {
	}
	public static AdminDao getInstance() {
		return dao;
	}
	
	/**
	 * dailyBatch테이블에 insert
	 * @param admin : admin객체 dto
	 * @param now : Time_now 객체 dto
	 * @return result : insert결과 1 이나오면 행입력된거임
	 */
	public void insertDaily(Time_now now, Admin admin) throws SQLException {
		System.out.println("3. AdminDao insertDaily(Admin admin)");
		Connection con = DBUtil.getConnection();
		String sql = "insert into dailyBatch ("
				+ "dailyDt,time,"//2017-08-07,12:12:12,
				+ "postCount,visitCount,"
				+ "userCount,puppyCount)"
				+ " values ("
				+ "?,?,"
				+ "(select count(*) from post where writeDt=?),"
				+ "(select count(*) from visit where visitDt=?),"
				+ "(select count(*) from user where regDt=?),"
				+ "?"
				+ ")";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, now.getSimple_today());
		pstmt.setString(2, now.getTimetoday());
		pstmt.setString(3, now.getSimple_today());
		pstmt.setString(4, now.getSimple_today());
		pstmt.setString(5, now.getSimple_today());
		pstmt.setInt(6, admin.getPuppyCount());
		int result = 0;
		result = pstmt.executeUpdate();
		System.out.println("dailybatch 테이블의 insert결과값 : " + result);
	}
	
	/**
	 * allBatch테이블에 insert
	 * 어제 allBatch테이블의 값과 오늘의 dailyBatch테이블의 값을 더해서 insert
	 * @param admin : admin객체 dto
	 * @param now : Time_now 객체 dto
	 * @return result : insert결과 1 이나오면 행입력된거임
	 */
	public void insertAll(Time_now now, Admin admin) throws SQLException {
		System.out.println("3. AdminDao insertAll(Time_now now, Admin admin)");
		Connection con = DBUtil.getConnection();
		String sql = "insert into allBatch ("
				+ "allDt,time,"//2017-08-07,12:12:12,
				+ "postCount,visitCount,"
				+ "userCount,puppyCount)"
				+ " values ("
				+ "?,?,"
				+ "(select count(*) from post),"
				+ "(select count(*) from visit),"
				+ "(select count(*) from user),"
				+ "?"
				+ ")";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, now.getSimple_today());
		pstmt.setString(2, now.getTimetoday());
		pstmt.setInt(3, admin.getPuppyCount());
		int result = 0;
		result = pstmt.executeUpdate();
		System.out.println("dailybatch 테이블의 insert결과값 : " + result);
	}
	
	/**
	 * monthlyBatch테이블에 insert
	 * 저번달 기록을 저장
	 * 20일에 실행됨
	 * @param admin : admin객체 dto
	 * @param now : Time_now 객체 dto
	 * @return result : insert결과 1 이나오면 행입력된거임
	 */
	public void insertMonthly(Time_now now, Admin admin) throws SQLException {
		System.out.println("3. AdminDao insertMonthly(Admin admin)");
		Connection con = DBUtil.getConnection();
		String sql = "insert into monthlyBatch ("
				+ "monthlyDt,time,"//2017-08-01,12:12:12,
				+ "postCount,visitCount,"
				+ "userCount,puppyCount)"
				+ " values ("
				+ "?,?,"
				+ "(select sum(postCount) from dailyBatch where dailyDt like ?),"//2017-08%
				+ "(select sum(visitCount) from dailyBatch where dailyDt like ?),"
				+ "(select sum(userCount) from dailyBatch where dailyDt like ?),"
				+ "(select sum(puppyCount) from dailyBatch where dailyDt like ?)"
				+ ")";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, now.getBefore1Month_start());
		pstmt.setString(2, now.getTimetoday());
		pstmt.setString(3, now.getBefore1Month_start().substring(0,7) + "%");
		pstmt.setString(4, now.getBefore1Month_start().substring(0,7) + "%");
		pstmt.setString(5, now.getBefore1Month_start().substring(0,7) + "%");
		pstmt.setString(6, now.getBefore1Month_start().substring(0,7) + "%");
		int result = 0;
		result = pstmt.executeUpdate();
		System.out.println("monthlyBatch 테이블의 insert결과값 : " + result);
	}	
}
