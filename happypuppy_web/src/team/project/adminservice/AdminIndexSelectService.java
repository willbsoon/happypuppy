package team.project.adminservice;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import team.project.dao.AdminDao;
import team.project.dto.Admin;
import team.project.dto.Recommend;
import team.project.dto.Time_now;
import team.project.service.NoticePasing;

public class AdminIndexSelectService {
	// 2.1 오늘의 방문자,가입자,유기동물수,게시글수
	/**
	 * Admin selectTodayData() AdminDao selectUser selectPost selectVisit api 파싱
	 * 
	 * @param Time_now
	 *            now
	 * @param Admin
	 *            admin
	 * @return Admin admin : todayPost, todayVisit, todayPuppy, todayUser
	 */
	public Admin selectTodayData(Time_now now, Admin admin) {
		System.out.println("2.1 AdminIndexSelectService selectTodayData()");
		// 2.1.1 오늘 등록된 동물수 api로부터 받아오고 admin객체에 set함
		System.out.println("2.1.1 오늘의 유기동물수 가져옴");
		Recommend rec = new Recommend();
		rec.setBgnde(now.getSimpletoday());// 20170812
		rec.setEndde(now.getSimpletoday());
		rec.setUpkind("");
		rec.setUpr_cd("");
		rec.setNumOfRows("10000");
		JSONObject data = null;
		NoticePasing notice = new NoticePasing();
		try {
			data = notice.pasing(rec);
			JSONObject data1 = (JSONObject) data.get("response");
			JSONObject data2 = (JSONObject) data1.get("body");
			int todayPuppy = 0;
			todayPuppy = (int) data2.get("totalCount");
			admin.setTodayPuppy(todayPuppy);
			System.out.println(now.getSimple_today()+ " 의 유기동물수 : " + admin.getTodayPuppy());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 2.1.2 오늘 가입자수 select하여 가져옴 AdminDao selectUser
		System.out.println("2.1.2 오늘의 가입자수 가져옴");
		try {
			int todayUser = 0;
			todayUser = AdminDao.getInstance().selectUser(now);
			admin.setTodayUser(todayUser);
			System.out.println("오늘의 가입자수 : " + admin.getTodayUser());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 2.1.3 오늘 게시글수 select하여 가져옴 AdminDao selectPost
		System.out.println("2.1.3 오늘의 게시글수 가져옴");
		int todayPost = 0;
		try {
			todayPost = AdminDao.getInstance().selectPost(now);
			admin.setTodayPost(todayPost);
			System.out.println("오늘의 게시글수 : " + admin.getTodayPost());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 2.1.4 오늘 방문자수 select하여 가져옴 AdminDao selectVisit
		System.out.println("2.1.4 오늘의 방문자수 가져옴");
		int todayVisit = 0;
		todayVisit = AdminDao.getInstance().selectVisit(now);
		admin.setTodayVisit(todayVisit);
		System.out.println("오늘의 방문자수 : " + admin.getTodayVisit());

		return admin;
	}

	// 2.2 매달 방문자,가입자,유기동물수,게시글수
	/**
	 * Admin selectMonthData() AdminDao selectMonthly selectDaily
	 * 
	 * @param Time_now
	 *            now
	 * @param Admin
	 *            admin
	 * @return Admin admin : month1Post, month1Visit, month1Puppy, month1User
	 */
	public Admin selectMonthData(Time_now now, Admin admin) {
		System.out.println("2.2 AdminIndexSelectService selectMonthData()");

		// 2.2.1 5개월전부터 한달씩의 데이터 select하여 가져옴 AdminDao selectMonthly
		System.out.println("2.2.1 5개월전부터 한달씩 데이터 가져옴");
		Admin admin_get = AdminDao.getInstance().selectMonthly(now.getBefore1Month_start(), admin);
		if(admin_get!=null) {
			admin.setMonth1Post(admin_get.getMonthPost());
			admin.setMonth1User(admin_get.getMonthUser());
			admin.setMonth1Puppy(admin_get.getMonthPuppy());
			admin.setMonth1Visit(admin_get.getMonthVisit());
			System.out.println("1개월전 데이터 가져옴");
		}
		else {
			admin.setMonth1Post(0);
			admin.setMonth1User(0);
			admin.setMonth1Puppy(0);
			admin.setMonth1Visit(0);
			System.out.println("1개월전 데이터 없음");
		}
		
		admin_get = AdminDao.getInstance().selectMonthly(now.getBefore2Month_start(), admin);
		if(admin_get!=null) {
			admin.setMonth2Post(admin_get.getMonthPost());
			admin.setMonth2User(admin_get.getMonthUser());
			admin.setMonth2Puppy(admin_get.getMonthPuppy());
			admin.setMonth2Visit(admin_get.getMonthVisit());
			System.out.println("2개월전 데이터 가져옴");
		}
		else {
			admin.setMonth2Post(0);
			admin.setMonth2User(0);
			admin.setMonth2Puppy(0);
			admin.setMonth2Visit(0);
			System.out.println("2개월전 데이터 없음");
		}
		
		admin_get = AdminDao.getInstance().selectMonthly(now.getBefore3Month_start(), admin);
		if(admin_get!=null) {
			admin.setMonth3Post(admin_get.getMonthPost());
			admin.setMonth3User(admin_get.getMonthUser());
			admin.setMonth3Puppy(admin_get.getMonthPuppy());
			admin.setMonth3Visit(admin_get.getMonthVisit());
			System.out.println("3개월전 데이터 가져옴");
		}
		else {
			admin.setMonth3Post(0);
			admin.setMonth3User(0);
			admin.setMonth3Puppy(0);
			admin.setMonth3Visit(0);
			System.out.println("3개월전 데이터 없음");
		}
		
		admin_get = AdminDao.getInstance().selectMonthly(now.getBefore4Month_start(), admin);
		if(admin_get!=null) {
			admin.setMonth4Post(admin_get.getMonthPost());
			admin.setMonth4User(admin_get.getMonthUser());
			admin.setMonth4Puppy(admin_get.getMonthPuppy());
			admin.setMonth4Visit(admin_get.getMonthVisit());
			System.out.println("4개월전 데이터 가져옴");
		}
		else {
			admin.setMonth4Post(0);
			admin.setMonth4User(0);
			admin.setMonth4Puppy(0);
			admin.setMonth4Visit(0);
			System.out.println("4개월전 데이터 없음");
		}
		
		admin_get = AdminDao.getInstance().selectMonthly(now.getBefore5Month_start(), admin);
		if(admin_get!=null) {
			admin.setMonth5Post(admin_get.getMonthPost());
			admin.setMonth5User(admin_get.getMonthUser());
			admin.setMonth5Puppy(admin_get.getMonthPuppy());
			admin.setMonth5Visit(admin_get.getMonthVisit());
			System.out.println("5개월전 데이터 가져옴");
		}
		else {
			admin.setMonth5Post(0);
			admin.setMonth5User(0);
			admin.setMonth5Puppy(0);
			admin.setMonth5Visit(0);
			System.out.println("5개월전 데이터 없음");
		}

		// 2.2.2 1일부터 어제까지의 한달간의 데이터 select하여 가져옴 AdminDao selectDaily
		System.out.println("2.2.2 요번달의 데이터 가져옴");
		admin = AdminDao.getInstance().selectDaily(now, admin);
		
		return admin;
	}

	// 2.3 어제까지의 총 방문자,가입자,유기동물수,게시글수
	/**
	 * Admin selectAllData() AdminDao selectAll
	 * 
	 * @param Time_now
	 *            now
	 * @param Admin
	 *            admin
	 * @return Admin admin : allPost, allVisit, allPuppy, allUser
	 */
	public Admin selectAllData(Time_now now, Admin admin) {
		System.out.println("2.3 AdminIndexSelectService selectAllData()");
		// 2.3.1 어제까지의 총 데이터 select하여 가져옴 AdminDao selectAll
		System.out.println("2.3.1 어제까지의 총 데이터 select하여 가져옴");
		Admin admin_get = AdminDao.getInstance().selectAll(now, admin);
		if(admin_get!=null) {
			admin.setAllPost(admin_get.getAllPost());
			admin.setAllUser(admin_get.getAllUser());
			admin.setAllPuppy(admin_get.getAllPuppy());
			admin.setAllVisit(admin_get.getAllVisit());
		}
		else {
			admin.setAllPost(0);
			admin.setAllUser(0);
			admin.setAllPuppy(0);
			admin.setAllVisit(0);
		}
		return admin;
	}

}
