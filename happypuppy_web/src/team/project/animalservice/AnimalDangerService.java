package team.project.animalservice;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import team.project.adminservice.DateToday;
import team.project.dao.AnimalDao;
import team.project.dto.Animal;
import team.project.dto.Time_now;
import team.project.service.NextPage;
import team.project.service.UserService;

//자연사, 안락사 확률이 높은 유기동물을 보여주는 서비스
public class AnimalDangerService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("2. AnimalDangerService Start");

		Time_now timeNow = new DateToday().getDate();
		
		
		ArrayList<Animal> animalList = new ArrayList<>();
		NextPage nextPage = new NextPage(); // 다음url 객체
		// 2. AnimalDao에서 animalList 받아옴
		try {
			animalList = AnimalDao.getInstance().selectDanger();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 3-1. 세션에 animalList를 저장
		// 다음 url : /jsp/animaldangerlist.jsp
		if (animalList != null) {
			request.getSession().setAttribute("animalListDanger", animalList);

			nextPage.setPageName(request.getContextPath() + "/jsp/animaldangerlist.jsp");
			nextPage.setRedirect(true);
		}

		// 3-2. AnimalDangerService실패
		else {
			request.setAttribute("errorMSG", "AnimalDangerService failed.");
			nextPage.setPageName(request.getContextPath() + "/error.jsp");
			nextPage.setRedirect(false);
		}
		
		return nextPage;
	}

}
