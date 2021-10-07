package team.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Calendar;

import team.project.dto.Post;
import team.project.dto.User;
import team.project.util.DBUtil;


//유저 DAO
public class UserDao {
	private static UserDao dao = new UserDao();

	private UserDao() {
	}

	public static UserDao getInstance() {
		return dao;
	}

	Calendar cal = Calendar.getInstance();
	String dateString = String.format("%04d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
			cal.get(Calendar.DAY_OF_MONTH));

	// 1.유저 추가 메소드
	public int insert(User user) throws SQLException {
		System.out.println("3. UserDao insert()");

		Connection con = DBUtil.getConnection();
		String sql = "insert into user values (?,?,?,?,?,?,null,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUserId());
		pstmt.setString(2, user.getEmail());
		pstmt.setString(3, user.getPassword());
		pstmt.setString(4, user.getName());
		pstmt.setString(5, user.getGender());
		pstmt.setString(6, user.getBorn());
		pstmt.setString(7, dateString);

		int result = pstmt.executeUpdate();

		DBUtil.close(con, pstmt);

		return result;
	}

	// 2. 유저 로그인 메소드
	public User login(User user) throws SQLException {
		System.out.println("3. UserDao login()");
		Connection con = DBUtil.getConnection();
		String sql = "select born,email,gender,name,password,regDt,userId from user where email=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getEmail());
		pstmt.setString(2, user.getPassword());

		ResultSet rs = null;
		rs = pstmt.executeQuery();
		String result = "";
		if (rs.next()) {// �����Ͱ� ������
			if (rs.getString("email").equals(user.getEmail())) {
				if (rs.getString("password").equals(user.getPassword())) {
					User loginedUser = new User();
					loginedUser.setBorn(rs.getString("born"));
					loginedUser.setEmail(rs.getString("email"));
					loginedUser.setGender(rs.getString("gender"));
					loginedUser.setName(rs.getString("name"));
					loginedUser.setPassword(rs.getString("password"));
					loginedUser.setRegDt(rs.getString("regDt"));
					loginedUser.setUserId(rs.getString("userId"));

					DBUtil.close(con, pstmt, rs);
					return loginedUser;// ���̵��� �� ������� ����
				} else
					DBUtil.close(con, pstmt, rs);
				return null;// ����� Ʋ����
			} else
				DBUtil.close(con, pstmt, rs);
			return null;//
		} else
			DBUtil.close(con, pstmt, rs);
		return null;
	}

	// 3. mySql에서 id비교하는 메소드
	public int idCheck(String email) {
		System.out.println("3. UserDao IdCheck()");
		Connection con;
		PreparedStatement pstmt;
		String sql = "select * from user where email=?";
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			// System.out.println(rs.getFetchSize());
			if (rs.next()) {// ���Ұ�
				DBUtil.close(con, pstmt, rs);
				return 0;
			} else
				DBUtil.close(con, pstmt, rs);
			return 1; // ��밡��.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;

	}
	// 유저정보 수정 메소드
	public synchronized int userUpdate(User user) throws SQLException {
		System.out.println("3. UserDao userUpdate()");
		Connection con;
		PreparedStatement pstmt;
		String sql = "UPDATE user set born=?, password=? WHERE userId=?";

		ResultSet rs = null;
		con = DBUtil.getConnection();

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getBorn());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getUserId());

		int result = pstmt.executeUpdate();

		DBUtil.close(con, pstmt);

		return result;
	}

	// 유저 삭제 메소드
	public synchronized int userDelete(User user) throws SQLException {
		System.out.println("3. UserDao userUpdate()");
		Connection con =  DBUtil.getConnection();
		PreparedStatement pstmt;
		ResultSet rs = null;
		String sql = "SELECT * FROM user WHERE userId=?";
		User savedUser = new User();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				savedUser.setUserId(rs.getString("userId"));
				savedUser.setEmail(rs.getString("email"));
				savedUser.setPassword(rs.getString("password"));
				savedUser.setBorn(rs.getString("born"));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("savedUser : " + savedUser);
		System.out.println("user : " + user);
		
		int result;
		if (savedUser.getUserId().equals(user.getUserId()) 
				&& savedUser.getEmail().equals(user.getEmail())
				&& savedUser.getPassword().equals(user.getPassword())
				&& savedUser.getBorn().equals(user.getBorn())) 
		{
			String sql2 = "DELETE FROM user WHERE userId = ?";
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, user.getUserId());
			result = pstmt.executeUpdate();
			DBUtil.close(con, pstmt);
		} else {
			result = 0;
		}

		return result;
	}

}
