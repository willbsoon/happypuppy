package team.project.boardservice;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dao.BoardDao;
import team.project.dto.Post;
import team.project.dto.User;
import team.project.service.UserService;
import team.project.service.NextPage;

public class PostWriteService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("2. BoardWriteService Start");
		User loginedUser = (User)request.getSession().getAttribute("loginedUser");
		Post post = new Post();
		
		
		NextPage nextPage = new NextPage();
		
		//유저가 로그인 상태면 게시글 객체 생성
		if(loginedUser!=null){
		post.setUserId(loginedUser.getUserId());
		post.setTitle(request.getParameter("title"));
		post.setContent(request.getParameter("content"));
		
		System.out.println("loginedUser.getUserId() : " + loginedUser.getUserId());
		System.out.println("title : " + request.getParameter("title"));
		System.out.println("content : " +request.getParameter("content"));
		}
		//로그인 상태가 아니면 메인페이지로 이동
		else if(loginedUser == null){
			nextPage.setPageName("logout.puppy");
			return nextPage;
		}
		
		
		int result = 0;
		try {
			//
			result = BoardDao.getInstance().postWrite(post);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(result !=0) {
			System.out.println(result + "개의 게시글이 삽입되었습니다.");
			request.getSession().setAttribute("loginedUser", loginedUser);
			request.getSession().setAttribute("postNo", post.getPostNo());
			
			nextPage.setPageName("postList.puppy");
			nextPage.setRedirect(false);
		}
		//PostWriteService Error 발생
		else {
			System.out.println("게시글이 삽입 실패했습니다.");
			request.setAttribute("errorMSG","PostWriteService Error 발생");
			nextPage.setPageName("index.jsp");
			nextPage.setRedirect(false);
		}
		return nextPage;
	}

}
