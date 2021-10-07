package team.project.mains;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import team.project.dao.AdminDao;
import team.project.dao.HiveDao;
import team.project.dto.Recommend;
import team.project.dto.Time_now;
import team.project.service.ApiToCsvFileService;
import team.project.service.DateToday;

public class mains {
	public static void main(String[] args) throws IOException, ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		System.out.println("==main문 실행============================================");
		// 오늘 날짜와 시간 받아옴
		Time_now now = new DateToday().getDate();
		System.out.println("시작날짜 : " + now.getTimetoday());
		System.out.println("시작시간 : " + now.getTimetoday());
		
		// HIVE=================================================================================
		// HiveDao.getInstance().hiveTest_showtables();
		// HiveDao.getInstance().hiveTest_showDB();
		// HIVE 테이블 만들기
		// HiveDao.getInstance().hive_create();
		// HIVE
		// 끝=================================================================================

		System.out.println("==API시작======");
		// API시작=============================================================================
		// 1. api실행 및 csv파일로 저장하는 부분(서울, 강아지만
		// 가능하게함)==========================================
		ApiToCsvFileService api = new ApiToCsvFileService();
		// 요청 변수 선언 및 설정
		Recommend rec = new Recommend();
		String upkind = "417000";// 동물종류// 강아지
		String upr_cd = "6110000";// 도시번호// 서울
		String bgnde = null;
		String endde = null;
/*
		// 1.1 api daily 파싱 forproject.traincsv
		System.out.println("1.1 api daily 파싱 - 3개월전");
		bgnde = now.getBefore3Month();
		endde = now.getBefore3Month();
		rec.setBgnde(bgnde);
		rec.setEndde(endde);
		rec.setUpkind(upkind);
		rec.setUpr_cd(upr_cd);
		api.pasing(rec,0);
		
		// 1.2 api daily 파싱 forproject.month3csv
		System.out.println("1.2 api daily 파싱 - 2주전");
		Recommend rec2 = new Recommend();
		bgnde = now.getBefore2weak();
		endde = now.getBefore2weak();
		rec2.setBgnde(bgnde);
		rec2.setEndde(endde);
		rec2.setUpkind(upkind);
		rec2.setUpr_cd(upr_cd);
		api.pasing(rec2,1);

		// 1.3 api daily파싱 -6개월치
		System.out.println("1.3 api daily 파싱 -6개월치");
		Recommend rec3 = new Recommend();
		bgnde = now.getBefore6Month_start();
		endde = now.getSimpletoday();
		rec3.setBgnde(bgnde);
		rec3.setEndde(endde);
		rec3.setUpkind(upkind);
		rec3.setUpr_cd(upr_cd);
		api.pasing(rec3, 2);

		// 1.4 api monthly 파싱 (세달전1일 ~ 세달전 마지막일) //20일에 실행 if
		if (now.getDay().equals("20")) {
			Recommend rec4 = new Recommend();
			System.out.println("1.4 api monthly 파싱");
			bgnde = now.getBefore3Month_start();// 세달전1일 //yyyyMMdd 
			endde = now.getBefore3Month_end();// 세달전 마지막일 //yyyyMMdd
			rec4.setBgnde(bgnde);
			rec4.setEndde(endde);
			rec4.setUpkind(upkind);
			rec4.setUpr_cd(upr_cd);
			api.pasing(rec4, 3);
		}
*/
		/*
		//임시테스트용
		System.out.println("1.5 api 테스트 파싱");
		rec.setBgnde("20170803"); rec.setEndde("20170803");
		rec.setUpkind(upkind); rec.setUpr_cd(upr_cd); api.pasing(rec,4);
		
		Recommend rec5 = new Recommend();
		System.out.println("1.6 api 테스트 파싱");
		rec2.setBgnde("20170804"); rec5.setEndde("20170804");
		rec2.setUpkind(upkind); rec5.setUpr_cd(upr_cd); api.pasing(rec5,5);
		*/
		// API끝===========================================================================

		// HIVE시작=========================================================================
		System.out.println("==HIVE시작======");
		// 테스트
		/*
		System.out.println("traincsv : 매일 3개월전 하루치의 api에서 받아온 csv를 hive로 보냄");
		HiveDao.getInstance().hive_loaddata_test();
		System.out.println("month3csv : 매일 2주전 하루치의 api에서 받아온 csv를 hive로 보냄");
		HiveDao.getInstance().hive_loaddata_test2();
		*/
		
		System.out.println("traincsv : 매일 3개월전 하루치의 api에서 받아온 csv를 hive로 보냄");
		HiveDao.getInstance().hive_loaddata_traincsv();
		System.out.println("month3csv : 매일 2주전 하루치의 api에서 받아온 csv를 hive로 보냄");
		HiveDao.getInstance().hive_loaddata_month3csv();
		
		
		// 매일 6개월치의 processState 값 받아옴
		System.out.println("하둡의 6개월치 csv를 hive의getcsv_6 테이블에 보냄");
		HiveDao.getInstance().hive_loaddata_month6csv();// 하둡의 6개월치 csv를 hive의 getcsv_6 테이블에 보냄
		System.out.println("매일 6개월치의 processState 값 받아옴");
		HashMap<String, Integer> processState = new HashMap<>();
		processState = HiveDao.getInstance().hive_select(processState);// 결과를 hashmap에 저장

		// 한달에 한번 한달치의 api에서 받아온 csv를 hive로 보냄
		if (now.getDay().equals("20"))
		{
			System.out.println("20일이 되었으므로 한달치를 monthly테이블에 보냄==");
			System.out.println("시작시간 : " + now.getTimetoday());
			HiveDao.getInstance().hive_loaddata_monthlycsv();// 하둡의 한달치 csv를
																// hive의monthlycsv
																// 테이블에 보냄
		}

		// HIVE끝=========================================================================
		
		// RDBMS시작=======================================================================
		System.out.println("==RDBMS시작==");
		System.out.println("1. AdminDao의 insertAdminPie(now, processState)실행");
		try {
			AdminDao.getInstance().insertAdminPie(now, processState);
		} catch (SQLException e) { // TODO Auto-generated catch block
			System.out.println("실패");
			e.printStackTrace();
		}
		
		System.out.println("끝난시간 : " + new DateToday().getDate().getTimetoday());
		// RDBMS끝===============================================================
	}
}
