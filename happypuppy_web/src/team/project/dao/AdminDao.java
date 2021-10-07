package team.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import team.project.dto.Admin;
import team.project.dto.Time_now;
import team.project.dto.User;
import team.project.util.DBUtil;

public class AdminDao {
	private static AdminDao dao = new AdminDao();

	private AdminDao() {
	}

	public static AdminDao getInstance() {
		return dao;
	}

	/**
	 * ===목록=== 
	 * 세션 insertVisit 
	 * 오늘의 ~~ selectVisit selectPost selectUser 
	 * 매달~~ selectMonthly selectDaily 
	 * 어제까지의 총~~ selectAll 
	 * 새로운 자료가 업로드된 시간(미구현) ~~ selectNewUserTime, selectNewVisitTime, selectNewPostTime
	 * ==회원목록==
	 * 회원목록 selectUserAll
	 * 회원삭제 deleteUser
	 * ==관리자페이지 adminindex==
	 * 파이그래프 selectAdminPie
	 */

	// ==세션실행시==============================================
	/**
	 * 총 방문자수를 증가시킨다.
	 * 
	 * @param Time_now
	 *            now : 날짜객체
	 * @return result : 데이터 insert결과
	 */
	// 매개변수로 현재 날짜와 시간을 받아서 visit테이블에 insert 시킬때 같이 포함하여 입력함
	public int insertVisit(Time_now now) throws SQLException {
		System.out.println("3. AdminDao insertVisit()");
		Connection con = null;
		PreparedStatement pstmt = null;
		// 총 방문자수를 증가시키기 위해 테이블에 현재 날짜와 시간을 추가시킨다.
		String sql = "insert into visit(visitDt,visittime) values (?,?)";
		// 커넥션을 가져온다.
		con = DBUtil.getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, now.getSimple_today());
		pstmt.setString(2, now.getTimetoday());
		int result = 0;
		result = pstmt.executeUpdate();
		// Connection, PreparedStatement를 닫는다.
		DBUtil.close(con, pstmt);
		pstmt = null;
		con = null;
		return result;

	}// end setTotalCount()

	// ==오늘의 ~~~~ selectUser selectPost
	// selectVisit==============================================

	/**
	 * selectUser 가입자 테이블에서 오늘 가입자수 가져옴
	 * 
	 * @param Time_now
	 *            now : 날짜객체
	 * @return result : 오늘 가입자수
	 */
	public int selectUser(Time_now now) throws SQLException {
		System.out.println("3. AdminDao selectUser()");
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select count(*) as todayCount from user where regDt=?";
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, now.getSimple_today());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("todayCount");
				System.out.println("오늘의 가입자 수 : " + result);
				DBUtil.close(con, pstmt, rs);
				return result;
			} else
				DBUtil.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * selectPost 오늘 게시글 수를 가져온다.
	 * 
	 * @param simple_today
	 *            : 오늘 날짜
	 * @return result : 오늘 게시글수
	 */
	public int selectPost(Time_now now) throws SQLException {
		System.out.println("3. AdminDao selectPost()");
		Connection con;
		PreparedStatement pstmt;
		String sql = "select count(*) as todayCount from post where writeDt=?";
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, now.getSimple_today());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("todayCount");
				System.out.println("오늘의 게시글 수 : " + result);
				DBUtil.close(con, pstmt, rs);
				return result;
			} else
				DBUtil.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * selectVisit 오늘 방문자 수를 가져온다.
	 * 
	 * @param Time_now
	 *            now : 날짜객체
	 * @return result : 오늘 방문자
	 */
	public int selectVisit(Time_now now) {
		System.out.println("3. AdminDao selectVisit()");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select count(*) as todayCount from visit where visitDt=?";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, now.getSimple_today());
			rs = pstmt.executeQuery();

			// 방문자 수를 result 변수에 담는다.
			if (rs.next()) {
				result = rs.getInt("todayCount");
				System.out.println("오늘의 방문자 수 : " + result);
				DBUtil.close(con, pstmt, rs);
				return result;
			} else
				DBUtil.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	// ==매달~~ selectMonth
	/**
	 * selectDaily : 1일부터 오늘까지의 방문자,가입자,게시글,유기동물 수를 가져온다.
	 * 
	 * @param simple_today
	 *            : 오늘 날짜
	 * @param Admin
	 *            admin : admin DTO
	 * @return admin : 어제까지의 방문자,가입자,게시글,유기동물 수
	 */
	public Admin selectDaily(Time_now now, Admin admin) {
		System.out.println("3. AdminDao selectDaily()");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select sum(postCount),sum(visitCount),sum(userCount),sum(puppyCount) from dailyBatch where dailyDt like ?";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, now.getSimple_before().substring(0, 7) + "%");// 2017-08%
			rs = pstmt.executeQuery();
			// 결과를 admin dto에 담는다.
			if (rs.next()) {
				result = rs.getInt("sum(postCount)");
				admin.setMonthPost(result);
				System.out.println("요번 달의 게시글 수 : " + result);

				result = rs.getInt("sum(visitCount)");
				admin.setMonthVisit(result);
				System.out.println("요번 달의 방문자 수 : " + result);

				result = rs.getInt("sum(userCount)");
				admin.setMonthUser(result);
				System.out.println("요번 달의 가입자 수 : " + result);

				result = rs.getInt("sum(puppyCount)");
				admin.setMonthPuppy(result);
				System.out.println("요번 달의 유기동물 수 : " + result);

				DBUtil.close(con, pstmt, rs);
				return admin;
			} else
				DBUtil.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * selectMonthly : 6개월 전까지의 방문자,가입자,게시글,유기동물 수를 가져온다.
	 * 
	 * @param simple_today
	 *            : 날짜 : 부르는 서비스에서 for문 돌려서 6개월 전까지의 날짜를 가져옴
	 * @param Admin
	 *            admin : admin DTO
	 * @return admin : 한달간의 방문자,가입자,게시글,유기동물 수
	 */
	public Admin selectMonthly(String simple_today, Admin admin) {
		System.out.println("3. AdminDao selectMonthly()");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select postCount,visitCount,userCount,puppyCount from monthlyBatch where monthlyDt like ?";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, simple_today.substring(0, 7) + "%");// 2017-08%
			rs = pstmt.executeQuery();
			// 결과를 admin dto에 담는다.
			if (rs.next()) {
				result = rs.getInt("postCount");
				admin.setMonthPost(result);
				System.out.println(simple_today + " 달의 게시글 수 : " + result);

				result = rs.getInt("visitCount");
				admin.setMonthVisit(result);
				System.out.println(simple_today + " 달의 방문자 수 : " + result);

				result = rs.getInt("userCount");
				admin.setMonthUser(result);
				System.out.println(simple_today + " 달의 가입자 수 : " + result);

				result = rs.getInt("puppyCount");
				admin.setMonthPuppy(result);
				System.out.println(simple_today + " 달의 유기동물 수 : " + result);

				DBUtil.close(con, pstmt, rs);
				return admin;
			} else
				DBUtil.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// ==어제까지의 총~~ selectAll==============================================
	/**
	 * selectAll : 어제까지의 모든 방문자,가입자,게시글,유기동물 수를 가져온다.
	 * 
	 * @param Time_now
	 *            now : 날짜 객체
	 * @param Admin
	 *            admin : admin DTO
	 * @return admin : 어제까지의 모든 방문자,가입자,게시글,유기동물 수
	 */
	public Admin selectAll(Time_now now, Admin admin) {
		System.out.println("3. AdminDao selectAll()");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select postCount,visitCount,userCount,puppyCount from allBatch where allDt=?";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, now.getSimple_before());// 2017-08-23 //어제날짜
			rs = pstmt.executeQuery();
			// 결과를 admin dto에 담는다.
			if (rs.next()) {
				result = rs.getInt("postCount");
				admin.setAllPost(result);
				System.out.println(now.getSimple_before() + "까지 게시글 수 : " + result);

				result = rs.getInt("visitCount");
				admin.setAllVisit(result);
				System.out.println(now.getSimple_before() + "까지 방문자 수 : " + result);

				result = rs.getInt("userCount");
				admin.setAllUser(result);
				System.out.println(now.getSimple_before() + "까지 가입자 수 : " + result);

				result = rs.getInt("puppyCount");
				admin.setAllPuppy(result);
				System.out.println(now.getSimple_before() + "까지 유기동물 수 : " + result);

				DBUtil.close(con, pstmt, rs);
				return admin;
			} else
				DBUtil.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	// ==새로운 자료가 업로드된 시간~~selectNewUserTime,selectNewVisitTime,selectNewPostTime==============================================
	//미구현~~
	/**
	 * selectNewUserTime 가입자 테이블에서 최신 가입자의 시간 가져옴
	 * 
	 * @param Time_now
	 *            now : 날짜객체
	 * @return int result : 최신 가입자의 시간
	 */
	public int selectNewUserTime(Time_now now) throws SQLException {
		System.out.println("3. AdminDao selectNewUserTime()");
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select count(regDt) from user where regDt=?";
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, now.getSimple_today());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count(regDt)");
				System.out.println("오늘의 가입자 수 : " + result);
				DBUtil.close(con, pstmt, rs);
				return result;
			} else
				DBUtil.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	// ==회원목록 selectUserAll==============================================
	/**
	 * selectUserAll : 모든 가입자 정보를 최신순으로 정렬하여 가져온다.
	 * 
	 * @return ArrayList<User> : 가입자 dto
	 */
	public ArrayList<User> selectUserAll() {
		System.out.println("3. AdminDao selectUserAll()");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> list_user = new ArrayList<User>();
		String sql = "select userId,email,password,name,gender,born,profile,regDt from user order by regDt desc";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUserId(rs.getString("userId"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setGender(rs.getString("gender"));
				user.setBorn(rs.getString("born"));
				//user.setProfile(rs.getString("profile"));
				user.setRegDt(rs.getString("regDt"));
				list_user.add(user);
			}
			DBUtil.close(con, pstmt, rs);
			return list_user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ==회원삭제 deleteUser==============================================
	/**
	 * deleteUser : 선택하는 가입자 정보를 삭제
	 * 
	 * @param email
	 * @return int : 지워졌으면 1 아니면 0
	 */
	public int deleteUser(String email) {
		System.out.println("3. AdminDao deleteUser()");
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("deleteUser : "+email);
		String sql = "delete from user where email = ?";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);// email
			int result = 0;
			result = pstmt.executeUpdate();
			DBUtil.close(con, pstmt);
			System.out.println(result + " 명이 지워짐");
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	// ==관리자페이지 파이그래프 selectAdminPie==============================================
		/**
		 * selectAdminPie : 6개월간의 상태를 가져옴.
		 * @param Time_now now : 날짜객체
		 * @return ArrayList<User> : 가입자 dto
		 */
		public HashMap<String,Integer> selectAdminPie(Time_now now) {
			System.out.println("3. AdminDao selectAdminPie()");
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			HashMap<String,Integer> map = new HashMap<String,Integer>();
			String sql = "select * from adminPie where dailyDt = ?";
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, now.getSimple_before());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					map.put("dead",rs.getInt("result_dead"));
					map.put("kill",rs.getInt("result_kill"));
					map.put("returned",rs.getInt("result_return"));
					map.put("getted",rs.getInt("result_getted"));
					map.put("live",rs.getInt("result_live"));					
				}
				else {
					map.put("dead", 500);
					map.put("kill", 4000);
					map.put("returned", 1000);
					map.put("getted", 3000);
					map.put("live", 4256);
				}
				DBUtil.close(con, pstmt, rs);
				return map;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return map;
		}
}
