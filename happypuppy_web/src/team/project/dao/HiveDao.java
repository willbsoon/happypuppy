package team.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import team.project.dto.User;
import team.project.util.DBUtil;
import team.project.util.HiveDBUtil;



public class HiveDao {
	private static HiveDao dao = new HiveDao();
	
	private HiveDao() {}
	public static HiveDao getInstance() {
		return dao;
	}
	Calendar cal = Calendar.getInstance();
    String dateString = String.format("%04d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));

		public int hiveTest(String string) {
			System.out.println("3. UserDao IdCheck()");
			Connection con;
			PreparedStatement pstmt;
			String sql = "show tables";
			ResultSet rs = null;
					
			try {
				con = HiveDBUtil.getConnection();
				pstmt = con.prepareStatement(sql);

				rs = pstmt.executeQuery();
				//System.out.println(rs.getFetchSize());
				if(rs.next()) {
					System.out.println("있따"+rs.getString(1));
					HiveDBUtil.close(con, pstmt, rs);
					return 0;
				}else HiveDBUtil.close(con, pstmt, rs);return 1; 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;

		}
		
}
