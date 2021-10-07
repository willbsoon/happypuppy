package team.project.postservice;

import team.project.service.UserService;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dao.PostDao;
import team.project.dto.Post;
import team.project.service.NextPage;

//선택 게시글 보여주는 서비스
public class PostReadService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("2. PostReadService Start");

		// 1. 파라미터 받아오기
		String postNo = request.getParameter("postNo"); // 보여줄 게시글의 postNo
		Post post = new Post(); // session영역에 저장할 게시글 객체
		NextPage nextPage = new NextPage(); // 다음url 객체

		// 2. DB접근
		try {
			// postNo와 일치하는 게시글 객체 선택
			post = PostDao.getInstance().selectPost(Integer.parseInt(postNo));
			// 선택 게시글의 조회수를 증가시키는 메소드
			PostDao.getInstance().upHit(postNo);
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 3-1. PostReadService성공
		// 게시글 객체가 존재하면 세션에 저장
		// 다음url : 해당 게시글의 comment객체를 불러오기 위해 commentList서비스로 이동
		if (post != null) {
			request.getSession().setAttribute("post", post);
			request.setAttribute("postNo", postNo);

			System.out.println("post : " + post);
			System.out.println("postNo : " + postNo);

			nextPage.setPageName("commentList.puppy?postNo=" + postNo + "&post=" + post);
			nextPage.setRedirect(true);
		}
		// 3-2. PostReadService실패
		else {
			request.setAttribute("errorMSG", "PostReadService failed");
			nextPage.setPageName("error.jsp");
			nextPage.setRedirect(false);
		}
		return nextPage;
	}

}
