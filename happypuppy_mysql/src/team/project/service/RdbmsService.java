package team.project.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import team.project.dao.IntroDao;
import team.project.dto.City;
import team.project.dto.IntroduceData;
import team.project.dto.Recommend;
import team.project.dto.Time_now;

public class RdbmsService {
	public void Introdailybatch(Time_now now, ArrayList cityList) {
		System.out.println("2. RdbmsService의 Introdailybatch(Time_now now, Admin admin)");

		//===============6개월간의 유기동물수 가져오기==================================
		//검색조건 설정(6개월 모든동물 특정지역)
		Recommend rec = new Recommend();
		IntroduceData introdata= new IntroduceData();
		ArrayList arrdata = new ArrayList();
		rec.setBgnde(now.getBefore6Month_start().replaceAll("-", ""));// 20170807
		rec.setEndde(now.getSimple_before().replaceAll("-", ""));
		rec.setUpkind("");
		
		for(int i=0 ; i < cityList.size(); i++) {
			rec.setUpr_cd((String)cityList.get(i));
			System.out.println(rec.getUpr_cd());
			//파싱하여 json으로 유기동물수(totalCount)받아오기
			JSONObject data = null;
			NoticePasing notice = new NoticePasing();
			try {
				data = notice.pasing(rec);
				JSONObject data1 = (JSONObject) data.get("response");
				JSONObject data2 = (JSONObject) data1.get("body");
				int totalCount = 0;
				totalCount = (int) data2.get("totalCount");
				arrdata.add(totalCount);
				System.out.println("totalCount : " + totalCount);
				JSONObject data3 = (JSONObject) data2.get("items");
				JSONArray data4 = data3.getJSONArray("item");
				int count_kill=0;
				for (int j = 0; j < data4.length(); j++) {
						// 3.상태
						if (data4.getJSONObject(j).getString("processState").contains("안락사"))
							count_kill++;
				}
				System.out.println("count_kill : " + count_kill);
				arrdata.add(count_kill);
				System.out.println("arrdata.get(" + i + ")" +arrdata.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//========userIntroduce테이블에 6개월간의 유기동물수,안락사수 insert========
		try {
			System.out.println("arrdata의 사이즈 34 == :"+ arrdata.size());
			IntroDao.getInstance().insertCount(now, arrdata);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
