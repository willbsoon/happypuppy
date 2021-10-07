package team.project.adminservice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dao.AdminDao;
import team.project.dto.User;
import team.project.service.NextPage;
import team.project.service.UserService;

public class AdminMemberListService implements UserService {
	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("2.AdminMemberListService");	
		
		User loginedUser = (User) request.getSession().getAttribute("loginedUser");
		String email = loginedUser.getEmail();
		System.out.println("로그인한 email : " + email);
		//회원목록 출력
		ArrayList<User> list_user = AdminDao.getInstance().selectUserAll(); //회원목록을 list에 담음
		
		
		// 4. 페이지 이동
		NextPage nextPage = new NextPage(); // 다음url 객체
		if(email.equals("admin@admin")) {
				request.setAttribute("loginedUser", loginedUser);
				request.setAttribute("list_user", list_user);
				nextPage.setPageName("./memberlist.jsp");
				nextPage.setRedirect(false);
		}
		else {
			nextPage.setPageName("logout.puppy");
			nextPage.setRedirect(true);
		}
		
		return nextPage;
	}
}