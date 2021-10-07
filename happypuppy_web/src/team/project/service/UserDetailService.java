package team.project.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import team.project.dto.Animal;
import team.project.dto.Recommend;

public class UserDetailService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("4.UserRecommendService");
		// 1. 파라미터 받아오기
		ArrayList<Animal> animalList = (ArrayList<Animal>)request.getSession().getAttribute("animalList");

		String popfile = request.getParameter("popfile");
		// 2. DB 접근
		
		for(int i=0; i<animalList.size(); i++){
			if((popfile.equals(animalList.get(i).getPopfile()))){
				Animal choicedAnimal = animalList.get(i);
				request.getSession().setAttribute("choicedAnimal", choicedAnimal);
				System.out.println(choicedAnimal);
				
			}
		}
		// 4. UserDetailService success
		NextPage nextPage = new NextPage();
		// 다음url : 해당 유저 정보를 불러오기 위해 detail.jsp로 이동
		if ( popfile != null) {
			System.out.println("널값 아니다");
			nextPage.setPageName("/jsp/detail.jsp");
			nextPage.setRedirect(false);
		}
		// 4. UserDetailService fail
		else {
			System.out.println("널값이다");
			request.setAttribute("errorMSG", "UserDetailService fail");
			nextPage.setPageName("error.jsp");
			nextPage.setRedirect(false);
		}
		// return nextPage;
		// }
		return nextPage;
	}

}
