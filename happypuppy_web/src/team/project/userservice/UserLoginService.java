package team.project.userservice;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team.project.dao.UserDao;
import team.project.dto.User;
import team.project.service.NextPage;
import team.project.service.UserService;

//유저 로그인 서비스
public class UserLoginService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("2. UserLoginService Start");

		// 1. 파라미터 받아오기
		String email = request.getParameter("email"); // 로그인할 user의 email
		String password = request.getParameter("password"); // 로그인할 user의 password
		NextPage nextPage = new NextPage(); // 다음url 객체
		HttpSession session = request.getSession(); // user를 저장할 세션객체
		String before_address = request.getHeader("referer");
		// 로그인할 user객체 생성
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		// 반환할 user객체 참조값
		User loginedUser = null;

		// 2. DB접근
		try {
			// email,password가 일치하는 user객체 선택
			loginedUser = UserDao.getInstance().login(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 3. DB접근 종료

		// 4-1. UserLoginService성공
		// email,password가 일치하면 user객체 세션에 저장
		// 다음url : index.html
		if (loginedUser != null) {
			session.setAttribute("loginedUser", loginedUser);

			System.out.println("loginedUser : " + loginedUser);

			nextPage.setPageName(before_address);
			nextPage.setRedirect(true);
		}
		// 4-2. UserLoginService실패
		// 에러메시지 출력
		else {
			request.setAttribute("errorMSG", "UserLoginService failed.");
			nextPage.setPageName(request.getContextPath() + "/error.jsp");
			nextPage.setRedirect(false);
		}
		return nextPage;
	}

}
