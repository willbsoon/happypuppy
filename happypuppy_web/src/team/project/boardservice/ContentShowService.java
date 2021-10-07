package team.project.boardservice;

import team.project.service.UserService;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dao.BoardDao;
import team.project.dto.Post;
import team.project.dto.User;
import team.project.service.NextPage;

public class ContentShowService implements UserService{

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("2. ContentShowService Start");
		User loginedUser = (User)request.getSession().getAttribute("loginedUser");
		String postNo = request.getParameter("postNo");
		
		Post post = new Post();
		NextPage nextPage = new NextPage();
		try {
			post = BoardDao.getInstance().selectPost(Integer.parseInt(postNo));
			//조회수 증가시키는 메소드
			BoardDao.getInstance().upHit(postNo);
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		if (post != null) {
			 //return "/list.jsp";
			
			request.getSession().setAttribute("post", post);
			request.getSession().setAttribute("postNo", postNo);
			System.out.println("post : " + post);
			System.out.println("postNo : " + postNo);
			if(loginedUser!=null) {
				request.getSession().setAttribute("loginedUser", loginedUser);
				request.getSession().setAttribute("userId", loginedUser.getUserId());
				System.out.println("loginedUser" + loginedUser);
				System.out.println("userId : " + loginedUser.getUserId());
			}
			
			nextPage.setPageName("./boardread.jsp?postNo="+request.
					getSession().getAttribute("postNo"));
			nextPage.setRedirect(false);
		}
		// ContentShowService 에러
		else {
			request.setAttribute("errorMSG", "ContentShowService 에러 발생.");
			nextPage.setPageName("error.jsp");
			nextPage.setRedirect(false);
		}
		return nextPage;
	}

}
