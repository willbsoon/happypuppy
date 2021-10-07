package team.project.userservice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dao.UserDao;
import team.project.service.NextPage;
import team.project.service.UserService;

//유저 Id확인 서비스
public class UserIdCheckService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("2. UserIdCheckService Start");

		// 1. 파라미터 받아오기
		String email = request.getParameter("email");

		// 2. DB에서 id확인
		int result = 0;
		result = UserDao.getInstance().idCheck(email);

		// 3-1. ajax로 비동기 출력성공
		try {
			PrintWriter out;
			out = response.getWriter();
			out.print(result);
			// 3-2. ajax로 비동기 출력실패
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
