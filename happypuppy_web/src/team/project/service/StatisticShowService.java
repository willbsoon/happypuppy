package team.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dto.Animal;

public class StatisticShowService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("2. StatisticShowService");
		// 1. 파라미터 받아오기
		request.getSession().getAttribute("animalList");

		ArrayList<Animal> animalList = (ArrayList<Animal>) request.getSession().getAttribute("animalList");
		System.out.println(animalList);

		//동물종류
		System.out.println("동물종류");
		HashSet<String> kindCdSet = new HashSet();
		for (int i = 0; i < animalList.size(); i++) {
			String kindCd = animalList.get(i).getKindCd();
			kindCd = kindCd.replace("[개] ", "");
			kindCdSet.add(kindCd);
		}
		System.out.println(kindCdSet);

		//나이
		System.out.println("나이");
		ArrayList<Integer> ageList = new ArrayList<Integer>();
		for (int i = 0; i < animalList.size(); i++) {
			String age = animalList.get(i).getAge();
			age = age.replace("(년생)", "");
			Integer integerAge = Integer.parseInt(age);
			ageList.add(integerAge);
		}
		//System.out.println(ageList);

		//몸무게
		System.out.println("몸무게");
		ArrayList<Double> weightList = new ArrayList<Double>();
		for (int i = 0; i < animalList.size(); i++) {
			String weight = animalList.get(i).getWeight();
			weight = weight.replace("(Kg)", "");
			Double doubleWeight = Double.parseDouble(weight);
			weightList.add(doubleWeight);
		}
		//System.out.println(weightList);

		//현재보호상태
		System.out.println("현재보호상태");
		ArrayList<Map<String, String>> processStateList = new ArrayList<Map<String, String>>();
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		int f = 0;
		for (int i = 0; i < animalList.size(); i++) {
			String state = animalList.get(i).getProcessState();
			if (state.equals("보호중")) {
				a = a + 1;
			} else if (state.equals("종료(반환)")) {
				b = b + 1;
			} else if(state.equals("종료(자연사)")){
				c = c + 1;
			} else if(state.equals("종료(안락사)")){
				d = d +1;
			} else if(state.equals("종료(입양)")){
				e = e+1;
			} else {
				f = f+1;
			}
			
		}
		Map<String, String> mapInstance = new HashMap<String, String>();
		mapInstance.put("보호중", Integer.toString(a));
		mapInstance.put("종료(반환)", Integer.toString(b));
		mapInstance.put("종료(자연사)", Integer.toString(c));
		mapInstance.put("종료(안락사)", Integer.toString(d));
		mapInstance.put("종료(입양)", Integer.toString(e));
		mapInstance.put("기타", Integer.toString(f));
		processStateList.add(mapInstance);
		//System.out.println(processStateList);
		
		//특징
		System.out.println("특징");
		ArrayList<Map<String, String>> specialMark = new ArrayList<Map<String, String>>();
		for(int i=0; i<animalList.size();i++){
			if(animalList.get(i).getProcessState().equals("보호중")){
				Map<String, String> mapInstance2 = new HashMap<String, String>();
				mapInstance2.put("보호중", animalList.get(i).getSpecialMark());
				specialMark.add(mapInstance2);
			}else if(animalList.get(i).getProcessState().equals("종료(반환)")){
				Map<String, String> mapInstance2 = new HashMap<String, String>();
				mapInstance2.put("종료(반환)", animalList.get(i).getSpecialMark());
				specialMark.add(mapInstance2);
			}else if(animalList.get(i).getProcessState().equals("종료(자연사)")){
				Map<String, String> mapInstance2 = new HashMap<String, String>();
				mapInstance2.put("종료(자연사)", animalList.get(i).getSpecialMark());
				specialMark.add(mapInstance2);
			}else if(animalList.get(i).getProcessState().equals("종료(안락사)")){
				Map<String, String> mapInstance2 = new HashMap<String, String>();
				mapInstance2.put("종료(안락사)", animalList.get(i).getSpecialMark());
				specialMark.add(mapInstance2);
			}else if(animalList.get(i).getProcessState().equals("종료(입양)")){
				Map<String, String> mapInstance2 = new HashMap<String, String>();
				mapInstance2.put("종료(입양)", animalList.get(i).getSpecialMark());
				specialMark.add(mapInstance2);
			}else {
				Map<String, String> mapInstance2 = new HashMap<String, String>();
				mapInstance2.put("기타", animalList.get(i).getSpecialMark());
				specialMark.add(mapInstance2);
			}
		}
		//System.out.println(specialMark);

		// 3. DB에서 통계 데이터 가져오기

		NextPage nextPage = new NextPage();
		// 4. StatisticShowService 성공
		if (animalList != null) {
			System.out.println("널값 아니다");
			request.getSession().setAttribute("kindCdSet", kindCdSet);
			request.getSession().setAttribute("ageList", ageList);
			request.getSession().setAttribute("weightList", weightList);
			request.getSession().setAttribute("processStateList", processStateList);
			request.getSession().setAttribute("specialMark", specialMark);
			nextPage.setPageName("./statisticShow.jsp");
			nextPage.setRedirect(false);
		}

		// StatisticShowService Fail
		else {
			System.out.println("널값이다 알려줘");
			request.setAttribute("errorMSG", "StatisticShowService Fail");
			nextPage.setPageName("error.jsp");
			nextPage.setRedirect(false);
		}

		return nextPage;
	}

}
