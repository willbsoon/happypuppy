package team.project.postservice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dao.PostDao;
import team.project.dto.Post;
import team.project.service.UserService;
import team.project.service.NextPage;

//게시글 리스트를 보여주는 서비스
public class PostListService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("2. PostListService Start");

		//1. 파라미터 받아오기
		int totalCount = 0; // 전체 글 수
		ArrayList<Post> postList = null; // 보여줄 게시글 리스트
		NextPage nextPage = new NextPage(); // 다음url 객체

		// 2.DB에서 게시글 리스트 가져오기
		try {
			// 전체 게시글 리스트를 리턴하는 selectPostList메소드
			postList = PostDao.getInstance().selectPostList();
			System.out.println("postList : " + postList);
			// 전체 게시글 수를 리턴하는 getPostTotalCount메소드
			totalCount = PostDao.getInstance().getPostTotalCount();
			System.out.println("totalCount : " + totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 3-1. PostListService성공
		//postList가 존재하면 postList를 session영역에 설정
		// 다음url : 게시글리스트를 보여주는 ./postlist.jsp
		if (postList != null) {
			request.getSession().setAttribute("postList", postList);
			nextPage.setPageName("./postlist.jsp");
			nextPage.setRedirect(true);
		}
		// 3-2. PostListService실패
		else {
			request.setAttribute("errorMSG", "PostListService failed.");
			nextPage.setPageName("error.jsp");
			nextPage.setRedirect(false);
		}
		return nextPage;
	}

}
