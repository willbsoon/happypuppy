package team.project.animalservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import team.project.adminservice.DateToday;
import team.project.dto.Animal;
import team.project.dto.Recommend;
import team.project.dto.Time_now;
import team.project.service.NextPage;
import team.project.service.NoticePasing;
import team.project.service.UserService;

public class AnimalImageSerchShowService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("2. AnimalImageSerchShowService Start");
		

		Recommend rec = new Recommend();
		DateToday dateToday = new DateToday();
		Time_now timeNow = dateToday.getDate();
		
		//3개월전~현재까지 유기동물 검색
		rec.setBgnde(timeNow.getBefore3Monthstart().replace("-", ""));
		rec.setEndde(timeNow.getSimpletoday());
		rec.setUpkind("");
		rec.setUpr_cd("");
		rec.setNumOfRows("100");
		
		String colorCd = (String)request.getSession().getAttribute("colorCd");
		if(colorCd!=null && colorCd.equals("white")) {
			colorCd="흰";
		}else if(colorCd!=null && colorCd.equals("black")) {
			colorCd="검";
		}else {
			colorCd="미상";
		}
		System.out.println(colorCd);
		
		// 2. JSON객체를 AnimalDTO로 변환
		JSONObject data = null;
		NoticePasing notice = new NoticePasing();
		ArrayList<Animal> animalList = new ArrayList<>();
		NextPage nextPage = new NextPage(); // 다음url 객체

		try {
			data = notice.pasing(rec);

			JSONObject data1 = (JSONObject) data.get("response");
			JSONObject data2 = (JSONObject) data1.get("body");
			JSONObject data3 = (JSONObject) data2.get("items");
			JSONArray data4 = data3.getJSONArray("item");
			// response.getWriter().print(data);
			for (int i = 0; i < data4.length(); i++) {

				Animal animal = new Animal();
				if (data4.getJSONObject(i).length() == 22) {
					// 1. 공고종료일
					if (data4.getJSONObject(i).getInt("noticeEdt") != -1)
						animal.setNoticeEdt(data4.getJSONObject(i).getString("noticeEdt"));
					// 2. 이미지
					if (data4.getJSONObject(i).getString("popfile") != null)
						animal.setPopfile(data4.getJSONObject(i).getString("popfile"));
					// 3. 상태
					if (data4.getJSONObject(i).getString("processState") != null)
						animal.setProcessState(data4.getJSONObject(i).getString("processState"));
					// 4. 성별
					if (data4.getJSONObject(i).getString("sexCd") != null)
						animal.setSexCd(data4.getJSONObject(i).getString("sexCd"));
					// 5. 중성화 여부
					if (data4.getJSONObject(i).getString("neuterYn") != null)
						animal.setNeuterYn(data4.getJSONObject(i).getString("neuterYn"));

					// 6. 특징
					if (data4.getJSONObject(i).getString("specialMark") != null)
						animal.setSpecialMark(data4.getJSONObject(i).getString("specialMark"));
					// 7. 보호소이름
					if (data4.getJSONObject(i).getString("careNm") != null)
						animal.setCareNm(data4.getJSONObject(i).getString("careNm"));
					// 8. 보호소전화번호
					if (data4.getJSONObject(i).getString("careTel") != null)
						animal.setCareTel(data4.getJSONObject(i).getString("careTel"));
					// 9. 보호장소
					if (data4.getJSONObject(i).getString("careAddr") != null)
						animal.setCareAddr(data4.getJSONObject(i).getString("careAddr"));
					// 10. 관할기관
					if (data4.getJSONObject(i).getString("orgNm") != null)
						animal.setOrgNm(data4.getJSONObject(i).getString("orgNm"));

					// 11. 담당자
					if (data4.getJSONObject(i).getString("chargeNm") != null)
						animal.setChargeNm(data4.getJSONObject(i).getString("chargeNm"));
					// 12. 담당자연락처
					if (data4.getJSONObject(i).getString("officetel") != null)
						animal.setOfficetel(data4.getJSONObject(i).getString("officetel"));
					// 13. 유기번호
					if (data4.getJSONObject(i).getInt("desertionNo") != -1)
						animal.setDesertionNo(data4.getJSONObject(i).getString("desertionNo"));
					// 14. 썸네일 이미지
					if (data4.getJSONObject(i).getString("filename") != null)
						animal.setFilename(data4.getJSONObject(i).getString("filename"));
					// 15. 접수일
					if (data4.getJSONObject(i).getInt("happenDt") != -1)
						animal.setHappenDt(data4.getJSONObject(i).getString("happenDt"));

					// 16. 발견장소
					if (data4.getJSONObject(i).getString("happenPlace") != null)
						animal.setHappenPlace(data4.getJSONObject(i).getString("happenPlace"));
					// 17. 품종
					if (data4.getJSONObject(i).getString("kindCd") != null)
						animal.setKindCd(data4.getJSONObject(i).getString("kindCd"));
					// 18. 색상
					if (data4.getJSONObject(i).getString("colorCd") != null)
						animal.setColorCd(data4.getJSONObject(i).getString("colorCd"));
					// 19. 나이
					if (data4.getJSONObject(i).getString("age") != null)
						animal.setAge(data4.getJSONObject(i).getString("age"));
					// 20. 체중
					if (data4.getJSONObject(i).getString("weight") != null)
						animal.setWeight(data4.getJSONObject(i).getString("weight"));

					// 21. 공고번호
					if (data4.getJSONObject(i).getString("noticeNo") != null)
						animal.setNoticeNo(data4.getJSONObject(i).getString("noticeNo"));
					// 22. 공고시작일
					if (data4.getJSONObject(i).getInt("noticeSdt") != -1)
						animal.setNoticeSdt(data4.getJSONObject(i).getString("noticeSdt"));

					// 3. animalList에 생성한 AnimalDTO를 append
					animalList.add(animal);
				}
			}
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 4-1. AnimalSearchService성공
		// 세션에 animalList를 저장
		// 다음 url : /jsp/index.jsp
		for(int i=0; i<animalList.size();i++) {
			System.out.println(animalList.get(i).getColorCd());
		}
		if (animalList != null) {
			request.getSession().setAttribute("colorCd", colorCd);
			request.getSession().setAttribute("animalListColor", animalList);
			request.getSession().setAttribute("Recommend", rec);

			nextPage.setPageName(request.getContextPath() + "/jsp/animalimagesearchshow.jsp");
			nextPage.setRedirect(true);
		}

		// 4-2. AnimalSearchService실패
		else {
			request.setAttribute("errorMSG", "AnimalSearchService failed.");
			nextPage.setPageName(request.getContextPath() + "/error.jsp");
			nextPage.setRedirect(false);
		}
		return nextPage;
		// TODO Auto-generated method stub
	}

}
