package team.project.userservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.adminservice.DateToday;
import team.project.dao.AnimalDao;
import team.project.dto.Animal;
import team.project.dto.Time_now;
import team.project.service.NextPage;
import team.project.service.UserService;

public class UserIntroduceService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("2. UserIntroduceService Start");

		Time_now now = new DateToday().getDate();
		
		ArrayList introdata = new ArrayList();
		NextPage nextPage = new NextPage(); // 다음url 객체
		// 2. AnimalDao에서 카운트 받아옴
		try {
			introdata = AnimalDao.getInstance().selectIntroduce(now);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 3-1. 세션에 animalList를 저장
		// 다음 url : /jsp/animaldangerlist.jsp
		if (introdata != null) {
			request.getSession().setAttribute("introdata", introdata);

			nextPage.setPageName(request.getContextPath() + "/jsp/userIntroduce.jsp");
			nextPage.setRedirect(true);
		}

		// 3-2. AnimalDangerService실패
		else {
			request.setAttribute("errorMSG", "UserIntroduceService failed.");
			nextPage.setPageName(request.getContextPath() + "/error.jsp");
			nextPage.setRedirect(false);
		}
		
		return nextPage;
	}
}
