package team.project.userservice;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dao.UserDao;
import team.project.dto.User;
import team.project.service.NextPage;
import team.project.service.UserService;

//유저 삭제 서비스
public class UserDeleteService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("2. UserDeleteService Start");

		// 1. 파라미터 받아오기
		User loginedUser = (User) request.getSession().getAttribute("loginedUser"); // 로그인한 유저객체
		String userId = loginedUser.getUserId();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String born = request.getParameter("born");

		int result = 0; // DB에서 삭제된 게시글 개수
		NextPage nextPage = new NextPage(); // 다음url 객체
		User user = new User();

		// 2-1. 로그인 상태가 아니면 메인페이지로 이동
		if (loginedUser == null) {
			nextPage.setPageName("userLogout.puppy");
			return nextPage;
		}
		// 2-2. 유저가 로그인 상태면
		// 유저객체를 생성하고 DB정보와 비교후 삭제
		else if (loginedUser != null) {
			try {
				user.setUserId(userId);
				user.setEmail(email);
				user.setPassword(password);
				user.setBorn(born);
				result = UserDao.getInstance().userDelete(user);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		// 3-1. UserDeleteService 성공
		// 다음 url : index.jsp
		if (result != 0) {
			System.out.println(result + "명의 유저가 삭제되었습니다.");
			nextPage.setPageName("userLogout.puppy");
			nextPage.setRedirect(true);
			// 3-2. UserDeleteService 실패
		} else {
			System.out.println("유저 삭제를 실패했습니다.");
			request.setAttribute("errorMSG", "UserDeleteService Failed.");
			nextPage.setPageName("index.jsp");
			nextPage.setRedirect(false);
		}
		return nextPage;
	}
}
