package team.project.adminservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import team.project.dto.Admin;
import team.project.dto.Time_now;
import team.project.dto.User;
import team.project.service.NextPage;
import team.project.service.UserService;


public class AdminIndexService implements UserService {
	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("2.AdminIndexService");	
		
		AdminIndexSelectService AdminIndexSelect = new AdminIndexSelectService();
		Admin admin = new Admin();
		Time_now now = new DateToday().getDate();
		User loginedUser = (User) request.getSession().getAttribute("loginedUser"); // 로그인한 유저객체
		String email = loginedUser.getEmail();
		System.out.println("email : " + email);

		AdminIndexSelect.selectTodayData(now, admin);
		AdminIndexSelect.selectMonthData(now, admin);
		AdminIndexSelect.selectAllData(now, admin);
		
		System.out.println(admin);

		// 4. 페이지 이동
		NextPage nextPage = new NextPage(); // 다음url 객체
		if(email.equals("admin@admin")) {
			if (admin != null) {
				request.setAttribute("admin_data", admin);
				request.getSession().setAttribute("loginedUser", loginedUser);
				nextPage.setPageName("./adminindex.jsp");
				nextPage.setRedirect(false);
			}
			else {
				request.setAttribute("errorMSG", "오류발생");
				nextPage.setPageName("./error.jsp");
				nextPage.setRedirect(false);
			}
		}
		else {
			nextPage.setPageName("logout.puppy");
			nextPage.setRedirect(true);
		}
		
		return nextPage;
	}

}
