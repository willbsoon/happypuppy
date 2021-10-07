package team.project.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team.project.dao.UserDao;
import team.project.dto.User;

public class UserLoginService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("2. UserLoginService");
		// 1. 파라미터 받아오기
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		User loginedUser = null;
		// 2. DB접근
		try {
			loginedUser = UserDao.getInstance().login(user);
			//UserDao.getInstance().hiveTest("1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		//4. UserLoginService 성공
		
		NextPage nextPage = new NextPage();
		HttpSession session = request.getSession();
		// 다음 url : /index.jsp
		if(loginedUser !=null) {
			
			session.setAttribute("loginedUser", loginedUser);
			session.setAttribute("name", loginedUser.getName());
			
			System.out.println("loginedUser : " + loginedUser);
			System.out.println("name : " + loginedUser.getName());
			
			nextPage.setPageName("./index.jsp");
			nextPage.setRedirect(true);
		}
		// 4-2. UserLoginService 실패
		else {
			request.setAttribute("errorMSG","UserLoginService 실패");
			nextPage.setPageName("error.jsp");
			nextPage.setRedirect(false);
		}
		return nextPage;
	}

}
