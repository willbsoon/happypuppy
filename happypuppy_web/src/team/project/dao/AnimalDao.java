package team.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;

import team.project.dto.Animal;
import team.project.dto.Time_now;
import team.project.util.DBUtil;

public class AnimalDao {
	private static AnimalDao animalDao = new AnimalDao();

	private AnimalDao() {
	}

	public static AnimalDao getInstance() {
		return animalDao;
	}

	/**
	 * ===목록=== 위험한 동물들을 위험도로 sort해서 가져옴
	 */

	/**
	 * selectDanger 위험한 동물들을 위험도로 sort해서 가져옴
	 * 
	 * @param String
	 *            upkind : 동물종류
	 * @param String
	 *            selector : 선택자 : all 이면 모든 동물
	 *            dog이면 강아지, cat이면 고양이,
	 *            etc면 그외동물
	 * @return ArrayList<Animal> animalList : 위험도 동물 리스트
	 */
	public ArrayList<Animal> selectDanger() throws SQLException {
		System.out.println("3. AnimalDao selectDanger()");
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "select * from animal where processState = '보호중' ORDER BY killed DESC";// kill컬럼이 높은
																// 순서대로 select해옴
		ResultSet rs = null;
		int result = 0;
		ArrayList<Animal> animalList = new ArrayList<Animal>();
		try {
			pstmt = con.prepareStatement(sql);
			// pstmt.setString(1, now.getSimple_today());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Animal animal = new Animal();
				animal.setAge(rs.getString("age"));
				animal.setCareAddr(rs.getString("careAddr"));
				animal.setCareNm(rs.getString("careNm"));
				animal.setCareTel(rs.getString("careTel"));
				animal.setChargeNm(rs.getString("chargeNm"));
				animal.setColorCd(rs.getString("colorCd"));
				animal.setDesertionNo(rs.getString("desertionNo"));
				animal.setFilename(rs.getString("filename"));
				animal.setHappenDt(rs.getString("happenDt"));
				animal.setHappenPlace(rs.getString("happenPlace"));
				animal.setKindCd(rs.getString("kindCd"));
				animal.setNeuterYn(rs.getString("neuterYn"));
				animal.setNoticeEdt(rs.getString("noticeEdt"));
				animal.setNoticeNo(rs.getString("noticeNo"));
				animal.setNoticeSdt(rs.getString("noticeSdt"));
				animal.setOfficetel(rs.getString("officetel"));
				animal.setOrgNm(rs.getString("orgNm"));
				animal.setPopfile(rs.getString("popfile"));
				animal.setProcessState(rs.getString("processState"));
				animal.setSexCd(rs.getString("sexCd"));
				animal.setSpecialMark(rs.getString("specialMark"));
				animal.setWeight(rs.getString("weight"));
				animal.setDeath(rs.getFloat("death"));
				animal.setKilled(rs.getFloat("killed"));
				animal.setBring(rs.getFloat("bring"));
				animal.setReturned(rs.getFloat("returned"));
				animalList.add(animal);
			}
			DBUtil.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.close(con, pstmt, rs);
		return animalList;
	}
	
	
	public ArrayList selectIntroduce(Time_now now) throws SQLException {
		System.out.println("3. AnimalDao selectIntroduce()");
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ArrayList introdata = new ArrayList();
		String sql = "select * from userIntroduce where insertDt = ?";// insert한 날짜로 찾음
		ResultSet rs = null;
		int result = 0;
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		SimpleDateFormat today_f = new SimpleDateFormat("yyyy-MM-dd");//하이픈 있는거
		calendar.add(Calendar.DAY_OF_YEAR, -2);// 이틀전 날짜
		String simple_day2before = today_f.format(calendar.getTime());//yyyy-MM-dd
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, simple_day2before);
			System.out.println("이틀전날짜 : " + simple_day2before);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				introdata.add(rs.getInt("busan"));
				introdata.add(rs.getInt("busan_kill"));
				introdata.add( rs.getInt("chungcheongbukdo") + (rs.getInt("sejong")/2));
				introdata.add( rs.getInt("chungcheongbukdo_kill")+ (rs.getInt("sejong_kill")/2));
				introdata.add(rs.getInt("chungcheongnamdo")+ (rs.getInt("sejong")/2));
				introdata.add(rs.getInt("chungcheongnamdo_kill")+ (rs.getInt("sejong_kill")/2));
				introdata.add(rs.getInt("daegu"));
				introdata.add(rs.getInt("daegu_kill"));
				introdata.add(rs.getInt("daejeon"));
				introdata.add(rs.getInt("daejeon_kill"));
				introdata.add(rs.getInt("gangwondo"));
				introdata.add( rs.getInt("gangwondo_kill"));
				introdata.add( rs.getInt("gwangjoo"));
				introdata.add(rs.getInt("gwangjoo_kill"));
				introdata.add(rs.getInt("gyeonggido"));
				introdata.add(rs.getInt("gyeonggido_kill"));
				introdata.add(rs.getInt("gyeongsangbukdo"));
				introdata.add( rs.getInt("gyeongsangbukdo_kill"));
				introdata.add(rs.getInt("gyeongsangnamdo"));
				introdata.add(rs.getInt("gyeongsangnamdo_kill"));
				introdata.add(rs.getInt("incheon"));
				introdata.add(rs.getInt("incheon_kill"));	
				introdata.add(rs.getInt("jeju"));
				introdata.add(rs.getInt("jeju_kill"));
				introdata.add(rs.getInt("jeollabukdo"));
				introdata.add(rs.getInt("jeollabukdo_kill"));
				introdata.add(rs.getInt("jeollanamdo"));
				introdata.add(rs.getInt("jeollanamdo_kill"));
				introdata.add(rs.getInt("seoul"));
				introdata.add(rs.getInt("seoul_kill"));
				introdata.add(rs.getInt("ulsan"));
				introdata.add(rs.getInt("ulsan_kill"));
				/*
				introdata.add(rs.getInt("sejong"));
				introdata.add(rs.getInt("sejong_kill"));
				*/
			}
			DBUtil.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.close(con, pstmt, rs);
		return introdata;
	}
	
	
	
}
