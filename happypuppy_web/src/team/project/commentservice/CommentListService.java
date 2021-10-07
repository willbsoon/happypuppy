package team.project.commentservice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dao.CommentDao;
import team.project.dto.Comment;
import team.project.dto.Post;
import team.project.service.UserService;
import team.project.service.NextPage;

//댓글 리스트를 가져오는 서비스
public class CommentListService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("2. CommentListService Start");

		// 1. 파라미터 받아오기
		String postNo = (String) request.getParameter("postNo"); // 보여줄 댓글이 있는 Post의 PostNo
		Post post = (Post)request.getSession().getAttribute("post");
		ArrayList<Comment> commentList = null; // 보여줄 댓글 리스트
		NextPage nextPage = new NextPage(); // 다음url 객체

		// 2. DB에서 commentList 가져오기
		try {
			commentList = CommentDao.getInstance().selectCommentList(Integer.parseInt(postNo));
			System.out.println("commentList : " + commentList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 3-1. CommentList존재함
		// 만약 commentList가 있으면 commentList를 Session영역에 저장
		// 다음url : 게시글을 보여주는 ./postread.jsp
		if (commentList != null) {
			request.getSession().setAttribute("commentList", commentList);
			nextPage.setPageName("./postread.jsp?postNo="+postNo+"&post="+post);
			nextPage.setRedirect(true);
		}
		// 3-2. CommentList존재하지않음
		// 만약 commentList가 null이면commentList를 null로 Session영역에 저장
		// 다음url : 게시글을 보여주는 ./postread.jsp
		else {
			request.getSession().setAttribute("commentList", null);
			nextPage.setPageName("./postread.jsp");
			nextPage.setRedirect(true);
		}
		return nextPage;
	}

}
