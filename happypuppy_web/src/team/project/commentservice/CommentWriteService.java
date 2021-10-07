package team.project.commentservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import team.project.dao.CommentDao;
import team.project.dto.Comment;
import team.project.dto.User;
import team.project.service.NextPage;
import team.project.service.UserService;

//댓글 추가 서비스
public class CommentWriteService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("2. CommentWriteService Start");

		//1. 파라미터 받아오기
		User loginedUser = (User) request.getSession().getAttribute("loginedUser");
		String content = request.getParameter("content");
		String postNo = request.getParameter("postNo");
		String userId = request.getParameter("userId");
		String name = request.getParameter("name");
		JSONArray jsonArray = new JSONArray();
		Comment comment = new Comment(); // DB에 저장할 댓글 객체생성
		NextPage nextPage = new NextPage(); // 다음 url 객체
		int result = 0; // DB에 저장한 댓글 수

		System.out.println("content : " + content);
		System.out.println("postNo : " + postNo);
		System.out.println("userId : " + userId);
		System.out.println("name : " + name);

		// 2-1. 유저가 로그인 상태가 아니면 댓글 삽입 불가, 메인페이지로 이동
		if (loginedUser == null) {
			nextPage.setPageName("userLogout.puppy");
			return nextPage;
		}

		// 2-2. 유저가 로그인 상태이면 삽입할 댓글 객체 필드값 설정
		else if (loginedUser != null) {
			comment.setContent(content);
			comment.setPostNo(Integer.parseInt(postNo));
			comment.setUserId(userId);
			comment.setName(name);
			try {
				// 3. 댓글 객체를 DB에 저장하고 comment객체 가져오기
				result = CommentDao.getInstance().commentWrite(comment);
				comment = CommentDao.getInstance().selectComment(Integer.parseInt(postNo));
				System.out.println("comment : " + comment);
			} catch (SQLException | NumberFormatException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			//4. 가져온 comment객체를 JSON객체로 생성(비동기)
			System.out.println("---------JSON Start---------");
			try {
				JSONObject obj = new JSONObject();
				obj.put("commentId", comment.getCommentId());
				obj.put("content", comment.getContent());
				obj.put("postNo", comment.getPostNo());
				obj.put("userId", comment.getUserId());
				obj.put("commentDt", comment.getCommentDt());
				obj.put("superComment", comment.getSuperComment());
				obj.put("likeCt", comment.getSuperComment());
				obj.put("name", comment.getName());
				jsonArray.put(obj);
				
				PrintWriter out = response.getWriter();
				out.print(jsonArray);
			} catch (JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(result + " 개의 댓글이 저장되었습니다.");
		
		return null;
	}

}
