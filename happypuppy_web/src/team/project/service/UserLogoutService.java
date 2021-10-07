package team.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team.project.dao.UserDao;

public class UserLogoutService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("2.UserLogoutService");
		// 1. 파라미터 받아오기
		HttpSession session = request.getSession();

		// 2. 세션 제거
		session.removeAttribute("loginedUser");

		// 3. 세션 다시 가져오기?
		String result = "";
		result = (String) session.getAttribute("loginedUser");
		// 4. UserLogoutService success
		System.out.println(result);
		NextPage nextPage = new NextPage();
		// 다음url : index.jsp
		if (result == null) {
			nextPage.setPageName("/webproject5/jsp/index.jsp");
			nextPage.setRedirect(true);
		}
		// 4. UserLogoutService 실패
		else {
			request.setAttribute("errorMSG", "UserLogoutService 실패");
			nextPage.setPageName("error.jsp");
			nextPage.setRedirect(false);
		}
		return nextPage;
	}

}