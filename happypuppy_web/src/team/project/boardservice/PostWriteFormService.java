package team.project.boardservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dto.User;
import team.project.service.NextPage;
import team.project.service.UserService;

//유저가 로그인을 했을때만 글쓰기를 할 수 있도록 하는 서비스
public class PostWriteFormService implements UserService{

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("2. BoardWriteFormService Start");
		User loginedUser = (User)request.getSession().getAttribute("loginedUser");
		
		NextPage nextPage = new NextPage();
		if(loginedUser!=null){
			System.out.println("loginedUser exists");
			request.setAttribute("pageNum", request.getParameter("pageNum"));
			nextPage.setPageName("./writeForm.jsp");
		}
		else if(loginedUser == null){
			System.out.println("loginedUser is null");
			nextPage.setPageName("index.jsp");
		}
		return nextPage;
	}

}
