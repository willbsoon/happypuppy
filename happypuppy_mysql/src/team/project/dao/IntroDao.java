package team.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import team.project.dto.Time_now;
import team.project.util.DBUtil;

public class IntroDao {
	// 싱글턴패턴
	private static IntroDao dao = new IntroDao();
	private IntroDao() {
	}
	public static IntroDao getInstance() {
		return dao;
	}
	/**
	 * userIntroduce테이블에 insert
	 */
	public void insertCount(Time_now now, ArrayList arrdata) throws SQLException {
		System.out.println("3. AdminDao insertCount(now,data)");
		Connection con = DBUtil.getConnection();
		String sql = "insert into userIntroduce ("
				+ "insertDt,insertTime,"//2017-08-01,12:12:12,
				+ "seoul,seoul_kill,"
				+ "busan,busan_kill,"
				+ "daegu,daegu_kill,"
				+ "incheon,incheon_kill,"//10
				+ "gwangjoo,gwangjoo_kill,"
				+ "sejong,sejong_kill,"
				+ "daejeon,daejeon_kill,"
				+ "ulsan,ulsan_kill,"
				+ "gyeonggido,gyeonggido_kill,"//20
				+ "gangwondo,gangwondo_kill,"
				+ "chungcheongbukdo,chungcheongbukdo_kill,"
				+ "chungcheongnamdo,chungcheongnamdo_kill,"
				+ "jeollabukdo,jeollabukdo_kill,"
				+ "jeollanamdo,jeollanamdo_kill,"//30
				+ "gyeongsangbukdo,gyeongsangbukdo_kill,"
				+ "gyeongsangnamdo,gyeongsangnamdo_kill,"
				+ "jeju,jeju_kill"//36
				+ ")"
				+ " values ("
				+ "?,?,"
				+ "?,?,"
				+ "?,?,"
				+ "?,?,"
				+ "?,?,"//10
				+ "?,?,"
				+ "?,?,"
				+ "?,?,"
				+ "?,?,"
				+ "?,?,"//20
				+ "?,?,"
				+ "?,?,"
				+ "?,?,"
				+ "?,?,"
				+ "?,?,"//30
				+ "?,?,"
				+ "?,?,"
				+ "?,?"//36
				+ ")";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, now.getSimple_before());
		pstmt.setString(2, now.getTimetoday());
		pstmt.setInt(3, (int)arrdata.get(0));
		pstmt.setInt(4, (int)arrdata.get(1));
		pstmt.setInt(5, (int)arrdata.get(2));
		pstmt.setInt(6, (int)arrdata.get(3));
		pstmt.setInt(7, (int)arrdata.get(4));
		pstmt.setInt(8, (int)arrdata.get(5));
		pstmt.setInt(9, (int)arrdata.get(6));
		pstmt.setInt(10, (int)arrdata.get(7));
		pstmt.setInt(11, (int)arrdata.get(8));
		pstmt.setInt(12, (int)arrdata.get(9));
		pstmt.setInt(13, (int)arrdata.get(10));
		pstmt.setInt(14, (int)arrdata.get(11));
		pstmt.setInt(15, (int)arrdata.get(12));
		pstmt.setInt(16, (int)arrdata.get(13));
		pstmt.setInt(17, (int)arrdata.get(14));
		pstmt.setInt(18, (int)arrdata.get(15));
		pstmt.setInt(19, (int)arrdata.get(16));
		pstmt.setInt(20, (int)arrdata.get(17));
		pstmt.setInt(21, (int)arrdata.get(18));
		pstmt.setInt(22, (int)arrdata.get(19));
		pstmt.setInt(23, (int)arrdata.get(20));
		pstmt.setInt(24, (int)arrdata.get(21));
		pstmt.setInt(25, (int)arrdata.get(22));
		pstmt.setInt(26, (int)arrdata.get(23));
		pstmt.setInt(27, (int)arrdata.get(24));
		pstmt.setInt(28, (int)arrdata.get(25));
		pstmt.setInt(29, (int)arrdata.get(26));
		pstmt.setInt(30, (int)arrdata.get(27));
		pstmt.setInt(31, (int)arrdata.get(28));
		pstmt.setInt(32, (int)arrdata.get(29));
		pstmt.setInt(33, (int)arrdata.get(30));
		pstmt.setInt(34, (int)arrdata.get(31));
		pstmt.setInt(35, (int)arrdata.get(32));
		pstmt.setInt(36, (int)arrdata.get(33));
		
		/*
		for(int i=0;i<arrdata.size();i++) {
			int j = 0;
			j = i + 3;
			pstmt.setInt(j, (int)arrdata.get(i));
		}
		 */
		int result = 0;
		result = pstmt.executeUpdate();
		System.out.println("userIntroduce 테이블의 insert결과값 : " + result);
	}	
}
