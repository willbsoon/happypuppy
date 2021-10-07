package team.project.postservice;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dao.PostDao;
import team.project.dto.Post;
import team.project.dto.User;
import team.project.service.UserService;
import team.project.service.NextPage;

//게시글 수정 서비스
public class PostUpdateService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("2. PostUpdateService Start");

		// 1. 파라미터 받아오기
		User loginedUser = (User) request.getSession().getAttribute("loginedUser"); // 로그인한 유저객체
		Post post = new Post(); // 업데이트할 게시글 객체 생성
		int result = 0; // DB에 업데이트된 게시글 수
		NextPage nextPage = new NextPage(); // 다음 url객체 생성

		// 2-1. 유저가 로그인 상태가 아니면 게시글 수정 불가, 메인페이지로 이동
		if (loginedUser == null) {
			nextPage.setPageName("userLogout.puppy");
			return nextPage;

		}

		// 2-2. 유저가 로그인 상태이면 업데이트할 게시글 객체 필드값 설정
		else if (loginedUser != null) {
			post.setTitle(request.getParameter("title"));
			post.setContent(request.getParameter("content"));
			post.setPostNo(Integer.parseInt(request.getParameter("postNo")));
			try {
				// DB에서 게시글 필드값 수정
				result = PostDao.getInstance().postUpdate(post);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// 3-1. PostUpdateService성공
		// 게시글이 수정되었으면(업데이트된 게시글 개수가 0이 아니면),
		// 다음url : 게시글을 보여주는 postList.puppy 서비스 실행
		if (result != 0) {
			System.out.println(result + "개의 게시글이 수정되었습니다.");

			nextPage.setPageName("./postList.puppy");
			nextPage.setRedirect(false);
		}
		//3-2. PostUpdateService실패
		else {
			request.setAttribute("errorMSG", "PostUpdateService failed");
			nextPage.setPageName(request.getContextPath() + "/index.html");
			nextPage.setRedirect(false);
		}
		return nextPage;
	}

}
