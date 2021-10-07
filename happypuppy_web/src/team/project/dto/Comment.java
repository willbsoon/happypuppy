package team.project.dto;

import java.io.Serializable;

//Comment DTO
public class Comment implements Serializable{
	private int commentId;//댓글 id
	private String content;//댓글 내용
	private int postNo;//게시글 번호
	private String userId;//작성자 Id
	private String commentDt;//작성일
	private int superComment;//상위 댓글
	private int likeCt;//좋아요 수
	private String name;//작성자 이름
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommentDt() {
		return commentDt;
	}
	public void setCommentDt(String commentDt) {
		this.commentDt = commentDt;
	}
	public int getSuperComment() {
		return superComment;
	}
	public void setSuperComment(int superComment) {
		this.superComment = superComment;
	}
	public int getLikeCt() {
		return likeCt;
	}
	public void setLikeCt(int likeCt) {
		this.likeCt = likeCt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", content=" + content + ", postNo=" + postNo + ", userId=" + userId
				+ ", commentDt=" + commentDt + ", superComment=" + superComment + ", likeCt=" + likeCt + ", name="
				+ name + "]";
	}

}
