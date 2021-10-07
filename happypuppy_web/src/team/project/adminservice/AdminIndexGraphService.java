package team.project.adminservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import team.project.dao.AdminDao;
import team.project.dto.Admin;
import team.project.dto.Time_now;
import team.project.service.NextPage;
import team.project.service.UserService;

public class AdminIndexGraphService implements UserService {
	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("2.AdminIndexGraphService");
		
		AdminIndexSelectService AdminIndexSelect = new AdminIndexSelectService();
		Admin admin = new Admin();
		Time_now now = new DateToday().getDate();
		
		AdminIndexSelect.selectMonthData(now, admin);
		System.out.println(admin);

		System.out.println("json--------------------");
		String a = request.getParameter("a");
		if(a.equals("1")) {
			System.out.println("선그래프");
			
			JSONArray jsonarray = new JSONArray();
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("monthVisit", admin.getMonthVisit());
			jsonobj.put("monthUser", admin.getMonthUser());
			jsonobj.put("month1Visit", admin.getMonth1Visit());
			jsonobj.put("month1User", admin.getMonth1User());
			jsonobj.put("month2Visit", admin.getMonth2Visit());
			jsonobj.put("month2User", admin.getMonth2User());
			jsonobj.put("month3Visit", admin.getMonth3Visit());
			jsonobj.put("month3User", admin.getMonth3User());
			jsonobj.put("month4Visit", admin.getMonth4Visit());
			jsonobj.put("month4User", admin.getMonth4User());
			jsonobj.put("month5Visit", admin.getMonth5Visit());
			jsonobj.put("month5User", admin.getMonth5User());
			jsonobj.put("month5",now.getBefore5Month_start().substring(5, 7));//03
			jsonobj.put("month4",now.getBefore4Month_start().substring(5, 7));//04
			jsonobj.put("month3",now.getBefore3Month_start().substring(5, 7));//05
			jsonobj.put("month2",now.getBefore2Month_start().substring(5, 7));//06
			jsonobj.put("month1",now.getBefore1Month_start().substring(5, 7));//07
			jsonobj.put("month",now.getSimple_today().substring(5, 7));//08
			
			jsonarray.add(jsonobj);
			
			System.out.println(jsonarray);
			
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print(jsonarray);
			
			return null;	
		}
		
		
		if(a.equals("2")) {
			System.out.println("파이그래프");

			JSONArray jsonarray = new JSONArray();
			JSONObject jsonobj = new JSONObject();
			//수치 받는거 수정필요
			HashMap<String,Integer> map = AdminDao.getInstance().selectAdminPie(now);
			jsonobj.put("live", map.get("live"));
			jsonobj.put("getted", map.get("getted"));
			jsonobj.put("kill", map.get("kill"));
			jsonobj.put("dead", map.get("dead"));
			jsonobj.put("returned", map.get("returned"));
			jsonarray.add(jsonobj);
			
			System.out.println(jsonarray);
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print(jsonarray);
			
			return null;	
		}
		return null;
	}
}
