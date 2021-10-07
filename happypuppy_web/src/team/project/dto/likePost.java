package team.project.dto;

public class likePost {
	private int likePostId;
	private String postNo;
	private String userId;
	public int getLikePostId() {
		return likePostId;
	}
	public void setLikePostId(int likePostId) {
		this.likePostId = likePostId;
	}
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "likePost [likePostId=" + likePostId + ", postNo=" + postNo + ", userId=" + userId + "]";
	}
	
	

}
