package team.project.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import team.project.dto.Animal;
import team.project.dto.Recommend;

public class UserRecommendService implements UserService{

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("2.UserRecommendService");
		// 1. 파라미터 가져오기
		String bgnde1 = request.getParameter("bgnde");// 검색시작날짜
		String endde1 = request.getParameter("endde");// 검색종료날짜
		String upkind = request.getParameter("upkind");// 검색 동물
		String upr_cd = request.getParameter("upr_cd");// 검색 도시
		String bgnde = bgnde1.replace("-", "");
		String endde = endde1.replace("-", "");

		Recommend rec = new Recommend();
		rec.setBgnde(bgnde);
		rec.setEndde(endde);
		rec.setUpkind(upkind);
		rec.setUpr_cd(upr_cd);

		// 2. DB 접근

		JSONObject data = null;
		NoticePasing notice = new NoticePasing();
		ArrayList<Animal> animalList = new ArrayList<>();

		try {
			data = notice.pasing(rec);

			JSONObject data1 = (JSONObject) data.get("response");
			JSONObject data2 = (JSONObject) data1.get("body");
			JSONObject data3 = (JSONObject) data2.get("items");
			JSONArray data4 = data3.getJSONArray("item");
			// response.getWriter().print(data);
			for (int i = 0; i < data4.length(); i++) {
				
				Animal animal = new Animal();
				if(data4.getJSONObject(i).getString("age") !=null)
				animal.setAge(data4.getJSONObject(i).getString("age"));
				
				if(data4.getJSONObject(i).getString("careAddr") !=null)
				animal.setCareAddr(data4.getJSONObject(i).getString("careAddr"));
				
				if(data4.getJSONObject(i).getString("careNm") !=null)
				animal.setCareNm(data4.getJSONObject(i).getString("careNm"));
				
				if(data4.getJSONObject(i).getString("careTel") !=null)
				animal.setCareTel(data4.getJSONObject(i).getString("careTel"));
				
				if(data4.getJSONObject(i).getString("chargeNm") !=null)
				animal.setChargeNm(data4.getJSONObject(i).getString("chargeNm"));
				
				if(data4.getJSONObject(i).getString("colorCd") !=null)
				animal.setColorCd(data4.getJSONObject(i).getString("colorCd"));
				
				if(data4.getJSONObject(i).getInt("desertionNo") != -1)
				animal.setDesertionNo(data4.getJSONObject(i).getString("desertionNo"));
				
				if(data4.getJSONObject(i).getString("filename") !=null)
				animal.setFilename(data4.getJSONObject(i).getString("filename"));
				
				if(data4.getJSONObject(i).getInt("happenDt") != -1)
				animal.setHappenDt(data4.getJSONObject(i).getString("happenDt"));
				
				if(data4.getJSONObject(i).getString("happenPlace") !=null)
				animal.setHappenPlace(data4.getJSONObject(i).getString("happenPlace"));
				
				if(data4.getJSONObject(i).getString("kindCd") !=null)
				animal.setKindCd(data4.getJSONObject(i).getString("kindCd"));
				
				if(data4.getJSONObject(i).getInt("noticeEdt") !=-1)
				animal.setNoticeEdt(data4.getJSONObject(i).getString("noticeEdt"));
				
				if(data4.getJSONObject(i).getInt("noticeSdt") !=-1)
				animal.setNoticeSdt(data4.getJSONObject(i).getString("noticeSdt"));
				
				if(data4.getJSONObject(i).getString("officetel") !=null)
				animal.setOfficetel(data4.getJSONObject(i).getString("officetel"));
				
				if(data4.getJSONObject(i).getString("orgNm") !=null)
				animal.setOrgNm(data4.getJSONObject(i).getString("orgNm"));
				
				if(data4.getJSONObject(i).getString("popfile") !=null)
				animal.setPopfile(data4.getJSONObject(i).getString("popfile"));
				
				if(data4.getJSONObject(i).getString("processState") !=null)
				animal.setProcessState(data4.getJSONObject(i).getString("processState"));
				
				if(data4.getJSONObject(i).getString("sexCd") !=null)
				animal.setSexCd(data4.getJSONObject(i).getString("sexCd"));
				
				if(data4.getJSONObject(i).getString("specialMark") !=null)
				animal.setSpecialMark(data4.getJSONObject(i).getString("specialMark"));
				
				if(data4.getJSONObject(i).getString("weight") !=null)
				animal.setWeight(data4.getJSONObject(i).getString("weight"));

				animalList.add(animal);
			}
			//System.out.println(animalList);
			// System.out.println(array);
			// response.getWriter().print(array);
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// 4. UserRecommendService 성공
		NextPage nextPage = new NextPage();
		if (animalList != null) {
			System.out.println("널값 아니다");
			nextPage.setPageName("./jsp/shophome.jsp");
			request.getSession().setAttribute("animalList", animalList);
			nextPage.setRedirect(true);
		}
		// 4. UserRecommendService 실패
		else {
			System.out.println("널값이다 알려줘");
			request.setAttribute("errorMSG", "UserRecommendService 실패");
			nextPage.setPageName("error.jsp");
			nextPage.setRedirect(false);
		}

		return nextPage;
	}
}
