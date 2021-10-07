package team.project.service;

import java.io.IOException;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;
import team.project.dao.AdminDao;
import team.project.dto.Admin;
import team.project.dto.Recommend;
import team.project.dto.Time_now;

public class RdbmsService {
	public void dailybatch(Time_now now, Admin admin) {
		System.out.println("2. RdbmsService의 dailybatch(Time_now now, Admin admin)");

		//===============오늘의 유기동물수 가져오기==================================
		//검색조건 설정(오늘 모든동물 모든지역)
		Recommend rec = new Recommend();
		rec.setBgnde(now.getSimpletoday());// 20170807
		rec.setEndde(now.getSimpletoday());
		rec.setUpkind("");
		rec.setUpr_cd("");
		
		//파싱하여 json으로 유기동물수(totalCount)받아오기
		JSONObject data = null;
		NoticePasing notice = new NoticePasing();
		try {
			data = notice.pasing(rec);
			JSONObject data1 = (JSONObject) data.get("response");
			JSONObject data2 = (JSONObject) data1.get("body");
			int puppyCount = 0;
			puppyCount = (int) data2.get("totalCount");
			System.out.println("puppyCount :" + puppyCount);// API의 totalCount
			if (puppyCount != 0)
				admin.setPuppyCount(puppyCount);// admin DTO에 오늘의 puppycount 설정
			//========daily테이블에 오늘의 게시글수,방문자수,가입자수,유기동물수 insert========
			System.out.println("2. dailybatch의 insertDaily 실행");
			AdminDao.getInstance().insertDaily(now, admin);
				
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//===============all테이블에 업데이트하기==================================
	public void allbatch(Time_now now, Admin admin) {
		System.out.println("2. RdbmsService의 allbatch(Time_now now, Admin admin)");
		try {
			AdminDao.getInstance().insertAll(now, admin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//===============monthlyBatch 테이블에 insert하기==================================
	public void monthlybatch(Time_now now, Admin admin) {
		System.out.println("2. RdbmsService의 monthlybatch(Time_now now, Admin admin)");	
		try {
			AdminDao.getInstance().insertMonthly(now, admin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
