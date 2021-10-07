package team.project.userservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team.project.service.NextPage;
import team.project.service.UserService;

//유저 로그아웃 서비스
public class UserLogoutService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("2. UserLogoutService Start");

		// 1. 파라미터 받아오기
		HttpSession session = request.getSession(); // loingedUser를 삭제할 세션객체
		
		// 2. 세션에서 loingedUser제거
		session.removeAttribute("loginedUser");

		// 3. 세션의 loingedUser변수 확인
		String result = "";
		result = (String) session.getAttribute("loginedUser");

		// 4-1. UserLogoutServicet성공
		// 다음url : index.html
		NextPage nextPage = new NextPage(); // 다음 url 객체
		if (result == null) {
			System.out.println("loginedUser : " + result);
			System.out.println("if loginedUser is null, logout have completed");
			
			nextPage.setPageName("../index.html");
			nextPage.setRedirect(true);
		}
		// 4-2. UserLogoutService실패
		else {
			request.setAttribute("errorMSG", "UserLogoutService failed");
			nextPage.setPageName(request.getContextPath() + "/error.jsp");
			nextPage.setRedirect(false);
		}
		return nextPage;
	}

}
