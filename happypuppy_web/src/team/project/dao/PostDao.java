package team.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;

import team.project.dto.Post;
import team.project.util.DBUtil;

//게시판 DAO
public class PostDao {
	private static PostDao boardDao = new PostDao();
	private PostDao() {}
	public static PostDao getInstance() {
		return boardDao;
	}

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	StringBuffer query;
	Post post;

	Calendar cal = Calendar.getInstance();
	String dateString = String.format("%04d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
			cal.get(Calendar.DAY_OF_MONTH));

	// 게시글을 작성하는 메소드
	public synchronized int postWrite(Post post) throws SQLException {
		System.out.println("3. PostDao postWrite()");

		con = DBUtil.getConnection();
		query = new StringBuffer();
		query.append("insert into post values (default,?,?,?,?,?,?,?,?)");
		
		pstmt = con.prepareStatement(query.toString());
		pstmt.setString(1, post.getUserId());
		pstmt.setString(2, post.getTitle());
		pstmt.setString(3, post.getContent());
		pstmt.setString(4, dateString);
		pstmt.setInt(5, post.getLikeCt());
		pstmt.setInt(6, post.getCommentCt());
		pstmt.setInt(7, post.getHitCt());
		pstmt.setString(8, post.getName());

		int result = pstmt.executeUpdate();

		DBUtil.close(con, pstmt);

		return result;
	}

	// 게시판의 전체 글수를 return하는 메소드
	public int getPostTotalCount() throws ClassNotFoundException, SQLException {
		System.out.println("3. PostDao getPostTotalCount()");
		
		con = DBUtil.getConnection();
		pstmt = con.prepareStatement("SELECT count(*) AS total_count FROM post");
		rs = pstmt.executeQuery();
		
		int totalCount = 0;
		if (rs.next()) {
			totalCount = rs.getInt(1); // index로 받아오는 것이 속도면에선 좋다.
			// totalCount = rs.getInt("total_count"); // 하지만 컬럼명이 보기엔 명확한 듯 하다.
		}

		DBUtil.close(con, pstmt);

		return totalCount;
	}

	// 게시글 번호를 받아 해당 게시글 객체를 return하는 메소드
	public Post selectPost(int postNo) throws ClassNotFoundException, SQLException {
		System.out.println("3. PostDao selectPost()");
		
		con = DBUtil.getConnection();
		query = new StringBuffer();
		query.append("SELECT *  FROM post WHERE postNo = ?");
		
		pstmt = con.prepareStatement(query.toString());
		pstmt.setString(1, Integer.toString(postNo));
		rs = pstmt.executeQuery();
		
		post = new Post();
		if (rs.next()) {
			post.setPostNo(rs.getInt("postNo"));
			post.setUserId(rs.getString("userId"));
			post.setHitCt(rs.getInt("hitCt"));
			post.setTitle(rs.getString("title"));
			post.setWriteDt(rs.getString("writeDt"));
			post.setContent(rs.getString("content"));
			post.setLikeCt(rs.getInt("likeCt"));
			post.setPostNo(rs.getInt("postNo"));
			post.setName(rs.getString("name"));
		}

		DBUtil.close(con, pstmt);
		return post;
	}

	// 모든 게시글 리스트를 반환하는 메소드
	public ArrayList<Post> selectPostList() throws ClassNotFoundException, SQLException {
		System.out.println("3. PostDao selectPostList()");
		
		con = DBUtil.getConnection();
		query = new StringBuffer();
		query.append("SELECT * FROM post order by postNo");

		pstmt = con.prepareStatement(query.toString());
		rs = pstmt.executeQuery();

		ArrayList<Post> postList = new ArrayList<Post>();
		while (rs.next()) {
			post = new Post();
			
			post.setPostNo(rs.getInt("postNo"));
			post.setUserId(rs.getString("userId"));
			post.setHitCt(rs.getInt("hitCt"));
			post.setTitle(rs.getString("title"));
			post.setWriteDt(rs.getString("writeDt"));
			post.setContent(rs.getString("content"));
			post.setLikeCt(rs.getInt("likeCt"));
			post.setPostNo(rs.getInt("postNo"));
			post.setName(rs.getString("name"));
			
			postList.add(post);
		}

		DBUtil.close(con, pstmt);

		return postList;
	}

	// 글 조회수 증가시키는 메소드
	public synchronized int upHit(String postNo) throws ClassCastException, SQLException {
		System.out.println("3. PostDao upHit()");
		
		con = DBUtil.getConnection();
		pstmt = con.prepareStatement("UPDATE post SET hitCt = hitCt + 1 WHERE postNo = ?");
		pstmt.setString(1, postNo);
		
		int result = pstmt.executeUpdate();

		DBUtil.close(con, pstmt);

		return result;
	}
	
	// 글 수정하는 메소드
	public synchronized int postUpdate(Post post) throws SQLException {
		System.out.println("3. PostDao postUpdate()");

		con = DBUtil.getConnection();
		query = new StringBuffer();
		query.append("UPDATE post set title=?, content=?, writeDt=? WHERE postNo=?");
		
		pstmt = con.prepareStatement(query.toString());
		pstmt.setString(1, post.getTitle());
		pstmt.setString(2, post.getContent());
		pstmt.setString(3, dateString);
		pstmt.setInt(4, post.getPostNo());

		int result = pstmt.executeUpdate();

		DBUtil.close(con, pstmt);

		return result;
	}
	
	// 글 삭제하는 메소드
	public synchronized int postDelete(int postNo) throws SQLException {
		System.out.println("3. PostDao postDelete()");
		
		con = DBUtil.getConnection();
		query = new StringBuffer();
		query.append("DELETE FROM post WHERE postNo = ?");
		
		pstmt = con.prepareStatement(query.toString());
		pstmt.setInt(1, postNo);
		
		int result = pstmt.executeUpdate();
		
		DBUtil.close(con, pstmt);
		
		return result;
	}

}
