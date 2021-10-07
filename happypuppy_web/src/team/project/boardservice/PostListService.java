package team.project.boardservice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dao.BoardDao;
import team.project.dto.Post;
import team.project.dto.User;
import team.project.service.UserService;
import team.project.service.NextPage;

public class PostListService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("2.PostListService Start");
		User loginedUser = (User)request.getSession().getAttribute("loginedUser");

		//전체 글 수
		int totalCount = 0;
		
		//PostDto 리스트 선언
		ArrayList<Post> postList = null;
		try {
			//전체 게시글 수를 리턴하는 getPostTotalCount메소드
			totalCount = BoardDao.getInstance().getPostTotalCount();
			//전체 게시글 리스트를 리턴하는 selectPostList메소드
			postList = BoardDao.getInstance().selectPostList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		NextPage nextPage = new NextPage();
		if (postList != null) {
			request.getSession().setAttribute("loginedUser", loginedUser);
			request.getSession().setAttribute("totalCount", totalCount);
			request.getSession().setAttribute("postList", postList);
			
			System.out.println("loginedUser : " + loginedUser);
			System.out.println("totalCount : " + totalCount);
			System.out.println("postList : " + postList);
			nextPage.setPageName("./board.jsp");
			nextPage.setRedirect(false);
		}
		// PostListService 에러
		else {
			request.setAttribute("errorMSG", "PostListService 에러 발생");
			nextPage.setPageName("error.jsp");
			nextPage.setRedirect(false);
		}
		return nextPage;
	}

}
