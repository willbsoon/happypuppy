package team.project.userservice;

import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team.project.dao.UserDao;
import team.project.dto.User;
import team.project.service.NextPage;
import team.project.service.UserService;

//유저 등록 서비스
public class UserRegisterService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("2. UserRegisterService Start");

		// 1. 파라미터 받아오기
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String born = request.getParameter("born");
		Calendar cal = Calendar.getInstance();
		String dateString = String.format("%02d%02d%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
				cal.get(Calendar.DAY_OF_MONTH));
		Integer randomId = (int) (Math.random() * 10000);

		User loginedUser = null; // 반환할 user객체 참조값
		HttpSession session = request.getSession(); // user를 저장할 세션객체
		NextPage nextPage = new NextPage(); // 다음 url 객체
		int result = 0;// DB에 insert된 user수

		// 2. DB에 저장할 user객체 생성
		User user = new User();
		String UserId = "local:" + dateString + randomId;
		int idCheck = 1;
		user.setUserId(UserId);
		user.setEmail(email);
		user.setPassword(password);
		user.setName(name);
		user.setGender(gender);
		user.setBorn(born);

		// 3. DB에 user정보 insert
		try {
			idCheck = UserDao.getInstance().idCheck(user.getEmail());
			if (idCheck == 1) {
				result = UserDao.getInstance().insert(user);
				loginedUser = UserDao.getInstance().login(user);
			} else if (idCheck == 0) {
				request.setAttribute("errorMSG", "UserRegisterService failed.");
				nextPage.setPageName(request.getContextPath() + "/error.jsp");
				nextPage.setRedirect(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 4-1. UserRegisterService성공
		// 다음url : jsp/userRgisterMsg.jsp
		if (result != 0) {
			session.setAttribute("loginedUser", loginedUser);
			System.out.println("UserRegisterService success.");
			
			nextPage.setPageName(request.getContextPath() + "/jsp/userregistermsg.jsp");
			nextPage.setRedirect(true);
		}
		// 4-2. UserRegisterService실패
		else {
			System.out.println("UserRegisterService failed.");
			request.setAttribute("errorMSG", "UserRegisterService failed.");
			nextPage.setPageName(request.getContextPath() + "/error.jsp");
			nextPage.setRedirect(false);
		}
		return nextPage;
	}

}
