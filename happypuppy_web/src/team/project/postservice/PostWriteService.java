package team.project.postservice;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dao.PostDao;
import team.project.dto.Post;
import team.project.dto.User;
import team.project.service.UserService;
import team.project.service.NextPage;

//게시글 추가 서비스
public class PostWriteService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("2. PostWriteService Start");

		// 1. 파라미터 받아오기
		User loginedUser = (User) request.getSession().getAttribute("loginedUser"); // 로그인한 유저객체
		String userId = loginedUser.getUserId();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String name = loginedUser.getName();
		Post post = new Post(); // DB에 저장할 게시글 객체생성
		NextPage nextPage = new NextPage(); // 다음url 객체
		int result = 0; // DB에 저장한 게시글 수

		// 2-1. 유저가 로그인 상태가 아니면 게시글 삽입 불가, 메인페이지로 이동
		if (loginedUser == null) {
			nextPage.setPageName("userLogout.puppy");
			return nextPage;
		}

		// 2-2. 유저가 로그인 상태이면 삽입할 게시글 객체 필드값 설정
		else if (loginedUser != null) {
			post.setUserId(userId);
			post.setTitle(title);
			post.setContent(content);
			post.setName(name);
			try {
				// 게시글 객체를 DB에 저장
				result = PostDao.getInstance().postWrite(post);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 3-1. PostWriteService성공
		// 게시글 객체가 DB저장 됐으면(DB에 저장한 게시글 수가 0이 아니면),
		// 다음url : 게시글을 보여주는 postList.puppy 서비스 실행
		if (result != 0) {
			System.out.println(result + "개의 게시글이 삽입되었습니다.");
			System.out.println(post);

			nextPage.setPageName("./postList.puppy");
			nextPage.setRedirect(true);
		}
		// 3-2. PostWriteService실패
		else {
			System.out.println("게시글 삽입이 실패했습니다.");
			request.setAttribute("errorMSG", "PostWriteService failed");
			nextPage.setPageName(request.getContextPath() +"/index.html");
			nextPage.setRedirect(false);
		}
		return nextPage;
	}

}
