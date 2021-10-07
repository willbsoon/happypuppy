package team.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import team.project.util.HiveDBUtil;

public class HiveDao {
	private static HiveDao dao = new HiveDao();

	private HiveDao() {
	}

	public static HiveDao getInstance() {
		return dao;
	}

	public int hiveTest_showtables() {
		System.out.println("3. HiveDao hiveTest_showtables()");
		Connection con;
		PreparedStatement pstmt;
		String sql = "show tables";
		ResultSet rs = null;

		try {
			con = HiveDBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			;
			while (rs.next()) {
				System.out.println("table : " + rs.getString(1));
			}
			HiveDBUtil.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public int hiveTest_showDB() {
		System.out.println("3. HiveDao hiveTest_showDB()");
		Connection con;
		PreparedStatement pstmt;
		String sql = "show databases";
		ResultSet rs = null;

		try {
			con = HiveDBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("DB : " + rs.getString(1));
			}
			HiveDBUtil.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * hive로 6개월 상태 값 받아옴 => admintable에 올리기
	 * 
	 * @param HashMap<String,Integer>
	 *            processState
	 * @return HashMap<String,Integer> processState
	 */
	public HashMap<String, Integer> hive_select(HashMap<String, Integer> processState) {
		System.out.println("3. HiveDao hive_select()");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select distinct desertionNo,processState from forproject.month6csv2";

		int result_dead = 0;// 종료(자연사)
		int result_kill = 0;// 종료(안락사)
		int result_return = 0;// 종료(반환)
		int result_getted = 0;// 종료(입양)
		int result_live = 0;// 보호중
		try {
			con = HiveDBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("processState"));
				if (rs.getString("processState").equals("종료(입양)")) {
					result_getted++;
				}
				if (rs.getString("processState").equals("종료(안락사)")) {
					result_kill++;
				}
				if (rs.getString("processState").equals("종료(자연사)")) {
					result_dead++;
				}
				if (rs.getString("processState").equals("종료(반환)")) {
					result_return++;
				}
				if (rs.getString("processState").equals("보호중")) {
					result_live++;
				}
			}
			HiveDBUtil.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		processState.put("result_getted", result_getted);
		processState.put("result_kill", result_kill);
		processState.put("result_dead", result_dead);
		processState.put("result_return", result_return);
		processState.put("result_live", result_live);
		return processState;
	}

	// dailycsv,monthlycsv
	public void hive_create() {
		System.out.println("3. HiveDao hive_create()");
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "create external table forproject.month3csv" + "(resultCode String," + "resultMsg String,"
				+ "age String," + "careAddr String," + "careNm String," + "careTel String," + "chargeNm String,"
				+ "colorCd String," + "desertionNo String," + "filename String," + "happenDt String,"
				+ "happenPlace String," + "kindCd String," + "neuterYn String," + "noticeEdt String,"
				+ "noticeNo String," + "noticeSdt String," + "officetel String," + "orgNm String," + "popfile String,"
				+ "processState String," + "sexCd String," + "specialmark String," + "weight String,"
				+ "numOfRows String," + "pageNo String," + "totalCount String) " + "row format delimited "
				+ "fields terminated by '|' " + "lines terminated by '\n' " + "stored as textfile";

		try {
			con = HiveDBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("테이블 만듬");
			HiveDBUtil.close(con, pstmt);
		} catch (SQLException e) {
			System.out.println("SQLException에러 : hive_create()");
			e.printStackTrace();
		}
	}

	public void hive_loaddata_month6csv() {
		System.out.println("3. HiveDao hive_loaddata_month6csv()");
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "load data inpath '/project/csv/daily/month' into table forproject.month6csv2";
		try {
			con = HiveDBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("month6csv 테이블에 데이터로드됨");
			HiveDBUtil.close(con, pstmt);
		} catch (SQLException e) {
			System.out.println("SQLException에러 : hive_loaddata_month6csv");
			e.printStackTrace();
		}
	}

	public void hive_loaddata_traincsv() {
		System.out.println("3. HiveDao hive_loaddata_traincsv()");
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "load data inpath '/project/csv/daily/train' into table forproject.traincsv";
		try {
			con = HiveDBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("traincsv 테이블에 데이터로드됨");
			HiveDBUtil.close(con, pstmt);
		} catch (SQLException e) {
			System.out.println("SQLException에러 : hive_loaddata_traincsv");
			e.printStackTrace();
		}
	}

	public void hive_loaddata_month3csv() {
		System.out.println("3. HiveDao hive_loaddata_month3csv()");
		Connection con;
		PreparedStatement pstmt;
		String sql = "load data inpath '/project/csv/daily/month3' into table forproject.month3csv";
		try {
			con = HiveDBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("month3csv 테이블에 데이터로드됨");
			HiveDBUtil.close(con, pstmt);
		} catch (SQLException e) {
			System.out.println("SQLException에러 : hive_loaddata_month3csv");
			e.printStackTrace();
		}
	}

	public void hive_loaddata_monthlycsv() {
		System.out.println("3. HiveDao hive_loaddata_monthlycsv()");
		Connection con;
		PreparedStatement pstmt;
		String sql = "load data inpath '/project/csv/monthly' into table forproject.monthlycsv2";
		try {
			con = HiveDBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("monthlycsv 테이블에 데이터로드됨");
			HiveDBUtil.close(con, pstmt);
		} catch (SQLException e) {
			System.out.println("SQLException에러 : hive_loaddata_monthlycsv");
			e.printStackTrace();
		}
	}

	public void hive_loaddata_test() {
		System.out.println("3. HiveDao hive_loaddata_test()");
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "load data inpath '/project/csv/test/sec_1' into table forproject.test";
		try {
			con = HiveDBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("test 테이블에 데이터로드됨");
			HiveDBUtil.close(con, pstmt);
		} catch (SQLException e) {
			System.out.println("SQLException에러 : hive_loaddata_test");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void hive_loaddata_test2() {
		System.out.println("3. HiveDao hive_loaddata_test2()");
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "load data inpath '/project/csv/test/sec_2' into table forproject.test";
		try {
			con = HiveDBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("test 테이블에 데이터로드됨");
			HiveDBUtil.close(con, pstmt);
		} catch (SQLException e) {
			System.out.println("SQLException에러 : hive_loaddata_test2");
			e.printStackTrace();
		}
	}

}
