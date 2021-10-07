package team.project.dto;

//Post DTO
public class Post {
	private int postNo;//글 번호
	private String userId;//유저 id
	private String title;//제목
	private String content;//내용
	private String writeDt;//작성일
	private int likeCt;//좋아요 수
	private int commentCt;//댓글수
	private int hitCt;//조회수
	private String name;//작성자 이름
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteDt() {
		return writeDt;
	}
	public void setWriteDt(String writeDt) {
		this.writeDt = writeDt;
	}
	public int getLikeCt() {
		return likeCt;
	}
	public void setLikeCt(int likeCt) {
		this.likeCt = likeCt;
	}
	public int getCommentCt() {
		return commentCt;
	}
	public void setCommentCt(int commentCt) {
		this.commentCt = commentCt;
	}
	public int getHitCt() {
		return hitCt;
	}
	public void setHitCt(int hitCt) {
		this.hitCt = hitCt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Post [postNo=" + postNo + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", writeDt=" + writeDt + ", likeCt=" + likeCt + ", commentCt=" + commentCt + ", hitCt=" + hitCt
				+ ", name=" + name + "]";
	}
	
	

}
