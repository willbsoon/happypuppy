package team.project.commentservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.project.dao.CommentDao;
import team.project.dao.UserDao;
import team.project.service.NextPage;
import team.project.service.UserService;

//댓글 수정 서비스
public class CommentUpdateService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("2. CommentUpdateService Start");

		// 1. 파라미터 받아오기
		String content = (String) request.getSession().getAttribute("content");
		String commentId = (String) request.getSession().getAttribute("commentId");
		String userId = (String) request.getSession().getAttribute("userId");
		System.out.println("content : " + content);
		NextPage nextPage = new NextPage();
		String commentUpdate = null;
		int idchek;

		// 2. DB에서 idcheck
		try {
			idchek = UserDao.getInstance().idCheck(userId);
			System.out.println("idchek : " + idchek);
			// 3-1. id가 일치하면 댓글 수정
			// 4-1. CommentUpdateService 성공
			// 다음 url : /index.html
			if (idchek == 1) {
				System.out.println(idchek);
				commentUpdate = CommentDao.getInstance().commentUpdate(content, commentId);
				request.getSession().setAttribute("message", "success");
				nextPage.setPageName(request.getContextPath() + "/index.html");
				nextPage.setRedirect(true);
				// 3-2. id가 일치하면 댓글 수정불가
				// 4-2. CommentUpdateService 실패
			} else {
				request.setAttribute("errorMSG", "CommentUpdateService failed");
				nextPage.setPageName(request.getContextPath() + "/error.jsp");
				nextPage.setRedirect(false);
			}
			// 4-2. CommentUpdateService 실패
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMSG", "CommentUpdateService failed");
			nextPage.setPageName(request.getContextPath() + "/error.jsp");
			nextPage.setRedirect(false);
		}
		return null;
	}

}
