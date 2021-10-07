package team.project.adminservice;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import team.project.dao.AdminDao;
import team.project.dto.User;
import team.project.service.NextPage;
import team.project.service.UserService;

public class AdminMemberDeleteService implements UserService {
	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("2.AdminMemberDeleteService");
		
		//회원삭제
		String email = (String) request.getParameter("email");
		email = email.replaceAll(" ","");//공백제거
		System.out.println("입력받은 email : " + email);
		int result = AdminDao.getInstance().deleteUser(email);
		System.out.println("result" + result);
		
		JSONArray jsonarray = new JSONArray();
		JSONObject jsonobj = new JSONObject();
		
		if(result==1) {
			jsonobj.put("delete", 1);
			jsonarray.add(jsonobj);
		}
		
		System.out.println(jsonarray);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(jsonarray);
		
		//회원목록 재출력
		ArrayList<User> list_user = AdminDao.getInstance().selectUserAll(); //회원목록을 list에 담음
		request.setAttribute("list_user", list_user);
		
		
		return null;
	}
}