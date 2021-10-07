package team.project.dto;

public class likeComment {
	private int likeCommentId;
	private int commentId;
	private int userId;
	public int getIkeCommentId() {
		return likeCommentId;
	}
	public void setIkeCommentId(int ikeCommentId) {
		this.likeCommentId = ikeCommentId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "likeComment [ikeCommentId=" + likeCommentId + ", commentId=" + commentId + ", userId=" + userId + "]";
	}
	
	
}
