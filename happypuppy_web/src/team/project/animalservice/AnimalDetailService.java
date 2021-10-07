package team.project.animalservice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dto.Animal;
import team.project.service.NextPage;
import team.project.service.UserService;

//유기동물의 세부정보를 보여주는 서비스
public class AnimalDetailService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("2. AnimalDetailService Start");

		// 1. 파라미터 받아오기
		String beforeAddress = request.getHeader("referer");
		ArrayList<Animal> animalList = null;
		if(beforeAddress.contains("animaldangerlist.jsp"))
			animalList = (ArrayList<Animal>) request.getSession().getAttribute("animalListDanger");
		if(beforeAddress.contains("index.jsp"))
			animalList = (ArrayList<Animal>) request.getSession().getAttribute("animalListNew");
		if(beforeAddress.contains("animalsearchshow.jsp"))
			animalList = (ArrayList<Animal>) request.getSession().getAttribute("animalListSearch");
		String popfile = request.getParameter("popfile");
		NextPage nextPage = new NextPage(); // 다음 url

		// 2. animalList접근
		for (int i = 0; i < animalList.size(); i++) {
			// 3. animalList에서 popfile이름이 일치하는 animal객체를 세션에 저장
			if ((popfile.equals(animalList.get(i).getPopfile()))) {
				Animal choicedAnimal = animalList.get(i);
				request.getSession().setAttribute("choicedAnimal", choicedAnimal);
				System.out.println(choicedAnimal);
			}
		}
		// 4-1. AnimalDetailService성공
		// 다음url : 동물 상세정보를 보여주는 /jsp/animaldetail.jsp
		if (popfile != null) {
			System.out.println("널값 아니다");
			nextPage.setPageName("/jsp/animaldetail.jsp");
			nextPage.setRedirect(false);
		}

		// 4-2. AnimalDetailService성공
		else {
			System.out.println("널값이다");
			request.setAttribute("errorMSG", "AnimalDetailService Failed.");
			nextPage.setPageName(request.getContextPath() + "/error.jsp");
			nextPage.setRedirect(false);
		}
		
		return nextPage;
	}

}
