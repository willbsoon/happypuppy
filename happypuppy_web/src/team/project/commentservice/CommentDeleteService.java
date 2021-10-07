package team.project.commentservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dao.CommentDao;
import team.project.dao.UserDao;
import team.project.service.NextPage;
import team.project.service.UserService;

//댓글 삭제 서비스
public class CommentDeleteService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("2. CommentDeleteService Start");

		// 1. 파라미터 받아오기
		String commentId = (String) request.getParameter("commentId");
		System.out.println(commentId);
		String userId = (String) request.getSession().getAttribute("userId");
		NextPage nextPage = new NextPage();
		String commentUpdate = null;
		int idcheck;

		// 2. DB에서 idcheck
		try {
			idcheck = UserDao.getInstance().idCheck(userId);
			if (idcheck == 1) {
				// 3. id가 일치하면 DB에서 댓글 삭제
				System.out.println(idcheck + " , 코멘트아이디 : " + commentId);
				commentUpdate = CommentDao.getInstance().commentDelete(commentId);
				request.getSession().setAttribute("message", "success");
				// 4-1. CommentDeleteService성공
				//다음 url : index.jsp
				nextPage.setPageName("index.jsp");
				nextPage.setRedirect(true);
			}
			// 4-2. CommentDeleteService실패
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMSG", "CommentDeleteService failed");
			nextPage.setPageName("error.jsp");
			nextPage.setRedirect(false);
		}
		return null;
	}

}
