package team.project.userservice;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dao.UserDao;
import team.project.dto.User;
import team.project.service.NextPage;
import team.project.service.UserService;

//유저정보 수정 서비스
public class UserUpdateService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("2. UserUpdateService Start");

		// 1. 파라미터 받아오기
		User loginedUser = (User) request.getSession().getAttribute("loginedUser"); // 로그인한 유저객체
		String born = request.getParameter("born");
		String password = request.getParameter("password");
		String userId = loginedUser.getUserId();
		User user = new User(); // 업데이트할 유저 객체 생성
		int result = 0; // DB에 업데이트된 유저의 수
		NextPage nextPage = new NextPage(); // 다음 url객체 생성

		// 2-1. 유저가 로그인 상태가 아니면 유저정보 수정 불가, 메인페이지로 이동
		if (loginedUser == null) {
			nextPage.setPageName("userLogout.puppy");
			return nextPage;

		}

		// 2-2. 유저가 로그인 상태이면 업데이트할 유저 객체 필드값 설정
		else if (loginedUser != null) {
			user.setBorn(born);
			user.setPassword(password);
			user.setUserId(userId);
			try {
				// 3. DB에서 유저 필드값 수정
				result = UserDao.getInstance().userUpdate(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// 4-1. UserUpdateService성공
		// 게시글이 수정되었으면(업데이트된 게시글 개수가 0이 아니면),
		// 다음url : ./index.jsp
		if (result != 0) {
			System.out.println(result + "명의 유저정보가 수정되었습니다.");

			nextPage.setPageName("./index.jsp");
			nextPage.setRedirect(true);
		}
		// 4-2. UserUpdateService실패
		else {
			request.setAttribute("errorMSG", "UserUpdateService failed");
			nextPage.setPageName(request.getContextPath() + "/index.html");
			nextPage.setRedirect(false);
		}
		return nextPage;

	}
}
