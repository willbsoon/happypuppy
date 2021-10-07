package team.project.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dao.UserDao;

public class UserIdCheckService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("UserIdCheckService execute()");
		String email = request.getParameter("email");
		System.out.println(email);
		
		int result = 0;
		result = UserDao.getInstance().idCheck(email);
		
		// 중복
		System.out.println(result);
		PrintWriter out;
		
		try {
			out = response.getWriter();
			out.print(result);//ajax 보내기
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
