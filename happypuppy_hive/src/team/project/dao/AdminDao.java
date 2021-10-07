package team.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
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
	 * insertAdminPie
	 * adminPie테이블에 insert
	 * @param HashMap<String,Integer> processState
	 * @param now : Time_now 객체 dto
	 * @return result : insert결과 1 이나오면 행입력된거임
	 */
	public void insertAdminPie(Time_now now, HashMap<String,Integer> processState) throws SQLException {
		System.out.println("3. AdminDao insertAdmin(now, processState)");
		Connection con = DBUtil.getConnection();
		//종료(자연사)
		//종료(안락사)
		//종료(반환)
		//종료(입양)
		//보호중
		String sql = "insert into adminPie ("
				+ "dailyDt,time,"//2017-08-07,12:12:12,
				+ "result_dead,result_kill,"
				+ "result_return,result_getted,"
				+ "result_live)"
				+ " values ("
				+ "?,?,"
				+ "?,?,"
				+ "?,?,"
				+ "?"
				+ ")";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, now.getSimple_today());
		pstmt.setString(2, now.getTimetoday());
		pstmt.setLong(3, processState.get("result_dead"));
		pstmt.setLong(4, processState.get("result_kill"));
		pstmt.setLong(5, processState.get("result_return"));
		pstmt.setLong(6, processState.get("result_getted"));
		pstmt.setLong(7, processState.get("result_live"));
		int result = 0;
		result = pstmt.executeUpdate();
		try {
			wait(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("insertAdminPie 테이블의 insert결과값 : " + result);
	}
	
}
